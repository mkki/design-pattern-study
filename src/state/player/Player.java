package state.player;

public class Player {
    private State state;

    public Player(State state) {
        this.state = state;
    }

    public void play() {
        state.pressButton(this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
