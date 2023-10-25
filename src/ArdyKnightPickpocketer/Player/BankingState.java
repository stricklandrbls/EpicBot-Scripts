package ArdyKnightPickpocketer.Player;

import java.util.List;

import com.epicbot.api.shared.APIContext;

import ArdyKnightPickpocketer.Constants;

class BankAction {
    private String what;
    private int amount;
    public BankAction(String what, int amount) {
        this.what = what;
        this.amount = amount;
    }
    public String what() { return what; }
    public int amount() { return amount; }
}

public class BankingState implements Player.IPlayerState {
    // TODO: Combine these into a separate data structure
    private BankAction[] _withdraws = new BankAction[28];
    private BankAction[] _deposits = new BankAction[28];
    private int _withdrawActions = 0;
    private int _depositActions = 0;
    private CompletedState done = new CompletedState();
    @Override
    public void update(Player p) {
        if(!APIContext.get().bank().isOpen())
            APIContext.get().bank().open();

        for( int i = 0; i < _withdrawActions; i++ )
            APIContext.get().bank().withdraw(this._withdraws[i].amount(), this._withdraws[i].what());
        for( int i = 0; i < _depositActions; i++ )
            APIContext.get().bank().deposit(this._deposits[i].amount(), this._deposits[i].what());

        APIContext.get().bank().close();
        this._withdrawActions = 0;
        this._depositActions = 0;
        p.state = done;
    }

    public boolean addWithdrawAction(BankAction withdraw) {
        this._withdraws[this._withdrawActions] = withdraw;
        this._withdrawActions++;
        return true;
    }
    public boolean addDepositAction(BankAction deposit) {
        this._deposits[this._depositActions] = deposit;
        this._depositActions++;
        return true;
    }
    @Override
    public String status() { return "Banking"; }
    @Override
    public int actionTime() { return 500 + Constants.random.nextInt(500);}

}

class CompletedState implements Player.IPlayerState {

        @Override
        public void update(Player p) { }

        @Override
        public String status() { return "Completed!"; }

        @Override
        public int actionTime() { return 5000; }

}