package mediator;

public class Client {
    public static void main(String[] args) {
        User foo = new User("foo");
        User bar = new User("bar");

        foo.sendMessage("Hi! bar!");
        bar.sendMessage("Hello! foo!");
    }
}