package net.blog.dev.check.stocks.mail;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

/**
 * Created by Xebia on 30/12/14.
 */
public class Main {

    public static void main(String[] args) throws SendGridException {
        SendGrid sendgrid = new SendGrid(System.getenv("SENDGRID_USERNAME"), System.getenv("SENDGRID_PASSWORD"));

        SendGrid.Email email = new SendGrid.Email();
        email.addTo(System.getenv("TEST_MAIL"));
        email.setFrom("other@example.com");
        email.setSubject("Hello World");
        email.setHtml("<h1>My first email through SendGrid");

        sendgrid.send(email);
    }


}
