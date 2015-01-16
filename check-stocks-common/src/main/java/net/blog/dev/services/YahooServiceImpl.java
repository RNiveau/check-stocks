package net.blog.dev.services;

import net.blog.dev.services.api.IYahooService;
import net.blog.dev.services.domain.historic.HistoricQuote;
import net.blog.dev.services.domain.quote.Quote;
import net.blog.dev.services.domain.historic.YahooResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

/**
 * Created by Xebia on 13/08/2014.
 */
public class YahooServiceImpl implements IYahooService {

    final private Client client = ClientBuilder.newClient();

    final private ObjectMapper mapper = new ObjectMapper();


    public YahooResponse getHistoric(String code, Integer duration) {

        if (code == null || duration == null)
            return null;

        LocalDate date = LocalDate.now();
        LocalDate dateBefore = date.minusMonths(duration);

        String query = "select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22"
                + code
                + ".PA%22%20and%20startDate%20%3D%20%22"
                + dateBefore.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + "%22%20and%20endDate%20%3D%20%22"
                + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "%22";

        WebTarget target = client.target("http://query.yahooapis.com").path("v1/public/yql")
                .queryParam("q", query)
                .queryParam("format", "json")
                .queryParam("env", "store%3A%2F%2Fdatatables.org%2Falltableswithkeys");

        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() != 200) {
            return null;
        }

        String json = response.readEntity(String.class);
        System.out.println(json);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        try {
            YahooResponse yahooResponse = mapper.readValue(json,
                    new TypeReference<YahooResponse>() {
                    });
            getQuote(code).ifPresent(quote -> {
                HistoricQuote historicQuote = new HistoricQuote();
                historicQuote.setAdj_Close(quote.getAsk());
                historicQuote.setClose(quote.getAsk());
                historicQuote.setDate(new Date());
                historicQuote.setHigh(quote.getDaysHigh());
                historicQuote.setLow(quote.getDaysLow());
                historicQuote.setOpen(quote.getOpen());
                historicQuote.setVolume(quote.getVolume());
                yahooResponse.getQuery().getResults().getQuote().add(0, historicQuote);
            });
            return yahooResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<Quote> getQuote(String code) {
        if (code == null)
            return Optional.empty();
        String query = "select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20%3D%20%22" + code + ".PA%22";

        WebTarget target = client.target("http://query.yahooapis.com").path("v1/public/yql")
                .queryParam("q", query)
                .queryParam("format", "json")
                .queryParam("env", "store%3A%2F%2Fdatatables.org%2Falltableswithkeys");

        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() != 200) {
            return Optional.empty();
        }

        String json = response.readEntity(String.class);
        System.out.println(json);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        try {
            net.blog.dev.services.domain.quote.YahooResponse yahooResponse = mapper.readValue(json,
                    new TypeReference<net.blog.dev.services.domain.quote.YahooResponse>() {
                    });
            return Optional.ofNullable(yahooResponse.getQuery().getResults().getQuote());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}