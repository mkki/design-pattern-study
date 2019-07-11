## Adapter Pattern
- 용어
    - `Adaptee`는 외부 시스템이나 라이브러리를 의미한다.
    - `Adapter`는 `Client`와 `Adaptee`를 연결해주는 역할을 한다.
- 클래스의 인터페이스를 다른 인터페이스로 변환하는 패턴으로, 함께 동작할 수 없는 클래스들이 **함께 작동하도록** 해준다.
    > **구조 패턴(Structural Pattern)** 중 하나이다.
- 구현 방법은 `class adapter implementation`과 `object adapter implementation`로 나뉜다.
    > 둘의 차이점은 상속을 통해 구현하는지, 내부에 인스턴스로 갖는지이다.

## UML
![adapter-pattern](https://t1.daumcdn.net/cfile/tistory/994FEF33599FB0D01D)

## Adapter Pattern in Java Library
`Java`에서는 하위 버전 호환성을 위해 사용된다.
- `java.util.Arrays#asList()`
- `java.util.Collections#list()`
- `java.util.Collections#enumeration()`
- `java.io.InputStreamReader(InputStream)`
- `java.io.OutputStreamWriter(OutputStream)`
- `javax.xml.bind.annotation.adapters.XmlAdapter#marshal()` and `#unmarshal()`

## Sample Code
![adapter-pattern-sample](https://user-images.githubusercontent.com/28993371/61036601-5dadb380-a404-11e9-96b1-9c5d8a10bc41.png)

`WebClient`는 요청을 처리하기 위해 `doWork()` 메서드를 호출하고, 이에 대한 처리를 `LegacyWebRequester`에 위임한다.
그러나 치명적인 이슈가 발생하였고, `AwesomeWebReqester` 라이브러리를 사용하기로 결정하였다.

이러한 상황에서 `Adapter Pattern`을 사용하면, 클라이언트 코드와 서드파티 라이브러리의 코드를 변경하지 않고 문제를 해결할 수 있다.
~~~
    public class WebClient {
        private WebRequester webRequester;
    
        public WebClient(WebRequester webRequester) {
            this.webRequester = webRequester;
        }
    
        public void doWork() {
            webRequester.requestHandler();
        }
    }
    
    public interface WebRequester {
        void requestHandler();
    }
    
    public class LegacyWebRequester implements WebRequester {
        @Override
        public void requestHandler() {
            System.out.println("LegacyWebRequester is working");
        }
    }
    
    public class AwesomeWebRequester {
        public void requestHandler() {
            System.out.println("AwesomeWebRequester is working");
        }
    }
~~~

- `object adapter implementation`
~~~
    public class WebAdapter implements WebRequester {
        private AwesomeWebRequester awesomeWebRequester;
    
        public WebAdapter(AwesomeWebRequester awesomeWebRequester) {
            this.awesomeWebRequester = awesomeWebRequester;
        }
    
        @Override
        public void requestHandler() {
            awesomeWebRequester.requestHandler();
        }
    }
~~~

- 실행 결과
~~~
    public class Client {
        private static final AwesomeWebRequester AWESOME_WEB_REQUESTER = new AwesomeWebRequester();
    
        public static void main(String[] args) {
            WebClient webClient = new WebClient(new WebAdapter(AWESOME_WEB_REQUESTER));
            webClient.doWork();
        }
    }
    
    // ouput
    AwesomeWebRequester is working
~~~

## References
- https://ko.wikipedia.org/wiki/%EC%96%B4%EB%8C%91%ED%84%B0_%ED%8C%A8%ED%84%B4
- https://sendgrid.com/blog/using-the-adapter-design-pattern-with-react/
- https://www.baeldung.com/java-adapter-pattern
- https://blog.seotory.com/post/2017/09/java-adapter-pattern
- https://yaboong.github.io/design-pattern/2018/10/15/adapter-pattern/
- https://refactoring.guru/design-patterns/adapter/java/example#lang-features