package src.Alcher.States;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.ItemWidget;
import com.epicbot.api.shared.methods.ITabsAPI.Tabs;
import com.epicbot.api.shared.model.Spell;

import lib.Player.IPlayerState.IPlayerState;
import lib.antiban.AntiBan;
import src.Alcher.Constants;

public class AlchingState extends IPlayerState{
  private ItemWidget  item_;
  private int         itemCount_ = 0;
  private boolean     spellReady_ = false;
  private int         stateTime_ = 500;

  public void setItem(String itemName){
    this.item_ = APIContext.get().inventory().getItem(itemName);
    this.itemCount_ = this.item_.getStackSize();
  }

  @Override
  public IPlayerState update() {
    if(APIContext.get().localPlayer().isAnimating()){
      this.itemCount_ = item_.getStackSize();
      APIContext.get().tabs().open(Tabs.MAGIC);
      AntiBan.mouse.CheckFigit();
      return this;
    }

    if(this.itemCount_ <= 0 || this.item_ == null){
      return Constants.Idling;
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
  }

  @Override
  public int actionTime() {
    return this.stateTime_;
  }

  @Override
  public String status() {
    return "Alching".concat(item_.getName());
  }
  
}
