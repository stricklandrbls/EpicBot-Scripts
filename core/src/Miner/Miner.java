package src.Miner;

import java.awt.Graphics2D;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;

import lib.Player.MyPlayer;
import src.Miner.States.InitializeState;

@ScriptManifest(name = "Miner", gameType = GameType.OS)
public class Miner extends LoopScript {

  MyPlayer player;
  @Override
  protected int loop() {
    player.update();
    return player.actionTime();
  }

  @Override
  public boolean onStart(String... arg0) {
    player = new MyPlayer(new InitializeState(), true);
    return true;
  }
  @Override
  public void onPaint(Graphics2D g, APIContext ctx){
    player.currentState().draw(g, ctx);
  }
}
