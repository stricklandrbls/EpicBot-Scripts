package src.Tester;

import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;

import lib.Bank.Deposit;
import lib.Bank.Withdraw;
import lib.Player.MyPlayer;
import lib.Player.IPlayerState.SharedStates;

@ScriptManifest(name = "Test Scriptv0.1.1", gameType = GameType.OS)
public class Test extends LoopScript {
  MyPlayer player;
  @Override
  protected int loop() {
    player.update();
    return player.actionTime();
  }

  @Override
  public boolean onStart(String... arg0) {
    SharedStates.Banking.nextState = SharedStates.Idling;
    // CanDepositAllSingleItem();
    // CanDepositAllMultipleItems();
    // DoesNotThrowIfItemNotFound();
    // CanDepositAmountSingleItem();
    // CanDepositAmountMultipleItems();
    // ClearsActionsOnceFinished();
    // CanWithdrawAllSingleItem();
    // CanWithdrawAllMultipleItems();
    // CanWithdrawAndDepositInOneSession();
    // CanDepositFirstIfConfigured();
    RunsToBankIfNotVisible();
    System.out.println("onStart");
    player = new MyPlayer(SharedStates.Banking);

    return true;
  }
  public void RunsToOnscreenBank(){
    CanWithdrawAllSingleItem();
  }
  public void RunsToBankIfNotVisible(){
    CanWithdrawAllSingleItem();
  }
  public void CanDepositFirstIfConfigured(){
    SharedStates.Banking.withdrawsFirst_ = false;
    SharedStates.Banking.add(new Withdraw("Shark"));
    SharedStates.Banking.add(new Deposit("Pure essence"));
  }
  public void CanWithdrawAllSingleItem(){
    SharedStates.Banking.add(new Withdraw("Pure essence"));
  }
  public void CanWithdrawAllMultipleItems(){
    SharedStates.Banking.add(new Withdraw("Shark"));
    SharedStates.Banking.add(new Withdraw("Strange fruit"));
  }
  public void CanWithdrawAndDepositInOneSession(){
    SharedStates.Banking.add(new Deposit("Shark"));
    SharedStates.Banking.add(new Withdraw("Pure essence"));
  }
  public void ClearsActionsOnceFinished(){
    CanDepositAmountMultipleItems();
  }
  public void CanDepositAmountSingleItem(){
    SharedStates.Banking.add(new Deposit("Air rune", 200));
  }
  public void CanDepositAmountMultipleItems(){
    SharedStates.Banking.add(new Deposit("Air rune", 200));
    SharedStates.Banking.add(new Deposit("Earth rune", 100));
  }
  public void CanDepositAllSingleItem(){
    SharedStates.Banking.add(new Deposit("Raw tuna"));
  }

  public void CanDepositAllMultipleItems(){
    SharedStates.Banking.add(new Deposit("Raw tuna"));
    SharedStates.Banking.add(new Deposit("Bowl"));
    SharedStates.Banking.add(new Deposit("Pure essence"));
  }
  public void DoesNotThrowIfItemNotFound(){
    SharedStates.Banking.add(new Deposit("Raw tuna"));
  }

}
