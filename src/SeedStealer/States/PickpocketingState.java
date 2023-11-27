package SeedStealer.States;

import MyPlayer.Player.IPlayerState;
import SeedStealer.Constants;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.NPC;
import com.epicbot.api.shared.model.Area;

public class PickpocketingState implements IPlayerState {
    Area targetArea;
    private static NPC target;

    @Override
    public IPlayerState update() {
        if(APIContext.get().inventory().isFull()) 
            return States.Organizing;
        
        if(Constants.amIInCombat()) 
            return States.Stunned;
        
        if(Constants.myHealth() < Constants.LowHealth)
            return States.Eating;

        target = APIContext.get()
            .npcs()
            .query()
            .named(Constants.TargetNPCName)
            .results()
            .first();
        
        if(target == null) {
            States.Relocating.destination = targetArea;
            States.Relocating.stateUponArrival = States.Pickpocketing;
            return States.Relocating;
        }
        else {
            if(!Constants.amIInteractingWith(target))
                target.interact(Constants.PickpocketAction);
            targetArea = target.getArea(4);
            return this;
        }
    }
    

    @Override
    public String status() { return "Pickpocketing"; }
    @Override
    public int actionTime() { return 250 + Constants.rand.nextInt(521); }
}
