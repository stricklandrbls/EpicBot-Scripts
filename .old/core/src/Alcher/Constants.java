package src.Alcher;

import lib.Player.IPlayerState.IPlayerState;
import lib.Player.IPlayerState.IdleState;
import src.Alcher.States.AlchingState;
import src.Alcher.States.InitState;

public class Constants {
  public static IdleState Idling = new IdleState();
  public static InitState InitialState = new InitState();
  public static AlchingState Alching = new AlchingState();
}
