package ArdyKnightPickpocketer.Player;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.methods.IEquipmentAPI.Slot;

import ArdyKnightPickpocketer.Constants;
import ArdyKnightPickpocketer.States;
import ArdyKnightPickpocketer.Player.Player.IPlayerState;

public class EquipingState implements IPlayerState {
    @Override
    public void update(Player p) {
        if(!APIContext.get().inventory().contains(Constants.Equipment)) {
            States.Banking.addWithdrawAction(new BankAction(Constants.Equipment[0], 5));
            p.state = States.Banking;
            return;
        }
        if(!APIContext.get().equipment().contains(Slot.NECK, Constants.Equipment))
            APIContext.get().inventory().getItem(Constants.Equipment).interact("Wear");
        p.state = States.Stealing;
    }

    @Override
    public String status() { return "Equiping Items"; }

    @Override
    public int actionTime() { return 250 + Constants.random.nextInt(500); }
}
