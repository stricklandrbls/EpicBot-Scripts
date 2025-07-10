import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.GameType;
import com.epicbot.api.shared.script.LoopScript;
import com.epicbot.api.shared.script.ScriptManifest;

@ScriptManifest(name = "Alcher", gameType = GameType.OS)
public class Alcher extends LoopScript{
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

    @Override
    protected int loop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loop'");
    }

    @Override
    public boolean onStart(String... arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onStart'");
    }
}
