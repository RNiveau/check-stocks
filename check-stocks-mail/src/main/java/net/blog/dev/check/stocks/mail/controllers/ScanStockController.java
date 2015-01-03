package net.blog.dev.check.stocks.mail.controllers;

import net.blog.dev.check.stocks.mail.services.api.IScanStockService;

import javax.inject.Inject;

/**
 * Created by Xebia on 02/01/15.
 */
public class ScanStockController {

   private IScanStockService scanStockService;

    @Inject
    public ScanStockController(IScanStockService scanStockService) {
        this.scanStockService = scanStockService;
    }

    public void execute() {
        scanStockService.scan();
    }

}
