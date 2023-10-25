package ArdyKnightPickpocketer;

import java.util.Random;

import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;

public class Constants {
    public class CoinPurse {
        public int max() { return 56; }
        public int id() { return 22531; }
        public String interact() { return "Open-all"; }
    }
    public static int FoodId = 373;
    public static int LowHealth = 50;
    public static int KnightId = 3297;
    public static String PickpocketAction = "Pickpocket";
    public static String TargetNPCName = "Master Farmer";
    public static String[] Equipment = { 
        "Dodgy necklace" 
    };   
    public static CoinPurse coinpurse;
    public static Random random = new Random();
    public static Area BankBoundary = new Area(
        new Tile(2655, 3287, 0),
        new Tile(2655, 3285, 0),
        new Tile(2649, 3287, 0),
        new Tile(2649, 3285, 0)
    );
}
