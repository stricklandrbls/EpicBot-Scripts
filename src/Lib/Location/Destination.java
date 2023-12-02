package Lib.Location;

import com.epicbot.api.shared.APIContext;
import com.epicbot.api.shared.entity.details.Locatable;
import com.epicbot.api.shared.model.Area;
import com.epicbot.api.shared.model.path.LocalPath;

public class Destination implements IDestination {
    protected Locatable destination = null;
    public Destination(Locatable destination){
        
    }

    @Override
    public LocalPath path() { }

    @Override
    public boolean canReach() { return this.destination.canReach(APIContext.get()); }
    @Override
    public Locatable destination() { return this.destination; }
    @Override
    public Area destinationArea(int tileRange) { return this.destination.getArea(tileRange); }
    
}
