package net.blog.dev.check.stocks.mappers;

import net.blog.dev.check.stocks.domain.CompleteStock;
import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.mappers.api.IStockMapper;
import net.blog.dev.services.beans.AlphaAvantageWrapper;
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
    public List<Stock> mappeAlphaToStock(AlphaAvantageWrapper response) {
        List<Stock> stocks = new ArrayList<>();
        if (response != null && response.getQuotes() != null) {
            stocks = response.getQuotes().stream().map(quote -> {
                Stock stock = new Stock();
                stock.setClose(getBigDecimal(quote.getClose()));
                stock.setDate(quote.getDate() != null ? quote.getDate() : LocalDate.MIN);
                stock.setHigh(getBigDecimal(quote.getHigh()));
                stock.setLow(getBigDecimal(quote.getLow()));
                stock.setOpen(getBigDecimal(quote.getOpen()));
                stock.setVolume(getBigDecimal(quote.getVolume()));
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
