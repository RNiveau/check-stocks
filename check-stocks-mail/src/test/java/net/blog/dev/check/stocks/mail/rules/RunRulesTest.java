package net.blog.dev.check.stocks.mail.rules;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by romainn on 06/11/2014.
 */
@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "json:target/cucumber-rules.json"}, features = {"check-stocks-mail/src/test/resources/net/blog/dev/check/stocks/mail/rules/RuleMM20.feature", "check-stocks-mail/src/test/resources/net/blog/dev/check/stocks/mail/rules/RuleDynamicRsi.feature"})
public class RunRulesTest {
}
