package net.blog.dev.check.stocks.mail;

import dagger.ObjectGraph;
import net.blog.dev.check.stocks.mail.controllers.ScanStockController;
import net.blog.dev.check.stocks.mail.modules.MailModule;

/**
 * Created by Xebia on 30/12/14.
 */
public class Main {

    public static void main(String[] args)  {

        ObjectGraph objectGraph = ObjectGraph.create(MailModule.class);
        ScanStockController scanStockController = objectGraph.get(ScanStockController.class);
        scanStockController.execute();
    }


}
