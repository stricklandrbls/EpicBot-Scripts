package lib.Bank;

import com.epicbot.api.shared.APIContext;

public class Deposit implements IBankAction{
  public static String Type = "Deposit";
  private Item item_;
  public Deposit(Item item){
    this.item_ = item;
  }

  @Override
  public String type(){ return Deposit.Type; }
  @Override
  public Item item() {
    return this.item_;
  }

  @Override
  public void execute(){
    if(!this.item_.all())
      APIContext.get().bank().depositAll(this.item_.what());
    APIContext.get().bank().deposit(this.item_.amount(), this.item_.what());
  }
  
}
