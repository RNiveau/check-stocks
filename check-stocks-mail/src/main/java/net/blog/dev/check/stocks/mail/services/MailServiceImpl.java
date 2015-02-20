package net.blog.dev.check.stocks.mail.services;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import net.blog.dev.check.stocks.mail.rules.domain.RuleResult;
import net.blog.dev.check.stocks.mail.services.api.IMailService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by Xebia on 02/01/15.
 */
public class MailServiceImpl implements IMailService {

    private static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    public void sendMail(List<RuleResult> resultList) {
        logger.debug("Send mail with {} result", resultList.size());
        String text = prepareStockHtml(resultList);



        SendGrid sendgrid = new SendGrid(System.getenv("SENDGRID_USERNAME"), System.getenv("SENDGRID_PASSWORD"));
        SendGrid.Email email = new SendGrid.Email();
        email.addTo(System.getenv("TEST_MAIL"));
        email.addSmtpApiTo(System.getenv("TEST_MAIL"));
        email.setFrom("admin@check-stock.fr");
        email.setSubject("Test");
        email.setHtml(createHtml(text));

        try {
            sendgrid.send(email);
        } catch (SendGridException e) {
            logger.error("Error when sending email: {}", e);
        }
    }

    private String prepareStockHtml(List<RuleResult> resultList) {
        resultList.stream()
                .forEach(result -> result.getStocks()
                        .sort((o1, o2) -> o1.getVariation().compareTo(o2.getVariation())));
        String html = resultList.stream().map(result ->
                "<h3>" + result.getName() + "</h3>"
                        + result.getStocks().stream()
                        .map(stock -> stock.toHtml())
                        .reduce((acc, item) -> acc + item).orElse("No result")
        ).reduce((acc, item) -> acc + item).orElse("No result");
        return html;
    }

    private String createHtml(String stockHtml) {
        try {
            String template = new String(IOUtils.toByteArray(ClassLoader.getSystemResource("template/template.html")));
            return template.replaceAll("###CONTENT###", stockHtml);
        } catch (IOException e) {
            logger.error("Error during html preparation: {}", e);
        }
        return stockHtml;
    }
}
