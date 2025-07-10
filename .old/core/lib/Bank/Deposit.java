package lib.Bank;

import com.epicbot.api.shared.APIContext;

public class Deposit implements IBankAction{
  public static String Type = "Deposit";
  private int amount_ = 0;
  private String  what_;
  private boolean all_ = false;

  public Deposit(String itemName){
    this.what_ = itemName;
    this.all_ = true;
  }
  public Deposit(String itemName, int amount){
    this.what_ = itemName;
    this.amount_ = amount;
    this.all_ = false;
  }

  public String what(){ return this.what_; }
  public boolean all(){ return this.all_; }

  @Override
  public String type(){ return Deposit.Type; }

  @Override
  public void execute(){
    if(!APIContext.get().inventory().contains(this.what_)){
      System.out.println("Inventory does not contain: ".concat(this.what_));
      return;
    }
    if(this.all_)
      APIContext.get().bank().depositAll(this.what_);
    APIContext.get().bank().deposit(this.amount_, this.what_);
  }
  public String toString(){
    return "Deposit -> "
      .concat(this.what_)
      .concat(": ")
      .concat(String.valueOf(this.amount_))
      .concat(": all = ")
      .concat(String.valueOf(this.all_));
  }
  
}
