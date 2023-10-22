package Pickpocketer;

import java.util.Random;
import java.util.function.Predicate;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.Item;
import com.epicbot.api.shared.entity.NPC;
import com.epicbot.api.shared.methods.IEquipmentAPI.Slot;
import com.epicbot.api.shared.methods.ITabsAPI.Tabs;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;

class States {
    public static Pickpocketing Pickpocketing = new Pickpocketing();
    public static Relocating Relocating = new Relocating();
    public static Eating Eating = new Eating();
    public static Stunned Stunned = new Stunned();
    public static EquipingItems EquipingItems = new EquipingItems();
    public static Banking Banking = new Banking();
    public static Organizing Organizing = new Organizing();
    public static Initializing Initializing = new Initializing();
}
class DontDropTheseItems implements Predicate<Item>{
    @Override
    public boolean test(Item t) {
        if(t.getId() == Constants.FoodId)
            return true;

        String itemName = t.getName();
        for(String i : Constants.Equipment) {
            if(i.equals(itemName))
                return true;
        }
        for(String i : Constants.SeedsToKeep) {
            if(itemName.equals(seedName(i)))
                return true;
        }
        return false;
    }
    private String seedName(String item) {
        return item + " seed";
    }
}
class NecessaryEquipment implements Predicate<Item> {
    public static int minimumFoodCount = 10;
    public static int minimumEquipmentCount = 3;
    @Override
    public boolean test(Item t) {
        if(t.getId() == Constants.FoodId)
            return true;

        String itemName = t.getName();
        for(String i : Constants.Equipment) {
            if(i.equals(itemName))
                return true;
        }
        return false;
    }

}
class Constants {
    public static int LowHealth = 50;
    public static int FoodId = 373;
    public static String PickpocketAction = "Pickpocket";
    public static String TargetNPCName = "Master Farmer";
    public static Area DraynorCenter = new Area(new Tile(3081, 3249, 0), 4);
    public static Area FarmingGuildArea = new Area(new Tile(1233, 3727, 0), 5);
    public static Area FarmingGuildBank = new Area(new Tile(1251, 3740, 0), 1);
    public static Area FarmingGuildSeedBank = new Area(new Tile(1246, 3740, 0), 1);
    public static Area DraynorBank = new Area(new Tile(3093, 3242), 1);
    public static Random rand = new Random();
    public static String[] Equipment = {"Dodgy necklace"};    
    public static String[] SeedsToKeep = {
        "Ranarr",
        "Strawberry",
        "Watermelon",
        "Torstol",
        "Snapdragon",
        "Avantoe",
        "Kwuarm",
        "Snape grass",
        "Landtadyme",
        "Cadatine",
        "Dwarf weed"
    };
    public static String[] SpecialsToKeep = {
        "Seaweed spore",
        "Mushroom spore"
    };
    public static DontDropTheseItems isWantedItem = new DontDropTheseItems();
    public static void dropUnwantedSeeds() {
        APIContext.get().inventory().dropAllExcept(isWantedItem);
    }
}
enum PlayerEvents {

}

public interface PlayerState {
    public void update(Player p);
    public String status();
    public int actionTime();
}

class Pickpocketing implements PlayerState {
    Area targetArea;
    private static NPC target;
    @Override
    public void update(Player p) {
        if(APIContext.get().inventory().isFull()) {
            p.state = States.Organizing;
            return;
        }
        if(p.inCombat()) {
            p.state = States.Stunned;
            return;
        }
        if(p.health() < Constants.LowHealth) {
            p.state = States.Eating;
            return;
        }

        target = APIContext.get().npcs().query().named(Constants.TargetNPCName).results().first();
        
        if(target == null) {
            States.Relocating.destination = targetArea;
            States.Relocating.stateUponArrival = States.Pickpocketing;
            p.state = States.Relocating;
            return;
        }
        else {
            if(!p.isInteractingWith(target))
                target.interact(Constants.PickpocketAction);
            targetArea = target.getArea(4);
            return ;
        }
    }
    public String status() { return "Pickpocketing"; }
    @Override
    public int actionTime() { return 250 + Constants.rand.nextInt(521); }
}

class Relocating implements PlayerState {
    protected Area destination;
    protected PlayerState stateUponArrival;

    public void setDestination(Area to, PlayerState nextState) { 
        destination = to; 
        stateUponArrival = nextState;
    }
    public String status() { return "Relocating "; }

    @Override
    public void update(Player p) {
        System.out.println(String.valueOf(p.location().getX()) + "," + String.valueOf(p.location().getY()));
        if(destination.contains(p.location()))
            p.state = stateUponArrival;
        else
            APIContext.get().walking().walkOnMap(destination.getRandomTile());
    }
    public int actionTime() { return 750 + Constants.rand.nextInt(750); }

}

