package MyPlayer.Player;

public interface IPlayerState {
    public IPlayerState update();
    public int  actionTime();
    public String status();
}
