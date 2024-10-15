package src.Pickpocketer;

import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;

import lib.Player.MyPlayer;

@ScriptManifest(name = "New Pickpocketer", gameType = GameType.OS)
public class Pickpocketer extends LoopScript{
  MyPlayer player;

  @Override
  protected int loop() {
    player.update();
    return player.actionTime();
  }

  @Override
  public boolean onStart(String... arg0) {
    player = new MyPlayer(Constants.Initializing);
    player.update();
    return false;
  }
  
}
