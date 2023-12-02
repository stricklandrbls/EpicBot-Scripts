package MonkFisher;

import java.awt.Graphics2D;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.model.Tile;
import com.epicbot.api.shared.model.path.LocalPath;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;
import com.epicbot.api.shared.util.paint.frame.PaintFrame;
import com.epicbot.api.shared.util.paint.frame.Seperator;

import MonkFisher.Player.Player;

@ScriptManifest(name = "Fisher", gameType = GameType.OS)
public class Script extends LoopScript{
    
    protected Player player = new Player(APIContext.get());
    @Override
    protected int loop() {
        player.update();
        return player.stateActionTime();
    }

    @Override
    public boolean onStart(String... arg0) {
        boolean canReach = Constants.CatherbyFishingAreas[0].getRandomTile().canReach(getAPIContext());
        System.out.println("canReach: "+ canReach);
        if(!canReach) {
            System.out.println("Attempting to get closest Tile");
            Tile closest = APIContext.get().walking().getClosestTileOnScreen(Constants.CatherbyFishingAreas[0].getRandomTile());
            if(closest != null){
                System.out.println("\t => Closest Tile {"+closest.getX()+", "+closest.getY()+"}");
                Tile[] path = APIContext.get().walking().findPath(closest).getTiles();
                APIContext.get().walking().walkPath(path);
            }
        }
        // LocalPath path = APIContext.get().walking().findPath(Constants.CatherbyBankArea.getRandomTile());
        // if(path == null)
        //     path = APIContext.get().walking().findPath(Constants.CatherbyMidPoint.getRandomTile());
        // if(path == null)
        //     System.out.println("All Paths is null");
        // else {
        //     for(Tile tile : path.getTiles()) {
        //         System.out.println("Tile { x: "+String.valueOf(tile.getX()) +", y: "+String.valueOf(tile.getY())+"}");
        //     }
        // }
            
        return false;
    }
    

    @Override
    protected void onPaint(Graphics2D g, APIContext ctx) {
        PaintFrame frame = new PaintFrame("Fisher");
        frame.addLine("State", player.status());
        frame.addLine(new Seperator(frame));
        frame.draw(g, 0, 170, ctx);
    }
}
