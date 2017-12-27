package assignment2;

public class Bank {

    private int maxAccount = 1000;
    private Account[] accountList = new Account[maxAccount];
    private int numAccounts = 0;

    public boolean addAccount(long accNum, double bal, String own){
        if(findAccount(accNum) == -1){
            Account newAccount = new Account(accNum,bal,own);
            accountList[numAccounts++] = newAccount;
            return true;
        }
        return false;
    }
    public String printAccounts(){
        String temp = "";
        for(int i = 0; i<numAccounts; i++){
            temp += String.format("Account Number: %d has a balance of: %f and belongs to %s.%n",accountList[i].getAccountNumber(),accountList[i].getBalance(),accountList[i].getOwner());

        }
        return temp;
    }
    public int findAccount(long accNum){
        for(int i = 0; i < numAccounts; i++){
            if(accountList[i].getAccountNumber() == accNum){
                return i;
            }
        }
        return -1;
    }
    public void depositAccount(long accNum, double amount){
        int result = findAccount(accNum);
        if(result != -1){
            accountList[result].deposit(amount);
        }
    }
    public boolean withdrawAccount(long accNum, double amount){
        int result = findAccount(accNum);
        if(result != -1){
            return accountList[result].withdraw(amount);

        }
        return false;
    }
    public boolean transfer(long accNumFrom, long accNumTo, double amount){
        int resultFrom = findAccount(accNumFrom);
        int resultTo = findAccount(accNumTo);
        if(resultFrom != -1 && resultTo != -1){
            return accountList[resultFrom].transfer(accountList[resultTo],amount);

        }
        return false;
    }

}