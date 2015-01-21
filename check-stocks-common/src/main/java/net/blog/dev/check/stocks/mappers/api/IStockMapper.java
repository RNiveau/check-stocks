package net.blog.dev.check.stocks.mappers.api;

import net.blog.dev.check.stocks.domain.CompleteStock;
import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.services.domain.historic.YahooResponse;
import net.blog.dev.services.domain.quote.Quote;

import java.util.List;

/**
 * Created by romainn on 05/01/2015.
 */
public interface IStockMapper {

    List<Stock> mappeYahooToStock(YahooResponse yahooResponse);

    CompleteStock mappeQuoteToStock(Quote quote);


}
