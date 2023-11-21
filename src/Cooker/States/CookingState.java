package Cooker.States;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.SceneObject;
import com.epicbot.api.shared.entity.WidgetChild;

import MyPlayer.Player.IPlayerState;
import MyPlayer.Player.CommonStates.BankAction;

public class CookingState implements IPlayerState {
    private String cooker;
    private String itemToCook;
    private String cookedItem = "Tuna";
    private static String statusMsg = "";


    private SceneObject CookerObject;

    private static int CookingAnimationId = 897;

    private boolean actionInitiated = false;
    private int actionTimeoutMax = 10;
    private int actionTimeoutCount = 0;
    private int actionTime = 500;

    public CookingState(
        String cookerItemName,
        String rawFoodItem
    ) {
        System.out.println("Cooking Const..");
        this.cooker = cookerItemName;
        this.itemToCook = rawFoodItem;
    }

    @Override
    public IPlayerState update() {
        if(outOfRawFood()) {
            CookingState.statusMsg = "Out of " + this.itemToCook;
            this.actionInitiated = false;
            
            States.Banking.depositInventory();
            States.Banking.addWithdrawAction(new BankAction(this.itemToCook, true));
            States.Banking.stateAfterBanking(States.Cooking);
            return States.Banking;
        }

        if(this.actionInitiated) {
            if(!APIContext.get().localPlayer().isAnimating()) {
                CookingState.statusMsg = "Already Interacted";
                if(!hasStartedCooking()){
                    CookingState.statusMsg = "Menu Open";
                    cookingMenuWidget().click();
                    APIContext.get().mouse().moveOffScreen();
                    return this;
                }
            }            
        }

        if(hasStartedCooking()) return this;

        CookingState.statusMsg = "Interacting";
        if( this.CookerObject == null ) 
            this.CookerObject = APIContext.get().objects().query().named(this.cooker).results().first();

        this.CookerObject.interact("Cook");
        this.actionInitiated = true;
        actionTimeoutCount = 0;
        APIContext.get().mouse().moveRandomly();
        
        return this;
    }

    @Override
    public int actionTime() { return 500 + actionTime; }

    @Override
    public String status() { return statusMsg; }

    private boolean outOfRawFood() {
        return !APIContext.get().inventory().contains(this.itemToCook);
    }

    private WidgetChild cookingMenuWidget() {
        CookingState.statusMsg = "Checking Widgets";
        return APIContext.get().widgets().get(270).getChild(14);
    }

    private boolean hasStartedCooking() {
        CookingState.statusMsg = this.itemToCook + " count: " + String.valueOf(APIContext.get().inventory().getCount(this.itemToCook));
        return APIContext.get().inventory().getCount(this.itemToCook) < 28;
    }
}
