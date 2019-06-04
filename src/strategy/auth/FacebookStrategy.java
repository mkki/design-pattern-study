package strategy.auth;

public class FacebookStrategy implements AuthenticationStrategy {
    @Override
    public void authenticate(String name) {
        System.out.println(name + " is logged in with facebook");
    }
}
