package SeedStealer.States;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;

import MyPlayer.Player.IPlayerState;
import SeedStealer.Constants;

public class RelocatingState implements IPlayerState {
    protected Area destination;
    protected Tile[] pathToDest;
    protected IPlayerState stateUponArrival;
    
    @Override
    public IPlayerState update() {
        Tile myLocation = APIContext.get().localPlayer().getLocation();
        if(destination.contains(myLocation))
            return stateUponArrival;
            
        if(pathToDest == null)
            pathToDest = APIContext.get()
                .walking()
                .findPath(this.destination.getRandomTile())
                .getTiles();

        APIContext.get().walking().walkPath(pathToDest);
        return this;
    }


    @Override
    public String status() { return "Walking to destination"; }
    
    @Override
    public int actionTime() { return 750 + Constants.rand.nextInt(750); }
}
