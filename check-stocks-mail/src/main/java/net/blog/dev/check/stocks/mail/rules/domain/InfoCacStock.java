package net.blog.dev.check.stocks.mail.rules.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by romainn on 11/02/2015.
 */
public class InfoCacStock extends RuleStock {

    private BigDecimal avg20;

    private BigDecimal distanceWithAvg20;

    private boolean eligibleMA20;

    public BigDecimal getAvg20() {
        return avg20;
    }

    public void setAvg20(BigDecimal avg20) {
        this.avg20 = avg20;
    }

    public BigDecimal getDistanceWithAvg20() {
        return distanceWithAvg20;
    }

    public void setDistanceWithAvg20(BigDecimal distanceWithAvg20) {
        this.distanceWithAvg20 = distanceWithAvg20;
    }

    public boolean isEligibleMA20() {
        return eligibleMA20;
    }

    public void setEligibleMA20(boolean eligibleMA20) {
        this.eligibleMA20 = eligibleMA20;
    }

    public String toHtml() {
        String html = "<p>" + this.getName() + " (" + this.getCode() + ")";
        if (this.getPrice() != null)
            html += ", " + this.getPrice().setScale(3, RoundingMode.HALF_EVEN).floatValue();
        if (this.getVariation() != null)
            html += ", " + this.getVariation().setScale(3, RoundingMode.HALF_EVEN).floatValue() + "%";
        if (this.isEligibleMA20())
            html += ", eligible MM20";
        if (this.getDistanceWithAvg20() != null)
            html += ", distance MM20 = " + this.getDistanceWithAvg20().setScale(3, RoundingMode.HALF_EVEN).floatValue() + "%";
        html += "</p>";
        return html;
    }

}
