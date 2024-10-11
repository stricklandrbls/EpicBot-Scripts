package lib.Player.IPlayerState;

import lib.Bank.BankingState;

public class SharedStates {
  public static BankingState Banking = new BankingState();
  public static IdleState Idling = new IdleState();
}
