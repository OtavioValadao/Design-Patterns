package Factory.Notification.domain;

import Factory.Notification.NotificationProcessor;

public class SmsNotificationProcessor implements NotificationProcessor {
    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("Sending SMS Notification to " + recipient + ": " + message);
    }
}
