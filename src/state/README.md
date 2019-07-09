## State Pattern
객체 자신이 클래스를 바꾸는 것처럼 내부의 상태에 따라 행동이 변경되도록 도와주는 디자인 패턴이다.
> 상태 패턴도 `행위 패턴(behavioral pattern)` 중 하나이다.

## Class Diagram
![Package player](https://user-images.githubusercontent.com/28993371/59419997-09181800-8e07-11e9-960a-af3b8ce0ae68.png)

## State Pattern vs Strategy Pattern
- 두 패턴의 공통점
    - 작성된 코드가 `OCP`를 따르게 한다.
    - `주체(Context)` 객체에 영향을 주지 않고 전략과 상태를 추가할 수 있다.
    - `주체(Context)` 객체는 초기 상태와 전략을 가진다.
    - 하위 클래스에 의존하여 행동이 구현, 확장된다.
- 두 패턴의 차이점
    - `Strategy Pattern`은 알고리즘을 캡슐화하여 클라이언트가 이를 런타임에 사용할 수 있게 한다.
    반면에 `State Pattern`은 클래스가 다른 상태에서 다른 동작을 나타낼 수 있도록 도와줍니다.
    - `Strategy Pattern`은 전략과 알고리즘을 주체와 분리하여 재사용할 수 있으며, 매개 변수로도 사용 가능하다.
    반면에 `State Pattern`에서 상태는 주체와 응집성이 높기에 분리하여 재사용하기 어렵다.
    - `State Pattern`에서 상태 변화를 위해 주체를 참조할 수 있지만, `Strategy Pattern`의 전략은 주체의 참조를 포함할 수 없다.
    - `Strategy Pattern`에서 각 전략은 서로 독립된 알고리즘을 캡슐화 하기에, `SRP`도 따른다. 즉, 각 전략이 독립적이다.
    - `State Pattern`은 **어떤** 상태를 **언제** 변화 시킬 것인가에 중점을 둔 것이다.
    반면에 `Strategy Pattern`은 정렬, 암호화 같은 전략을 **어떻게** 구현할 것인가에 중점을 둔다. 즉, 목적이 다르다고 볼 수 있다.
    - `Strategy Pattern`에서 전략의 변경은 클라이언트에 의해 이루어지지만,
    `State Pattern`에서 상태의 변경은 주체나 상태 객체 자신에 의해 수행될 수 있다.

## Sample Code
~~~
    public class Player {
        private State state;
    
        public Player(State state) {
            this.state = state;
        }
    
        public void play() {
            state.pressButton(this);
        }
    
        public State getState() {
            return state;
        }
    
        public void setState(State state) {
            this.state = state;
        }
    }
    
    public interface State {
        public void pressButton(Player player);
    }

    public class PlayingState implements State {
        @Override
        public void pressButton(Player player) {
            player.setState(new StoppedState());
            System.out.println("stopped..");
        }
    }
    
    public class StandbyState implements State {
        @Override
        public void pressButton(Player player) {
            player.setState(new PlayingState());
            System.out.println("playing...");
        }
    }
    
    public class StoppedState implements State {
        @Override
        public void pressButton(Player player) {
            player.setState(new StandbyState());
            System.out.println("standby...");
            showContent();
        }
    
        private void showContent() {
            System.out.println("Other Content");
            System.out.println("1. Harry Potter");
            System.out.println("2. Lord of the Ring");
        }
    }
    
    public class Client {
        public static void main(String[] args) {
            Player moviePlayerOnWeb = new Player(new StandbyState());
    
            moviePlayerOnWeb.play();    // play some content
            moviePlayerOnWeb.play();    // stop
            moviePlayerOnWeb.play();    // standby
            moviePlayerOnWeb.play();    // play other content
        }
    }
    
    // output
    playing...
    stopped..
    standby...
    Other Content
    1. Harry Potter
    2. Lord of the Ring
    playing...
~~~

## References
- https://www.baeldung.com/java-state-design-pattern
- https://refactoring.guru/design-patterns/state/java/example
- https://javarevisited.blogspot.com/2014/04/difference-between-state-and-strategy-design-pattern-java.html