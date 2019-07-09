package observer;

public class UpdateChecker implements Observer {
    public UpdateChecker() {
    }

    public void subscribe(Subject subject) {
        subject.addObserver(this);
    }

    public void unsubscribe(Subject subject) {
        subject.removeObserver(this);
    }

    public void check(Subject subject) {
        subject.notifyObservers(this);
    }

    @Override
    public void update(Publisher.ContentState contentState) {
        switch (contentState) {
            case UPDATE:
                System.out.println("[CHECK] update content!");
                break;
            case DELETE:
                System.out.println("[CHECK] delete content!");
                break;
            case NONE:
                System.out.println("[CHECK] content is not updated");
                break;
        }
    }
}
