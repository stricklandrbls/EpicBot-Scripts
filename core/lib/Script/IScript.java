package lib.Script;

import lib.Player.MyPlayer;
import lib.Player.IPlayerState.IPlayerState;

public abstract class IScript {
  abstract MyPlayer InitializePlayer(IPlayerState initialState);
}
