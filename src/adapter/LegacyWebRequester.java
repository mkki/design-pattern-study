package adapter;

public class LegacyWebRequester implements WebRequester {
    @Override
    public void requestHandler() {
        System.out.println("LegacyWebRequester is working");
    }
}
