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
    public void testArithmeticAverage() {
        // Test on ACA stocks
        List<Stock> stockList = getStocksAca20140902();
        Assert.assertEquals(new BigDecimal("10.945"), CalculUtils.arithmeticAverage(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));
        stockList.add(createStock(LocalDate.of(2014, 9, 3), 11.480));
        Assert.assertEquals(new BigDecimal("11.007"), CalculUtils.arithmeticAverage(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));
        stockList.add(createStock(LocalDate.of(2014, 9, 4), 11.750));
        Assert.assertEquals(new BigDecimal("11.099"), CalculUtils.arithmeticAverage(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));
        stockList.add(createStock(LocalDate.of(2014, 9, 5), 11.960));
        Assert.assertEquals(new BigDecimal("11.192"), CalculUtils.arithmeticAverage(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));
    }

    @Test
    public void testExponentialAverage() {
        // Test on ACA stocks
        List<Stock> stockList = getStocksAca20140902();
        Assert.assertEquals(new BigDecimal("11.000"), CalculUtils.exponentialAverage(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));
        stockList.add(createStock(LocalDate.of(2014, 9, 3), 11.480));
        Assert.assertEquals(new BigDecimal("11.064"), CalculUtils.exponentialAverage(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));
        stockList.add(createStock(LocalDate.of(2014, 9, 4), 11.750));
        Assert.assertEquals(new BigDecimal("11.155"), CalculUtils.exponentialAverage(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));
        stockList.add(createStock(LocalDate.of(2014, 9, 5), 11.960));
        Assert.assertEquals(new BigDecimal("11.263"), CalculUtils.exponentialAverage(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));
        Assert.assertEquals(new BigDecimal("11.119"), CalculUtils.exponentialAverage(stockList, 20).setScale(3, RoundingMode.HALF_EVEN));
    }

        @Test
    public void testRsi() throws Exception {
        // Basic test from http://www.trading-school.eu/glossaire-bourse/fiche-Relative-Strength-Index-RSI--45

        List<Stock> stockList = getStocksAca20140902();
        // exemple ACA 28/08/2014
        CalculUtils.rsi4(stockList, 14).setScale(3, RoundingMode.HALF_EVEN);
        Assert.assertEquals(new BigDecimal("63.708"), CalculUtils.rsi4(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));

        stockList.clear();
        stockList.add(createStock(LocalDate.of(2014, 7, 18), 10.275));
        stockList.add(createStock(LocalDate.of(2014, 7, 21), 10.085));
        stockList.add(createStock(LocalDate.of(2014, 7, 22), 10.200));
        stockList.add(createStock(LocalDate.of(2014, 7, 23), 10.130));
        stockList.add(createStock(LocalDate.of(2014, 7, 24), 10.715));
        stockList.add(createStock(LocalDate.of(2014, 7, 25), 10.680));
        stockList.add(createStock(LocalDate.of(2014, 7, 28), 10.740));
        stockList.add(createStock(LocalDate.of(2014, 7, 29), 10.685));
        stockList.add(createStock(LocalDate.of(2014, 7, 30), 10.570));
        stockList.add(createStock(LocalDate.of(2014, 7, 31), 10.120));
        stockList.add(createStock(LocalDate.of(2014, 8, 1), 10.085));
        stockList.add(createStock(LocalDate.of(2014, 8, 4), 10.285));
        stockList.add(createStock(LocalDate.of(2014, 8, 5), 10.510));
        stockList.add(createStock(LocalDate.of(2014, 8, 6), 10.580));
        stockList.add(createStock(LocalDate.of(2014, 8, 7), 10.360));
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
        stockList.add(createStock(LocalDate.of(2014, 8, 29), 11.285));
        stockList.add(createStock(LocalDate.of(2014, 9, 1), 11.205));
        stockList.add(createStock(LocalDate.of(2014, 9, 2), 11.295));
        stockList.add(createStock(LocalDate.of(2014, 9, 3), 11.480));
        stockList.add(createStock(LocalDate.of(2014, 9, 4), 11.750));
        stockList.add(createStock(LocalDate.of(2014, 9, 5), 11.960));
//        stockList.add(createStock(LocalDate.of(2014, 9, 5), 11.480));
        Assert.assertEquals(new BigDecimal("75.566"), CalculUtils.rsi4(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));
    }

    private Stock createStock(LocalDate date, double close) {
        Stock stock = new Stock();
        stock.setDate(date);
        stock.setClose(new BigDecimal(close).setScale(3, RoundingMode.HALF_EVEN));
        return stock;
    }

    private List<Stock> getStocksAca20140902() {
        List<Stock> stockList = new ArrayList<>();
        stockList.add(createStock(LocalDate.of(2014, 4, 30), 11.355));
        stockList.add(createStock(LocalDate.of(2014, 5, 2), 11.375));
        stockList.add(createStock(LocalDate.of(2014, 5, 5), 11.245));
        stockList.add(createStock(LocalDate.of(2014, 5, 6), 11.105));
        stockList.add(createStock(LocalDate.of(2014, 5, 7), 11.86));
        stockList.add(createStock(LocalDate.of(2014, 5, 8), 12.045));
        stockList.add(createStock(LocalDate.of(2014, 5, 9), 11.83));
        stockList.add(createStock(LocalDate.of(2014, 5, 12), 11.9));
        stockList.add(createStock(LocalDate.of(2014, 5, 13), 11.885));
        stockList.add(createStock(LocalDate.of(2014, 5, 14), 11.635));
        stockList.add(createStock(LocalDate.of(2014, 5, 15), 11.15));
        stockList.add(createStock(LocalDate.of(2014, 5, 16), 11.275));
        stockList.add(createStock(LocalDate.of(2014, 5, 19), 11.095));
        stockList.add(createStock(LocalDate.of(2014, 5, 20), 11.01));
        stockList.add(createStock(LocalDate.of(2014, 5, 21), 11.24));
        stockList.add(createStock(LocalDate.of(2014, 5, 22), 11.26));
        stockList.add(createStock(LocalDate.of(2014, 5, 23), 11.56));
        stockList.add(createStock(LocalDate.of(2014, 5, 26), 11.67));
        stockList.add(createStock(LocalDate.of(2014, 5, 27), 11.76));
        stockList.add(createStock(LocalDate.of(2014, 5, 28), 11.95));
        stockList.add(createStock(LocalDate.of(2014, 5, 29), 11.89));
        stockList.add(createStock(LocalDate.of(2014, 5, 30), 11.445));
        stockList.add(createStock(LocalDate.of(2014, 6, 2), 11.385));
        stockList.add(createStock(LocalDate.of(2014, 6, 3), 11.39));
        stockList.add(createStock(LocalDate.of(2014, 6, 4), 11.55));
        stockList.add(createStock(LocalDate.of(2014, 6, 5), 11.655));
        stockList.add(createStock(LocalDate.of(2014, 6, 6), 11.915));
        stockList.add(createStock(LocalDate.of(2014, 6, 9), 11.95));
        stockList.add(createStock(LocalDate.of(2014, 6, 10), 11.88));
        stockList.add(createStock(LocalDate.of(2014, 6, 11), 11.685));
        stockList.add(createStock(LocalDate.of(2014, 6, 12), 11.72));
        stockList.add(createStock(LocalDate.of(2014, 6, 13), 11.715));
        stockList.add(createStock(LocalDate.of(2014, 6, 16), 11.455));
        stockList.add(createStock(LocalDate.of(2014, 6, 17), 11.405));
        stockList.add(createStock(LocalDate.of(2014, 6, 18), 11.325));
        stockList.add(createStock(LocalDate.of(2014, 6, 19), 11.35));
        stockList.add(createStock(LocalDate.of(2014, 6, 20), 11.145));
        stockList.add(createStock(LocalDate.of(2014, 6, 23), 11.025));
        stockList.add(createStock(LocalDate.of(2014, 6, 24), 10.92));
        stockList.add(createStock(LocalDate.of(2014, 6, 25), 10.71));
        stockList.add(createStock(LocalDate.of(2014, 6, 26), 10.595));
        stockList.add(createStock(LocalDate.of(2014, 6, 27), 10.6));
        stockList.add(createStock(LocalDate.of(2014, 6, 30), 10.3));
        stockList.add(createStock(LocalDate.of(2014, 7, 1), 10.605));
        stockList.add(createStock(LocalDate.of(2014, 7, 2), 10.56));
        stockList.add(createStock(LocalDate.of(2014, 7, 3), 10.705));
        stockList.add(createStock(LocalDate.of(2014, 7, 4), 10.64));
        stockList.add(createStock(LocalDate.of(2014, 7, 7), 10.435));
        stockList.add(createStock(LocalDate.of(2014, 7, 8), 10.17));
        stockList.add(createStock(LocalDate.of(2014, 7, 9), 10.21));
        stockList.add(createStock(LocalDate.of(2014, 7, 10), 10));
        stockList.add(createStock(LocalDate.of(2014, 7, 11), 9.898));
        stockList.add(createStock(LocalDate.of(2014, 7, 14), 10.02));
        stockList.add(createStock(LocalDate.of(2014, 7, 15), 9.915));
        stockList.add(createStock(LocalDate.of(2014, 7, 16), 10.39));
        stockList.add(createStock(LocalDate.of(2014, 7, 17), 10.17));
        stockList.add(createStock(LocalDate.of(2014, 7, 18), 10.275));
        stockList.add(createStock(LocalDate.of(2014, 7, 21), 10.085));
        stockList.add(createStock(LocalDate.of(2014, 7, 22), 10.200));
        stockList.add(createStock(LocalDate.of(2014, 7, 23), 10.130));
        stockList.add(createStock(LocalDate.of(2014, 7, 24), 10.715));
        stockList.add(createStock(LocalDate.of(2014, 7, 25), 10.680));
        stockList.add(createStock(LocalDate.of(2014, 7, 28), 10.740));
        stockList.add(createStock(LocalDate.of(2014, 7, 29), 10.685));
        stockList.add(createStock(LocalDate.of(2014, 7, 30), 10.570));
        stockList.add(createStock(LocalDate.of(2014, 7, 31), 10.120));
        stockList.add(createStock(LocalDate.of(2014, 8, 1), 10.085));
        stockList.add(createStock(LocalDate.of(2014, 8, 4), 10.285));
        stockList.add(createStock(LocalDate.of(2014, 8, 5), 10.510));
        stockList.add(createStock(LocalDate.of(2014, 8, 6), 10.580));
        stockList.add(createStock(LocalDate.of(2014, 8, 7), 10.360));
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
        stockList.add(createStock(LocalDate.of(2014, 8, 29), 11.285));
        stockList.add(createStock(LocalDate.of(2014, 9, 1), 11.205));
        stockList.add(createStock(LocalDate.of(2014, 9, 2), 11.295));
        return stockList;
    }


}
