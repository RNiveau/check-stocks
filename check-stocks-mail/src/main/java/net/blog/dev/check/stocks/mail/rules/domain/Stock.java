package net.blog.dev.check.stocks.mail.rules.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by romainn on 19/02/2015.
 */
public class Stock {
    private LocalDate date;

    private BigDecimal open;

    private BigDecimal close;

    private BigDecimal low;

    private BigDecimal high;

    private BigDecimal volume;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " " + close.doubleValue();
    }

    public String toJson() {
        return "{open:" + open + ", close:" + close + ", low:" + low + ",high:" + high + ", date:moment('" + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                "').toDate()" + "}";
    }

}
