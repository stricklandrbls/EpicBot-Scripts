package lib.Bank;

public class Item {
  private String  what_;
  private boolean all_ = false;
  private int     amount_ = 0;
  
  public Item(String name){
    this.what_ = name;
    this.all_ = true;
  }
  public Item(String name, int amount){
    this.what_ = name;
    this.amount_ = amount;
  }

  public int amount(){ return this.amount_; }
  public String what(){ return this.what_; }
  public boolean all(){ return this.all_; }
}
