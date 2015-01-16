package net.blog.dev.services.api;

import net.blog.dev.services.domain.historic.YahooResponse;

/**
 * Created by Xebia on 04/01/15.
 */
public interface IYahooService {

    YahooResponse getHistoric(String code, Integer duration);

}
