package Cooker;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;

import Cooker.States.States;
import MyPlayer.Player.Player;

@ScriptManifest(name = "Cooker", gameType = GameType.OS)
public class Script extends LoopScript{

    private Player player = new Player(States.Cooking);

    @Override
    protected int loop() {
        player.update();
        System.out.println(player.status());
        return player.stateActionTime();
    }

    @Override
    public boolean onStart(String... arg0) {
        System.out.println("Starting..");
        return true;
    }
    
}
