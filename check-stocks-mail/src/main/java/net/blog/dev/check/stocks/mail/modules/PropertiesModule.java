package net.blog.dev.check.stocks.mail.modules;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import java.util.Properties;

@Module(library = true)
public class PropertiesModule {
    public final Properties props;

    PropertiesModule(Properties props) {
        this.props = props;
    }

    @Provides
    @Named("stocks.codes")
    public String providesStocksCode() {
        return "ACA,CAP";
    }

}