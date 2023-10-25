package Pickpocketer.PlayerState;

import com.epicbot.api.shared.APIContext;

public class OrganizingState implements IPlayerState {

    @Override
    public void update(Player p) {
        Constants.dropUnwantedSeeds();
        if(APIContext.get().inventory().isFull()){
            States.Relocating.destination = Constants.FarmingGuildBank;
            States.Relocating.stateUponArrival = States.Banking;
        }
        p.state = States.Pickpocketing;
    }

    @Override
    public String status() { return "Dropping Unwanted Seeds"; }
    public int actionTime() { return 750 + Constants.rand.nextInt(750); }
    
}
