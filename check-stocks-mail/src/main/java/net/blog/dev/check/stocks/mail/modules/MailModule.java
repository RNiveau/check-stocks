package net.blog.dev.check.stocks.mail.modules;

import dagger.Module;
import dagger.Provides;
import net.blog.dev.check.stocks.mail.controllers.ScanStockController;
import net.blog.dev.check.stocks.mail.services.MailServiceImpl;
import net.blog.dev.check.stocks.mail.services.ScanStockServiceImpl;
import net.blog.dev.check.stocks.mail.services.api.IMailService;
import net.blog.dev.check.stocks.mail.services.api.IScanStockService;
import net.blog.dev.services.YahooServiceImpl;
import net.blog.dev.services.api.IYahooService;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by Xebia on 02/01/15.
 */
@Module(injects =
        ScanStockController.class,
        includes = PropertiesModule.class

)
public class MailModule {

    @Provides
    @Singleton
    public IMailService provideMailService() {
        return new MailServiceImpl();
    }

    @Provides
    @Singleton
    public IYahooService provideYahooService() {
        return new YahooServiceImpl();
    }

    @Provides
    @Singleton
    public IScanStockService provideScanStockService(@Named("stocks.codes") String codes, IYahooService yahooService) {
        return new ScanStockServiceImpl(codes, yahooService);
    }

}
