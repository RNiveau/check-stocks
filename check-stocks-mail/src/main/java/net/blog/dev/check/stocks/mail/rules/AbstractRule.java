package net.blog.dev.check.stocks.mail.rules;

import net.blog.dev.check.stocks.domain.CompleteStock;
import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.mail.rules.api.IRule;
import net.blog.dev.check.stocks.mail.rules.domain.RuleStock;

import java.util.List;
import java.util.Optional;

/**
 * Created by romainn on 15/01/2015.
 */
public abstract class AbstractRule implements IRule {

    @Override
    public Optional<RuleStock> isEligible(List<Stock> stockList, CompleteStock lastStock) {
        if (stockList.size() == 0 || lastStock == null)
            return Optional.empty();
        return lastStock.getVolume().intValue() > 100000 ? Optional.of(new RuleStock()) : Optional.empty();
    }

    protected void setLastStock(RuleStock ruleStock, Stock stock) {
        net.blog.dev.check.stocks.mail.rules.domain.Stock lastStock = new net.blog.dev.check.stocks.mail.rules.domain.Stock();
        lastStock.setClose(stock.getClose());
        lastStock.setDate(stock.getDate());
        lastStock.setHigh(stock.getHigh());
        lastStock.setLow(stock.getLow());
        lastStock.setOpen(stock.getOpen());
        ruleStock.setLastStock(lastStock);
    }
}
