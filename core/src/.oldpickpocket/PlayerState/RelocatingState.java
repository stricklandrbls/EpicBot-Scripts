package Pickpocketer.PlayerState;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;

public class RelocatingState implements IPlayerState {
    protected Area destination;
    protected Tile[] pathToDest;
    protected IPlayerState stateUponArrival;

    @Override
    public void update(Player p) {
        if(destination.contains(p.location())) {
            p.state = stateUponArrival;
        }
        else {
            pathToDest = APIContext.get().walking().findPath(this.destination.getRandomTile()).getTiles();
            APIContext.get().walking().walkPath(pathToDest);
        }
    }
    
    public int actionTime() { return 750 + Constants.rand.nextInt(750); }
    public void setDestination(Area to, IPlayerState nextState) { 
        destination = to; 
        stateUponArrival = nextState;
    }
    public String status() { return "Relocating "; }
    
}
