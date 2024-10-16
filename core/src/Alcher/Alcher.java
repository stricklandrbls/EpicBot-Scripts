package src.Alcher;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;

import java.awt.*;

import lib.Player.MyPlayer;

@ScriptManifest(name = "Alcher", gameType = GameType.OS)
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

  @Override
  protected void onPaint(Graphics2D g, APIContext ctx) {
    Player.currentState().draw(g, ctx);
  }

}
