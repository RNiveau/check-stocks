package net.blog.dev.services;

import net.blog.dev.services.api.IAlphaAvantageService;
import net.blog.dev.services.beans.AlphaAvantageWrapper;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Optional;

public class AlphaAvantageServiceImpl implements IAlphaAvantageService {

    private static Logger logger = LoggerFactory.getLogger(AlphaAvantageServiceImpl.class);

    final private Client client = ClientBuilder.newClient();

    final private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Optional<AlphaAvantageWrapper> getHistoric(String code) {

        if (code == null)
            return Optional.empty();

        WebTarget target = client.target("https://www.alphavantage.co").path("query")
                .queryParam("function", "TIME_SERIES_DAILY")
                .queryParam("symbol", code+".PA")
                .queryParam("apikey", "Q9SRZZ9SLVV8RW2H");

        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() != 200) {
            logger.warn("Failed to get historic for {}", code);
            return Optional.empty();
        }

        String json = response.readEntity(String.class);

        AlphaAvantageWrapper alphaAvantageWrapper = null;
        try {
            alphaAvantageWrapper = mapper.readValue(json, AlphaAvantageWrapper.class);
        } catch (IOException e) {
            logger.warn(e.getMessage());
            logger.debug("getHistoric {}", json);
            return Optional.empty();
        }
        return Optional.of(alphaAvantageWrapper);
    }
}
