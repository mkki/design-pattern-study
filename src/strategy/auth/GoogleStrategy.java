package strategy.auth;

public class GoogleStrategy implements AuthenticationStrategy {
    @Override
    public void authenticate(String name) {
        System.out.println(name + " is logged in with google");
    }
}
