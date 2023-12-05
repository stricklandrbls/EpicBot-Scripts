package IdleFighter;

import java.util.function.Predicate;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.entity.Item;
import com.epicbot.api.shared.entity.ItemWidget;
import com.epicbot.api.shared.methods.IPrayerAPI.Prayer;
import com.epicbot.api.shared.methods.ITabsAPI.Tabs;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;

import MyPlayer.Player.Player;

@ScriptManifest(name = "Idle Fighter", gameType = GameType.OS)
public class Script extends LoopScript {
    class PrayerPotion implements Predicate<Item> {
        @Override
        public boolean test(Item t) {
            return t.getName().contains("Prayer potion");
        }
    }
    class DragonDagger implements Predicate<Item> {
        @Override
        public boolean test(Item t) {
            return t.getName().contains("Dragon dagger");
        }
    }
    class DragonMace implements Predicate<Item> {
        @Override
        public boolean test(Item t) {
            return t.getName().contains("Dragon mace");
        }
    }

    private PrayerPotion prayerPot = new PrayerPotion();
    private DragonDagger dds = new DragonDagger();
    private DragonMace mace = new DragonMace();
    private boolean useSpecial = false;
    @Override
    protected int loop() { 
        checkPrayer();
        checkSpecial();
        // checkBoosts();

        keepAlive();
        return 1500 + Constants.rand.nextInt(1000);
    }

    @Override
    public boolean onStart(String... arg0) { 
        return true; 
    }

    private void checkBoost() {

    }
    private void checkSpecial() {
        if(APIContext.get().combat().getSpecialAttackEnergy() >= 85) {
            if(APIContext.get().inventory().contains(dds))
                APIContext.get().inventory().getItem(dds).click();
            useSpecial = true;
            APIContext.get().mouse().moveRandomly();
        }
        if(APIContext.get().combat().getSpecialAttackEnergy() < 25) {
            if(APIContext.get().inventory().contains(mace))
                APIContext.get().inventory().getItem(mace).click();
            useSpecial = false;
            APIContext.get().mouse().moveOffScreen();
        }
        if(useSpecial && !APIContext.get().combat().isSpecialActive()){
            APIContext.get().combat().toggleSpecialAttack(true);
            APIContext.get().mouse().moveRandomly();
        }
    }

    private void checkPrayer() {
        if(!APIContext.get().prayers().isActive(Prayer.PROTECT_FROM_MELEE))
            APIContext.get().prayers().toggle(true, Prayer.PROTECT_FROM_MELEE);

        ItemWidget potion = APIContext.get().inventory().getItem(prayerPot);
        if(potion != null)
            checkPrayerPoints(potion);
        
    }
    private void checkPrayerPoints(ItemWidget potion) {
        if(APIContext.get().prayers().getPoints() < prayerThreshold()){
            potion.click();
            APIContext.get().mouse().moveOffScreen();
        }
    }
    private int prayerThreshold() {
        return 72-25+Constants.rand.nextInt(10);
    }


    private void keepAlive() {
        switch(Constants.rand.nextInt(20)) {
            case 1:
            case 2:
                int Pitch = APIContext.get().camera().getPitch();
                APIContext.get().camera().setPitch(Pitch + 5);
                APIContext.get().camera().setPitch(Pitch - 5);
                break;
            case 19:
                if(APIContext.get().tabs().getCurrent() == Tabs.INVENTORY)
                    APIContext.get().tabs().open(Tabs.SKILLS);
                else
                    APIContext.get().tabs().open(Tabs.INVENTORY);
        }
    }
}
