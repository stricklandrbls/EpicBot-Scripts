package Smelter;
import java.util.Random;

import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;

public class Constants {
    public static String Mould      = "Ammo mould";
    public static String Cannonball = "Cannonball";
    public static String Steel      = "Steel bar";
    public static String Smelter    = "Furnace";

    public static int InterfaceDialogWidgetNumber = 270;
    public static int SmeltButtonWidgetNumber     = 14;

    public static Area FurnaceArea   = new Area( new Tile(3107, 3498, 0), 1 );
    public static Area EdgevilleBank = new Area( new Tile(3096, 3495, 0), 1 );

    public static Random Random = new Random();
}
