package ArdyKnightPickpocketer.Player;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.NPC;

import ArdyKnightPickpocketer.Constants;
import ArdyKnightPickpocketer.States;
import ArdyKnightPickpocketer.Player.Player.IPlayerState;

public class PickPocketingState implements IPlayerState{
    private NPC knight;
    @Override
    public void update(Player p) {
        // check health
        if(p.health() <= Constants.LowHealth)
            p.state = States.Banking; // Eating

        // check location
        if(!Constants.BankBoundary.getBounds().contains(p.location().getLocation().getCentralPoint())) {
            // Need to relocate
            // return p.state = States.Relocating
        }
        if(p.inCombat())
            // p.state = States.Stunned
            // return
        if(APIContext.get().inventory().contains(Constants.coinpurse.id()) && 
            APIContext.get().inventory().getCount(Constants.coinpurse.id())  == Constants.coinpurse.max())
            APIContext.get().inventory().getItem(Constants.coinpurse.id()).interact(Constants.coinpurse.interact());
            
        if(this.knight == null)
            APIContext.get().npcs().get(Constants.KnightId);
        
        this.knight.interact(Constants.PickpocketAction);
    }

    @Override
    public String status() { return "Stealing Money"; }
    @Override
    public int actionTime() { return 500 + Constants.random.nextInt(200); }
    
}
