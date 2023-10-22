package Pickpocketer;
import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;
import com.epicbot.api.shared.util.paint.frame.PaintFrame;

import java.awt.*;
import java.util.Random;

import Pickpocketer.Player;

@ScriptManifest(name = "Pockpicketer", gameType = GameType.OS)
public class Pickpocketer extends LoopScript {
    Random rand = new Random();
    Player player = new Player();
    String statusMsg = "<NONE>";

    @Override
    public boolean onStart(String... strings) {
        player.state = States.Initializing;
        return true;
    }

    @Override
    protected int loop() {
        statusMsg = player.update();
        moveScreen();
        return player.state.actionTime();
    }

    @Override
    protected void onPaint(Graphics2D g, APIContext ctx) {
        PaintFrame frame = new PaintFrame("Test Script");
        frame.addLine("State", statusMsg);
        frame.draw(g, 0, 170, ctx);
    }

    protected void moveMouse() {
        switch(rand.nextInt(10)) {

        }
    }
    protected void moveScreen() {
        switch(rand.nextInt(30)) {
            case 10:
                APIContext.get().camera().setPitch(80+rand.nextInt(15));
                break;
            case 20:
                APIContext.get().camera().setYaw(rand.nextInt(2050));
                break;
        }
    }
}
