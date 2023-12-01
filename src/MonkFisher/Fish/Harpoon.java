package MonkFisher.Fish;

class Harpoon implements IFishingEquipment {
    private int fishingSpotNPCId = 0;
    public Harpoon(int fishingSpotNPCId) { this.fishingSpotNPCId = fishingSpotNPCId; }
    @Override
    public String name() { return "Harpoon"; }
    @Override
    public String action() { return "Harpoon"; }
    @Override
    public int fishingSpotNPCId() { return fishingSpotNPCId; }

}