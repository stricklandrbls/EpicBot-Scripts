package MonkFisher;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.List;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.entity.ItemWidget;
import com.epicbot.api.shared.model.Tile;
import com.epicbot.api.shared.model.path.LocalPath;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;
import com.epicbot.api.shared.util.paint.Dimension2D;
import com.epicbot.api.shared.util.paint.frame.FramePart;
import com.epicbot.api.shared.util.paint.frame.PaintFrame;
import com.epicbot.api.shared.util.paint.frame.Seperator;

import MonkFisher.Player.Player;

@ScriptManifest(name = "FishingGuildFisher", gameType = GameType.OS)
public class Script extends LoopScript{
    private List<ItemWidget> invItems;
    private Rectangle invItemGUI[] = new Rectangle[4];

    protected Player player = new Player(APIContext.get());
    @Override
    protected int loop() {
        player.update();
        return player.stateActionTime();
    }

    @Override
    public boolean onStart(String... arg0) {
        return true;
    }
    

    // @Override
    // protected void onPaint(Graphics2D g, APIContext ctx) {
    //     PaintFrame frame = new PaintFrame("Fisher");
        
    //     Rectangle test = new Rectangle(0, 0, 100, 200);
    //     g.draw(test);
    //     int i = 0;
    //     for(Rectangle r : invItemGUI) {
    //         switch(i) {
    //             case 0:
    //                 g.setColor(Color.BLACK);
    //                 break;
    //             case 1: 
    //                 g.setColor(Color.RED);
    //                 break;
    //             case 2:
    //                 g.setColor(Color.WHITE);
    //                 break;
    //             default:
    //                 g.setColor(Color.PINK);
    //         }
    //         i++;
    //         if(r != null)
    //             g.draw(r);
    //     }
        
    //     frame.addLine("State", player.status());
    //     frame.addLine(new Seperator(frame));
    //     frame.draw(g, 0, 170, ctx);
    // }
}
