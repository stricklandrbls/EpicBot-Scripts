package lib.Player.IPlayerState;

import com.epicbot.api.shared.APIContext;

import lib.Script.StatusFrame.StatusFrame;

import java.awt.*;

public abstract class IPlayerState {
  public abstract IPlayerState update();
  public abstract int  actionTime();
  public abstract String status();
  public abstract String stateName();
  protected StatusFrame status;

  public void onEnter(){
    System.out.println(this.status());
  }
  protected void onExit(){

  }
  public boolean amIdle() {
    return !APIContext.get().localPlayer().isAnimating();
  }
  public boolean hasStatusFrame(){
    return this.status != null;
  }
  public void draw(Graphics2D g, APIContext ctx){
    if(hasStatusFrame())
      this.status.draw(g, ctx);
    return;
  }
}