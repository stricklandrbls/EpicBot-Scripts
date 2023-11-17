package MonkFisher;

import java.util.Random;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;

import AntiBan.AntiBan;

public class Constants {
    public static String fishName = "Raw monkfish";
    public static String fishAction = "Net";
    public static String[] equipment = { "Small fishing net" };
    
    public static int fishingSpot = 4316;

    public static Area BankArea = new Area(new Tile(2330, 3689, 0), 2);
    public static Area FishingAreas[] = { 
        new Area(new Tile(2347, 3700, 0), 1),
        new Area(new Tile(2315, 3698, 0), 2),
    };

    public static Random random = new Random();
}
