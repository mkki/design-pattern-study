package strategy;

import strategy.model.User;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Kim", "facebook"));
        userList.add(new User("Lee", "google"));
        userList.add(new User("Park", "github"));
        userList.add(new User("Cho"));

        for (User user : userList) {
            user.login();
        }
    }
}
