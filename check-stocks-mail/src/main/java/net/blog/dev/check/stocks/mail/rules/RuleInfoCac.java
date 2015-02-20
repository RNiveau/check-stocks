package net.blog.dev.check.stocks.mail.rules;

import net.blog.dev.check.stocks.domain.CompleteStock;
import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.mail.rules.domain.InfoCacStock;
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
public class RuleInfoCac extends AbstractRule {

    @Override
    public String getName() {
        return "Info CAC 40";
    }

    @Override
    public Optional<RuleStock> isEligible(List<Stock> stockList, CompleteStock lastStock) {
        RuleStock ruleStock = null;
        if (lastStock != null && "^FCHI".equals(lastStock.getCode())) {
            if (CollectionUtils.isNotEmpty(stockList)) {
                RuleMobileAvg20 ruleMobileAvg20 = new RuleMobileAvg20();

                stockList.sort(CalculUtils.reverseSort);
                List<BigDecimal> lastPrice = new ArrayList<>();

                stockList.stream().forEach(stock -> {
                    lastPrice.add(stock.getClose());
                });

                BigDecimal avg = CalculUtils.arithmeticAverageBigDecimal(lastPrice, 20);
                ruleStock = new InfoCacStock();
                ruleStock.setVariation(lastStock.getLastVariation());
                ruleStock.setCode(lastStock.getCode());
                ruleStock.setName(lastStock.getName());
                ruleStock.setPrice(lastStock.getClose());
                ruleStock.setVariation(lastStock.getLastVariation());
                ((InfoCacStock) ruleStock).setAvg20(avg);
                ((InfoCacStock)ruleStock).setDistanceWithAvg20(CalculUtils.getPercentageBetweenTwoValues(lastStock.getClose(), avg));
                ((InfoCacStock) ruleStock).setEligibleMA20(ruleMobileAvg20.isEligible(stockList, lastStock).isPresent());
                setLastStock(ruleStock, lastStock);
            }
        }
        return Optional.ofNullable(ruleStock);
    }
}
