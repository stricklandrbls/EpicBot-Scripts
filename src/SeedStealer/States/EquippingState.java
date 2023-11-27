package SeedStealer.States;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.methods.IEquipmentAPI.Slot;

import MyPlayer.Player.IPlayerState;
import MyPlayer.Player.CommonStates.BankAction;
import SeedStealer.Constants;

public class EquippingState implements IPlayerState {

    @Override
    public IPlayerState update() {
        if(!APIContext.get().inventory().contains(Constants.Equipment)) {
            States.Relocating.destination = Constants.FarmingGuildBank;
            States.Relocating.stateUponArrival = States.Banking;
            States.Banking.addWithdrawAction(new BankAction(Constants.Equipment[0], 3));
            return States.Relocating;
        }
        if(!APIContext.get().equipment().contains(Slot.NECK, Constants.Equipment))
            APIContext.get().inventory().getItem(Constants.Equipment).interact("Wear");
        return States.Pickpocketing;
    }

    @Override
    public String status() { return "Equiping Items"; }

    @Override
    public int actionTime() { return 250 + Constants.rand.nextInt(500); }
    
}
