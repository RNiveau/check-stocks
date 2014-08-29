package net.blog.dev.check.stocks.utils;

import net.blog.dev.check.stocks.dto.Stock;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by romainn on 28/08/2014.
 */
public class CalculUtilsTest {

    @Test
    public void testRsi() throws Exception {
        // Basic test from http://www.trading-school.eu/glossaire-bourse/fiche-Relative-Strength-Index-RSI--45

        List<Stock> stockList = new ArrayList<>();
        stockList.add(createStock(LocalDate.of(2000, 1, 1), 12));
        stockList.add(createStock(LocalDate.of(2000, 1, 2), 13));
        stockList.add(createStock(LocalDate.of(2000, 1, 3), 12.5));
        stockList.add(createStock(LocalDate.of(2000, 1, 4), 14));
        stockList.add(createStock(LocalDate.of(2000, 1, 5), 13));
        stockList.add(createStock(LocalDate.of(2000, 1, 6), 12));
        stockList.add(createStock(LocalDate.of(2000, 1, 7), 11));

        Assert.assertEquals(new BigDecimal("50.00"), CalculUtils.rsi(stockList, 5).setScale(2, RoundingMode.HALF_EVEN));

        stockList.remove(0);
        Assert.assertEquals(new BigDecimal("30.02"), CalculUtils.rsi(stockList, 5).setScale(2, RoundingMode.HALF_EVEN));

        // exemple ACA 28/04/2014
        stockList.clear();
        stockList.add(createStock(LocalDate.of(2014, 8, 8), 10.435));
        stockList.add(createStock(LocalDate.of(2014, 8, 11), 10.45));
        stockList.add(createStock(LocalDate.of(2014, 8, 12), 10.43));
        stockList.add(createStock(LocalDate.of(2014, 8, 13), 10.565));
        stockList.add(createStock(LocalDate.of(2014, 8, 14), 10.605));
        stockList.add(createStock(LocalDate.of(2014, 8, 15), 10.47));
        stockList.add(createStock(LocalDate.of(2014, 8, 18), 10.655));
        stockList.add(createStock(LocalDate.of(2014, 8, 19), 10.715));
        stockList.add(createStock(LocalDate.of(2014, 8, 20), 10.69));
        stockList.add(createStock(LocalDate.of(2014, 8, 21), 10.81));
        stockList.add(createStock(LocalDate.of(2014, 8, 22), 10.805));
        stockList.add(createStock(LocalDate.of(2014, 8, 25), 10.99));
        stockList.add(createStock(LocalDate.of(2014, 8, 26), 11.145));
        stockList.add(createStock(LocalDate.of(2014, 8, 27), 11.365));
        stockList.add(createStock(LocalDate.of(2014, 8, 28), 11.19));
        Assert.assertEquals(new BigDecimal("63.708"), CalculUtils.rsi(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));


    }

    private Stock createStock(LocalDate date, double close) {
        Stock stock = new Stock();
        stock.setDate(date);
        stock.setClose(new BigDecimal(close).setScale(3, RoundingMode.HALF_EVEN));
        return stock;
    }

}
