package net.blog.dev.check.stocks.mail.rules.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romainn on 06/01/2015.
 */
public class RuleResult {

    private String name;

    private List<RuleStock> stocks;

    public void addStock(RuleStock stock) {
        if (stocks == null)
            stocks = new ArrayList<>();
        stocks.add(stock);
    }

    public List<RuleStock> getStocks() {
        return stocks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
