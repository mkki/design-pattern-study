## Facade Pattern
- 프레임워크나 라이브러리 등 복잡한 서브시스템을 단순화 된 인터페이스로 캡슐화시켜주는 패턴이다.
    > 구조 패턴 중 하나이다.
- `Facade`는 **건물의 정면/외관**을 의미한다.
이 뜻처럼 특정 라이브러리의 코드 밖에서 해당 라이브러리에 의존하는 않는 코드를 작성할 수 있게 된다.

## UML
![facade-patter-uml](https://refactoring.guru/images/patterns/diagrams/facade/structure-indexed.png)

## Real-World Analogy
다음은 엔진의 동작 원리를 구현한 코드이다.
![start-a-car-work-flow](https://www.baeldung.com/wp-content/uploads/2018/04/facade-class-diagram.png)

기존 코드에서 시동을 걸었을 때 엔진의 동작은 꽤나 복잡하다.
~~~
    airFlowController.takeAir()
    fuelInjector.on()
    fuelInjector.inject()
    starter.start()
    coolingController.setTemperatureUpperLimit(DEFAULT_COOLING_TEMP)
    coolingController.run()
    catalyticConverter.on()
~~~

비슷하게 시동을 끌 때에도 몇몇 단계를 거친다.
~~~
    fuelInjector.off()
    catalyticConverter.off()
    coolingController.cool(MAX_ALLOWED_TEMP)
    coolingController.stop()
    airFlowController.off()
~~~

다음은 `Facade Pattern`을 적용한 코드이다.
복잡한 단계들을 캡슐화하여 `startEngine()`과 `stopEngine()`으로 단순화 시켜준다.
~~~
    public class CarEngineFacade {
        private static int DEFAULT_COOLING_TEMP = 90;
        private static int MAX_ALLOWED_TEMP = 50;
        private FuelInjector fuelInjector = new FuelInjector();
        private AirFlowController airFlowController = new AirFlowController();
        private Starter starter = new Starter();
        private CoolingController coolingController = new CoolingController();
        private CatalyticConverter catalyticConverter = new CatalyticConverter();
     
        public void startEngine() {
            fuelInjector.on();
            airFlowController.takeAir();
            fuelInjector.on();
            fuelInjector.inject();
            starter.start();
            coolingController.setTemperatureUpperLimit(DEFAULT_COOLING_TEMP);
            coolingController.run();
            catalyticConverter.on();
        }
     
        public void stopEngine() {
            fuelInjector.off();
            catalyticConverter.off();
            coolingController.cool(MAX_ALLOWED_TEMP);
            coolingController.stop();
            airFlowController.off();
        }
    }
~~~

클라이언트는 시동을 켜고 끄기 위해 13줄의 코드를 작성하는것 대신에 2줄의 코드만 작성하면 된다.
~~~
    facade.startEngine();
    facade.stopEngine();
~~~

## Facade Pattern in Java Library
- `javax.faces.context.FacesContext`는 `LifeCycle`, `ViewHandler`, `NavigationHandler` 클래스를 사용한다. 그러나 대부분의 클라이언트는 인식하지 못한다.
- `javax.faces.context.ExternalContext`는 `ServletContext`, `HttpSession`, `HttpServletRequest`, `HttpServletResponse` 등의 클래스를 사용한다.
    > `JSF(Java Server Faces)`는 자바 기반 웹 프로그래밍 모델의 일종이다. 과거에 이를 구현하기 위해 사용되었다는 것 정도만 알고 넘어가자.

## Relations with Other Patterns
- `Facade Pattern`은 새로운 인터페이스를 정의하고, 모든 객체 서브 시스템과 작동한다.
 `Adapter Pattern`은 기존 인터페이스를 사용 가능하게 만들고, 보통 하나의 객체만을 감싼다.
- `Facade Pattern`과 `Mediator Pattern` 모두 결합도가 높은 클래스 간의 공통 작업을 구성하는데 사용된다.
    - `Facade`는 서브 시스템을 인터페이스로 단순화 시키지만, 새로운 기능을 추가하진 않는다.
    서브 시스템 자체는 `Facade`를 인식하지 못하고, 내부의 객체들은 직접 통신할 수 있다.
    - `Mediator Pattern`에서 컴포넌트는 `Mediator` 객체에 대해서만 알고 있으며 서로 통신하지 않는다.
- `Facade Pattern`과 `Proxy Pattern` 모두 복잡한 엔티티를 버퍼링하고 초기화한다.
`Facede`와 다르게 `Proxy`는 서비스 객체와 동일한 인터페이스를 갖는다.
- `Facade` 클래스는 대부분의 경우 하나로 충분하기에, `Singleton`으로 변환될 수 있다.

## Sample Code
[Example of Video Conversion Framework](https://refactoring.guru/design-patterns/facade/java/example#lang-features)

## References
- https://www.baeldung.com/java-facade-pattern
- https://refactoring.guru/design-patterns/facade
- https://www.geeksforgeeks.org/facade-design-pattern-introduction/