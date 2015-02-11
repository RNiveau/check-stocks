package net.blog.dev.check.stocks.mail.rules.domain;

import java.math.BigDecimal;

/**
 * Created by romainn on 11/02/2015.
 */
public class MA20Stock extends RuleStock {

    private BigDecimal ma20;

    public BigDecimal getMa20() {
        return ma20;
    }

    public void setMa20(BigDecimal ma20) {
        this.ma20 = ma20;
    }
}