package Observer;

import Observer.ObserverV1.ConcreteObserver;
import Observer.ObserverV1.Subject;

public class MainObserver {
    public static void main(String[] args) {
        observerV1(true);
    }

    public static void observerV1(boolean isProcessable) {
        if (isProcessable) {

            Subject subject = new Subject();

            ConcreteObserver observer1 = new ConcreteObserver("Observatory 1");
            ConcreteObserver observer2 = new ConcreteObserver("Observatory 2");

            subject.addObserver(observer1);
            subject.addObserver(observer2);

            subject.setState("New place 1");

            subject.removeObserver(observer1);
            subject.setState("New Place 2");
        }
    }
}
