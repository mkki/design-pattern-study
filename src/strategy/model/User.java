package strategy.model;

import strategy.auth.*;

public class User {
    private String name;
    private AuthenticationStrategy authenticationStrategy;

    public User(String name) {
        this.name = name;
        this.authenticationStrategy = new LocalStrategy();
    }

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

    public String getName() {
        return name;
    }

    public void setAuthenticationStrategy(AuthenticationStrategy authenticationStrategy) {
        this.authenticationStrategy = authenticationStrategy;
    }
}
