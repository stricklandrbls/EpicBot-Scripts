package lib.Bank;

import com.epicbot.api.shared.APIContext;

public class BankAction {
  public static void DepositInventory(){
    APIContext.get().bank().depositInventory();
  }
  private String  what;
  private boolean all = false;
  private int     amount = 0;
  public BankAction(String item){
    this.what = item;
    this.all = true;
  }  
  public BankAction(String item, int amount){
    this.what = item;
    this.amount = amount;
  }
}
