package Alcher.States;

import com.epicbot.api.shared.APIContext;

import MyPlayer.Player.IPlayerState;

public class AlchingState implements IPlayerState {
    class ItemsToAlch {

    }

    private static int itemCount = 0;
    private static String itemsToAlch[] = null; 
    
    @Override
    public IPlayerState update() {
        
        return this;
    }

    @Override
    public int actionTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionTime'");
    }

    @Override
    public String status() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'status'");
    }
    
    public boolean isAlching() {
        return APIContext.get().localPlayer().isAnimating();
    }
}
