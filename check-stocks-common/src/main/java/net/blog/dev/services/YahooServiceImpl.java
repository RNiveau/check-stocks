package net.blog.dev.services;

import net.blog.dev.services.api.IYahooService;
import net.blog.dev.services.domain.historic.YahooResponse;
import net.blog.dev.services.domain.quote.Quote;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Created by Xebia on 13/08/2014.
 */
public class YahooServiceImpl implements IYahooService {

    private static Logger logger = LoggerFactory.getLogger(YahooServiceImpl.class);

    final private Client client = ClientBuilder.newClient();

    final private ObjectMapper mapper = new ObjectMapper();


    public Optional<YahooResponse> getHistoric(String code, Integer duration) {

        if (code == null || duration == null)
            return Optional.empty();

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
            logger.warn("Failed to get historic for {}", code);
            return Optional.empty();
        }

        String json = response.readEntity(String.class);
        logger.debug("getHistoric {}", json);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        try {
            YahooResponse yahooResponse = mapper.readValue(json,
                    new TypeReference<YahooResponse>() {
                    });
            if (yahooResponse.getQuery().getResults() == null) {
                logger.warn("Failed to get historic for {}", code);
                return Optional.empty();
            }
            return Optional.of(yahooResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.warn("Failed to get historic for {}", code);
        return Optional.empty();
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
            logger.warn("Failed to get historic for {}", code);
            return Optional.empty();
        }

        String json = response.readEntity(String.class);
        logger.debug("getQuote {}", json);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        try {
            net.blog.dev.services.domain.quote.YahooResponse yahooResponse = mapper.readValue(json,
                    new TypeReference<net.blog.dev.services.domain.quote.YahooResponse>() {
                    });
            if (yahooResponse.getQuery().getResults() == null) {
                logger.warn("Failed to get quote for {}", code);
                return Optional.empty();
            }
            return Optional.ofNullable(yahooResponse.getQuery().getResults().getQuote());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}