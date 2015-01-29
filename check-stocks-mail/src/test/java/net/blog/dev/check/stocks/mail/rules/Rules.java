package net.blog.dev.check.stocks.mail.rules;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import net.blog.dev.check.stocks.domain.CompleteStock;
import net.blog.dev.check.stocks.domain.Stock;
import net.blog.dev.check.stocks.mail.rules.api.IRule;
import net.blog.dev.check.stocks.mail.rules.domain.RuleStock;

import java.math.BigDecimal;
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
        for (int i = 0; i < 20; i++) {
            stocks.add(new Stock());
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

    @Given("a last stock price (\\d+)$")
    public void givenLastStock(int price) {
        lastStock.setLastVariation(new BigDecimal(price));
    }

    @When("When I run eligibility ([A-Z0-9_-]+)$")
    public void when(String eligibility) {
        if ("MM20".equals(eligibility))
            rule = new RuleMobileAvg20();
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
