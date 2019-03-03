package net.blog.dev.services;

import net.blog.dev.check.stocks.domain.CompleteStock;
import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.utils.CalculUtils;
import net.blog.dev.services.api.IBoursoramaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BoursoramaServiceImpl implements IBoursoramaService {

    private static Logger logger = LoggerFactory.getLogger(AlphaAvantageServiceImpl.class);

    final private Client client = ClientBuilder.newClient();

    @Override
    public Optional<List<Stock>> getHistoric(String code) {

        if (code == null)
            return Optional.empty();

        WebTarget target = client.target("https://www.boursorama.com/bourse/action/graph/ws/download")
                .queryParam("length", "365")
                .queryParam("period", "-1")
                .queryParam("symbol", "1rP" + code);

        Response response = target.request().header("Cookie", "uuid230=f1de7f13-c9b6-46e6-8494-1df6576654b0;").get();
        if (response.getStatus() != 200) {
            return Optional.empty();
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        String pattern = "###.####";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        String csv = response.readEntity(String.class);
        String[] tab = csv.split("\r?\n");
        List<Stock> list = Arrays.stream(tab)
                .filter(line -> !line.startsWith("date"))
                .map(line -> {
                    String[] split = line.split("\t");
                    Stock stock = new Stock();
                    try {
                        stock.setDate(LocalDate.parse(split[0], DateTimeFormatter.ofPattern("dd/MM/yyyy 00:00")));
                        stock.setOpen((BigDecimal) decimalFormat.parse(split[1]));
                        stock.setHigh((BigDecimal) decimalFormat.parse(split[2]));
                        stock.setLow((BigDecimal) decimalFormat.parse(split[3]));
                        stock.setLow((BigDecimal) decimalFormat.parse(split[3]));
                        stock.setClose((BigDecimal) decimalFormat.parse(split[4]));
                        stock.setVolume((BigDecimal) decimalFormat.parse(split[5]));
                    } catch (ParseException e) {
                        logger.error(e.getMessage());
                    }
                    return stock;

                }).collect(Collectors.toList());
        list.sort(CalculUtils.reverseSort);
        return Optional.of(list);
    }
}
