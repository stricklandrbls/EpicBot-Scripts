package SeedStealer.States;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.methods.ITabsAPI.Tabs;

import MyPlayer.Player.IPlayerState;
import SeedStealer.Constants;

public class StunnedState implements IPlayerState {

    @Override
    public IPlayerState update() {
        mouseMove();
        if(!Constants.amIInCombat())
            return States.Pickpocketing;
        else {
            return States.Equipping;
        }
    }

    @Override
    public String status() {
        return "Stunned!";
    }

    private void mouseMove() {
        switch(Constants.rand.nextInt(10)) {
            case 0:
                APIContext.get().tabs().open(Tabs.INVENTORY);
                APIContext.get().mouse().move(APIContext.get().inventory().getItemAt(Constants.rand.nextInt(27)));
                break;
            case 5:
                if(APIContext.get().tabs().getCurrent().equals(Tabs.INVENTORY))
                    APIContext.get().tabs().open(Tabs.EQUIPMENT);
                else
                    APIContext.get().tabs().open(Tabs.INVENTORY);
                break;
        }
    }
    public int actionTime() { return 1750 + Constants.rand.nextInt(750); }
}
