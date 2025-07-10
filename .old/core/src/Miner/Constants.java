package src.Miner;

import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.Tile;

import src.Miner.States.MiningState;

public class Constants {
  public static MiningState Mining = new MiningState();

  public static Area bankChestArea = new Area(
    new Tile(3014, 9718, 0), 2
  );
  public static Tile ironRockTiles[] = {
    new Tile(3021, 9721, 0),
    new Tile(3029, 9720, 0)
  };
  public static int currentOreTileIndex = 0;
  public static Tile nextRockTile(){
    return Constants.ironRockTiles[ Constants.currentOreTileIndex ];
  }
  public static Area nextRockArea(){
    return new Area(nextRockTile(), 2);
  }
}
