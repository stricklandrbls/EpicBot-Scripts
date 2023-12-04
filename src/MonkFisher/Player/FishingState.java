package MonkFisher.Player;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.NPC;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.query.result.LocatableEntityQueryResult;

import AntiBan.AntiBan;
import MonkFisher.Player.Player;
import MonkFisher.Player.Player.IPlayerState;
import MonkFisher.Constants;

public class FishingState implements IPlayerState{
    NPC target;
    public int currentFishingSpot = 0;
    private static int fishCaught = 0;

    // private AntiBan AntiBan = new AntiBan();
    @Override
    public void update(Player p) {
        if(APIContext.get().inventory().isFull()) {
            if(APIContext.get().bank().isReachable()) {
                p.state = States.Banking;
                return;
            }
            States.Relocating.destination = Constants.FishingGuildBank;
            States.Relocating.stateUponArrival = States.Banking;
            p.state = States.Relocating;
            return;
        }
        if(APIContext.get().localPlayer().getInteracting() != null){
            int x = Constants.random.nextInt(25);
            if(x % 7 == 0)
                shakeCamera();
            return;
        }
        
        Area myGeneralArea = new Area(APIContext.get().localPlayer().getLocation(), 8);
        LocatableEntityQueryResult<NPC> npcs = APIContext.get().npcs().query().id(Constants.Shark.equipment().fishingSpotNPCId()).located(myGeneralArea.getTiles()).results();
        if( !npcs.isEmpty() )
            this.target = npcs.nearest();
        else {
            States.Relocating.destination = Constants.FishingGuildFishingArea;
            States.Relocating.stateUponArrival = States.Fishing;
            p.state = States.Relocating;
            return;
        }

        if(Constants.random.nextInt(3) == 0)
            APIContext.get().camera().turnTo(target);
        this.target.interact(Constants.Shark.equipment().action());
        APIContext.get().mouse().moveOffScreen();
    }

    @Override
    public String status() {return "Fishing"; }

    @Override
    public int actionTime() { return 1909 + Constants.random.nextInt(10000);}
    
    public void shakeCamera() {
        int yaw = APIContext.get().camera().getYawDeg();
        int adjustments[] = { Constants.random.nextInt(25), Constants.random.nextInt(40) };
        int maxYawDeg = 360;
        APIContext.get().camera().setYawDeg( (yaw += adjustments[0]) % maxYawDeg);
        if(adjustments[1] % 3 == 0)
            APIContext.get().camera().setYawDeg((yaw -= adjustments[1]) % maxYawDeg);
    }
}
