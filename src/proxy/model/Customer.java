package proxy.model;

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
