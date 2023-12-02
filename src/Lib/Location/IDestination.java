package Lib.Location;

import com.epicbot.api.shared.entity.details.Locatable;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.path.LocalPath;

public interface IDestination {
    public Locatable destination();
    public Area destinationArea(int tileRange);
    public LocalPath path();
    public boolean canReach();
    public void walkTo();
}
