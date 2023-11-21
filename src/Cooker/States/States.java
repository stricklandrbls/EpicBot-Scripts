package Cooker.States;

import MyPlayer.Player.CommonStates.BankingState;

public class States {
    public static CookingState Cooking = new CookingState("Fire", "Raw tuna");
    public static BankingState Banking = new BankingState();
}
