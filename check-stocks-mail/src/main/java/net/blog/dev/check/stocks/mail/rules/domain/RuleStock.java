package net.blog.dev.check.stocks.mail.rules.domain;

import java.math.BigDecimal;

/**
 * Created by romainn on 06/01/2015.
 */
public class RuleStock {

    private String code;

    private BigDecimal price;

    private BigDecimal variation;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVariation() {
        return variation;
    }

    public void setVariation(BigDecimal variation) {
        this.variation = variation;
    }
}
