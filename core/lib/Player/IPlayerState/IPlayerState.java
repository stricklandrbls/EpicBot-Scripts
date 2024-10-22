package lib.Player.IPlayerState;

import com.epicbot.api.shared.APIContext;

import lib.Script.StatusFrame.StatusFrame;

import java.awt.*;
import java.util.function.Supplier;

public abstract class IPlayerState {
  public abstract int  actionTime();
  public String status(){ return "IPlayerState"; };
  public String stateName(){return "IPlayerState"; };

  protected StatusFrame status;
  public void setUpdateStrategy(Supplier<IPlayerState> strategy){
    this.updateStrategy = strategy;
  }
  protected Supplier<IPlayerState> updateStrategy = null;

  /// TODO: Make this `public final` and have IPlayerState classes provide an updateStrategy
  public IPlayerState update(){
    if(updateStrategy == null)
      throw new UnsupportedOperationException("IPlayerState implementations require an initial update strategy");
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