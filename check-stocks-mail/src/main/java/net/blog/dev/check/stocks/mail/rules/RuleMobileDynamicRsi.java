package net.blog.dev.check.stocks.mail.rules;

import net.blog.dev.check.stocks.domain.CompleteStock;
import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.domain.indicators.DynamicRsi;
import net.blog.dev.check.stocks.mail.rules.domain.RuleStock;
import net.blog.dev.check.stocks.utils.CalculUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static net.blog.dev.check.stocks.utils.CalculUtils.getCleanBigDecimal;

/**
 * Created by romainn on 05/01/2015.
 */
public class RuleMobileDynamicRsi extends AbstractRule {

    private static Logger logger = LoggerFactory.getLogger(RuleMobileDynamicRsi.class);

    private Double tolerance;

    @Inject
    public RuleMobileDynamicRsi(@Named("stocks.dynamic.rsi.tolerance") Double dynamicRsiTolerance) {
        tolerance = dynamicRsiTolerance;
        logger.debug("RuleMobileDynamicRsi initialized with tolerance={}", tolerance);
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
                    BigDecimal stdPlusTolerance = CalculUtils.addPercentage(dynamicRsi.getStdHigh(), getCleanBigDecimal(tolerance));
                    BigDecimal stdLessTolerance = CalculUtils.addPercentage(dynamicRsi.getStdHigh(), getCleanBigDecimal(-tolerance));
                    if (rsi.doubleValue() < stdPlusTolerance.doubleValue() && rsi.doubleValue() > stdLessTolerance.doubleValue()) {
                        ruleStock = new RuleStock();
                        ruleStock.setCode(lastStock.getCode());
                        ruleStock.setName(lastStock.getName());
                        ruleStock.setPrice(lastStock.getClose());
                        ruleStock.setVariation(lastStock.getLastVariation());
                    }
                    stdPlusTolerance = CalculUtils.addPercentage(dynamicRsi.getStdLow(), getCleanBigDecimal(tolerance));
                    stdLessTolerance = CalculUtils.addPercentage(dynamicRsi.getStdLow(), getCleanBigDecimal(-tolerance));
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
