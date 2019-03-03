package net.blog.dev.services.api;

import net.blog.dev.check.stocks.domain.CompleteStock;
import net.blog.dev.check.stocks.domain.Stock;

import java.util.List;
import java.util.Optional;

public interface IBoursoramaService {
    Optional<List<Stock>> getHistoric(String code);
}
