package net.blog.dev.check.stocks.mail.modules;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

@Module(library = true)
public class PropertiesModule {

    @Provides
    @Named("stocks.codes")
    public String providesStocksCode() {
        return "ACA,CAP,ALU";
    }

}