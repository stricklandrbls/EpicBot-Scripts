package Pickpocketer.PlayerState;

import com.epicbot.api.shared.APIContext;

public class EatingState implements IPlayerState {
    private int healthyPercent = 95;
    @Override
    public void update(Player p) {
        if(!APIContext.get().inventory().contains(Constants.FoodId)) {
            States.Relocating.destination = Constants.FarmingGuildBank;
            States.Relocating.stateUponArrival = States.Banking;
            Constants.dropUnwantedSeeds();
            p.state = States.Relocating;
            return;
        }
        if(p.health() < healthyPercent)
            APIContext.get().inventory().interactItem("Eat", Constants.FoodId);
        else
            p.state = States.Pickpocketing;
    }
    public String status() { return "Eating"; }
    public int actionTime() { return 1750 + Constants.rand.nextInt(750); }
    
}
