package net.blog.dev.check.stocks.mail.services;

import net.blog.dev.check.stocks.domain.CompleteStock;
import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.mail.rules.api.IRule;
import net.blog.dev.check.stocks.mail.rules.domain.RuleResult;
import net.blog.dev.check.stocks.mail.rules.domain.RuleStock;
import net.blog.dev.check.stocks.mail.services.api.IScanStockService;
import net.blog.dev.check.stocks.mappers.api.IStockMapper;
import net.blog.dev.services.api.IYahooService;
import net.blog.dev.services.domain.historic.HistoricQuote;
import net.blog.dev.services.domain.historic.YahooResponse;
import net.blog.dev.services.domain.quote.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

/**
 * Created by Xebia on 02/01/15.
 */
public class ScanStockServiceImpl implements IScanStockService {

    private static Logger logger = LoggerFactory.getLogger(IScanStockService.class);

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
            logger.debug("Scan code {}", code);
            yahooService.getHistoric(code, 12).ifPresent(yahooResponse -> {
                Optional<Quote> quote = addTodayInHistoric(code, yahooResponse);
                List<Stock> stocks = stockMapper.mappeYahooToStock(yahooResponse);
                CompleteStock completeStock = stockMapper.mappeQuoteToStock(quote.orElse(null));
                rules.forEach(rule -> {
                    Optional<RuleStock> eligible = rule.isEligible(stocks, completeStock);
                    eligible.ifPresent(stock -> {
                        logger.debug("{} is eligible", stock.getCode());
                        Optional<RuleResult> matchingRule = ruleResults.stream().filter(r -> r.getName().equals(rule.getName())).findFirst();
                        RuleResult ruleResult = matchingRule.orElseGet(() -> createRuleResult(ruleResults, rule));
                        ruleResult.addStock(stock);
                    });
                });
            });
        });
        return ruleResults;
    }

    private Optional<Quote> addTodayInHistoric(String code, YahooResponse yahooResponse) {
        Optional<Quote> lastQuote = yahooService.getQuote(code);
        lastQuote.ifPresent(quote -> {
            Float close = quote.getAsk() != null ? quote.getAsk() : quote.getLastTradePriceOnly();
            HistoricQuote historicQuote = new HistoricQuote();
            historicQuote.setAdj_Close(close);
            historicQuote.setClose(close);
            historicQuote.setDate(new Date());
            historicQuote.setHigh(quote.getDaysHigh());
            historicQuote.setLow(quote.getDaysLow());
            historicQuote.setOpen(quote.getOpen());
            historicQuote.setVolume(quote.getVolume());
            yahooResponse.getQuery().getResults().getQuote().add(0, historicQuote);
        });
        return lastQuote;
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
