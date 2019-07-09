## Observer Pattern
- 용어
    - `Observable(Subject, Publish)`는 주체가 되는 객체로 상태를 가지고 있다.
    - `Observer(Subscribe)`는 주체의 상태 변화에 관심이 있는 객체이다.
- 정의
    - 주체의 상태 변화가 일어났을 때, 자신을 등록한 객체에 **의존하지 않으면서** 상태 변화를 통지하도록 도와주는 패턴이다.
        > 행위 패턴 중 하나이다.
    - 이벤트를 발생시키는 객체와 이를 처리하는 객체의 **결합도를 줄이는 것**이 목표이다.

## UML
![observer-patter-uml](https://www.oreilly.com/library/view/learning-javascript-design/9781449334840/httpatomoreillycomsourceoreillyimages1547801.png)

## 적용 예
- `Reactive Extension(ReactiveX) Project`, `RabbitMQ`
    > UI 관련 코드에서 자주 사용된다고 한다.

## Observer Pattern in Java Library
- `Java`에는  `Observable Class`와 `Observer Interface`를 제공한다.
그러나 `Observable`은 클래스이기에 서브클래싱이 필요하고, 확장과 재사용성도 떨어진다.

~~~
    // Subject
    class Subject extends Observable {
        private int data;
    
        public Subject() {
            super();
        }
    
        public void setData(int data) {
            this.data = data;
            dataChanged();
        }
    
        private void dataChanged() {
            setChanged();
            notifyObservers();
        }
    
        public int getData() {
            return data;
        }
    }
    
    // observer
    interface Displayer {
        void display();
    }
    
    class Subscriber implements Observer, Displayer {
        private Observable observable;
        private int data;
    
        public Subscriber(Observable observable) {
            this.observable = observable;
            observable.addObserver(this);
        }
    
        @Override
        public void update(Observable o, Object arg) {
            if (o instanceof Subject) {
                Subject subject = (Subject) o;
                this.data = subject.getData();
                display();
            }
        }
        
        @Override
        public void display() {
            System.out.println("MY DATA: " + data);
        }
    }
~~~

## Sample Code
`YouTube`에서 구독자의 채널 구독을 예로 코드를 작성해보았다.

- **Push** 방식의 코드, `Subject` 객체가 `Observer` 객체로 상태를 전달
~~~
    // subject
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

    // observer
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
    
    // client
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
    
    // output
    update content!
    update content!
    update content!
    =================
    delete content!
    delete content!
~~~

- **Pull** 방식의 코드, `Observer` 객체가 `Subject` 객체에 상태를 요청
    - `void notifyObservers(Observer observer)` 메서드 추가
    ~~~
        public interface Subject {
            void addObserver(Observer observer);
            void removeObserver(Observer observer);
            void notifyObservers();
            void notifyObservers(Observer observer);
        }
    ~~~
    
   - `UpdateChecker Observer` 추가
   ~~~
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
   ~~~
   
   - `Client` 클래스에 `UpdateChecker` 추가
   ~~~
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
        
        // output
        update content!
        update content!
        update content!
        [CHECK] update content!
        =================
        delete content!
        delete content!
        [CHECK] delete content!
        =================
        [CHECK] delete content!
   ~~~


## References
- https://www.baeldung.com/java-observer-pattern
- https://www.oreilly.com/library/view/learning-javascript-design/9781449334840/ch09s05.html
- https://hyeonstorage.tistory.com/165
- https://www.geeksforgeeks.org/observer-pattern-set-1-introduction/
- https://nesoy.github.io/articles/2019-02/RabbitMQ
- http://friday.fun25.co.kr/blog/?p=157
- https://dynaticy.tistory.com/entry/DesignPattern-2-Observer-Pattern%EC%97%90-%EB%8C%80%ED%95%9C-%EA%B3%A0%EB%AF%BC