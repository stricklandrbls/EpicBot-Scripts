package lib.antiban;
import com.epicbot.api.shared.APIContext;

import lib.antiban.AntiBan;

public class Mouse {
  
  private int   offscreenChecksRemaining_ = 50;
  private int   figitChecksRemainig = 15;

  private int   offscreenModulo  = 10;
  private int   figitModulo  = 10;

  private int   offscreenCheckBound = 100;
  private int   figitCheckBound = 100;

  public void CheckMoveOffscreen(){
    if(offscreenChecksRemaining_ <= 1 || __shouldRun(offscreenChecksRemaining_, offscreenModulo))
    {
      offscreenChecksRemaining_ = 50;
      APIContext.get().mouse().moveOffScreen();
      return;
    }
    offscreenChecksRemaining_--;
  }
  public void CheckFigit(){
    if(figitChecksRemainig <= 1 || __shouldRun(figitChecksRemainig, figitModulo)){
      figitChecksRemainig = 15;
      APIContext.get().mouse().moveRandomly();
      return;
    }
    figitChecksRemainig--;
  }

  public void MoveOffscreenOrFigit(){
    CheckFigit();
    CheckMoveOffscreen();
  }

  private boolean __shouldRun(int total, int modulo){
    int target = AntiBan.Randomizer.nextInt(modulo);
    target++;
    return total % target == 0;
  }
}
