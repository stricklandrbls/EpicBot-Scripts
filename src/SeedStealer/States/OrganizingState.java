package SeedStealer.States;

import com.epicbot.api.shared.APIContext;

import MyPlayer.Player.IPlayerState;
import SeedStealer.Constants;

public class OrganizingState implements IPlayerState {

    @Override
    public IPlayerState update() {
        Constants.dropUnwantedSeeds();
        if(APIContext.get().inventory().isFull()){
            States.Relocating.destination = Constants.FarmingGuildBank;
            States.Relocating.stateUponArrival = States.Banking;
        }
        return States.Pickpocketing;
    }
    
    @Override
    public String status() { return "Dropping Unwanted Seeds"; }
    @Override
    public int actionTime() { return 750 + Constants.rand.nextInt(750); }
    
}
