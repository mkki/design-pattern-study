package proxy;

import proxy.model.Customer;

public class Client {
    public static void main(String[] args) {
        Customer customer1 = new Customer("foo", "bar");
        Customer customer2 = new Customer("f", "b");
        customer1.login();
        customer2.login();
    }
}
