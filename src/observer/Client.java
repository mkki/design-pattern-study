package observer;

public class Client {
    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        Subscriber firstSubscriber = new Subscriber();
        Subscriber secondSubscriber = new Subscriber();
        Subscriber thirdSubscriber = new Subscriber();

        firstSubscriber.subscribe(publisher);
        secondSubscriber.subscribe(publisher);
        thirdSubscriber.subscribe(publisher);
        publisher.setContentState(Publisher.ContentState.UPDATE);

        System.out.println("=================");

        thirdSubscriber.unsubscribe(publisher);
        publisher.setContentState(Publisher.ContentState.DELETE);
    }
}