class Eating implements PlayerState {
    private int healthyPercent = 95;
    @Override
    public void update(Player p) {
        if(!APIContext.get().inventory().contains(Constants.FoodId)) {
            States.Relocating.destination = Constants.FarmingGuildBank;
            States.Relocating.stateUponArrival = States.Banking;
            Constants.dropUnwantedSeeds();
            p.state = States.Relocating;
            return;
        }
        if(p.health() < healthyPercent)
            APIContext.get().inventory().interactItem("Eat", Constants.FoodId);
        else
            p.state = States.Pickpocketing;
    }
    public String status() { return "Eating"; }
    public int actionTime() { return 1750 + Constants.rand.nextInt(750); }

}

class Stunned implements PlayerState {

    @Override
    public void update(Player p) {
        mouseMove(p);
        if(!p.inCombat())
            p.state = States.Pickpocketing;
        else {
            p.state = States.EquipingItems;
        }
    }

    @Override
    public String status() {
        return "Stunned!";
    }

    private void mouseMove(Player p) {
        switch(Constants.rand.nextInt(10)) {
            case 0:
                APIContext.get().tabs().open(Tabs.INVENTORY);
                APIContext.get().mouse().move(APIContext.get().inventory().getItemAt(Constants.rand.nextInt(27)));
                break;
            case 5:
                if(APIContext.get().tabs().getCurrent().equals(Tabs.INVENTORY))
                    APIContext.get().tabs().open(Tabs.EQUIPMENT);
                else
                    APIContext.get().tabs().open(Tabs.INVENTORY);
                break;
            case 8:
            case 9:
            case 10:
                p.state = States.Organizing;

        }
    }
    public int actionTime() { return 1750 + Constants.rand.nextInt(750); }

}

class Banking implements PlayerState {
    @Override
    public void update(Player p) {
        if(!APIContext.get().bank().isOpen())
            APIContext.get().bank().open();
        else {
            withdrawNecklaces();
            int foodWithdrawAmount = NecessaryEquipment.minimumFoodCount - APIContext.get().inventory().getCount(Constants.FoodId);
            if(foodWithdrawAmount > 0)
                APIContext.get().bank().withdraw(foodWithdrawAmount, Constants.FoodId);
            else {
                APIContext.get().bank().close();
                States.Relocating.destination = Constants.FarmingGuildArea;
                States.Relocating.stateUponArrival = States.Pickpocketing;
                p.state = States.Relocating;
            }
        }
    }

    @Override
    public String status() { return "Banking"; }
    public int actionTime() { return 750 + Constants.rand.nextInt(750); }
    private boolean withdrawNecklaces() {
        int necklaceWithdrawAmount = NecessaryEquipment.minimumEquipmentCount - APIContext.get().inventory().getCount(Constants.Equipment);
        if(!APIContext.get().inventory().contains(Constants.Equipment))
            return APIContext.get().bank().withdraw(necklaceWithdrawAmount, Constants.Equipment);
        return false;
    }

}

class EquipingItems implements PlayerState {

    @Override
    public void update(Player p) {
        if(!APIContext.get().inventory().contains(Constants.Equipment)) {
            States.Relocating.destination = Constants.FarmingGuildBank;
            States.Relocating.stateUponArrival = States.Banking;
            p.state = States.Relocating;
            return;
        }
        if(!APIContext.get().equipment().contains(Slot.NECK, Constants.Equipment))
            APIContext.get().inventory().getItem(Constants.Equipment).interact("Wear");
        p.state = States.Pickpocketing;
    }

    @Override
    public String status() { return "Equiping Items"; }

    @Override
    public int actionTime() { return 250 + Constants.rand.nextInt(500); }

}

class Organizing implements PlayerState {

    @Override
    public void update(Player p) {
        Constants.dropUnwantedSeeds();
        if(APIContext.get().inventory().isFull()){
            States.Relocating.destination = Constants.FarmingGuildBank;
            States.Relocating.stateUponArrival = States.Banking;
        }
        p.state = States.Pickpocketing;
    }

    @Override
    public String status() { return "Dropping Unwanted Seeds"; }
    public int actionTime() { return 750 + Constants.rand.nextInt(750); }

}

class Initializing implements PlayerState {

    @Override
    public void update(Player p) {
        if(playerHasPickpocketingItems()){
            States.Relocating.destination = Constants.FarmingGuildArea;
            States.Relocating.stateUponArrival = States.Pickpocketing;
        }
        else {
            States.Relocating.destination = Constants.FarmingGuildBank;
            States.Relocating.stateUponArrival = States.Banking;
        }
        p.state = States.Relocating;
    }

    @Override
    public String status() { return "Initializing"; }
    public int actionTime() { return 750 + Constants.rand.nextInt(750); }
    private boolean playerHasPickpocketingItems() {
        int foodCount = APIContext.get().inventory().getCount(Constants.FoodId);
        int necklaceCount = APIContext.get().inventory().getCount(Constants.Equipment);
        System.out.println("Food: " + String.valueOf(foodCount) + "; Necklaces: " + String.valueOf(necklaceCount));
        if(foodCount < NecessaryEquipment.minimumFoodCount ||
             necklaceCount < NecessaryEquipment.minimumEquipmentCount)
            return false;
        return true;
    }
}