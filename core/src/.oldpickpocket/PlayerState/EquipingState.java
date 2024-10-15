package Pickpocketer.PlayerState;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.methods.IEquipmentAPI.Slot;

public class EquipingState implements IPlayerState {

    @Override
    public void update(Player p) {
        if(!APIContext.get().inventory().contains(Constants.Equipment)) {
            States.Relocating.destination = Constants.FarmingGuildBank;
            States.Relocating.stateUponArrival = States.Banking;
            p.state = States.Relocating;
            return;
        }
        if(!APIContext.get().equipment().contains(Slot.NECK, Constants.Equipment))
            APIContext.get().inventory().getItem(Constants.Equipment).interact("Wear");
        p.state = States.Pickpocketing;
    }

    @Override
    public String status() { return "Equiping Items"; }

    @Override
    public int actionTime() { return 250 + Constants.rand.nextInt(500); }
    
}
