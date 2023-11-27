package SeedStealer;

import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;

import MyPlayer.Player.Player;
import SeedStealer.States.States;

@ScriptManifest(name = "Seed Stealer", gameType = GameType.OS)
public class Script extends LoopScript{
    private Player player = new Player(States.Organizing);

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
