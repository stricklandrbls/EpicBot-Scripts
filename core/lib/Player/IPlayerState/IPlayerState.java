package lib.Player.IPlayerState;

import com.epicbot.api.shared.APIContext;

import java.awt.*;

public abstract class IPlayerState {
  public abstract IPlayerState update();
  public abstract int  actionTime();
  public abstract String status();
  public abstract String stateName();
  
  protected void onEnter(){
    System.out.println(this.status());
  }
  protected void onExit(){

  }

  public void draw(Graphics2D g, APIContext ctx){
    return;
  }
}