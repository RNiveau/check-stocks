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
        List<Stock> stockList = new ArrayList<>();

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
        List<Stock> stockList = new ArrayList<>();

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
        Assert.assertEquals(new BigDecimal("11.004"), CalculUtils.exponentialAverage(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));
        stockList.add(createStock(LocalDate.of(2014, 9, 3), 11.480));
        Assert.assertEquals(new BigDecimal("11.064"), CalculUtils.exponentialAverage(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));
        stockList.add(createStock(LocalDate.of(2014, 9, 4), 11.750));
        Assert.assertEquals(new BigDecimal("11.156"), CalculUtils.exponentialAverage(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));
        stockList.add(createStock(LocalDate.of(2014, 9, 5), 11.960));
        Assert.assertEquals(new BigDecimal("11.263"), CalculUtils.exponentialAverage(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));
        Assert.assertEquals(new BigDecimal("11.119"), CalculUtils.exponentialAverage(stockList, 20).setScale(3, RoundingMode.HALF_EVEN));
    }


    @Test
    public void testRsi() throws Exception {
        // Basic test from http://www.trading-school.eu/glossaire-bourse/fiche-Relative-Strength-Index-RSI--45

        List<Stock> stockList = new ArrayList<>();
//        stockList.add(createStock(LocalDate.of(2000, 1, 1), 12));
//        stockList.add(createStock(LocalDate.of(2000, 1, 2), 13));
//        stockList.add(createStock(LocalDate.of(2000, 1, 3), 12.5));
//        stockList.add(createStock(LocalDate.of(2000, 1, 4), 14));
//        stockList.add(createStock(LocalDate.of(2000, 1, 5), 13));
//        stockList.add(createStock(LocalDate.of(2000, 1, 6), 12));
//        stockList.add(createStock(LocalDate.of(2000, 1, 7), 11));
//
//        Assert.assertEquals(new BigDecimal("50.00"), CalculUtils.rsi(stockList, 5).setScale(2, RoundingMode.HALF_EVEN));
//
//        stockList.remove(0);
//        Assert.assertEquals(new BigDecimal("30.02"), CalculUtils.rsi(stockList, 5).setScale(2, RoundingMode.HALF_EVEN));

        // exemple ACA 28/04/2014
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
        CalculUtils.rsi4(stockList, 14).setScale(3, RoundingMode.HALF_EVEN);
//        Assert.assertEquals(new BigDecimal("63.708"), CalculUtils.rsi(stockList, 14).setScale(3, RoundingMode.HALF_EVEN));

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

}
