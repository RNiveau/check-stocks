package net.blog.dev.check.stocks.domain.indicators;

import java.math.BigDecimal;

/**
 * Created by romainn on 17/12/2014.
 */
public class DynamicRsi {

    private BigDecimal rsi;

    private BigDecimal stdHigh;

    private BigDecimal stdLow;

    private BigDecimal std;

    public BigDecimal getRsi() {
        return rsi;
    }

    public void setRsi(BigDecimal rsi) {
        this.rsi = rsi;
    }

    public BigDecimal getStdHigh() {
        return stdHigh;
    }

    public void setStdHigh(BigDecimal stdHigh) {
        this.stdHigh = stdHigh;
    }

    public BigDecimal getStdLow() {
        return stdLow;
    }

    public void setStdLow(BigDecimal stdLow) {
        this.stdLow = stdLow;
    }

    public BigDecimal getStd() {
        return std;
    }

    public void setStd(BigDecimal std) {
        this.std = std;
    }
}
