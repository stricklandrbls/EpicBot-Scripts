package MonkFisher.Fish;

public class Shark implements IFish {
    private Harpoon harpoon = new Harpoon(1520);
    @Override
    public String name() { return "Raw shark"; }

    @Override
    public IFishingEquipment equipment() { return this.harpoon; }

}
