package ArdyKnightPickpocketer.Player;

import ArdyKnightPickpocketer.Constants;
import ArdyKnightPickpocketer.States;
import ArdyKnightPickpocketer.Player.Player.IPlayerState;

public class StunnedState implements IPlayerState {
        @Override
    public void update(Player p) {
        // mouseMove(p);
        if(!p.inCombat())
            p.state = States.Stealing;
        else {
            p.state = States.Equiping;
        }
    }

    @Override
    public String status() {
        return "Stunned!";
    }
    public int actionTime() { return 1750 + Constants.random.nextInt(750); }
}
