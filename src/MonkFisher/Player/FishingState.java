package MonkFisher.Player;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.NPC;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.query.result.LocatableEntityQueryResult;

import MonkFisher.Player.Player;
import MonkFisher.Player.Player.IPlayerState;
import MonkFisher.Constants;

public class FishingState implements IPlayerState{
    NPC target;
    public int currentFishingSpot = 0;
    private static int fishCaught = 0;
    @Override
    public void update(Player p) {
        if(APIContext.get().inventory().isFull()) {
            States.Relocating.destination = Constants.BankArea;
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
        LocatableEntityQueryResult<NPC> npcs = APIContext.get().npcs().query().id(Constants.fishingSpot).located(myGeneralArea.getTiles()).results();
        if( npcs != null )
            this.target = npcs.first();
        else {
            this.currentFishingSpot = this.currentFishingSpot % Constants.FishingAreas.length;
            States.Relocating.destination = Constants.FishingAreas[this.currentFishingSpot];
            States.Relocating.stateUponArrival = States.Fishing;
            System.out.println("Moving to FishingSpot #" + String.valueOf(currentFishingSpot));
            p.state = States.Relocating;
            return;
        }
        this.target = APIContext.get().npcs().query().id(Constants.fishingSpot).results().nearest();
        System.out.println("target distance: " + String.valueOf(target.distanceTo(APIContext.get())));

        if(Constants.random.nextInt(3) == 0)
            APIContext.get().camera().turnTo(target);
        this.target.interact(Constants.fishAction);
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