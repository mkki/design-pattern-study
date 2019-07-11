package adapter;

public class Client {
    private static final AwesomeWebRequester AWESOME_WEB_REQUESTER = new AwesomeWebRequester();

    public static void main(String[] args) {
        WebClient webClient = new WebClient(new WebAdapter(AWESOME_WEB_REQUESTER));
        webClient.doWork();
    }
}
