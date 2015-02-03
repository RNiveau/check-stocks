package net.blog.dev.check.stocks.mail.rules;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.blog.dev.check.stocks.domain.CompleteStock;
import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.mail.rules.api.IRule;
import net.blog.dev.check.stocks.mail.rules.domain.RuleStock;
import org.junit.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by romainn on 29/01/2015.
 */
public class Rules {

    private List<Stock> stocks;

    private CompleteStock lastStock;

    private IRule rule;

    private Optional<RuleStock> result;

    @Before
    public void init() {
        stocks = new ArrayList<>();
        lastStock = new CompleteStock();
        for (int i = 0; i < 200; i++) {
            Stock stock = new Stock();
            stock.setDate(LocalDate.ofEpochDay((i + 1) * 90000));
            stocks.add(stock);
        }
    }

    @Given("a generated stock list with a mobile avg (\\d+)$")
    public void givenGeneratedList(int avg) {
        stocks.stream().forEach(stock -> stock.setClose(new BigDecimal(avg)));
    }

    @Given("a high mobile avg (\\d+)$")
    public void givenHighAvg(int avg) {
        stocks.stream().forEach(stock -> stock.setHigh(new BigDecimal(avg)));
    }

    @Given("a low mobile avg (\\d+)$")
    public void givenLowAvg(int avg) {
        stocks.stream().forEach(stock -> stock.setLow(new BigDecimal(avg)));
    }

    @Given("a volume for last stock of (\\d+)$")
    public void givenVolume(int volume) {
        lastStock.setVolume(new BigDecimal(volume));
    }

    @Given("a last stock price (\\d+)$")
    public void givenLastStock(int price) {
        lastStock.setClose(new BigDecimal(price));
    }

    @When("I run eligibility ([A-Z0-9_-]+)$")
    public void when(String eligibility) {
        if ("MM20".equals(eligibility))
            rule = new RuleMobileAvg20();
        else if ("DRSI".equals(eligibility))
            rule = new RuleMobileDynamicRsi(5d);
        result = rule.isEligible(stocks, lastStock);
    }

    @Then("The stock is not eligible")
    public void thenNotEligible() {
        Assert.assertFalse(result.isPresent());
    }

    @Then("The stock is eligible")
    public void thenEligible() {
        Assert.assertTrue(result.isPresent());
    }
}
