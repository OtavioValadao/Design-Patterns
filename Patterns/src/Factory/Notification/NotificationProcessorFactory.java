package Factory.Notification;

import Factory.Notification.domain.EmailNotificationProcessor;
import Factory.Notification.domain.PushNotificationProcessor;
import Factory.Notification.domain.SmsNotificationProcessor;

public class NotificationProcessorFactory {


    public NotificationProcessorFactory() {
    }

    public NotificationProcessor getProcessor(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Notification type cannot be null");
        }
        return switch (type.toLowerCase()) {
            case "email" -> new EmailNotificationProcessor();
            case "sms" -> new SmsNotificationProcessor();
            case "push" -> new PushNotificationProcessor();
            default -> throw new IllegalArgumentException("Unsupported notification type: " + type);
        };
    }
}
