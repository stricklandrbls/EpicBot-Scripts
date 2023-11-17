package MyPlayer.Player;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.model.Tile;


public class Player {
    // private     APIContext _ctx;
    protected   IPlayerState state;

    public Player(IPlayerState startingState) {

        System.out.println("Player Const..1");
        this.state = startingState; 
        System.out.println("Player Const..2");
        // this._ctx = ctx; 
    }
    
    public void     update() { this.state = this.state.update(); }
    public int      stateActionTime() { return this.state.actionTime(); }
    public String   status() { return this.state.status(); }

    // public int      health() { return _ctx.localPlayer().getHealthPercent(); }
    public boolean  inCombat() { return APIContext.get().localPlayer().isInCombat(); }
    public Tile     location() { return APIContext.get().localPlayer().getLocation(); }
}