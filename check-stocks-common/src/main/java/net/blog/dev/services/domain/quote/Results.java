package net.blog.dev.services.domain.quote;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Created by romainn on 12/08/2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Results implements Serializable {

    @JsonProperty
    private Quote quote;


    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }
}
