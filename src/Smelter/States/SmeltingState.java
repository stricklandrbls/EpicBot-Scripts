package Smelter.States;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.SceneObject;
import com.epicbot.api.shared.entity.WidgetChild;

import MyPlayer.Player.IPlayerState;
import MyPlayer.Player.CommonStates.BankAction;
import Smelter.Constants;

public class SmeltingState implements IPlayerState {
    private String  statusMsg       = "Smelting";
    private int     actionTime      = 5000;
    private boolean smeltingStarted = false;

    private SceneObject smelterObject;

    @Override
    public IPlayerState update() {
        if(outOfMaterials()) 
            return setBankingState();

        if(smeltingStarted && amIdle()) {
                statusMsg = "Selecting: " + Constants.Cannonball;
                if(!hasStartedSmelting()){
                    statusMsg = "Have not selected Cannonballs";
                    APIContext.get().mouse().move(menuWidget().getRandomPoint());
                    APIContext.get().mouse().click();
                    statusMsg = "Smelting: " + Constants.Cannonball;
                    APIContext.get().mouse().moveOffScreen();
                }
                return this;
        }
        if(hasStartedSmelting()) return this;

        statusMsg = "Finding: " + Constants.Smelter;
        if(smelterObject == null)
            smelterObject = APIContext.get()
                .objects()
                .query()
                .named(Constants.Smelter)
                .results()
                .first();
        
        smelterObject.click();
        smeltingStarted = true;
        statusMsg = "Smelting: " + Constants.Cannonball;
        
        return this;
    }

    @Override
    public int actionTime() { return Constants.Random.nextInt(1000) + actionTime; }

    @Override
    public String status() { return statusMsg; }
    
    private boolean outOfMaterials() {
        return !APIContext.get().inventory().contains(Constants.Steel);
    }
    private IPlayerState setBankingState() {
            States.Banking.addWithdrawAction(new BankAction(Constants.Steel, true));
            States.Banking.stateAfterBanking(this);
            smeltingStarted = false;
            return States.Banking;
    }
    private boolean smelting() {
        return APIContext.get().localPlayer().isAnimating();
    }
    private boolean amIdle() {
        return !smelting();
    }
    private WidgetChild menuWidget() {
        return APIContext.get()
            .widgets()
            .get(Constants.InterfaceDialogWidgetNumber)
            .getChild(Constants.SmeltButtonWidgetNumber);
    }
    private boolean hasStartedSmelting() {
        return APIContext.get().inventory().getCount(Constants.Steel) < 26;
    }
}
