package net.blog.dev.check.stocks.mail.rules.api;

import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.mail.rules.domain.RuleStock;

import java.util.List;
import java.util.Optional;

/**
 * Created by romainn on 05/01/2015.
 */
public interface IRule {

    String getName();

    Optional<RuleStock> isEligible(List<Stock> stockList, String code);

}
