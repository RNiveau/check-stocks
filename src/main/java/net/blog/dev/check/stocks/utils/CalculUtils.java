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
        List<Stock> limitedStock = stocks.stream().limit(period).collect(Collectors.toList());

        logger.debug("LimitedStock={}", limitedStock.size());

        // init mme
        for (int i = 0; i < period; i++)
            stocks.remove(0);
        List<Stock> collect = stocks.stream().limit(period).collect(Collectors.toList());
        Collections.reverse(collect);
        logger.debug("Collect={}, {}", collect.size(), collect);
        BigDecimal avgLost = arithmeticAvgRsi(
                collect, period, false);
        collect = stocks.stream().limit(period).collect(Collectors.toList());
        Collections.reverse(collect);
        BigDecimal avgProfit = arithmeticAvgRsi(collect, period, true);

        BigDecimal a = new BigDecimal(2).setScale(10, RoundingMode.HALF_EVEN).divide(new BigDecimal(period + 1), RoundingMode.HALF_EVEN);

        logger.debug("a={}", a);

        // Mme(j) = (1-a) x MME(j-1) + a x Z
        // Z = value
        // a = 2 / (period + 1)

        Collections.reverse(limitedStock);
        Stock last = limitedStock.remove(0);
        int nbRsi = period;
        period = 0;
//        try with it http://fr.wikipedia.org/wiki/Moyenne_glissante
        for (Stock stock : limitedStock) {
            BigDecimal difference = last.getClose().subtract(stock.getClose()).abs().setScale(10, RoundingMode.HALF_EVEN);
//            logger.debug("Difference between {} and {}: {}", last.getDate(), stock.getDate(), difference);
            if (stock.getClose().compareTo(last.getClose()) > 0){
                BigDecimal tmp = new BigDecimal(1).subtract(a);
                BigDecimal tmp2 = tmp.multiply(avgProfit);
                avgProfit = tmp2.add(a.multiply(difference));
            }
            else {
                BigDecimal tmp = new BigDecimal(1).subtract(a);
                BigDecimal tmp2 = tmp.multiply(avgLost);
                avgLost = tmp2.add(a.multiply(difference));
            }
            last = stock;
        }
        logger.debug("Avg lost={}, Avg profit={}", avgLost, avgProfit);
        BigDecimal rsi = new BigDecimal(100).multiply(avgProfit).divide(avgLost.add(avgProfit), RoundingMode.HALF_EVEN);
        logger.debug("Rsi={}", rsi);
        return rsi;
    }

    private static BigDecimal arithmeticAvgRsi(List<Stock> stocks, int period, boolean profit) {
        BigDecimal avgLost = BigDecimal.ZERO.setScale(10, RoundingMode.HALF_EVEN);
        BigDecimal avgProfit = BigDecimal.ZERO.setScale(10, RoundingMode.HALF_EVEN);
        Stock last = stocks.remove(0);
        for (Stock stock : stocks) {
            BigDecimal difference = last.getClose().subtract(stock.getClose()).abs();
//            logger.debug("Difference between {} and {}: {}", last.getDate(), stock.getDate(), difference);
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
