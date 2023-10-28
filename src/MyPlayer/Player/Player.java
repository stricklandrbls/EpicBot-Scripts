package MyPlayer.Player;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.model.Tile;

import AntiBan.AntiBan;

public class Player {
    private     APIContext _ctx;
    protected   IPlayerState state;

    public Player(APIContext ctx, IPlayerState startingState) {
        this.state = startingState; 
        this._ctx = ctx; 
    }

    public static AntiBan antiBan = new AntiBan(APIContext.get());
    
    public void     update() { this.state = this.state.update(); }
    public int      stateActionTime() { return this.state.actionTime(); }
    public String   status() { return this.state.status(); }

    public int      health() { return _ctx.localPlayer().getHealthPercent(); }
    public boolean  inCombat() { return APIContext.get().localPlayer().isInCombat(); }
    public Tile     location() { return APIContext.get().localPlayer().getLocation(); }
}