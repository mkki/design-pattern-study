package proxy.model;

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
