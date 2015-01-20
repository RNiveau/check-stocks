package net.blog.dev.check.stocks.mail.services.api;

import net.blog.dev.check.stocks.mail.rules.domain.RuleResult;

import java.util.List;

/**
 * Created by Xebia on 02/01/15.
 */
public interface IMailService {

    public void sendMail(List<RuleResult> resultList);
}
