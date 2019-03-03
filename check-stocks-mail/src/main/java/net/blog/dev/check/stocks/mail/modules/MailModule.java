package net.blog.dev.check.stocks.mail.modules;

import dagger.Module;
import dagger.Provides;
import net.blog.dev.check.stocks.mail.controllers.ScanStockController;
import net.blog.dev.check.stocks.mail.rules.RuleCheckStock;
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
import net.blog.dev.services.BoursoramaServiceImpl;
import net.blog.dev.services.api.IAlphaAvantageService;
import net.blog.dev.services.api.IBoursoramaService;

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
    public IBoursoramaService provideApiService() {
        return new BoursoramaServiceImpl();
    }

    @Provides
    @Singleton
    public IStockMapper provideStockMapper() {
        return new StockMapperImpl();
    }

    @Provides
    @Singleton
    public IScanStockService provideScanStockService(@Named("stocks.codes") String codes, @Named("stocks.codesCrypto") String codesCrypto, List<IRule> rules, IBoursoramaService apiService, IStockMapper stockMapper) {
        return new ScanStockServiceImpl(codes, codesCrypto, rules, apiService, stockMapper);
    }

    @Provides
    @Singleton
    public List<IRule> provideRules(@Named("stocks.dynamic.rsi.tolerance") Double dynamicRsiTolerance, @Named("stocks.rule.check.code") String codes) {
        List<IRule> rules = new ArrayList<>();
        rules.add(new RuleInfoCac());
        rules.add(new RuleMobileAvg20());
        rules.add(new RuleMobileDynamicRsi(dynamicRsiTolerance));
        rules.add(new RuleCheckStock(codes.split(",")));
        return rules;
    }
}
