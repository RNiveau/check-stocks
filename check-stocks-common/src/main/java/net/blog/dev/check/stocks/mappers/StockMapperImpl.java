package net.blog.dev.check.stocks.mappers;

import net.blog.dev.check.stocks.domain.CompleteStock;
import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.mappers.api.IStockMapper;
import net.blog.dev.services.domain.historic.YahooResponse;
import net.blog.dev.services.domain.quote.Quote;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by romainn on 05/01/2015.
 */
public class StockMapperImpl implements IStockMapper {
    @Override
    public List<Stock> mappeYahooToStock(YahooResponse yahooResponse) {
        List<Stock> stocks = new ArrayList<>();
        if (yahooResponse != null && yahooResponse.getQuery() != null && yahooResponse.getQuery().getResults() != null && CollectionUtils.isNotEmpty(yahooResponse.getQuery().getResults().getQuote())) {
            stocks = yahooResponse.getQuery().getResults().getQuote().stream().map(yahoo -> {
                Stock stock = new Stock();
                stock.setClose(getBigDecimal(yahoo.getClose()));
                stock.setDate(yahoo.getDate() != null ? yahoo.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : LocalDate.MIN);
                stock.setHigh(getBigDecimal(yahoo.getHigh()));
                stock.setLow(getBigDecimal(yahoo.getLow()));
                stock.setOpen(getBigDecimal(yahoo.getOpen()));
                stock.setVolume(getBigDecimal(yahoo.getVolume()));
                return stock;
            }).collect(Collectors.toList());
        }
        return stocks;
    }

    private BigDecimal getBigDecimal(Float number) {
        return new BigDecimal(number != null ? number : 0);
    }

    private BigDecimal getBigDecimal(Long number) {
        return new BigDecimal(number != null ? number : 0);
    }


    @Override
    public CompleteStock mappeQuoteToStock(Quote quote) {
        CompleteStock completeStock = null;
        if (quote != null) {
            completeStock = new CompleteStock();
            Float close = quote.getAsk() != null ? quote.getAsk() : quote.getLastTradePriceOnly();
            completeStock.setName(quote.getName());
            completeStock.setCode(quote.getSymbol());
            completeStock.setLastVariation(getBigDecimal(quote.getChangeinPercent()));
            completeStock.setClose(getBigDecimal(close));
            completeStock.setDate(LocalDate.now());
            completeStock.setHigh(getBigDecimal(quote.getDaysHigh()));
            completeStock.setLow(getBigDecimal(quote.getDaysLow()));
            completeStock.setOpen(getBigDecimal(quote.getOpen()));
            completeStock.setVolume(getBigDecimal(quote.getVolume()));
        }
        return completeStock;
    }
}
