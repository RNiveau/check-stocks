package net.blog.dev.check.stocks.utils;

import net.blog.dev.check.stocks.dto.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by romainn on 28/08/2014.
 */
public class CalculUtils {

    private static Logger logger = LoggerFactory.getLogger(CalculUtils.class);

    /**
     * Formula: 100 - (100 / (1 + (AvgProfit / AvgLost)))
     * Avg calculate from mme
     * http://www.boursorama.com/q-comment-calculer-le-rsi/2467
     *
     * @param stocks
     * @param period
     * @return
     */
    public static BigDecimal rsi(List<Stock> stocks, int period) {
        // Reverse sort
        stocks.sort((s1, s2) -> {
            if (s1.getDate().equals(s2.getDate()))
                return 0;
            return s1.getDate().isBefore(s2.getDate()) ? 1 : -1;
        });
        List<Stock> limitedStock = stocks.stream().limit(period + 1).collect(Collectors.toList());

        // init mme
        for (int i = 0; i < period; i++)
            stocks.remove(0);
        List<Stock> collect = stocks.stream().limit(period).collect(Collectors.toList());
        Collections.reverse(collect);
        BigDecimal avgLost = arithmeticAvgRsi(
                collect, period, false);
        collect = stocks.stream().limit(period).collect(Collectors.toList());
        Collections.reverse(collect);
        BigDecimal avgProfit = arithmeticAvgRsi(collect, period, true);

        BigDecimal periodAvg = new BigDecimal(((double) period + 1)).setScale(3, RoundingMode.HALF_EVEN);
        BigDecimal a = new BigDecimal(2).setScale(3, RoundingMode.HALF_EVEN).divide(periodAvg, RoundingMode.HALF_EVEN);

        // Mme(j) = (1-a) x MME(j-1) + a x Z
        // Z = value
        // a = 2 / (period + 1)

        Collections.reverse(limitedStock);
        Stock last = limitedStock.remove(0);
        last = limitedStock.remove(0);
        int nbRsi = period;
//        avgLost = BigDecimal.ZERO;
//        avgProfit = BigDecimal.ZERO;
//        avgLost = avgLost.abs().divide(new BigDecimal(period), RoundingMode.HALF_EVEN);
//        avgProfit = avgProfit.abs().divide(new BigDecimal(period), RoundingMode.HALF_EVEN);
        BigDecimal rsi = null;
        for (Stock stock : limitedStock) {
            avgProfit = avgProfit.multiply(new BigDecimal(period - 1));
            avgLost = avgLost.multiply(new BigDecimal(period - 1));
            BigDecimal difference = last.getClose().subtract(stock.getClose());
            difference = difference.multiply(new BigDecimal(-1));
            logger.debug("Difference between {} and {}: {}", last.getDate(), stock.getDate()    , difference);
            if (stock.getClose().compareTo(last.getClose()) > 0)
                avgProfit = avgProfit.add(difference);
            else
                avgLost = avgLost.add(difference);
            avgLost = avgLost.abs().divide(new BigDecimal(period), RoundingMode.HALF_EVEN);
            avgProfit = avgProfit.abs().divide(new BigDecimal(period), RoundingMode.HALF_EVEN);
            rsi = new BigDecimal(100).multiply(avgProfit).divide(avgLost.add(avgProfit), RoundingMode.HALF_EVEN);  //new BigDecimal(100).setScale(3, RoundingMode.HALF_EVEN);
            last = stock;
        }
        return rsi;
//        logger.debug("Sum lost={}, Sum profit={}", avgLost, avgProfit);
//        avgLost = avgLost.abs().divide(new BigDecimal(period), RoundingMode.HALF_EVEN);
//        avgProfit = avgProfit.abs().divide(new BigDecimal(period), RoundingMode.HALF_EVEN);
//        logger.debug("Avg lost={}, Avg profit={}", avgLost, avgProfit);
//
//        if (avgLost.equals(BigDecimal.ZERO))
//            return new BigDecimal(100);
//        else if (avgProfit.equals(BigDecimal.ZERO))
//            return new BigDecimal(0);
//
//
//        BigDecimal rsi = new BigDecimal(100).multiply(avgProfit).divide(avgLost.add(avgProfit), RoundingMode.HALF_EVEN);  //new BigDecimal(100).setScale(3, RoundingMode.HALF_EVEN);
////        BigDecimal tmp = new BigDecimal(100).setScale(3, RoundingMode.HALF_EVEN).divide(
////                new BigDecimal(1).add(
////                        avgProfit.divide(avgLost, RoundingMode.HALF_EVEN)), RoundingMode.HALF_EVEN);
////        return rsi.subtract(tmp);
//        return rsi;
    }

    private static BigDecimal arithmeticAvgRsi(List<Stock> stocks, int period, boolean profit) {
        BigDecimal avgLost = BigDecimal.ZERO.setScale(3, RoundingMode.HALF_EVEN);
        BigDecimal avgProfit = BigDecimal.ZERO.setScale(3, RoundingMode.HALF_EVEN);
        Stock last = stocks.remove(0);
        for (Stock stock : stocks) {
            BigDecimal difference = last.getClose().subtract(stock.getClose());
            difference = difference.multiply(new BigDecimal(-1));
            logger.debug("Difference between {} and {}: {}", last.getDate(), stock.getDate(), difference);
            if (stock.getClose().compareTo(last.getClose()) > 0)
                avgProfit = avgProfit.add(difference);
            else
                avgLost = avgLost.add(difference);
            last = stock;
        }
        logger.debug("Sum lost={}, Sum profit={}", avgLost, avgProfit);
        avgLost = avgLost.abs().divide(new BigDecimal(period), RoundingMode.HALF_EVEN);
        avgProfit = avgProfit.abs().divide(new BigDecimal(period), RoundingMode.HALF_EVEN);
        logger.debug("Avg lost={}, Avg profit={}", avgLost, avgProfit);
        return profit ? avgProfit : avgLost;
    }

}
