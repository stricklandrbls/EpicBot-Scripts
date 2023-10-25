package ArdyKnightPickpocketer.Player;

import com.epicbot.api.shared.APIContext;

import ArdyKnightPickpocketer.Constants;
import ArdyKnightPickpocketer.States;
import ArdyKnightPickpocketer.Player.Player.IPlayerState;

public class HealingState implements IPlayerState{
        private int healthyPercent = 95;
    @Override
    public void update(Player p) {
        if(!APIContext.get().inventory().contains(Constants.FoodId)) {
            States.Banking.addWithdrawAction(new BankAction(Constants.FoodStr, 20));
            p.state = States.Banking;
            return;
        }
        if(p.health() < healthyPercent)
            APIContext.get().inventory().interactItem("Eat", Constants.FoodId);
        else
            p.state = States.Stealing;
    }
    public String status() { return "Eating"; }
    public int actionTime() { return 1750 + Constants.random.nextInt(750); }
}
