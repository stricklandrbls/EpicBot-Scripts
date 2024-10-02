package src.Alcher;

import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;

import lib.Player.MyPlayer;

@ScriptManifest(name = "New Alcher", gameType = GameType.OS)
public class Alcher extends LoopScript{

  protected MyPlayer Player;

  @Override
  protected int loop() {
    Player.update();
    return Player.actionTime();
  }

  @Override
  public boolean onStart(String... arg0) {
    Player = new MyPlayer(Constants.InitialState);
    return true;
  }
  
}
