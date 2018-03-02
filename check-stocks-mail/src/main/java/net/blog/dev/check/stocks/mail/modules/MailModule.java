package net.blog.dev.check.stocks.mail.modules;

import dagger.Module;
import dagger.Provides;
import net.blog.dev.check.stocks.mail.controllers.ScanStockController;
import net.blog.dev.check.stocks.mail.rules.RuleInfoCac;
import net.blog.dev.check.stocks.mail.rules.RuleMobileAvg20;
import net.blog.dev.check.stocks.mail.rules.RuleMobileDynamicRsi;
import net.blog.dev.check.stocks.mail.rules.api.IRule;
import net.blog.dev.check.stocks.mail.services.MailServiceImpl;
import net.blog.dev.check.stocks.mail.services.ScanStockServiceImpl;
import net.blog.dev.check.stocks.mail.services.api.IMailService;
import net.blog.dev.check.stocks.mail.services.api.IScanStockService;
import net.blog.dev.check.stocks.mappers.StockMapperImpl;
import net.blog.dev.check.stocks.mappers.api.IStockMapper;
import net.blog.dev.services.AlphaAvantageServiceImpl;
import net.blog.dev.services.api.IAlphaAvantageService;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

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
    public IAlphaAvantageService provideAlphaAvantageService() {
        return new AlphaAvantageServiceImpl();
    }

    @Provides
    @Singleton
    public IStockMapper provideStockMapper() {
        return new StockMapperImpl();
    }

    @Provides
    @Singleton
    public IScanStockService provideScanStockService(@Named("stocks.codes") String codes, List<IRule> rules, IAlphaAvantageService alphaAvantageService,  IStockMapper stockMapper) {
        return new ScanStockServiceImpl(codes, rules, alphaAvantageService, stockMapper);
    }

    @Provides
    @Singleton
    public List<IRule> provideRules(@Named("stocks.dynamic.rsi.tolerance") Double dynamicRsiTolerance) {
        List<IRule> rules = new ArrayList<>();
        rules.add(new RuleInfoCac());
        rules.add(new RuleMobileAvg20());
        rules.add(new RuleMobileDynamicRsi(dynamicRsiTolerance));
        return rules;
    }
}
