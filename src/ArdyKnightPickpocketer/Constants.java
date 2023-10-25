package ArdyKnightPickpocketer;

import java.util.Random;

import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;

class CoinPurse {
    public int max() { return 56; }
    public int id() { return 22531; }
    public String interact() { return "Open-all"; }
}
public class Constants {
    public static int FoodId = 373;
    public static String FoodStr = "Swordfish";
    public static int LowHealth = 50;
    public static String KnightId = "Knight of Ardougne";
    public static String PickpocketAction = "Pickpocket";
    public static String TargetNPCName = "Master Farmer";
    public static String[] Equipment = { 
        "Dodgy necklace" 
    };   
    public static int CoinPurseMax = 56;
    public static String CoinPurseId = "Coin pouch";
    public static String CoinPurseInteract = "Open-all";
    public static Random random = new Random();
    public static Area BankBoundary = new Area(
        new Tile(2653, 3284, 0), 4
    );
}
