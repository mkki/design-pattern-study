package observer;

public class Client {
    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        Subscriber firstSubscriber = new Subscriber();
        Subscriber secondSubscriber = new Subscriber();
        Subscriber thirdSubscriber = new Subscriber();
        UpdateChecker updateChecker = new UpdateChecker();

        firstSubscriber.subscribe(publisher);
        secondSubscriber.subscribe(publisher);
        thirdSubscriber.subscribe(publisher);
        updateChecker.subscribe(publisher);
        publisher.setContentState(Publisher.ContentState.UPDATE);
        System.out.println("=================");

        thirdSubscriber.unsubscribe(publisher);
        publisher.setContentState(Publisher.ContentState.DELETE);
        System.out.println("=================");

        updateChecker.check(publisher);
    }
}
