package lib.Bank;
import java.util.ArrayList;
import java.util.Iterator;

import com.epicbot.api.shared.APIContext;

import lib.Player.IPlayerState.*;
import lib.antiban.AntiBan;
import lib.antiban.Time.TypicalActionTime;
public class BankingState extends IPlayerState{
  private int actionTime_ = 1000;
  public boolean withdrawsFirst_ = true;

  private boolean depositInventory = false;

  private ArrayList<Deposit> deposits_ = new ArrayList<Deposit>();
  private ArrayList<Withdraw> withdraws_ = new ArrayList<Withdraw>();
  public IPlayerState nextState;


// for (Iterator<DrugStrength> it = aDrugStrengthList.iterator(); it.hasNext(); ) {
//   DrugStrength aDrugStrength = it.next();
//   if (!aDrugStrength.isValidDrugDescription()) {
//       it.remove();
//   }
// }
  private IPlayerState runBankActions(){
    System.out.println("runBankActions");
    if(withdrawsFirst_){{
      if(withdraws_.size() > 0)
        for(Iterator<Withdraw> wit = withdraws_.iterator(); wit.hasNext(); ){
          Withdraw w = wit.next();
          System.out.println(w);
          w.execute();
        }
      }
      if(deposits_.size() > 0){
        for(Iterator<Deposit> dit = deposits_.iterator(); dit.hasNext(); ){
          Deposit d = dit.next();
          System.out.println(d);
          d.execute();
        }
      }
    }
    else {
      if(deposits_.size() > 0){
        for(Iterator<Deposit> dit = deposits_.iterator(); dit.hasNext(); ){
          Deposit d = dit.next();
          System.out.println(d);
          d.execute();
        }
      }
      if(withdraws_.size() > 0){
        for(Iterator<Withdraw> wit = withdraws_.iterator(); wit.hasNext(); ){
          Withdraw w = wit.next();
          System.out.println(w);
          w.execute();
        }
      }
    }
    APIContext.get().bank().close();
    this.onExit();
    return nextState;
  }

  @Override
  public IPlayerState update() {
    System.out.println("update");
    if(APIContext.get().localPlayer().isMoving())
      return this;

    if(!APIContext.get().bank().isOpen()){
      APIContext.get().bank().open();
      return this;
    }
    return depositInventory
     ? dumpInventory()
     : runBankActions();
  }
  public void setDepositInventory(boolean v){ this.depositInventory = v; }
  private IPlayerState dumpInventory() {
    APIContext.get().bank().depositInventory();
    return nextState;
  }
  @Override
  public int actionTime() { return AntiBan.time.MaskActionTime(this.actionTime_, TypicalActionTime.SHORT); }
  @Override
  public String status() { return "Banking"; }
  @Override
  public String stateName() { return "Banking"; }
  @Override
  public void onExit(){
    deposits_.clear();
    withdraws_.clear();
    
    APIContext.get().bank().close();

    System.out.println("Deposits: "
      .concat(
        String.valueOf(numDepositsLeft()
      )
    ));
    System.out.println("Withdraws: "
      .concat(
        String.valueOf(numDepositsLeft()
      )
    ));
  }

  public void add(IBankAction action){ 
    System.out.println("add ".concat(action.type()));
    if(action.type().equals(Deposit.Type))
      this.deposits_.add((Deposit)action);
    else
      this.withdraws_.add((Withdraw)action);
  }
  public int numDepositsLeft(){
    return this.deposits_.size();
  }
  public int numWithdrawsLeft(){
    return this.withdraws_.size();
  }
}
