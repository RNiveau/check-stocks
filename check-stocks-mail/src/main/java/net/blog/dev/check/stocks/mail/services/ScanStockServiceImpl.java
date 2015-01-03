package net.blog.dev.check.stocks.mail.services;

import net.blog.dev.check.stocks.mail.services.api.IMailService;
import net.blog.dev.check.stocks.mail.services.api.IScanStockService;

import javax.inject.Inject;

/**
 * Created by Xebia on 02/01/15.
 */
public class ScanStockServiceImpl implements IScanStockService {

    private IMailService mailServiceImpl;

    @Inject
    public ScanStockServiceImpl(IMailService mailServiceImpl) {
        this.mailServiceImpl = mailServiceImpl;
    }

    public void scan() {
        mailServiceImpl.sendMail("test");
    }

}
