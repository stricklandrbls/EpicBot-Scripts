package src.StallStealer;

import java.util.List;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.entity.SceneObject;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;

import lib.Player.MyPlayer;
import lib.Script.IScript;

@ScriptManifest(name = "Stall Stealer", gameType = GameType.OS)
public class StallStealer extends LoopScript {

  private MyPlayer player = new MyPlayer(Constants.InitialState);

  @Override
  protected int loop() {
    player.update();
    System.out.println(player.status());
    return player.actionTime();
  }

  @Override
  public boolean onStart(String... arg0) {
    player.getState().onEnter();
    return true;
  }
  
}
