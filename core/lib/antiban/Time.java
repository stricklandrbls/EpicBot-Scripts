package lib.antiban;

import lib.antiban.AntiBan;

public class Time {
  private int     minBound_ = 500;
  private int[]   TimeBounds_ = {1000, 2500, 5000};
  public enum TypicalActionTime {
    SHORT,
    MEDIUM,
    LONG
  }

  public int MaskActionTime(int actionTime, TypicalActionTime humanTime){
    return actionTime + __generateMultiplier(humanTime);
  }

  public void SetMinimumTimeBound(int bound){
    this.minBound_ = bound;
  }

  private int __generateMultiplier(TypicalActionTime typical){
    int ret = 0;
    if(typical == TypicalActionTime.SHORT)
      ret = AntiBan.Randomizer.nextInt(TimeBounds_[0] + minBound_);
    if(typical == TypicalActionTime.MEDIUM)
      ret = AntiBan.Randomizer.nextInt(TimeBounds_[1] + minBound_);
    if(typical == TypicalActionTime.LONG)
      ret = AntiBan.Randomizer.nextInt(TimeBounds_[2] + minBound_);
    return ret;
  }
}
