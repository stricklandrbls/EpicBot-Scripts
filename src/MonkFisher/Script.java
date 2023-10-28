package MonkFisher;

import java.awt.Graphics2D;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;
import com.epicbot.api.shared.util.paint.frame.PaintFrame;
import com.epicbot.api.shared.util.paint.frame.Seperator;

import MonkFisher.Player.Player;

@ScriptManifest(name = "MonkFisher", gameType = GameType.OS)
public class Script extends LoopScript{
    
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
    

    @Override
    protected void onPaint(Graphics2D g, APIContext ctx) {
        PaintFrame frame = new PaintFrame("MonkFisher");
        frame.addLine("State", player.status());
        frame.addLine(new Seperator(frame));
        frame.draw(g, 0, 170, ctx);
    }
}
