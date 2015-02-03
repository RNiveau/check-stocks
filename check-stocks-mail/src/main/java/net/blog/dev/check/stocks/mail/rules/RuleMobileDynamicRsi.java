package net.blog.dev.check.stocks.mail.rules;

import net.blog.dev.check.stocks.domain.CompleteStock;
import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.domain.indicators.DynamicRsi;
import net.blog.dev.check.stocks.mail.rules.domain.RuleStock;
import net.blog.dev.check.stocks.utils.CalculUtils;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by romainn on 05/01/2015.
 */
public class RuleMobileDynamicRsi extends AbstractRule {

    private Double tolerance;

    public RuleMobileDynamicRsi(Double dynamicRsiTolerance) {
        tolerance = dynamicRsiTolerance;
    }

    @Override
    public String getName() {
        return "RSI Dynamique";
    }

    /**
     * If rsi is in tolerance zone for high or low std, stock is eligible
      * @param stockList
     * @param lastStock
     * @return
     */
    @Override
    public Optional<RuleStock> isEligible(List<Stock> stockList, CompleteStock lastStock) {
        Optional<RuleStock> eligible = super.isEligible(stockList, lastStock);
        RuleStock ruleStock = null;
        if (eligible.isPresent()) {
            if (CollectionUtils.isNotEmpty(stockList)) {

                DynamicRsi dynamicRsi = CalculUtils.dynamicRsi(stockList, 14, 20);
                if (dynamicRsi != null) {
                    BigDecimal rsi = dynamicRsi.getRsi();
                    BigDecimal stdPlusTolerance = CalculUtils.addPercentage(dynamicRsi.getStdHigh(), new BigDecimal(tolerance));
                    BigDecimal stdLessTolerance = CalculUtils.addPercentage(dynamicRsi.getStdHigh(), new BigDecimal(-tolerance));
                    if (rsi.doubleValue() < stdPlusTolerance.doubleValue() && rsi.doubleValue() > stdLessTolerance.doubleValue()) {
                        ruleStock = new RuleStock();
                        ruleStock.setCode(lastStock.getCode());
                        ruleStock.setName(lastStock.getName());
                        ruleStock.setPrice(lastStock.getClose());
                        ruleStock.setVariation(lastStock.getLastVariation());
                    }
                    stdPlusTolerance = CalculUtils.addPercentage(dynamicRsi.getStdLow(), new BigDecimal(tolerance));
                    stdLessTolerance = CalculUtils.addPercentage(dynamicRsi.getStdLow(), new BigDecimal(-tolerance));
                    if (rsi.doubleValue() < stdPlusTolerance.doubleValue() && rsi.doubleValue() > stdLessTolerance.doubleValue()) {
                        ruleStock = new RuleStock();
                        ruleStock.setCode(lastStock.getCode());
                        ruleStock.setName(lastStock.getName());
                        ruleStock.setPrice(lastStock.getClose());
                        ruleStock.setVariation(lastStock.getLastVariation());
                    }
                }
            }
        }
        return Optional.ofNullable(ruleStock);
    }
}