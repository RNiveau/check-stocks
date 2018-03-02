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
public class AlphaAvantageWrapper implements Serializable {

    private List<AlphaAvantage> quotes;

    @JsonCreator
    public AlphaAvantageWrapper(Map<String,Object> delegate) {
        this.quotes = new ArrayList<>();
        Map<String, Map> quotes = (Map<String, Map>) delegate.get("Time Series (Daily)");
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        for (String key : quotes.keySet()) {
            Map<String, String> map = quotes.get(key);

            String json = "{" + String.join(",", map.keySet().stream().map(x -> '"' + x + "\": \"" + map.get(x) + '"').collect(Collectors.toList())) + "}";
            try {
                AlphaAvantage alphaAvantage = mapper.readValue(json, AlphaAvantage.class);
                alphaAvantage.setDate(LocalDate.parse(key, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                this.quotes.add(alphaAvantage);
            } catch (IOException e) {
            }
        }
    }

    public List<AlphaAvantage> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<AlphaAvantage> quotes) {
        this.quotes = quotes;
    }
}
