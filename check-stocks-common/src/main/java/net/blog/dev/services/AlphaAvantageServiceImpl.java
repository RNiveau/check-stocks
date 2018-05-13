package net.blog.dev.services;

import net.blog.dev.services.api.IAlphaAvantageService;
import net.blog.dev.services.beans.AlphaAvantageCryptoWrapper;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AlphaAvantageServiceImpl implements IAlphaAvantageService {

    private static Logger logger = LoggerFactory.getLogger(AlphaAvantageServiceImpl.class);

    final private Client client = ClientBuilder.newClient();

    final private ObjectMapper mapper = new ObjectMapper();

    final private List<String> apiKeys;

    public AlphaAvantageServiceImpl(String... apiKeys) {
        this.apiKeys = new ArrayList<>();
        this.apiKeys.addAll(Arrays.asList(apiKeys));
    }


    @Override
    public Optional<AlphaAvantageWrapper> getHistoric(String code) {

        if (code == null)
            return Optional.empty();

        WebTarget target = client.target("https://www.alphavantage.co").path("query")
                .queryParam("function", "TIME_SERIES_DAILY")
                .queryParam("symbol", code.startsWith("^") ? code : code + ".PA");

        Optional<String> json = call(target, code, 0);
        if (json.isPresent()) {
            AlphaAvantageWrapper alphaAvantageWrapper;
            try {
                alphaAvantageWrapper = mapper.readValue(json.get(), AlphaAvantageWrapper.class);
            } catch (IOException e) {
                logger.warn(e.getMessage());
                logger.debug("getHistoric {}", json);
                return Optional.empty();
            }
            alphaAvantageWrapper.getQuotes().sort((q1, q2) -> {
                if (q1.getDate().isBefore(q2.getDate()))
                    return 1;
                return q1.getDate().isAfter(q2.getDate()) ? -1 : 0;
            });
            return Optional.of(alphaAvantageWrapper);
        }
        return Optional.empty();
    }

    @Override
    public Optional<AlphaAvantageCryptoWrapper> getCryptoHistoric(String code) {

        if (code == null)
            return Optional.empty();

        WebTarget target = client.target("https://www.alphavantage.co").path("query")
                .queryParam("function", "DIGITAL_CURRENCY_DAILY")
                .queryParam("symbol", code)
                .queryParam("market", "EUR");

        Optional<String> json = call(target, code, 0);
        if (json.isPresent()) {
            AlphaAvantageCryptoWrapper alphaAvantageWrapper;
            try {
                alphaAvantageWrapper = mapper.readValue(json.get(), AlphaAvantageCryptoWrapper.class);
            } catch (IOException e) {
                logger.warn(e.getMessage());
                logger.debug("getHistoric {}", json);
                return Optional.empty();
            }
            return Optional.of(alphaAvantageWrapper);
        }
        return Optional.empty();
    }

    private Optional<String> call(WebTarget webTarget, String code, int retry) {
        WebTarget target = webTarget.queryParam("apikey", apiKeys.get(0));
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() != 200) {
            return retryCall(code, retry, target);
        }
        String json = response.readEntity(String.class);
        if (json.contains("Invalid API call") || json.contains("Please consider optimizing your API call frequency")) {
            return retryCall(code, retry, target);
        }
        return Optional.of(json);
    }

    private Optional<String> retryCall(String code, int retry, WebTarget target) {
        if (retry == 0) {
            String apiKey = apiKeys.remove(0);
            apiKeys.add(apiKey);
            logger.debug("Apikey rotation");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.info("Thread: {}", e.getMessage());
            }
            return call(target, code, 1);
        }
        logger.warn("Failed to get historic for {}", code);
        return Optional.empty();
    }
}
