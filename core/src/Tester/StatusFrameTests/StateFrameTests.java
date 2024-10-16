package src.Tester.StatusFrameTests;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;

import lib.Player.MyPlayer;
import lib.Player.IPlayerState.IPlayerState;
import src.Tester.StatusFrameTests.States.Constants;

import java.awt.*;

@ScriptManifest(name="Status Frame Test", gameType = GameType.OS)
public class StateFrameTests extends LoopScript {
  MyPlayer player;
  @Override
  protected int loop() {
    player.update();
    return player.actionTime();
  }

  @Override
  public boolean onStart(String... arg0) {
    player = new MyPlayer(IPlayerState.Enter(Constants.Init));
    return true;
  }
  
  @Override
  public void onPaint(Graphics2D g, APIContext ctx){
    if(player.currentState() != null)
      player.currentState().draw(g, ctx);
  }
}
