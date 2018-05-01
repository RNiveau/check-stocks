package net.blog.dev.check.stocks.utils;

import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.domain.indicators.DynamicRsi;
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

    private static int BIGDECIMAL_SCALE = 10;

    public static Comparator<? super Stock> reverseSort = (s1, s2) -> {
        if (s1.getDate().equals(s2.getDate()))
            return 0;
        return s1.getDate().isBefore(s2.getDate()) ? 1 : -1;
    };

    public static Comparator<? super Stock> sort = (s1, s2) -> {
        if (s1.getDate().equals(s2.getDate()))
            return 0;
        return s1.getDate().isBefore(s2.getDate()) ? -1 : 1;
    };

    static public BigDecimal getCleanBigDecimal(BigDecimal bigDecimal) {
        return bigDecimal.setScale(BIGDECIMAL_SCALE, RoundingMode.HALF_EVEN);
    }

    static public BigDecimal getCleanBigDecimal(int integer) {
        return new BigDecimal(integer).setScale(BIGDECIMAL_SCALE, RoundingMode.HALF_EVEN);
    }

    static public BigDecimal getCleanBigDecimal(double d) {
        return new BigDecimal(d).setScale(BIGDECIMAL_SCALE, RoundingMode.HALF_EVEN);
    }

    /**
     * Donne la difference en pourcentage entre f1 et f2<br>
     * Renvoie toujours un taux positif
     *
     * @param f1
     * @param f2
     * @return
     */
    static public BigDecimal getPercentageBetweenTwoValues(BigDecimal f1, BigDecimal f2) {
        logger.debug("getPercentageBetweenTwoValues {}, {}", f1, f2);
        if (f1 == null || f2 == null)
            return BigDecimal.ZERO;
        BigDecimal f3 = getCleanBigDecimal(f2).divide(getCleanBigDecimal(f1), RoundingMode.HALF_EVEN);
        // taux positif
        if (f3.min(BigDecimal.ONE).equals(BigDecimal.ONE))
            return f3.subtract(BigDecimal.ONE).multiply(getCleanBigDecimal(100));
        return getCleanBigDecimal(100).subtract(f3.multiply(getCleanBigDecimal(100)));
    }

    /**
     * Add percentage to the value
     *
     * @param value
     * @param percentage
     * @return
     */
    static public BigDecimal addPercentage(BigDecimal value, BigDecimal percentage) {
        logger.debug("addPercentage {}, {}", value, percentage);
        if (value == null || percentage == null)
            return BigDecimal.ZERO;
        if (percentage.doubleValue() >= 0)
            return getCleanBigDecimal(value).multiply(BigDecimal.ONE.add(getCleanBigDecimal(percentage).divide(getCleanBigDecimal(100), RoundingMode.HALF_EVEN)));
        return getCleanBigDecimal(value).multiply(BigDecimal.ONE.add(getCleanBigDecimal(percentage).divide(getCleanBigDecimal(100), RoundingMode.HALF_EVEN)));
    }

    /**
     * Formula: 100 - (100 / (1 + (AvgProfit / AvgLost)))
     * Avg calculate from mme
     * http://www.boursorama.com/q-comment-calculer-le-rsi/2467
     * http://en.wikipedia.org/wiki/Relative_strength_index
     * http://www.macroption.com/rsi-calculation/
     *
     * @param stocks
     * @param period
     * @return
     */
    public static BigDecimal rsi(List<Stock> stocks, int period) {
        stocks.sort(sort);
        // clone
        stocks = stocks.stream().collect(Collectors.toList());
        List<BigDecimal> profits = new ArrayList<>();
        List<BigDecimal> losts = new ArrayList<>();

        Stock last = stocks.remove(0);
        for (Stock stock : stocks) {
            int bigger = stock.getClose().compareTo(last.getClose());
            if (bigger == 1) {
                profits.add(stock.getClose().subtract(last.getClose()));
                losts.add(BigDecimal.ZERO);
            } else if (bigger == -1) {
                losts.add(stock.getClose().subtract(last.getClose()).abs());
                profits.add(BigDecimal.ZERO);
            } else {
                losts.add(BigDecimal.ZERO);
                profits.add(BigDecimal.ZERO);
            }
            last = stock;
        }
        BigDecimal avgProfit = wilderAverageBigDecimal(profits, period);
        BigDecimal avgLost = wilderAverageBigDecimal(losts, period);
        if (avgLost.compareTo(BigDecimal.ZERO) == 0) {
            logger.warn("AvgLost eq 0, fail, return ZERO");
            return BigDecimal.ZERO;
        }
        BigDecimal rs = avgProfit.divide(avgLost, RoundingMode.HALF_EVEN);

        BigDecimal rsi = getCleanBigDecimal(100).subtract(getCleanBigDecimal(100).divide(getCleanBigDecimal(1).add(rs), RoundingMode.HALF_EVEN));
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
        return getCleanBigDecimal(average.getAsDouble());
    }

    private static BigDecimal exponentialAverageBigDecimal(List<BigDecimal> stocks, int period) {
        List<BigDecimal> limitedStock = stocks.stream().limit(period).collect(Collectors.toList());
        BigDecimal avg = arithmeticAverageBigDecimal(limitedStock, period);

        // init mme
        for (int i = 0; i < period; i++)
            stocks.remove(0);

        BigDecimal alpha = getCleanBigDecimal(2).divide(getCleanBigDecimal(period + 1), RoundingMode.HALF_EVEN);

        // Mme(j) = (1-alpha) x MME(j-1) + alpha x Z
        // Z = value
        // alpha = 2 / (period + 1)

        for (BigDecimal bigDecimal : stocks) {
            BigDecimal tmp = BigDecimal.ONE.subtract(alpha);
            BigDecimal tmp2 = tmp.multiply(avg);
            avg = tmp2.add(alpha.multiply(bigDecimal));
        }
        logger.debug("Mme={}", avg);
        return avg;

    }

    private static BigDecimal wilderAverageBigDecimal(List<BigDecimal> stocks, int period) {
        List<BigDecimal> limitedStock = stocks.stream().limit(period).collect(Collectors.toList());
        BigDecimal avg = arithmeticAverageBigDecimal(limitedStock, period);

        // init mme
        for (int i = 0; i < period; i++)
            stocks.remove(0);

        BigDecimal alpha = getCleanBigDecimal(1).divide(getCleanBigDecimal(period), RoundingMode.HALF_EVEN);

        // Mme(j) = (1-alpha) x MME(j-1) + alpha x Z
        // Z = value
        // alpha = 2 / (period + 1)

        for (BigDecimal bigDecimal : stocks) {
            BigDecimal tmp = BigDecimal.ONE.subtract(alpha);
            BigDecimal tmp2 = tmp.multiply(avg);
            avg = tmp2.add(alpha.multiply(bigDecimal));
        }
        logger.debug("Mme={}", avg);
        return avg;

    }


    public static BigDecimal exponentialAverage(List<Stock> stocks, int period) {
        // Reverse sort
        stocks.sort(sort);
        List<BigDecimal> lists = stocks.stream().map(stock -> stock.getClose()).collect(Collectors.toList());
        return exponentialAverageBigDecimal(lists, period);
    }

    public static Optional<DynamicRsi> dynamicRsi(List<Stock> stocks, int rsiPeriod, int avgPeriod) {
        stocks.sort(reverseSort);

        List<Stock> lastStocks = stocks.stream().limit(avgPeriod).collect(Collectors.toList());
        lastStocks.sort(sort);

        stocks.sort(sort);
        if (stocks.size() - avgPeriod < 0)
            return Optional.empty();
        final List<Stock> workingStocks = stocks.stream().limit(stocks.size() - avgPeriod).collect(Collectors.toList());

        List<BigDecimal> rsis = new ArrayList<>();
        lastStocks.forEach(last -> {
            workingStocks.add(last);
            rsis.add(rsi(workingStocks, rsiPeriod));
        });

        DynamicRsi dynamicRsi = new DynamicRsi();
        dynamicRsi.setRsi(rsis.get(rsis.size() - 1));

        BigDecimal average = arithmeticAverageBigDecimal(rsis, avgPeriod);

        // variance simple
        double variance = rsis.stream().mapToDouble(rsi -> Math.pow(rsi.subtract(getCleanBigDecimal(average.doubleValue())).doubleValue(), 2)).sum();
        logger.debug("Sum={}", variance);
        variance /= avgPeriod;
        logger.debug("Variance={}", variance);

        BigDecimal std = getCleanBigDecimal(Math.sqrt(variance));
        logger.debug("Std={}", std);

        dynamicRsi.setStd(std);
        dynamicRsi.setStdHigh(average.add(std));
        dynamicRsi.setStdLow(average.subtract(std));
        return Optional.of(dynamicRsi);
    }
}
