package Pickpocketer;
import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.NPC;
import com.epicbot.api.shared.methods.ILocalPlayerAPI;
import com.epicbot.api.shared.model.Tile;

import Pickpocketer.IPlayerState;

public class Player {

    protected IPlayerState state;
    protected String statusMsg = "Initialializing";

    public int health() { return APIContext.get().localPlayer().getHealthPercent(); }
    public boolean inCombat() { return APIContext.get().localPlayer().isInCombat(); }
    public Tile location() { return APIContext.get().localPlayer().getLocation(); }
    public String update() { 
        state.update(this);
        return state.status();
    }
    public boolean isInteractingWith(NPC target) {
        return APIContext.get().localPlayer().getInteracting() == target;
    }
}

