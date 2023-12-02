package MonkFisher;

import java.util.Random;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;

import AntiBan.AntiBan;
import MonkFisher.Fish.IFish;
import MonkFisher.Fish.IFishingEquipment;
import MonkFisher.Fish.Shark;

public class Constants {
    public static Shark Shark = new Shark();
    public static String fishName = "Raw monkfish";
    public static String fishAction = "Net";
    public static String[] equipment = { "Small fishing net" };
    
    public static int fishingSpot = 4316;

    public static Area BankArea = new Area(new Tile(2330, 3689, 0), 2);
    public static Area FishingAreas[] = { 
        new Area(new Tile(2347, 3700, 0), 1),
        new Area(new Tile(2315, 3698, 0), 2),
    };
    public static Area FishingGuildFishingArea = new Area(
        new Tile(2599, 3421), 1
    );
    public static Tile FishingGuildFishingAreaTile = new Tile(2600, 3421,0);
    public static Area FishingGuildBank = new Area(new Tile(2589, 3418), 1);
    // public static Area CatherbyBankArea = new Area(new Tile(2809, 3440, 0), 1);
    // public static Area CatherbyMidPoint = new Area(new Tile(2824, 3437, 0), 1);
    // public static Area CatherbyFishingAreas[] = { 
    //     new Area(new Tile(2854, 3427, 0), 2),
    //     new Area(new Tile(2843, 3433, 0), 1),
    // };
    public static Random random = new Random();
}
