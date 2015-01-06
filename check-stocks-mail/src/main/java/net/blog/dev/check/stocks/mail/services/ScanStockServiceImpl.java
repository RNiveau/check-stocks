package net.blog.dev.check.stocks.mail.services;

import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.mail.rules.api.IRule;
import net.blog.dev.check.stocks.mail.services.api.IScanStockService;
import net.blog.dev.check.stocks.mappers.api.IStockMapper;
import net.blog.dev.services.api.IYahooService;
import net.blog.dev.services.domain.YahooResponse;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public void scan() {
        getListCode().stream().forEach((code) -> {
            YahooResponse historic = yahooService.getHistoric(code, 12);
            rules.forEach(rule -> rule.isEligible(new ArrayList<Stock>()));
        });
    }

    private List<String> getListCode() {
        return Arrays.asList(codes.split(","));
    }

}
