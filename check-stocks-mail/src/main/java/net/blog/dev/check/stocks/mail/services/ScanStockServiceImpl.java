package net.blog.dev.check.stocks.mail.services;

import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.mail.rules.api.IRule;
import net.blog.dev.check.stocks.mail.rules.domain.RuleResult;
import net.blog.dev.check.stocks.mail.rules.domain.RuleStock;
import net.blog.dev.check.stocks.mail.services.api.IScanStockService;
import net.blog.dev.check.stocks.mappers.api.IStockMapper;
import net.blog.dev.services.api.IYahooService;
import net.blog.dev.services.domain.historic.YahooResponse;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Xebia on 02/01/15.
 */
public class ScanStockServiceImpl implements IScanStockService {

    private IYahooService yahooService;

    private String codes;

    private List<IRule> rules;

    private IStockMapper stockMapper;

    @Inject
    public ScanStockServiceImpl(@Named("stocks.codes") String codes, List<IRule> rules, IYahooService yahooService, IStockMapper stockMapper) {
        this.yahooService = yahooService;
        this.codes = codes;
        this.rules = rules;
        this.stockMapper = stockMapper;
    }

    public List<RuleResult> scan() {
        final List<RuleResult> ruleResults = new ArrayList<>();
        getListCode().stream().forEach((code) -> {
            YahooResponse yahooResponse = yahooService.getHistoric(code, 12);
            List<Stock> stocks = stockMapper.mappeYahooToStock(yahooResponse);
            rules.forEach(rule -> {
                Optional<RuleStock> eligible = rule.isEligible(stocks, code);
                eligible.ifPresent(stock -> {
                    Optional<RuleResult> matchingRule = ruleResults.stream().filter(r -> r.getName().equals(rule.getName())).findFirst();
                    RuleResult ruleResult = matchingRule.orElseGet(() -> createRuleResult(ruleResults, rule));
                    ruleResult.addStock(stock);
                });
            });
        });
        return ruleResults;
    }

    private RuleResult createRuleResult(List<RuleResult> ruleResults, IRule rule) {
        RuleResult newRuleResult = new RuleResult();
        newRuleResult.setName(rule.getName());
        ruleResults.add(newRuleResult);
        return newRuleResult;
    }

    private List<String> getListCode() {
        return Arrays.asList(codes.split(","));
    }
}
