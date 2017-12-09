package assignment2;

/**
 * Created by MGXA2 on 12/9/17.
 */
public class ChequingAccount extends Account {

    private double overDraftLimit;

    public ChequingAccount (long accNum, double bal, String own, double over) {
        super(accNum, bal, own);
        this.overDraftLimit = over;
    }

    @Override
    public boolean withdraw (double amount) {
        if (this.getBalance() >= amount) {
            return super.withdraw(amount);
        } else if (this.getBalance() + overDraftLimit >= amount) {
            double residual = amount - this.getBalance();
            overDraftLimit -= residual;
            return super.withdraw(this.getBalance());
        } else {
            System.out.println("No deposit done");
        }
        return false;

    }
    @Override
    public String toString() {
        String s = "Account Number: " + super.getAccountNumber() +"\n" + "Balance: " + super.getBalance()+ "\n" + "Account Owner: "+super.getOwner() + "\n"+"Overdraft Limit: " + this.overDraftLimit;
        return s;
    }

}
