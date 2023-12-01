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
        // boolean canReach = Constants.CatherbyBankArea.getRandomTile().canReach(getAPIContext());
        // System.out.println("canReach: "+ canReach);
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
            
        return true;
    }
    

    @Override
    protected void onPaint(Graphics2D g, APIContext ctx) {
        PaintFrame frame = new PaintFrame("Fisher");
        frame.addLine("State", player.status());
        frame.addLine(new Seperator(frame));
        frame.draw(g, 0, 170, ctx);
    }
}
