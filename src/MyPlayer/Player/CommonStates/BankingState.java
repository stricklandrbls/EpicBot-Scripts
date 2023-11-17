package MyPlayer.Player.CommonStates;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.model.Area;

import Cooker.States.Constants;
import MyPlayer.Player.IPlayerState;


public class BankingState implements IPlayerState {
    interface IOnExitCallback {
        public void call(IPlayerState stateOnExit);
    }
    private BankAction[] _deposits = new BankAction[15];
    private BankAction[] _withdraws = new BankAction[15];
    private int _depositActions = 0;
    private int _withdrawActions = 0;
    
    private IPlayerState stateAfterBanking;

    private boolean _depositInventory = false;
    @Override
    public IPlayerState update() {
        
        if(!APIContext.get().bank().isOpen())
            APIContext.get().bank().open();
        
        if(this._depositActions > 0) {
            if(this._depositInventory)
                APIContext.get().bank().depositInventory();
            else {
                for(int i = 0; i < this._depositActions; i++) {
                    if(this._deposits[i].all())
                        APIContext.get().bank().depositAll(this._deposits[i].what());
                    else
                        APIContext.get().bank().deposit(this._deposits[i].amount(), this._deposits[i].what());
                }                 
            }

            this._depositActions = 0;
            this._depositInventory = false;
            return this;        
        }

        for(int i = 0; i < this._withdrawActions; i++) {
            if(this._withdraws[i].all())
                APIContext.get().bank().withdrawAll(this._withdraws[i].what());
            else
                APIContext.get().bank().withdraw(this._withdraws[i].amount(), this._withdraws[i].what());


        }
        this._withdrawActions = 0;
        
        APIContext.get().bank().close();
        return stateAfterBanking;
    }
    public void depositInventory() {
        this._depositInventory = true;
        this._depositActions++;
    }
    public boolean addDepositAction(BankAction deposit) {
        this._deposits[this._depositActions] = deposit;
        this._depositActions++;
        return true;
    }
    public boolean addWithdrawAction(BankAction withdraw) {
        this._withdraws[this._withdrawActions] = withdraw;
        this._withdrawActions++;
        return true;
    }

    public void stateAfterBanking(IPlayerState state) {

    }

    public void stateAfterBanking(IPlayerState state, Area relocateTo) {

    }

    private IPlayerState onExit(IOnExitCallback onExitFn) { // TODO: Strategy pattern this to accept a Fn pointer
        return null;
    }
    @Override
    public String status() { return "Banking"; }
    @Override
    public int actionTime() { return 500 + Constants.random.nextInt(500);}

    
}