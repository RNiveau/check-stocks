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
        if (stockList.size() == 0)
            return Optional.empty();
        return lastStock.getVolume().intValue() > 100000 ? Optional.of(new RuleStock()) : Optional.empty();
    }

}
