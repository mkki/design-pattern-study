## Decorator Pattern
런타임에 새로운 기능을 추가하거나 행위를 변경시켜 유연한 확장을 제공해준다.
서브클래싱 대신 `Concrete Component`를 감싸는 `Decorator`를 사용하여 상속 트리의 복잡도를 줄여준다.
> 구조 패턴 중 하나이다.

## Class Diagram
![decorator](https://user-images.githubusercontent.com/28993371/59831907-b21dc000-937d-11e9-8217-c67c5fcf3304.png)

## Decorator Pattern in Java Library
대표적으로 `java.io`의 클래스들이 데코레이터 패턴으로 구현되어 있다.

- 상속으로만 구현
![inheritance-only](http://stg-tud.github.io/sedc/Lecture/ws13-14/Images/DP-Decorator-IOWithInheritanceOnly.png)

- `Decorator Pattern` 적용
![decorator-pattern](http://stg-tud.github.io/sedc/Lecture/ws13-14/Images/DP-Decorator-java.io.png)

아래 예시에서 `BufferedReader`는 `Concrete Decorator`이다. `InputStream`은 `Abstract Component`이고,
이를 상속하는 `InputStreamReader`가 `Concrete Component`이다.
```
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
```


## Decorator Pattern vs Strategy Pattern
- 두 패턴의 공통점
    - 런타임에 동적으로 `Component`의 행위를 변경할 수 있다.
- 두 패턴의 차이점
    - `Decorator Pattern`은 `Component`의 외부에 있는 `Decorator`로 확장한다.
    `Strategy Pattern`은 `Component`의 내부에서 `Strategy`로 확장한다.
    - `Decorator Pattern`이 모듈화와 확장성에서 더 뛰어나다. 따라서 많은 요구사항에 맞는 인터페이스 설계가 어려울 때, 요구사항의 다양성을
    예측할 수 없을 때 사용하면 좋다.
    - 그러나 `Decorator Interface` 자체가 `Component Interface`를 따라야하기에 `Component` 클래스가 중량급이면,
    각 전략이 독립적인 `Strategy Pattern`이 적합하다.

## 장점
- 서브클래싱으로 확장하지 않기에, 클래스 구조의 복잡도가 줄어든다.

## 단점
- `Component`를 초기화하는데 필요한 코드가 복잡해진다. 인스턴스화 뿐만 아니라, 여러 `Decorator`로 감싸줘야하기 때문이다.
> 그렇기에 `Factory Pattern`, `Builder Pattern`과 함께 사용된다고 한다.

## Sample Code
~~~
    public class LowercaseInputStream extends FilterInputStream {
        public LowercaseInputStream(InputStream in) {
            super(in);
        }
    
        @Override
        public int read() throws IOException {
            int c = super.read();
            return c == -1 ? c : Character.toLowerCase(c);
        }
    
        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            int result = super.read(b, off, len);
            for (int i = off; i < off + result; i++) {
                b[i] = (byte) Character.toLowerCase(b[i]);
            }
            return result;
        }
    }
    
    public class Client {
        public static void main(String[] args) {
            try (InputStream in = new LowercaseInputStream(new BufferedInputStream(System.in))) {
                int c;
                while ((c = in.read()) >= 0) {
                    System.out.print((char) c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    // input
    ABCdef
    
    // output
    abcdef
~~~

## References
- https://www.baeldung.com/java-decorator-pattern
- http://stg-tud.github.io/sedc/Lecture/ws13-14/5.3-Decorator.html#mode=document
- https://unabated.tistory.com/entry/%EB%8D%B0%EC%BD%94%EB%A0%88%EC%9D%B4%ED%84%B0-%ED%8C%A8%ED%84%B4Decorator-Pattern