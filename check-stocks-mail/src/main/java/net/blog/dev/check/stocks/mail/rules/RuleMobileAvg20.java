package net.blog.dev.check.stocks.mail.rules;

import net.blog.dev.check.stocks.domain.CompleteStock;
import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.mail.rules.domain.RuleStock;
import net.blog.dev.check.stocks.utils.CalculUtils;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by romainn on 05/01/2015.
 */
public class RuleMobileAvg20 extends AbstractRule {

    @Override
    public String getName() {
        return "Moyenne 20";
    }

    @Override
    public Optional<RuleStock> isEligible(List<Stock> stockList, CompleteStock lastStock) {
        Optional<RuleStock> eligible = super.isEligible(stockList, lastStock);
        RuleStock ruleStock = null;
        if (eligible.isPresent()) {
            if (CollectionUtils.isNotEmpty(stockList)) {
                stockList.sort(CalculUtils.reverseSort);
                BigDecimal lastPrice = lastStock.getClose();

                List<BigDecimal> lastHigh = new ArrayList<>();
                List<BigDecimal> lastLow = new ArrayList<>();

                stockList.stream().forEach(stock -> {
                    lastHigh.add(stock.getHigh());
                    lastLow.add(stock.getLow());
                });

                BigDecimal avgHigh = CalculUtils.arithmeticAverageBigDecimal(lastHigh, 20);
                BigDecimal avgLow = CalculUtils.arithmeticAverageBigDecimal(lastLow, 20);

                if (lastPrice.doubleValue() < avgHigh.doubleValue() && lastPrice.doubleValue() > avgLow.doubleValue()) {
                    ruleStock = new RuleStock();
                    ruleStock.setCode(lastStock.getCode());
                    ruleStock.setName(lastStock.getName());
                    ruleStock.setPrice(lastPrice);
                    ruleStock.setVariation(lastStock.getLastVariation());
                }
            }
        }
        return Optional.ofNullable(ruleStock);
    }
}
