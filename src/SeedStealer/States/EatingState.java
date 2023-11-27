package SeedStealer.States;

import com.epicbot.api.shared.APIContext;

import MyPlayer.Player.IPlayerState;
import MyPlayer.Player.CommonStates.BankAction;
import SeedStealer.Constants;

public class EatingState implements IPlayerState {
    private int healthyPercent = 95;
    @Override
    public IPlayerState update() {
        if(!APIContext.get().inventory().contains(Constants.FoodId)) {
            States.Relocating.destination = Constants.FarmingGuildBank;
            States.Relocating.stateUponArrival = States.Banking;
            States.Banking.addWithdrawAction(new BankAction(Constants.FoodId, 10));
            Constants.dropUnwantedSeeds();

            return States.Relocating;
        }
        if(Constants.myHealth() < healthyPercent)
            APIContext.get().inventory().interactItem("Eat", Constants.FoodId);
        else
            return this;
        return States.Pickpocketing;
    }
    public String status() { return "Eating"; }
    public int actionTime() { return 1750 + Constants.rand.nextInt(750); }
    
}
