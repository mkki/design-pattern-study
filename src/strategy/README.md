## Strategy Pattern
동일 계열의 알고리즘을 정의하고 캡슐화하여, 서로 상호교환이 가능하도록 해준다.

## 활용
- 하나의 클래스에 많은 행동이 정의되어 분기 처리가 필요할 경우
- 행동들이 개념적으로 비슷하나 알고리즘의 변형이 필요할 경우

## 장점
- 동일 계열의 관련 알고리즘 군이 생기며, 이를 재사용 할 수 있다.
- 서브클래스를 사용하지 않아도 된다.
> `Context(User) class`를 직접 상속 받아 행동과 알고리즘을 구현하지 않는다.

## 단점
- 정의된 여러 전략에 대해 알아야 한다.
- 각 전략을 클래스로 정의하기 때문에 애플리케이션 내 객체 수가 늘어난다.

## 적용 예
`Express` 진영의 `passport.js`가 해당 패턴을 활용하고 있는데, 이를 아주 간단하게 자바 코드로 흉내내보았다.

### Class Diagram
![strategy-pattern](https://user-images.githubusercontent.com/28993371/58857903-43840580-86e1-11e9-9092-2b9619103428.png)

### Sample Code
인증 전략 인터페이스 생성 후, 해당 인터페이스를 구현하는 인증 전략 클래스들을 만들었다.
```
    // Authentication Strategy Interface
    public interface AuthenticationStrategy {
        public void authenticate(String name);
    }
   
    // Facebook Authentication
    public class FacebookStrategy implements AuthenticationStrategy {
        @Override
        public void authenticate(String name) {
            ...
        }
    }
```

`User` 클래스를 추상클래스로 선언하고 각기 다른 인증 전략을 가지는 클래스들을 만들 수도 있었지만,
클래스 복잡도만 늘어나는 것 같아 객체 생성 시 분기 처리하였다.
```
    public class User {
        private String name;
        private AuthenticationStrategy authenticationStrategy;
        
        public User(String name, String provider) {
            this.name = name;
    
            switch (provider) {
                case "facebook": {
                    this.authenticationStrategy = new FacebookStrategy();
                    break;
                }
                case "github": {
                    this.authenticationStrategy = new GithubStrategy();
                    break;
                }
                case "google": {
                    this.authenticationStrategy = new GoogleStrategy();
                    break;
                }
                default: {
                    this.authenticationStrategy = new LocalStrategy();
                }
            }
        }
    
        public void login() {
            authenticationStrategy.authenticate(name);
        }
        
        ...
    }
```

사용자마다 선택한 인증 전략을 통해 로그인 하게 된다.
```
    List<User> userList = new ArrayList<>();
    userList.add(new User("Kim", "facebook"));
    userList.add(new User("Lee", "google"));
    userList.add(new User("Park", "github"));
    userList.add(new User("Cho"));

    for (User user : userList) {
        user.login();
    }
    
    // output
    Kim is logged in with facebook
    Lee is logged in with google
    Park is logged in with github
    Cho is logged in with local account
```