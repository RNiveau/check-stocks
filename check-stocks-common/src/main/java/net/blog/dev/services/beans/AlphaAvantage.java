package net.blog.dev.services.beans;

import net.blog.dev.gestion.stocks.back.json.CleanFloatDeserializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.io.Serializable;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlphaAvantage implements Serializable {

    private LocalDate date;

    @JsonProperty(value = "1. open")
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float open;

    @JsonProperty(value = "2. high")
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float high;

    @JsonProperty(value = "3. low")
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float low;

    @JsonProperty(value = "4. close")
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float close;

    @JsonProperty(value = "5. volume")
    private Long volume;

    public Float getOpen() {
        return open;
    }

    public void setOpen(Float open) {
        this.open = open;
    }

    public Float getHigh() {
        return high;
    }

    public void setHigh(Float high) {
        this.high = high;
    }

    public Float getLow() {
        return low;
    }

    public void setLow(Float low) {
        this.low = low;
    }

    public Float getClose() {
        return close;
    }

    public void setClose(Float close) {
        this.close = close;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
