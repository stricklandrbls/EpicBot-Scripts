package Pickpocketer.PlayerState;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.methods.ITabsAPI.Tabs;

public class StunnedState implements IPlayerState{

    @Override
    public void update(Player p) {
        mouseMove(p);
        if(!p.inCombat())
            p.state = States.Pickpocketing;
        else {
            p.state = States.EquipingItems;
        }
    }

    @Override
    public String status() {
        return "Stunned!";
    }

    private void mouseMove(Player p) {
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
            case 8:
            case 9:
            case 10:
                p.state = States.Organizing;

        }
    }
    public int actionTime() { return 1750 + Constants.rand.nextInt(750); }
    
}
