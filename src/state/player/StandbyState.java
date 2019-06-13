package state.player;

public class StandbyState implements State {
    @Override
    public void pressButton(Player player) {
        player.setState(new PlayingState());
        System.out.println("playing...");
    }
}
