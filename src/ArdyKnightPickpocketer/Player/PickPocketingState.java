package ArdyKnightPickpocketer.Player;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.NPC;
import com.epicbot.api.shared.model.Tile;

import ArdyKnightPickpocketer.Constants;
import ArdyKnightPickpocketer.States;
import ArdyKnightPickpocketer.Player.Player.IPlayerState;

public class PickPocketingState implements IPlayerState{
    private NPC knight;
    @Override
    public void update(Player p) {
        // check health
        if(p.health() <= Constants.LowHealth) {
            p.state = States.Healing; // Eating
            return;
        }

        // check location
        if(!Constants.BankBoundary.contains(p.location())){
            Tile[] pathToKnight = APIContext.get().walking().findPath(this.knight).getTiles();
            APIContext.get().walking().walkPath(pathToKnight);
            // throw new UnsupportedOperationException("I'm out of bounds somehow");
        }
        if(p.inCombat()) {
            p.state = States.Stunned;
            return;
        }
        if(APIContext.get().inventory().contains(Constants.CoinPurseId)) {
            if(APIContext.get().inventory().getItem(Constants.CoinPurseId).getStackSize() >= Constants.CoinPurseMax - Constants.random.nextInt(10))
                APIContext.get().inventory().getItem(Constants.CoinPurseId).click();
        }
            
        if(this.knight == null)
            this.knight = APIContext.get().npcs().query().named(Constants.KnightId).results().first();
        else
            this.knight.click();
    }

    @Override
    public String status() { return "Stealing Money"; }
    @Override
    public int actionTime() { return 200 + Constants.random.nextInt(200); }
    
}
