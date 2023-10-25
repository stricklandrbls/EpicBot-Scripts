package ArdyKnightPickpocketer;

import ArdyKnightPickpocketer.Player.BankingState;
import ArdyKnightPickpocketer.Player.EquipingState;
import ArdyKnightPickpocketer.Player.PickPocketingState;


public class States {
    public static BankingState Banking = new BankingState();
    public static PickPocketingState Stealing = new PickPocketingState();
    public static EquipingState Equiping = new EquipingState();
}
