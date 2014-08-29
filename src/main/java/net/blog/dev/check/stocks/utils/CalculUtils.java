package net.blog.dev.check.stocks.utils;

import net.blog.dev.check.stocks.dto.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by romainn on 28/08/2014.
 */
public class CalculUtils {

    private static Logger logger = LoggerFactory.getLogger(CalculUtils.class);

    /**
     * Formula: 100 - (100 / (1 + (AvgProfit / AvgLost)))
     *
     * @param stocks
     * @param period
     * @return
     */
    public static BigDecimal rsi(List<Stock> stocks, int period) {
        stocks.sort((s1, s2) -> {
            if (s1.getDate().equals(s2.getDate()))
                return 0;
            return s1.getDate().isBefore(s2.getDate()) ? -1 : 1;
        });
        List<Stock> limitedStock = stocks.stream().limit(period + 1).collect(Collectors.toList());

        Stock last = limitedStock.remove(0);
        BigDecimal avgLost = BigDecimal.ZERO.setScale(3, RoundingMode.HALF_EVEN);
        BigDecimal avgProfit = BigDecimal.ZERO.setScale(3, RoundingMode.HALF_EVEN);
        for (Stock stock : limitedStock) {
            BigDecimal difference = stock.getClose().subtract(last.getClose());
            logger.debug("Difference between {} and {}: {}", last.getClose(), stock.getClose(), difference);
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

        if (avgLost.equals(BigDecimal.ZERO))
            return new BigDecimal(100);
        else if (avgProfit.equals(BigDecimal.ZERO))
            return new BigDecimal(0);


        BigDecimal rsi = new BigDecimal(100).setScale(3, RoundingMode.HALF_EVEN);
        BigDecimal tmp = new BigDecimal(100).setScale(3, RoundingMode.HALF_EVEN).divide(
                new BigDecimal(1).add(
                        avgProfit.divide(avgLost, RoundingMode.HALF_EVEN)), RoundingMode.HALF_EVEN);
        return rsi.subtract(tmp);
    }
}
