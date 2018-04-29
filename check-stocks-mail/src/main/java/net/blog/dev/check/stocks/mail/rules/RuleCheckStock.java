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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static net.blog.dev.check.stocks.utils.CalculUtils.getCleanBigDecimal;

/**
 * Created by romainn on 05/01/2015.
 */
public class RuleCheckStock extends AbstractRule {

    private static Logger logger = LoggerFactory.getLogger(RuleCheckStock.class);

    private String[] codes;

    public RuleCheckStock(String[] codes) {
        this.codes = codes;
        logger.debug("RuleMobileDynamicRsi initialized with codes={}", codes);
    }

    @Override
    public String getName() {
        return "Check codes";
    }

    /**
     * @param stockList
     * @param lastStock
     * @return
     */
    @Override
    public Optional<RuleStock> isEligible(List<Stock> stockList, CompleteStock lastStock) {
        if (CollectionUtils.isNotEmpty(stockList)) {
            Optional<String> first = Arrays.stream(this.codes).filter(code -> lastStock.getCode().equals(code)).findFirst();
            if (first.isPresent()) {
                RuleStock ruleStock = new RuleStock();
                ruleStock.setCode(lastStock.getCode());
                ruleStock.setName(lastStock.getName());
                ruleStock.setPrice(lastStock.getClose());
                ruleStock.setVariation(lastStock.getLastVariation());
                return Optional.of(ruleStock);
            }
        }
        return Optional.empty();
    }
}
