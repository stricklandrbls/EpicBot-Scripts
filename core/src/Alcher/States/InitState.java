package src.Alcher.States;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.ItemWidget;

import lib.Player.IPlayerState.IPlayerState;
import src.Alcher.Constants;

public class InitState extends IPlayerState{

  private String statusStr_ = "Awaiting Item Selection";
  private ItemWidget selectedItem_;
  @Override
  public IPlayerState update() {
    ItemWidget selectedItem = APIContext.get().inventory().getSelectedItem();
    if(selectedItem != null) {
      statusStr_ = "Item was selected: ".concat(selectedItem.getName());
      Constants.Alching.setItem(selectedItem.getName());
      APIContext.get().inventory().deselectItem();
      return Constants.Alching;
    }
    return this;
  }

  @Override
  public int actionTime() {
    return 1500;
  }

  @Override
  public String status() {
    return this.statusStr_;
  }

}
