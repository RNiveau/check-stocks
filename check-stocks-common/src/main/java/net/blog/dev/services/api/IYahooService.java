package net.blog.dev.services.api;

import net.blog.dev.services.domain.historic.YahooResponse;
import net.blog.dev.services.domain.quote.Quote;

import java.util.Optional;

/**
 * Created by Xebia on 04/01/15.
 */
public interface IYahooService {

    /**
     * Get historic (j-1)
     * @param code
     * @param duration
     * @return
     */
    Optional<YahooResponse> getHistoric(String code, Integer duration);

    Optional<Quote> getQuote(String code);

}
