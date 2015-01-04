package net.blog.dev.check.stocks.mail.services;

import net.blog.dev.check.stocks.mail.services.api.IScanStockService;
import net.blog.dev.services.api.IYahooService;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Xebia on 02/01/15.
 */
public class ScanStockServiceImpl implements IScanStockService {

    private IYahooService yahooService;

    private String codes;

    @Inject
    public ScanStockServiceImpl(@Named("stocks.codes") String codes, IYahooService yahooService) {
        this.yahooService = yahooService;
        this.codes = codes;
    }

    public void scan() {
    }

}
