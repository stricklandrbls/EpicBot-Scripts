package lib.Player.IPlayerState;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.details.Locatable;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;

import lib.Script.StatusFrame.StatusFrame;
import lib.antiban.AntiBan;
import lib.antiban.Time.TypicalActionTime;

public class RelocatingState extends IPlayerState {
  private Area destArea_;
  private Tile destTile_;
  private Tile currentTile_;
  private Tile[] tilePath_;
  private IPlayerState nextState_;

  private int actionTime_ = 250;
  private boolean precise = false;

  public RelocatingState(){
    status = new StatusFrame("Relocating");
    status.add("dest", status.new LineData("Destination", ""));
  }
  public RelocatingState(
    Area destinationArea,
    IPlayerState nextState
  ){
    destArea_ = destinationArea;
    nextState_ = nextState;
  }
  public RelocatingState(
    Tile destinationArea,
    IPlayerState nextState
  ){
    this.precise = true;
    destTile_ = destinationArea;
    nextState_ = nextState;
  }
  
  @Override
  public void onEnter(){
    currentTile_ = APIContext.get().localPlayer().getLocation();
  }

  
  @Override
  public IPlayerState update() {
    System.out.println("relo::update");
    if(APIContext.get().localPlayer().isMoving())
      return this;
      
    currentTile_ = APIContext.get().localPlayer().getLocation();
    if(precise){
      if(currentTile_.equals(destTile_))
        return nextState_;
        tilePath_ = APIContext.get().walking().findPath(destTile_).getTiles();
      }
    else {
      if(destArea_.contains(currentTile_))
        return nextState_;
      tilePath_ = APIContext.get().walking().findPath(destArea_.getRandomTile()).getTiles();
    }
    if(tilePath_ == null)
      throw new UnsupportedOperationException("tile path is null");

    APIContext.get().walking().walkPath(tilePath_);
    APIContext.get().mouse().moveOffScreen();
    // status.update("dest", "walking to destination");
    return this;
  }

  @Override
  public int actionTime() {
    return AntiBan.time.MaskActionTime(actionTime_, TypicalActionTime.SHORT);
  }

  @Override
  public String status() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'status'");
  }

  @Override
  public String stateName() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'stateName'");
  }
  
}
