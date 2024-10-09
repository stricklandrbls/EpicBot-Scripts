package lib.Bank;

public interface IBankAction {
  public abstract Item item();
  public abstract void execute();
  public String type();
}
