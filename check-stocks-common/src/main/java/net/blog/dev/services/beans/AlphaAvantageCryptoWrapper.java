package net.blog.dev.services.beans;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlphaAvantageCryptoWrapper implements Serializable {

    private List<AlphaAvantageCrypto> quotes;

    @JsonCreator
    public AlphaAvantageCryptoWrapper(Map<String,Object> delegate) {
        this.quotes = new ArrayList<>();
        Map<String, Map> quotes = (Map<String, Map>) delegate.get("Time Series (Digital Currency Daily)");
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        for (String key : quotes.keySet()) {
            Map<String, String> map = quotes.get(key);

            String json = "{" + String.join(",", map.keySet().stream().map(x -> '"' + x + "\": \"" + map.get(x) + '"').collect(Collectors.toList())) + "}";
            try {
                AlphaAvantageCrypto alphaAvantage = mapper.readValue(json, AlphaAvantageCrypto.class);
                alphaAvantage.setDate(LocalDate.parse(key, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                this.quotes.add(alphaAvantage);
            } catch (IOException e) {
            }
        }
    }

    public List<AlphaAvantageCrypto> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<AlphaAvantageCrypto> quotes) {
        this.quotes = quotes;
    }
}
