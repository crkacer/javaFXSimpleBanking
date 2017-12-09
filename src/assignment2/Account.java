package assignment2;

/**
 * Created by MGXA2 on 12/9/17.
 */
public class Account {

    private long accountNum;
    private double balance;
    private String owner;

    public Account(long accNum, double bal, String own) {
        accountNum = accNum;
        balance = bal;
        owner = own;
    }

    public boolean withdraw (double amount) {
        if (this.balance >= amount) {
            this.balance -=  amount;
            return true;
        } else {
            System.out.println("No deposit done");
            return false;
        }
    }

    public void deposit (double amount) {
        this.balance += amount;
    }

    public boolean transfer(Account recievedAccount, double amount) {
        if (withdraw(amount)) {
            recievedAccount.deposit(amount);
            return true;
        }

        return false;
    }

    public String toString() {

        String s = "Account Number: " + this.accountNum+ "\n" + "Balance: " + this.balance+ "\n" + "Account Owner: "+this.owner;
        return s;
    }

    public long getAccountNumber() {
        return this.accountNum;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getOwner() {
        return this.owner;
    }

}
