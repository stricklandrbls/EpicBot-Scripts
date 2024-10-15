package Pickpocketer.PlayerState;

import com.epicbot.api.shared.APIContext;

public class BankingState implements IPlayerState{
    @Override
    public void update(Player p) {
        if(!APIContext.get().bank().isOpen())
            APIContext.get().bank().open();
        else {
            withdrawNecklaces();
            int foodWithdrawAmount = NecessaryEquipment.minimumFoodCount - APIContext.get().inventory().getCount(Constants.FoodId);
            if(foodWithdrawAmount > 0)
                APIContext.get().bank().withdraw(foodWithdrawAmount, Constants.FoodId);
            else {
                APIContext.get().bank().close();
                States.Relocating.destination = Constants.FarmingGuildArea;
                States.Relocating.stateUponArrival = States.Pickpocketing;
                p.state = States.Relocating;
            }
        }
    }

    @Override
    public String status() { return "Banking"; }
    public int actionTime() { return 750 + Constants.rand.nextInt(750); }
    private boolean withdrawNecklaces() {
        int necklaceWithdrawAmount = NecessaryEquipment.minimumEquipmentCount - APIContext.get().inventory().getCount(Constants.Equipment);
        if(!APIContext.get().inventory().contains(Constants.Equipment))
            return APIContext.get().bank().withdraw(necklaceWithdrawAmount, Constants.Equipment);
        return false;
    }
    private int withdrawAmount(int minimumAmount, int invAmount) {
        return minimumAmount - invAmount;
    }
}


