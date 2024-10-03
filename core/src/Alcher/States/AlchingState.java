package src.Alcher.States;

import java.awt.Graphics2D;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.ItemWidget;
import com.epicbot.api.shared.methods.ITabsAPI.Tabs;
import com.epicbot.api.shared.model.Spell;
import com.epicbot.api.shared.util.paint.frame.Line;
import com.epicbot.api.shared.util.paint.frame.PaintFrame;

import lib.Player.IPlayerState.IPlayerState;
import lib.Player.IPlayerState.StatusFrame;
import lib.antiban.AntiBan;
import src.Alcher.Constants;

public class AlchingState extends IPlayerState{
  private PaintFrame frame_ = new PaintFrame("Alching");
  private Line countLine = new Line(frame_, "Item Count", APIContext -> { return this.countStr_; });
  // private Line countLine = new Line(frame_, "Item Count", this.itemCount_);
  private class StatusLine extends Line {
    public String value = "";
    public StatusLine(PaintFrame frame, String txt, String value){
      super(frame, txt, APIContext -> { return value; });
    }
    @Override
    public String getValue(APIContext ctx){
      return this.value;
    }
  }
  private StatusLine statusLine_ = new StatusLine(frame_, "Item:", "None Selected");
  private StatusLine countLine_ = new StatusLine(frame_, "Count", "");
  private ItemWidget  item_;
  private int         itemCount_ = 0;
  private String      countStr_ = "";
  private boolean     spellReady_ = false;
  private int         stateTime_ = 500;

  public void setItem(String itemName){
    this.item_ = APIContext.get().inventory().getItem(itemName);
    this.itemCount_ = this.item_.getStackSize();
    statusLine_.value = item_.getName();
    countLine_.value = String.valueOf(itemCount_);
    frame_.addPart(statusLine_);
    frame_.addPart(countLine);
    frame_.addPart(countLine_); // this works
  }

  @Override
  public String stateName(){ return "Alching"; }
  
  @Override
  public IPlayerState update() {
    if(APIContext.get().localPlayer().isAnimating()){
      this.itemCount_ = item_.getStackSize();
      countLine_.value = String.valueOf(itemCount_);
      // System.out.println(statusLine_.value.concat(":").concat(countLine_.value));
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
  

  @Override
  public void draw(Graphics2D g, APIContext ctx){
    if(item_ == null)
      return;
    frame_.draw(g, 0, 200, ctx);
  }
}
