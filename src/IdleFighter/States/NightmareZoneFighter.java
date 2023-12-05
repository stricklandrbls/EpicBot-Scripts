package IdleFighter.States;

import com.epicbot.api.shared.APIContext;

import IdleFighter.Constants;
import MyPlayer.Player.IPlayerState;

public class NightmareZoneFighter implements IPlayerState {

    private int prayerPointThreshold = 72-25;

    @Override
    public IPlayerState update() { 
        if(APIContext.get().prayers().getPoints() <= prayerThreshold() && APIContext.get().inventory().contains(Constants.prayerPotionIds))
            APIContext.get().inventory().getItem(Constants.prayerPotionIds).click();
        
        return this;
    }

    @Override
    public int actionTime() { return 5000; }

    @Override
    public String status() { return "Fighting"; }
    
    private int prayerThreshold() {
        return this.prayerPointThreshold - Constants.rand.nextInt(10);
    }
}
