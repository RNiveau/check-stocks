package net.blog.dev.check.stocks.mail.modules;

import dagger.Module;
import dagger.Provides;
import net.blog.dev.check.stocks.mail.controllers.ScanStockController;
import net.blog.dev.check.stocks.mail.services.MailServiceImpl;
import net.blog.dev.check.stocks.mail.services.ScanStockServiceImpl;
import net.blog.dev.check.stocks.mail.services.api.IMailService;
import net.blog.dev.check.stocks.mail.services.api.IScanStockService;

import javax.inject.Singleton;

/**
 * Created by Xebia on 02/01/15.
 */
@Module(injects = {
        ScanStockController.class
})
public class MailModule {

    @Provides
    @Singleton
    public IMailService provideMailService() {
        return new MailServiceImpl();
    }

    @Provides
    @Singleton
    public IScanStockService provideScanStockService(IMailService mailService) {
        return new ScanStockServiceImpl(mailService);
    }
}
