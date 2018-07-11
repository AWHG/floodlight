package net.floodlightcontroller.notification.syslog;

import benbi.util.DatabaseUtil;
import benbi.util.HttpRequesterTest;
import org.slf4j.Logger;

import net.floodlightcontroller.notification.INotificationManager;

public class SyslogNotificationManager implements INotificationManager {

    private final Logger logger;
    private int count =0;

    public SyslogNotificationManager(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void postNotification(String notes) {
        System.out.println("*******************************************************************************************************");
        HttpRequesterTest.testPut(notes);
        System.out.println(notes);
        logger.warn(notes);
        count= DatabaseUtil.count(count);
    }

}
