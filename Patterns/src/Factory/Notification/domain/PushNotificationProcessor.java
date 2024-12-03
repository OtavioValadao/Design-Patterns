package Factory.Notification.domain;

import Factory.Notification.NotificationProcessor;

public class PushNotificationProcessor implements NotificationProcessor {
    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("Sending PUSH Notification to " + recipient + ": " + message);
    }
}
