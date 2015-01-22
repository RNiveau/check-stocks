package net.blog.dev.check.stocks.mail;

import dagger.ObjectGraph;
import net.blog.dev.check.stocks.mail.controllers.ScanStockController;
import net.blog.dev.check.stocks.mail.modules.MailModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Xebia on 30/12/14.
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args)  {

        logger.info("Mail job starts");

        ObjectGraph objectGraph = ObjectGraph.create(MailModule.class);
        ScanStockController scanStockController = objectGraph.get(ScanStockController.class);
        scanStockController.execute();
    }


}
