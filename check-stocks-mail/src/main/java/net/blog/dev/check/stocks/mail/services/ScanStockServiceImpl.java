package net.blog.dev.check.stocks.mail.services;

import net.blog.dev.check.stocks.domain.CompleteStock;
import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.mail.rules.api.IRule;
import net.blog.dev.check.stocks.mail.rules.domain.RuleResult;
import net.blog.dev.check.stocks.mail.rules.domain.RuleStock;
import net.blog.dev.check.stocks.mail.services.api.IScanStockService;
import net.blog.dev.check.stocks.mappers.api.IStockMapper;
import net.blog.dev.check.stocks.utils.CalculUtils;
import net.blog.dev.services.AlphaAvantageServiceImpl;
import net.blog.dev.services.api.IAlphaAvantageService;
import net.blog.dev.services.beans.AlphaAvantage;
import net.blog.dev.services.beans.AlphaAvantageCryptoWrapper;
import net.blog.dev.services.beans.AlphaAvantageWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Xebia on 02/01/15.
 */
public class ScanStockServiceImpl implements IScanStockService {

    private static Logger logger = LoggerFactory.getLogger(ScanStockServiceImpl.class);

    private IAlphaAvantageService stockService;

    private String codes;

    private String codesCrypto;

    private List<IRule> rules;

    private IStockMapper stockMapper;

    @Inject
    public ScanStockServiceImpl(@Named("stocks.codes") String codes, @Named("stocks.codesCrypto") String codesCrypto, List<IRule> rules, IAlphaAvantageService yahooService, IStockMapper stockMapper) {
        this.stockService = yahooService;
        this.codes = codes;
        this.codesCrypto = codesCrypto;
        this.rules = rules;
        this.stockMapper = stockMapper;
    }

    public List<RuleResult> scan() {
        final List<RuleResult> ruleResults = new ArrayList<>();
        scanCodes(ruleResults);
        scanCodesCrypto(ruleResults);
        return ruleResults;
    }

    private void scanCodes(List<RuleResult> ruleResults) {
        getListCode().stream().forEach((code) -> {
            logger.debug("Scan code {}", code);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
            Optional<AlphaAvantageWrapper> historic = stockService.getHistoric(code);
            historic.ifPresent(response -> {
                List<Stock> stocks = stockMapper.mappeAlphaToStock(response);
                if (stocks.size() < 2)
                    return;
                Stock lastQuote = stocks.get(0);
                CompleteStock completeStock = new CompleteStock();
                completeStock.setCode(code);
                BigDecimal percent = CalculUtils.getPercentageBetweenTwoValues(lastQuote.getClose(), stocks.get(1).getClose());
                if (stocks.get(1).getClose().floatValue() > lastQuote.getClose().floatValue())
                    percent = percent.multiply(new BigDecimal(-1));
                completeStock.setLastVariation(percent);
                completeStock.setName("");
                completeStock.setClose(lastQuote.getClose());
                completeStock.setHigh(lastQuote.getHigh());
                completeStock.setDate(lastQuote.getDate());
                completeStock.setLow(lastQuote.getLow());
                completeStock.setOpen(lastQuote.getOpen());
                completeStock.setVolume(lastQuote.getVolume());
                rules.forEach(rule -> {
                    Optional<RuleStock> eligible = rule.isEligible(stocks, completeStock);
                    eligible.ifPresent(stock -> {
                        logger.debug("{} is eligible to {}", stock.getCode(), rule.getName());
                        Optional<RuleResult> matchingRule = ruleResults.stream().filter(r -> r.getName().equals(rule.getName())).findFirst();
                        RuleResult ruleResult = matchingRule.orElseGet(() -> createRuleResult(ruleResults, rule));
                        ruleResult.addStock(stock);
                    });
                });
            });
            if (!historic.isPresent())
                logger.warn("Failed to get historic for {}", code);
        });
    }

    private void scanCodesCrypto(List<RuleResult> ruleResults) {
        getListCodeCrypto().stream().forEach((code) -> {
            logger.debug("Scan code {}", code);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
            Optional<AlphaAvantageCryptoWrapper> historic = stockService.getCryptoHistoric(code);
            historic.ifPresent(response -> {
                List<Stock> stocks = stockMapper.mappeAlphaToStock(response);
                if (stocks.size() < 2)
                    return;
                Stock lastQuote = stocks.get(0);
                CompleteStock completeStock = new CompleteStock();
                completeStock.setCode(code);
                BigDecimal percent = CalculUtils.getPercentageBetweenTwoValues(lastQuote.getClose(), stocks.get(1).getClose());
                if (stocks.get(1).getClose().floatValue() > lastQuote.getClose().floatValue())
                    percent = percent.multiply(new BigDecimal(-1));
                completeStock.setLastVariation(percent);
                completeStock.setName("");
                completeStock.setClose(lastQuote.getClose());
                completeStock.setHigh(lastQuote.getHigh());
                completeStock.setDate(lastQuote.getDate());
                completeStock.setLow(lastQuote.getLow());
                completeStock.setOpen(lastQuote.getOpen());
                completeStock.setVolume(lastQuote.getVolume());
                rules.forEach(rule -> {
                    Optional<RuleStock> eligible = rule.isEligible(stocks, completeStock);
                    eligible.ifPresent(stock -> {
                        logger.debug("{} is eligible to {}", stock.getCode(), rule.getName());
                        Optional<RuleResult> matchingRule = ruleResults.stream().filter(r -> r.getName().equals(rule.getName())).findFirst();
                        RuleResult ruleResult = matchingRule.orElseGet(() -> createRuleResult(ruleResults, rule));
                        ruleResult.addStock(stock);
                    });
                });
            });
            if (!historic.isPresent())
                logger.warn("Failed to get historic for {}", code);
        });
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

    private List<String> getListCodeCrypto() {
        return Arrays.asList(codesCrypto.split(","));
    }
}
