package Pickpocketer;
import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;
import com.epicbot.api.shared.util.paint.frame.PaintFrame;
import com.epicbot.api.shared.util.paint.frame.Seperator;

import java.awt.*;
import java.util.Random;

import Pickpocketer.Player;
import Pickpocketer.PlayerState.BankingState;

enum Program {
    ARDYKNIGHT,
    MSTRFARMER
}

@ScriptManifest(name = "Pickpocketer", gameType = GameType.OS)
public class Pickpocketer extends LoopScript {
    Program pickpocketProgram;
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
        return player.state.actionTime();
    }

    // @Override
    // protected void onPaint(Graphics2D g, APIContext ctx) {
    //     String frameTitle = (pickpocketProgram.equals(Program.ARDYKNIGHT))
    //         ? "Ardy Knight Pickpocketer"
    //         : "Master Farmer Pickpocketer";

    //     PaintFrame frame = new PaintFrame(frameTitle);
    //     frame.addLine("State", statusMsg);
    //     frame.addLine(new Seperator(frame));
    //     if(statusMsg.equals(States.Relocating.status()))
    //         frame.addLine("Path[]", "[...]");
    //     frame.draw(g, 0, 170, ctx);
    // }
}
