package lib.Player.IPlayerState;

import com.epicbot.api.shared.APIContext;

import lib.Script.StatusFrame.StatusFrame;

import java.awt.*;
import java.util.function.Supplier;

public abstract class IPlayerState {
  public abstract int  actionTime();
  public abstract String status();
  public abstract String stateName();

  protected StatusFrame status;
  protected Supplier<IPlayerState> updateStrategy = () -> {
    return this;
  };

  /// TODO: Make this `public final` and have IPlayerState classes provide an updateStrategy
  public IPlayerState update(){
    return this.updateStrategy.get();
  }

  public static IPlayerState Enter(IPlayerState state){
    state.onEnter();
    return state;
  }

  public static IPlayerState Exit(IPlayerState currentState, IPlayerState next){
    currentState.onExit();
    return next;
  }

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