package Factory.Notification.domain;

import Factory.Notification.NotificationProcessor;

public class EmailNotificationProcessor implements NotificationProcessor {
    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("Sending EMAIL Notification to " + recipient + ": " + message);
    }
}
