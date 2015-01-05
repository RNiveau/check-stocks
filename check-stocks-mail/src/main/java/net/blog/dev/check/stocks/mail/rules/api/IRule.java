package net.blog.dev.check.stocks.mail.rules.api;

import net.blog.dev.check.stocks.domain.Stock;

import java.util.List;

/**
 * Created by romainn on 05/01/2015.
 */
public interface IRule {

    boolean isEligible(List<Stock> stockList);

}
