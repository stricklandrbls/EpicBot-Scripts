package Pickpocketer.PlayerState;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.NPC;
import com.epicbot.api.shared.model.Area;

public class PickpocketingState implements IPlayerState{

    Area targetArea;
    private static NPC target;
    @Override
    public void update(Player p) {
        if(APIContext.get().inventory().isFull()) {
            p.state = States.Organizing;
            return;
        }
        if(p.inCombat()) {
            p.state = States.Stunned;
            return;
        }
        if(p.health() < Constants.LowHealth) {
            p.state = States.Eating;
            return;
        }

        target = APIContext.get().npcs().query().named(Constants.TargetNPCName).results().first();
        
        if(target == null) {
            States.Relocating.destination = targetArea;
            States.Relocating.stateUponArrival = States.Pickpocketing;
            p.state = States.Relocating;
            return;
        }
        else {
            if(!p.isInteractingWith(target))
                target.interact(Constants.PickpocketAction);
            targetArea = target.getArea(4);
            return ;
        }
    }
    
    @Override
    public String status() { return "Pickpocketing"; }
    @Override
    public int actionTime() { return 250 + Constants.rand.nextInt(521); }
    
}
