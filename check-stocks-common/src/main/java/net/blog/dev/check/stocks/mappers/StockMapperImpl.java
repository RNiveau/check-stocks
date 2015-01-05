package net.blog.dev.check.stocks.mappers;

import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.mappers.api.IStockMapper;
import net.blog.dev.services.domain.YahooResponse;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
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
        if (yahooResponse != null && yahooResponse.getQuery().getResults() != null && CollectionUtils.isNotEmpty(yahooResponse.getQuery().getResults().getQuote())) {
           stocks = yahooResponse.getQuery().getResults().getQuote().stream().map(yahoo -> {
                Stock stock = new Stock();
                stock.setClose(new BigDecimal(yahoo.getClose()));
                stock.setDate(yahoo.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                stock.setHigh(new BigDecimal(yahoo.getHigh()));
                stock.setLow(new BigDecimal(yahoo.getLow()));
                stock.setOpen(new BigDecimal(yahoo.getOpen()));
                stock.setVolume(new BigDecimal(yahoo.getVolume()));
                return stock;
            }).collect(Collectors.toList());
        }
        return stocks;
    }
}
