package state;

import state.player.Player;
import state.player.StandbyState;

public class Client {
    public static void main(String[] args) {
        Player moviePlayerOnWeb = new Player(new StandbyState());

        moviePlayerOnWeb.play();    // play some content
        moviePlayerOnWeb.play();    // stop
        moviePlayerOnWeb.play();    // standby
        moviePlayerOnWeb.play();    // play other content
    }
}
