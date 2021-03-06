package net.blog.dev.services.api;

import net.blog.dev.services.beans.AlphaAvantageCryptoWrapper;
import net.blog.dev.services.beans.AlphaAvantageWrapper;

import java.util.Optional;

public interface IAlphaAvantageService {
    Optional<AlphaAvantageWrapper> getHistoric(String code);

    Optional<AlphaAvantageCryptoWrapper> getCryptoHistoric(String code);
}
