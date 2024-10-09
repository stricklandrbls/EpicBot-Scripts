package src.Smelter;

import java.awt.Graphics2D;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;

import lib.Bank.Item;
import lib.Bank.Withdraw;
import lib.Player.MyPlayer;
import lib.Player.IPlayerState.SharedStates;

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
    if(!APIContext.get().inventory().contains(item -> {
      return item.getName().equals("Steel bar");
    })){
      SharedStates.Banking.add(new Withdraw(new Item("Steel bar")));
      SharedStates.Banking.nextState = Constants.SmeltingState;
      player = new MyPlayer(SharedStates.Banking);
      return true;
    }

    player = new MyPlayer(Constants.SmeltingState);
    return true;
  }
  
  @Override
  protected void onPaint(Graphics2D g, APIContext ctx){
    player.currentState().draw(g, ctx);
  }
}
