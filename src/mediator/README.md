## Mediator Pattern
상호작용하는 클래스들의 결합도를 줄이기 위해 `Mediator(중재자)`를 두는 방법이다. `M:N`의 관계를 `M:1`의 관계로 바꾸어준다.

## UML
![adapter-pattern](https://www.oreilly.com/library/view/learning-javascript-design/9781449334840/httpatomoreillycomsourceoreillyimages1547805.png)

## 적용 예
- `Vuex`에서 `action`들의 결합도를 낮추기 위해 사용된다고 한다.
    > https://itnext.io/decouple-vuex-actions-with-the-mediator-pattern-58a8879de1b4

## Mediator Pattern in Java Library
- `java.util.Timer`
- `java.util.concurrent.Executor#execute()`
- `java.util.concurrent.ExecutorService`
- `java.util.concurrent.ScheduledExecutorService`
- `java.lang.reflect.Method#invoke()`

## Mediator Pattern / Observer Pattern / Strategy Pattern
- `Mediator Pattern`은 `Mediator`에 컴포넌트 로직을 중앙집중화키는 것이 목표이다. 로직이 캡슐화 되어있으며, 흐름을 파악하기 쉽다.
    > `Observer Pattern`으로 구현하는 것이 일반적이라고 한다.
- `Observer Pattern`은 객체 간 **결합도를 낮추는 것**이 목표이다. `Mediator Pattern`에 재사용성이 높으나, 로직의 흐름을 파악하기 어렵다.
    > `Strategy Pattern`과 달리 객체 간 **통신**이 목적이다.

## Sample Code
간단한 채팅 애플리케이션에 `Mediator Pattern`을 적용해보았다.
~~~
    public class ChatRoom {
        public static void showMessage(User user, String message) {
            System.out.println(new Date().toString() + " [" + user.getName() + "]: " + message);
        }
    }
    
    public class User {
        private String name;
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public User(String name) {
            this.name = name;
        }
    
        public void sendMessage(String message) {
            ChatRoom.showMessage(this, message);
        }
    }
    
    public class Client {
        public static void main(String[] args) {
            User foo = new User("foo");
            User bar = new User("bar");
    
            foo.sendMessage("Hi! bar!");
            bar.sendMessage("Hello! foo!");
        }
    }
    
    // output
    Thu Jul 11 18:31:23 KST 2019 [foo] : Hi! bar!
    Thu Jul 11 18:31:23 KST 2019 [bar] : Hello! foo!
~~~

## References
- https://www.tutorialspoint.com/design_pattern/mediator_pattern.htm
- https://www.oreilly.com/library/view/learning-javascript-design/9781449334840/ch09s06.html
- https://sonseungha.tistory.com/500
