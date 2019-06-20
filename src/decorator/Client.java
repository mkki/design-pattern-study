package decorator;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Client {
    public static void main(String[] args) {
        try (InputStream in = new LowercaseInputStream(new BufferedInputStream(System.in))) {
            int c;
            while ((c = in.read()) >= 0) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
