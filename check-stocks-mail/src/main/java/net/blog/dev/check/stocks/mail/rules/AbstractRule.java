package net.blog.dev.check.stocks.mail.rules;

import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.mail.rules.domain.RuleStock;
import net.blog.dev.check.stocks.utils.CalculUtils;

import java.util.List;
import java.util.Optional;

/**
 * Created by romainn on 15/01/2015.
 */
public class AbstractRule {

    public Optional<RuleStock> isEligible(List<Stock> stockList, String code) {
        stockList.sort(CalculUtils.reverseSort);
        Stock stock = stockList.get(0);
        return stock.getVolume().intValue() > 100000 ? Optional.of(new RuleStock()) : Optional.empty();
    }

}
