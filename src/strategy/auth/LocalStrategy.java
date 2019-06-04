package strategy.auth;

public class LocalStrategy implements AuthenticationStrategy {
    @Override
    public void authenticate(String name) {
        System.out.println(name + " is logged in with local account");
    }
}
