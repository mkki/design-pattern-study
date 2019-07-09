package observer;

import java.util.LinkedList;
import java.util.List;

public class Publisher implements Subject {
    public enum ContentState {
        UPDATE, DELETE
    }

    private List<Observer> subscribers;
    private ContentState contentState;

    public Publisher() {
        subscribers = new LinkedList<>();
    }

    @Override
    synchronized public void addObserver(Observer observer) {
        subscribers.add(observer);
    }

    @Override
    synchronized public void removeObserver(Observer observer) {
        if (subscribers.contains(observer)) {
            subscribers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer subscriber : subscribers) {
            subscriber.update(contentState);
        }
    }

    public ContentState getContentState() {
        return contentState;
    }

    public void setContentState(ContentState contentState) {
        this.contentState = contentState;
        notifyObservers();
    }
}
