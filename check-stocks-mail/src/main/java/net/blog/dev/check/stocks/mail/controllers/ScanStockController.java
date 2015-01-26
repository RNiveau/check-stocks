package net.blog.dev.check.stocks.mail.controllers;

import net.blog.dev.check.stocks.mail.rules.domain.RuleResult;
import net.blog.dev.check.stocks.mail.services.api.IMailService;
import net.blog.dev.check.stocks.mail.services.api.IScanStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;

/**
 * Created by Xebia on 02/01/15.
 */
public class ScanStockController {

    private static Logger logger = LoggerFactory.getLogger(ScanStockController.class);

    private IScanStockService scanStockService;

    private IMailService mailService;

    @Inject
    public ScanStockController(IScanStockService scanStockService, IMailService mailService) {
        this.scanStockService = scanStockService;
        this.mailService = mailService;
    }

    public void execute() {
        int dayOfWeek = LocalDate.now().get(ChronoField.DAY_OF_WEEK);
        if (dayOfWeek > 5) {
            logger.info("DayOfWeek {} > 5, doesn't scan stock", dayOfWeek);
            return;
        }
        List<RuleResult> scan = scanStockService.scan();
        mailService.sendMail(scan);
    }

}
