package lib.Bank;

import com.epicbot.api.shared.APIContext;

public class Withdraw implements IBankAction {
  public static String Type = "Withdraw";
  private int amount_ = 0;
  private String  what_;
  private boolean all_ = false;

  public Withdraw(String itemName){
    this.what_ = itemName;
    this.all_ = true;
  }
  public Withdraw(String itemName, int amount){
    this.what_ = itemName;
    this.amount_ = amount;
  }

  public String what(){ return this.what_; }
  public boolean all(){ return this.all_; }

  @Override
  public String type(){ return Withdraw.Type; }

  @Override
  public void execute(){
    if(!APIContext.get().bank().contains(this.what_)){
      System.out.println("Bank does not contain: ".concat(this.what_));
      return;
    }
    if(this.all_)
      APIContext.get().bank().withdrawAll(this.what_);
    APIContext.get().bank().withdraw(this.amount_, this.what_);
  }
  public String toString(){
    return "Withdraw -> "
      .concat(this.what_)
      .concat(": ")
      .concat(String.valueOf(this.amount_))
      .concat(": all = ")
      .concat(String.valueOf(this.all_));
  }
}
