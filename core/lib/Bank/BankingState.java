package lib.Bank;
import java.util.ArrayList;

import com.epicbot.api.shared.APIContext;

import lib.Player.IPlayerState.*;
import lib.antiban.AntiBan;
import lib.antiban.Time.TypicalActionTime;
public class BankingState extends IPlayerState{
  private int actionTime_ = 1000;
  private boolean withdrawsFirst_ = true;

  private ArrayList<Deposit> deposits_ = new ArrayList<Deposit>();
  private ArrayList<Withdraw> withdraws_ = new ArrayList<Withdraw>();
  public IPlayerState nextState;

  @Override
  public IPlayerState update() {
    if(APIContext.get().localPlayer().isMoving())
      return this;

    if(!APIContext.get().bank().isOpen()){
      APIContext.get().bank().open();
    }
    
    if(withdrawsFirst_){
      if(withdraws_.size() > 0){
        for(Withdraw w : withdraws_){
          w.execute();
          withdraws_.remove(w);
        }
      }
      if(deposits_.size() > 0){
        for(Deposit d : deposits_){
          d.execute();
          withdraws_.remove(d);
        }
      }
      APIContext.get().bank().close();
      return nextState;
    }

    if(deposits_.size() > 0){
      for(Deposit d : deposits_){
        d.execute();
        withdraws_.remove(d);
      }
    }
    if(withdraws_.size() > 0){
      for(Withdraw w : withdraws_){
        w.execute();
        withdraws_.remove(w);
      }
    }
    APIContext.get().bank().close();
    return nextState;
  }

  @Override
  public int actionTime() { return AntiBan.time.MaskActionTime(this.actionTime_, TypicalActionTime.SHORT); }
  @Override
  public String status() { return "Banking"; }
  @Override
  public String stateName() { return "Banking"; }

  public void add(IBankAction action){ 
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
