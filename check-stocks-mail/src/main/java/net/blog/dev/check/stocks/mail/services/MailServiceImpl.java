package net.blog.dev.check.stocks.mail.services;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import net.blog.dev.check.stocks.mail.rules.domain.RuleResult;
import net.blog.dev.check.stocks.mail.rules.domain.RuleStock;
import net.blog.dev.check.stocks.mail.services.api.IMailService;

import java.math.RoundingMode;
import java.util.List;

/**
 * Created by Xebia on 02/01/15.
 */
public class MailServiceImpl implements IMailService {

    public void sendMail(List<RuleResult> resultList) {
        String text = prepareHtml(resultList);
        SendGrid sendgrid = new SendGrid(System.getenv("SENDGRID_USERNAME"), System.getenv("SENDGRID_PASSWORD"));
        SendGrid.Email email = new SendGrid.Email();
        email.addTo(System.getenv("TEST_MAIL"));
        email.addSmtpApiTo(System.getenv("TEST_MAIL"));
        email.setFrom("admin@check-stock.fr");
        email.setSubject("Test");
        email.setHtml(text);

        try {
            sendgrid.send(email);
        } catch (SendGridException e) {
            e.printStackTrace();
        }
    }

    private String prepareHtml(List<RuleResult> resultList) {
        String html = resultList.stream().map(result ->
                "<h3>" + result.getName() + "</h3>"
                        + result.getStocks().stream()
                        .map(stock -> prepareHtmlStock(stock))
                        .reduce((acc, item) -> acc + item).orElse("No result")
        ).reduce((acc, item) -> acc + item).orElse("No result");
        return html;
    }
    
    private String prepareHtmlStock(RuleStock stock) {
        String html = "<p>" + stock.getCode();
        if (stock.getPrice() != null)
            html += ", " + stock.getPrice().setScale(3, RoundingMode.HALF_EVEN).floatValue();
        if (stock.getVariation() != null)
            html += ", " + stock.getVariation().setScale(3, RoundingMode.HALF_EVEN).floatValue();
        html += "</p>";
        return html;
    }
}
