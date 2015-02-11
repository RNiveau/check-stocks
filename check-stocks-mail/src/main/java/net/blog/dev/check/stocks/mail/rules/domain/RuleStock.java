package net.blog.dev.check.stocks.mail.rules.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by romainn on 06/01/2015.
 */
public class RuleStock {

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toHtml() {
        String html = "<p>" + this.getName() + " (" + this.getCode() + ")";
        if (this.getPrice() != null)
            html += ", " + this.getPrice().setScale(3, RoundingMode.HALF_EVEN).floatValue();
        if (this.getVariation() != null)
            html += ", " + this.getVariation().setScale(3, RoundingMode.HALF_EVEN).floatValue() + "%";
        html += "</p>";
        return html;
    }
}
