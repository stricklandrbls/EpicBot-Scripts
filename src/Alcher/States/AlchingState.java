package Alcher.States;

import java.util.Random;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.ItemWidget;
import com.epicbot.api.shared.methods.ITabsAPI.Tabs;
import com.epicbot.api.shared.model.Spell;

import MyPlayer.Player.IPlayerState;

public class AlchingState implements IPlayerState {
    class ItemsToAlch {

    }

    private Random random = new Random();
    private int itemCount = 0;
    private String itemsToAlch[] = { "Fire battlestaff" }; 
    private boolean spellReady = false;
    private int actionTime = 1234;

    @Override
    public IPlayerState update() {
        ItemWidget itemToAlch = APIContext.get().inventory().getItem(itemsToAlch[0]);
        if(itemToAlch == null)
            APIContext.get().script().stop("No more " + itemsToAlch[0]);
        if(!APIContext.get().magic().isSpellSelected()){
            selectSpell();
            spellReady = true;
            if(!APIContext.get().tabs().isOpen(Tabs.INVENTORY))
                APIContext.get().tabs().open(Tabs.INVENTORY);
            APIContext.get().mouse().move(itemToAlch.getRandomPoint());
        }
        if(isAlching()){
            return this;
        }


        itemCount = itemToAlch.getStackSize();

        if(!APIContext.get().magic().isSpellSelected()){
            selectSpell();
            spellReady = true;
        }
        itemToAlch.click();
        spellReady = false;
        if(actionTime > 2000)
            APIContext.get().mouse().moveOffScreen();
        else {
            switch(random.nextInt(10)) {
                case 0:
                case 1:
                    APIContext.get().mouse().moveRandomly();
            }
        }
        return this;
    }

    @Override
    public int actionTime() { 
        generateActionTime();
        return actionTime + 250; 
    }

    @Override
    public String status() { return "Alching"; }
    
    public boolean isAlching() {
        return APIContext.get().localPlayer().isAnimating();
    }

    private void selectSpell() {
        APIContext.get().magic().cast(Spell.Modern.HIGH_LEVEL_ALCHEMY, itemsToAlch[0]);
    }

    private void generateActionTime() {
        actionTime = random.nextInt(500);
    }
}
