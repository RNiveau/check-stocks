package net.blog.dev.check.stocks.utils;

import net.blog.dev.check.stocks.dto.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by romainn on 28/08/2014.
 */
public class CalculUtils {

    private static Logger logger = LoggerFactory.getLogger(CalculUtils.class);

    private static Comparator<? super Stock> reverseSort = (s1, s2) -> {
        if (s1.getDate().equals(s2.getDate()))
            return 0;
        return s1.getDate().isBefore(s2.getDate()) ? 1 : -1;
    };

    private static Comparator<? super Stock> sort = (s1, s2) -> {
        if (s1.getDate().equals(s2.getDate()))
            return 0;
        return s1.getDate().isBefore(s2.getDate()) ? -1 : 1;
    };

    /**
     * Formula: 100 - (100 / (1 + (AvgProfit / AvgLost)))
     * Avg calculate from mme
     * http://www.boursorama.com/q-comment-calculer-le-rsi/2467
     *
     * @param stocks
     * @param period
     * @return
     */
    public static BigDecimal rsi4(List<Stock> stocks, int period) {
        stocks.sort(sort);
        // clone
        stocks = stocks.stream().collect(Collectors.toList());
        List<BigDecimal> profits = new ArrayList<>();
        List<BigDecimal> losts = new ArrayList<>();

        Stock last = stocks.remove(0);
        for (Stock stock : stocks) {
            BigDecimal difference = last.getClose().subtract(stock.getClose()).abs().setScale(10, RoundingMode.HALF_EVEN);
            if (stock.getClose().compareTo(last.getClose()) > 0) {
                profits.add(difference);
            } else {
                losts.add(difference);
            }
            last = stock;
        }
        BigDecimal avgProfit = exponentialAverageBigDecimal(profits, period);
        BigDecimal avgLost = exponentialAverageBigDecimal(losts, period);
        BigDecimal rsi = new BigDecimal(100).multiply(avgProfit).divide(avgLost.add(avgProfit), RoundingMode.HALF_EVEN);
        logger.debug("Rsi={}", rsi);
        return rsi;
    }

    public static BigDecimal arithmeticAverage(List<Stock> stocks, int period) {
        stocks.sort(reverseSort);
        List<BigDecimal> lists = stocks.stream().map(stock -> stock.getClose()).collect(Collectors.toList());
        return arithmeticAverageBigDecimal(lists, period);
    }

    public static BigDecimal arithmeticAverageBigDecimal(List<BigDecimal> lists, int period) {
        List<BigDecimal> limitedStock = lists.stream().limit(period).collect(Collectors.toList());
        Collections.reverse(limitedStock);
        OptionalDouble average = limitedStock.stream().mapToDouble(stock -> stock.doubleValue()).average();
        logger.debug("arithmeticAverageBigDecimal={}", average.getAsDouble());
        return new BigDecimal(average.getAsDouble());
    }

    public static BigDecimal exponentialAverageBigDecimal(List<BigDecimal> stocks, int period) {
        List<BigDecimal> limitedStock = stocks.stream().limit(period).collect(Collectors.toList());
        logger.debug("Start point collections={}, {}", limitedStock.size(), limitedStock);
        BigDecimal avg = arithmeticAverageBigDecimal(limitedStock, period);

        // init mme
        for (int i = 0; i < period; i++)
            stocks.remove(0);

        BigDecimal alpha = new BigDecimal(2).setScale(5, RoundingMode.HALF_EVEN).divide(new BigDecimal(period + 1), RoundingMode.HALF_EVEN);

        logger.debug("alpha={}", alpha);
        logger.debug("Avg start={}", avg.doubleValue());

        // Mme(j) = (1-alpha) x MME(j-1) + alpha x Z
        // Z = value
        // alpha = 2 / (period + 1)

        logger.debug("Next collections={}, {}", stocks.size(), stocks);
        for (BigDecimal bigDecimal : stocks) {
            BigDecimal tmp = new BigDecimal(1).setScale(5, RoundingMode.HALF_EVEN).subtract(alpha);
            BigDecimal tmp2 = tmp.multiply(avg);
            avg = tmp2.add(alpha.multiply(bigDecimal));
        }
        logger.debug("Mme={}", avg.doubleValue());
        return avg;

    }


    public static BigDecimal exponentialAverage(List<Stock> stocks, int period) {
        // Reverse sort
        stocks.sort(sort);
        List<BigDecimal> lists = stocks.stream().map(stock -> stock.getClose()).collect(Collectors.toList());
        return exponentialAverageBigDecimal(lists, period);
    }
}
