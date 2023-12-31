package MonkFisher.Player;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.model.Area;

import MonkFisher.Constants;
import MonkFisher.Player.Player.IPlayerState;

class BankAction {
    private String what;
    private int amount;
    private boolean all;
    public BankAction(String what, boolean all) {
        this.all = all;
        this.what = what;
    }
    public BankAction(String what, int amount) {
        this.what = what;
        this.amount = amount;
    }
    public String what() { return what; }
    public int amount() { return amount; }
    public boolean all() { return all; }
}
public class BankingState implements IPlayerState {

    private BankAction[] _deposits = { new BankAction(Constants.Shark.name(), true)};
    private int _depositActions = 1;

    @Override
    public void update(Player p) {
        if(!APIContext.get().bank().isOpen())
            APIContext.get().bank().open();

        APIContext.get().bank().depositAll(Constants.Shark.name());

        APIContext.get().bank().close();

        if(APIContext.get().inventory().contains(Constants.Shark.name()))
            throw new UnsupportedOperationException("Inventory is still full");
            
        States.Relocating.destination = new Area(Constants.FishingGuildFishingAreaTile, 1);
        States.Relocating.stateUponArrival = States.Fishing;
        p.state = States.Relocating;
    }

    public boolean addDepositAction(BankAction deposit) {
        this._deposits[this._depositActions] = deposit;
        this._depositActions++;
        return true;
    }
    @Override
    public String status() { return "Banking"; }
    @Override
    public int actionTime() { return 500 + Constants.random.nextInt(500);}
    
}
