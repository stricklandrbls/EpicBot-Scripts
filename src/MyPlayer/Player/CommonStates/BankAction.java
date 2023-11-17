package MyPlayer.Player.CommonStates;

public class BankAction {
    public BankAction(String what, boolean all) {
        this.all = all;
        this.what = what;
    }
    public BankAction(String what, int amount) {
        this.what = what;
        this.amount = amount;
    }
    private String what;
    private int amount;
    private boolean all;
    public String what() { return what; }
    public int amount() { return amount; }
    public boolean all() { return all; }
}
