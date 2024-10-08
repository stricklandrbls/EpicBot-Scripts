package lib.Player.IPlayerState;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;

import lib.antiban.AntiBan;
import lib.antiban.Time.TypicalActionTime;

public class RelocatingState extends IPlayerState {

  private Area dest_;
  private Tile currentTile_;
  private Tile[] tilePath_;
  private IPlayerState nextState_;

  private int actionTime_ = 250;
  
  @Override
  public void onEnter(){
    currentTile_ = APIContext.get().localPlayer().getLocation();
  }

  
  @Override
  public IPlayerState update() {
    if(dest_.contains(currentTile_))
      return nextState_;
    
    tilePath_ = APIContext.get().walking().findPath(dest_.getRandomTile()).getTiles();
    APIContext.get().walking().walkPath(tilePath_);
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
