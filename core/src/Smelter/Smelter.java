package src.Smelter;

import java.awt.Graphics2D;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;

import lib.Player.MyPlayer;

@ScriptManifest(name = "Edgeville Smelter", gameType = GameType.OS)
public class Smelter extends LoopScript {
  MyPlayer player;
  
  @Override
  protected int loop() {
    player.update();
    return player.actionTime();
  }

  @Override
  public boolean onStart(String... arg0) {
    Constants.SmeltingState.setProfile(Constants.Cannonball);
    player = new MyPlayer(Constants.SmeltingState);
    return true;
  }
  
  @Override
  protected void onPaint(Graphics2D g, APIContext ctx){
    player.currentState().draw(g, ctx);
  }
}
