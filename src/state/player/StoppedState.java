package state.player;

public class StoppedState implements State {
    @Override
    public void pressButton(Player player) {
        player.setState(new StandbyState());
        System.out.println("standby...");
        showContent();
    }

    private void showContent() {
        System.out.println("Other Content");
        System.out.println("1. Harry Potter");
        System.out.println("2. Lord of the Ring");
    }
}
