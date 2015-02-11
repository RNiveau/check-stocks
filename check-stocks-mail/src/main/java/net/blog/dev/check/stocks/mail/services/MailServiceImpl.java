package net.blog.dev.check.stocks.mail.services;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import net.blog.dev.check.stocks.mail.rules.domain.RuleResult;
import net.blog.dev.check.stocks.mail.services.api.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Xebia on 02/01/15.
 */
public class MailServiceImpl implements IMailService {

    private static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    public void sendMail(List<RuleResult> resultList) {
        logger.debug("Send mail with {} result", resultList.size());
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
                        .map(stock -> stock.toHtml())
                        .reduce((acc, item) -> acc + item).orElse("No result")
        ).reduce((acc, item) -> acc + item).orElse("No result");
        return html;
    }
}
