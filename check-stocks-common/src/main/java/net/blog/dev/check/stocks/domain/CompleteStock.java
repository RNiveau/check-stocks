package net.blog.dev.check.stocks.domain;

import java.math.BigDecimal;

/**
 * Created by romainn on 21/01/2015.
 */
public class CompleteStock extends Stock {

    private String code;

    private String name;

    private BigDecimal lastVariation;

    public BigDecimal getLastVariation() {
        return lastVariation;
    }

    public void setLastVariation(BigDecimal lastVariation) {
        this.lastVariation = lastVariation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
