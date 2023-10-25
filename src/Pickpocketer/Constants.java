package Pickpocketer;

import java.util.Random;
import java.util.function.Predicate;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.Item;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;

public class Constants {
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
        for(String i : Constants.SpecialsToKeep) {
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