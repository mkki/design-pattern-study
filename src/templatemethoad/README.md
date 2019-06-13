## Template Method Pattern
서브클래스의 공통 사항을 상위 추상클래스에 구현하고, 상속을 통해 확장 개발을 도와주는 `행위 패턴(behavior pattern)` 중 하나이다.
> `Strategy Pattern`도 행위 패턴의 일종

## Class Diagram
![template-method-pattern](https://user-images.githubusercontent.com/28993371/59412372-3f9a6680-8df8-11e9-9d21-d4fae0465e8a.png)

## Template Method Pattern in Java Core Library
- 다음 클래스의 모든 **non-abstract methods** `java.io.InputStream`, `java.io.OutputStream`,  `java.io.Reader`, `java.io.Writer`, `java.util.AbstractList`, `java.util.AbstractSet`, `java.util.AbstractMap`
- `javax.servlet.http.HttpServlet`의 `doXXX()`

## Template Method Pattern vs Strategy Pattern
- **Template Method Pattern**은 `is-a(상속 관계)`이다. 상위 추상 클래스의 골격을 사용하여 하위 클래스의 서브 클래싱을 도와준다.
- **Strategy Pattern**은 `has-a(포함 관계)`이다. 런타임에서 `주체(Context)`의 `동작(Strategy)`이 수정될 수 있다.

## Abstract Classes vs Interfaces
Which should you use, abstract classes or interfaces?

- Consider using abstract classes if any of these statements apply to your situation:
    - You want to share code among several closely related classes.
    - You expect that classes that extend your abstract class have many common methods or fields, or require access modifiers other than public (such as protected and private).
    - You want to declare non-static or non-final fields. This enables you to define methods that can access and modify the state of the object to which they belong.
- Consider using interfaces if any of these statements apply to your situation:
    - You expect that unrelated classes would implement your interface. For example, the interfaces Comparable and Cloneable are implemented by many unrelated classes.
    - You want to specify the behavior of a particular data type, but not concerned about who implements its behavior.


## Sample Code
~~~
    public abstract class ParserTemplate {
        public enum DataType {
            JSON("JSON data"), XML("XML data"), HTML("HTML data");
    
            private String type;
    
            DataType(String type) {
                this.type = type;
            }
    
            public String getType() {
                return type;
            }
        }
    
        public final void parseData(DataType data) {
            readData();
            checkDataType(data);
            doParse();
            System.out.println("==================================");
        }
    
        private void readData() {
            System.out.println("read data");
        }
    
        private void checkDataType(DataType type) {
            System.out.println("Data Type: " + type.getType());
        }
    
        abstract void doParse();
    }
    
    public class XMLParser extends ParserTemplate{
        @Override
        void doParse() {
            System.out.println("parsing xml data successfully");
        }
    }

    public class HTMLParser extends ParserTemplate{
        @Override
        void doParse() {
            System.out.println("parsing html data successfully");
        }
    }
    
    public class JSONParser extends ParserTemplate{
        @Override
        void doParse() {
            System.out.println("parsing json data successfully");
        }
    }
    
    public class Client {
        public static void main(String[] args) {
            ParserTemplate xmlParser = new XMLParser();
            ParserTemplate htmlParser = new HTMLParser();
            ParserTemplate jsonParser = new JSONParser();
    
            xmlParser.parseData(ParserTemplate.DataType.XML);
            htmlParser.parseData(ParserTemplate.DataType.HTML);
            jsonParser.parseData(ParserTemplate.DataType.JSON);
        }
    }
    
    // output
    read data
    Data Type: XML data
    parsing xml data successfully
    ==================================
    read data
    Data Type: HTML data
    parsing html data successfully
    ==================================
    read data
    Data Type: JSON data
    parsing json data successfully
    ==================================
~~~


## References
- https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html
- https://refactoring.guru/design-patterns/template-method/java/example
- https://www.codeproject.com/Articles/695871/Comparison-of-Template-and-Strategy-Design-Pattern
- https://www.geeksforgeeks.org/parse-json-java/