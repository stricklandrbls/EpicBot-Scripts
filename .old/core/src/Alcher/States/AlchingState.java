package src.Alcher.States;

import java.awt.Graphics2D;
import java.util.function.*;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.ItemWidget;
import com.epicbot.api.shared.methods.ITabsAPI.Tabs;
import com.epicbot.api.shared.model.Spell;

import lib.Player.IPlayerState.IPlayerState;
import lib.Script.StatusFrame.StatusFrame;
import lib.antiban.AntiBan;
import src.Alcher.Constants;

public class AlchingState extends IPlayerState{
  private StatusFrame statusFrame_= new StatusFrame("Alching");
  private ItemWidget  item_;
  private int         itemCount_ = 0;
  private boolean     spellReady_ = false;
  private int         stateTime_ = 500;
  private Supplier<IPlayerState> updater  = () -> {
    if(APIContext.get().localPlayer().isAnimating()){
      this.itemCount_ = item_.getStackSize();
      statusFrame_.update("count", itemCount_);
      APIContext.get().tabs().open(Tabs.MAGIC);
      AntiBan.mouse.CheckFigit();
      return this;
    }

    if(this.itemCount_ <= 0 || this.item_ == null){
      return IPlayerState.Enter(Constants.InitialState);
    }

    if(!this.spellReady_){
      APIContext.get().magic().cast(Spell.Modern.HIGH_LEVEL_ALCHEMY);
      this.spellReady_ = true;
    }
    else {
      if(!APIContext.get().tabs().isOpen(Tabs.INVENTORY))
        APIContext.get().tabs().open(Tabs.INVENTORY);
        
      APIContext.get().mouse().click(item_.getRandomPoint());
      AntiBan.mouse.MoveOffscreenOrFigit();
      this.spellReady_ = false;
    }
    return this;
  };
  public AlchingState(){
    this.updateStrategy = updater;
  }
  public void setItem(String itemName){
    this.item_ = APIContext.get().inventory().getItem(itemName);
    this.itemCount_ = this.item_.getStackSize();
    statusFrame_.add("item", statusFrame_.new LineData("Item Selected:", item_.getName()));
    statusFrame_.add("count", statusFrame_.new LineData("Count:", itemCount_));
  }

  @Override
  public String stateName(){ return "Alching"; }

  @Override
  public int actionTime() {
    return this.stateTime_;
  }

  @Override
  public String status() {
    return "Alching".concat(item_.getName());
  }
  

  @Override
  public void draw(Graphics2D g, APIContext ctx){
    if(item_ == null)
      return;
    statusFrame_.draw(g,ctx);
  }
}
