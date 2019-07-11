package adapter;

public class WebAdapter implements WebRequester {
    private AwesomeWebRequester awesomeWebRequester;

    public WebAdapter(AwesomeWebRequester awesomeWebRequester) {
        this.awesomeWebRequester = awesomeWebRequester;
    }

    @Override
    public void requestHandler() {
        awesomeWebRequester.requestHandler();
    }
}
