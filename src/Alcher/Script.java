package Alcher;

import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;

import Alcher.States.States;
import MyPlayer.Player.Player;

@ScriptManifest(name = "Alcher", gameType = GameType.OS)
public class Script extends LoopScript {
    private Player player = new Player(States.Alching);
    @Override
    protected int loop() {
        player.update();
        return player.stateActionTime();
    }

    @Override
    public boolean onStart(String... arg0) {
        return true;
    }
    
}
