## Proxy Pattern
객체의 `Proxy(대리자)`를 두어 역할을 수행하도록 위임하는 패턴이다. 객체의 액세스 권한 제어를 수행한다고 볼 수 있다.
> 구조 패턴 중 하나이다.

## Class Diagram
![proxy-pattern](https://user-images.githubusercontent.com/28993371/59838572-1ba3cb80-938a-11e9-813b-5301dbd559f8.png)

## 적용 예
- `가상 프록시(virtual proxy)`
    - 고비용의 객체가 필요할 때 사용된다. 인터페이스만 존재하고 실제 객체는 생성하지 않는 **스켈레톤 객체**를 사용하여 메모리를 절약할 수 있다.
        > 데이터베이스를 예로 들 수 있다.
- `리모트 프록시(remote proxy)`
    - 메서드를 호출하려는 객체가 다른 머신에서 실행되고 있는 상황에서 사용된다.
        > 소캣 통신에 사용되는 프로토콜이 이러한 구조로 구현되어 있다.
- `보호 프록시(protection proxy)`
    - 객체의 액세스 권한 제어를 위해 사용된다. `Proxy`의 메서드는 전달 받은 인증 토큰을 처리하여, 인증되지 않는 접근에 예외를 던진다.

## Sample Code
~~~
    public class Customer {
        private String id;
        private String password;
        private Database database;
    
        public Customer(String id, String password) {
            this.id = id;
            this.password = password;
            this.database = new DatabaseProxy(id, password);
        }
    
        public void login() {
            try {
                database.loginProcess(this.id, this.password);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    interface Database {
        void loginProcess(String id, String password) throws Exception;
    }
    
    public class DatabaseImpl implements Database {
        @Override
        public void loginProcess(String id, String password) throws Exception {
            accessData();
        }
    
        private void accessData() {
            System.out.println("access some data");
            System.out.println();
        }
    }

    public class DatabaseProxy implements Database {
        private boolean isEnrolled;
        private DatabaseImpl database;
    
        public DatabaseProxy(String id, String password) {
            if ("foo".equals(id) && "bar".equals(password)) {
                this.isEnrolled = true;
            }
            this.database = new DatabaseImpl();
        }
    
        @Override
        public void loginProcess(String id, String password) throws Exception {
            if (isEnrolled) {
                System.out.println("log-in succeed");
                database.loginProcess(id, password);
            } else {
                throw new Exception("sign-up required");
            }
        }
    }

    public class Client {
        public static void main(String[] args) {
            Customer customer1 = new Customer("foo", "bar");
            Customer customer2 = new Customer("f", "b");
            customer1.login();
            customer2.login();
        }
    }

    // output
    log-in succeed
    access some data
    
    sign-up required
~~~

## References
- https://www.baeldung.com/java-decorator-pattern
- https://blog.seotory.com/post/2017/09/java-proxy-pattern