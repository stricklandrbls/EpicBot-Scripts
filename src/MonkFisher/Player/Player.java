package MonkFisher.Player;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.model.Tile;

import MonkFisher.Constants;
import MonkFisher.Player.States;

public class Player {
    private APIContext ctx;
    protected IPlayerState state = States.Fishing;
    public Player(APIContext ctx) { 
        this.ctx = ctx; 
    }
    
    public interface IPlayerState {
        public void update(Player p);
        public String status();
        public int actionTime();
    }

    public void     update() { this.state.update(this); }
    public int      stateActionTime() { return this.state.actionTime(); }
    public String   status() { return this.state.status(); }
    public int      health() { return APIContext.get().localPlayer().getHealthPercent(); }
    public boolean  inCombat() { return APIContext.get().localPlayer().isInCombat(); }
    public Tile     location() { return APIContext.get().localPlayer().getLocation(); }
    
    public void DEBUG_setBankingState() { 
        System.out.println("Adding bank item list");
        States.Banking.addDepositAction(new BankAction(Constants.fishName, true));
        this.state = States.Banking;
    }
}
