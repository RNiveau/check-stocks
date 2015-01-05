package net.blog.dev.check.stocks.mail.controllers;

import net.blog.dev.check.stocks.mail.services.api.IMailService;
import net.blog.dev.check.stocks.mail.services.api.IScanStockService;

import javax.inject.Inject;

/**
 * Created by Xebia on 02/01/15.
 */
public class ScanStockController {

   private IScanStockService scanStockService;

    private IMailService mailService;

    @Inject
    public ScanStockController(IScanStockService scanStockService, IMailService mailService) {
        this.scanStockService = scanStockService;
        this.mailService = mailService;
    }

    public void execute() {
        scanStockService.scan();
    }

}
