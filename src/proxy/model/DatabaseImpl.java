package proxy.model;

public class DatabaseImpl implements Database {
    @Override
    public void loginProcess(String id, String password) throws Exception {
        accessData();
    }

    private void accessData() {
        System.out.println("access some data");
        System.out.println();
    }
}
