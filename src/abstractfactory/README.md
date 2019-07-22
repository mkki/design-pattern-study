## Abstract Factory Pattern
- `Concreate Class`를 지정하지 않고도 서로 연관되거나 의존 관계가 있는 객체의 조합을 생성하는 인터페이스를 제공하는 패턴
    > 생성 패턴 중 하나이다.
- 객체 생성 처리를 서브클래스로 분리하는 `Factory Method Pattern`과 `Singleton Pattern`을 사용한다.
    > 클라이언트 코드에서 `new` 연산자로 생성자를 호출할 필요가 없어진다.
    
## UML
![abstract-factory-patter-uml](https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Abstract_factory_UML.svg/1920px-Abstract_factory_UML.svg.png)

## Abstarct Factory Pattern in Java Library
- `javax.faces.context.FacesContext`는 `LifeCycle`, `ViewHandler`, `NavigationHandler` 클래스를 사용한다. 그러나 대부분의 클라이언트는 인식하지 못한다.
- `javax.faces.context.ExternalContext`는 `ServletContext`, `HttpSession`, `HttpServletRequest`, `HttpServletResponse` 등의 클래스를 사용한다.
    > `JSF(Java Server Faces)`는 자바 기반 웹 프로그래밍 모델의 일종이다. 과거에 이를 구현하기 위해 사용되었다는 것 정도만 알고 넘어가자.

## Relations with Other Patterns
- 보통 초기에 `Factory Method Pattern`을 시작으로 `Abstract Factory Pattern`, `Prototype Pattern`, `Builder Pattern`으로 발전한다.
- `Abstract Factory Pattern`은 관련 객체를 모두 포함하여 객체를 생성하며, 이를 즉시 반환한다.
반면에 `Builder Pattern`은 객체 생성을 **단계적으로 구성**하는데 중점을 둔다. 필수 인자만으로 `Builder`를 생성하고, 체이닝을 통해 선택적 인자를 추가할 수 있다.
    > `Builder Pattern`이 보다 확장성이 높아보인다.
- `Abstract Factory` 클래스는 여러 개의 `Factory Method` 기반으로 구성되는 경우도 있고, 메서드 구현에 `Prototype`을 사용할 수도 있다.
- 서브 시스템 생성 코드를 클라이언트 코드로부터 숨기고자 하는 경우에 `Abstract Factory Pattern`이 `Facade Pattern`의 대안으로 사용될 수 있다.
- `Abstact Factory Pattern`, `Builder Pattern`, `Prototype Pattern` 모두 `Singleton`으로 구현될 수 있다.

## Sample Code
[Example of XMLParser](https://www.javacodegeeks.com/2015/09/abstract-factory-design-pattern.html)

## References
- https://en.wikipedia.org/wiki/Abstract_factory_pattern
- https://refactoring.guru/design-patterns/abstract-factory
- https://www.baeldung.com/java-abstract-factory-pattern
- https://www.javacodegeeks.com/2015/09/abstract-factory-design-pattern.html