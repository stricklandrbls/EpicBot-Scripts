package AntiBan;

import java.util.Random;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.methods.ICameraAPI;
import com.epicbot.api.shared.methods.IMouseAPI;
import com.epicbot.api.shared.methods.ITabsAPI;
import com.epicbot.api.shared.methods.ITabsAPI.Tabs;
import com.epicbot.api.shared.model.Skill;
import com.epicbot.api.shared.model.Skill.Skills;

public class AntiBan {
    protected ICameraAPI camera;
    protected ITabsAPI tabs;
    protected IMouseAPI mouse;

    public Random random = new Random();

    public AntiBan(APIContext api) {
        this.camera = api.camera();
        this.tabs = api.tabs();
        this.mouse = api.mouse();
    }
    
    public void shakeCamera() {
        int yaw = this.camera.getYawDeg();
        int adjustments[] = { this.random.nextInt(25), this.random.nextInt(40) };
        int maxYawDeg = 360;
        this.camera.setYawDeg( (yaw += adjustments[0]) % maxYawDeg);
        if(adjustments[1] % 3 == 0)
            this.camera.setYawDeg((yaw -= adjustments[1]) % maxYawDeg);
    }

    public void twitchMouseAfterAction() {
        this.mouse.moveRandomly();
    }

    public void checksStats() {
        this.tabs.open(Tabs.SKILLS);
    }
    public void checkInv() {
        this.tabs.open(Tabs.INVENTORY);
    }
}
