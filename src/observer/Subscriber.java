package observer;

public class Subscriber implements Observer, Displayer {
    public Subscriber() {
    }

    public void subscribe(Subject subject) {
        subject.addObserver(this);
    }

    public void unsubscribe(Subject subject) {
        subject.removeObserver(this);
    }

    @Override
    public void update(Publisher.ContentState contentState) {
        display(contentState);
    }

    @Override
    public void display(Publisher.ContentState contentState) {
        switch (contentState) {
            case UPDATE:
                System.out.println("update content!");
                break;
            case DELETE:
                System.out.println("delete content!");
                break;
        }
    }
}
