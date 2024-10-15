package Pickpocketer.PlayerState;

import com.epicbot.api.shared.APIContext;

public class InitialState implements IPlayerState {

    @Override
    public void update(Player p) {
        if(playerHasPickpocketingItems()){
            States.Relocating.destination = Constants.FarmingGuildArea;
            States.Relocating.stateUponArrival = States.Pickpocketing;
        }
        else {
            States.Relocating.destination = Constants.FarmingGuildBank;
            States.Relocating.stateUponArrival = States.Banking;
        }
        p.state = States.Relocating;
    }

    @Override
    public String status() { return "Initializing"; }
    public int actionTime() { return 750 + Constants.rand.nextInt(750); }
    private boolean playerHasPickpocketingItems() {
        int foodCount = APIContext.get().inventory().getCount(Constants.FoodId);
        int necklaceCount = APIContext.get().inventory().getCount(Constants.Equipment);
        System.out.println("Food: " + String.valueOf(foodCount) + "; Necklaces: " + String.valueOf(necklaceCount));
        if(foodCount < NecessaryEquipment.minimumFoodCount ||
             necklaceCount < NecessaryEquipment.minimumEquipmentCount)
            return false;
        return true;
    }

    
}
