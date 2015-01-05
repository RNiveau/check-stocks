package net.blog.dev.check.stocks.mail.rules;

import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.mail.rules.api.IRule;

import java.util.List;

/**
 * Created by romainn on 05/01/2015.
 */
public class RuleMobileAvg20 implements IRule {

    @Override
    public boolean isEligible(List<Stock> stockList) {
        return false;
    }

}
