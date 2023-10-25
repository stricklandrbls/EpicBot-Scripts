package ArdyKnightPickpocketer;

import ArdyKnightPickpocketer.Player.BankingState;
import ArdyKnightPickpocketer.Player.EquipingState;
import ArdyKnightPickpocketer.Player.HealingState;
import ArdyKnightPickpocketer.Player.PickPocketingState;
import ArdyKnightPickpocketer.Player.StunnedState;


public class States {
    public static BankingState Banking = new BankingState();
    public static PickPocketingState Stealing = new PickPocketingState();
    public static EquipingState Equiping = new EquipingState();
    public static StunnedState Stunned = new StunnedState();
    public static HealingState Healing = new HealingState();
}
