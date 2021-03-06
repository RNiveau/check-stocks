package net.blog.dev.services.beans;

import net.blog.dev.services.jackson.deserializer.CleanFloatDeserializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.io.Serializable;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlphaAvantageCrypto implements Serializable {

    private LocalDate date;

    @JsonProperty(value = "1a. open (EUR)")
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float open;

    @JsonProperty(value = "2a. high (EUR)")
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float high;

    @JsonProperty(value = "3a. low (EUR)")
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float low;

    @JsonProperty(value = "4a. close (EUR)")
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float close;

    @JsonProperty(value = "5. volume")
    @JsonDeserialize(using = CleanFloatDeserializer.class)
    private Float volume;

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

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
