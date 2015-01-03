package net.blog.dev.check.stocks.mail.services;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import net.blog.dev.check.stocks.mail.services.api.IMailService;

/**
 * Created by Xebia on 02/01/15.
 */
public class MailServiceImpl implements IMailService {

    public void sendMail(String text) {
        SendGrid sendgrid = new SendGrid(System.getenv("SENDGRID_USERNAME"), System.getenv("SENDGRID_PASSWORD"));

        SendGrid.Email email = new SendGrid.Email();
        email.addTo(System.getenv("TEST_MAIL"));
        email.addSmtpApiTo(System.getenv("TEST_MAIL"));
        email.setFrom("admin@check-stock.fr");
        email.setSubject("Test");
        email.setText(text);
        email.setHtml(text);

        try {
            sendgrid.send(email);
        } catch (SendGridException e) {
            e.printStackTrace();
        }
    }


}
