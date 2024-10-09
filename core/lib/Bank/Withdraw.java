package lib.Bank;

import com.epicbot.api.shared.APIContext;

public class Withdraw implements IBankAction {
  public static String Type = "Withdraw";
  private Item item_;
  public Withdraw(Item item){
    this.item_ = item;
  }

  @Override
  public String type(){ return Withdraw.Type; }
  @Override
  public Item item() {
    return this.item_;
  }

  @Override
  public void execute(){
    if(!this.item_.all())
      APIContext.get().bank().withdrawAll(this.item_.what());
    APIContext.get().bank().withdraw(this.item_.amount(), this.item_.what());
  }
}
