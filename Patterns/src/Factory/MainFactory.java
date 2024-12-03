package Factory;

import Factory.Notification.NotificationProcessor;
import Factory.Notification.NotificationProcessorFactory;

public class MainFactory {
    public static void main(String[] args) {
        factoryV1(true);
    }

    public static void factoryV1(boolean isProcessable) {
        if (isProcessable) {

            var recipient = "email@email.com";
            var message = "message to message";
            var factory = new NotificationProcessorFactory();

            NotificationProcessor processor = factory.getProcessor("push");
            processor.sendNotification(recipient, message);
        }
    }
}
