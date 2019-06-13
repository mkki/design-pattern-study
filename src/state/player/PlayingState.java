package state.player;

public class PlayingState implements State {
    @Override
    public void pressButton(Player player) {
        player.setState(new StoppedState());
        System.out.println("stopped..");
    }
}
