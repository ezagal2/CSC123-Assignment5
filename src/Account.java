//Erick Zagal (ezagal2@toromail.csudh.edu)
import java.util.ArrayList;

public class Account {
    //Fields

    private int accountNumber;
    private String type;
    private boolean accountOpen;
    private double balance;
    private Person accountHolder;
    private double overdraftLimit;
    private int transactionId = 120;
    private String transaction;
    private ArrayList<String> transactions = new ArrayList<String>();
    //Constructor
    public Account(int accountNumber, String type, Person accountHolder, double overdraftLimit) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.accountHolder = accountHolder;
        this.overdraftLimit = overdraftLimit;
        accountOpen=true;
    }
    public void withdraw(double amount) throws AccountClosedException, InsufficientBalanceException {
        if (!isOpen() && balance <= 0) {
            throw new AccountClosedException("Cannot withdraw amount, you have no money left in the account");
        } else if ((isOpen() && ((type.equals("Savings") && balance < amount) || balance - amount < overdraftLimit * -1)) || amount < 0) {
            throw new InsufficientBalanceException(String.format("not enough funds to make a withdrawal, the balance is: %.2f\n", balance));
        } else {
            balance -= amount;
            transaction = String.format("%d : Debit : -%.2f", transactionId++, amount);
            transactions.add(transaction);
            System.out.printf("Withdrawal successful, the balance is: %.2f\n", balance);
        }
    }

    public void deposit(double amount) throws AccountClosedException {
        if(amount<0) System.out.printf("Deposit failed, the balance is: %.2f\n", balance);
        else if (!isOpen() && balance+amount > 0){
            throw new AccountClosedException("Error Cannot deposit, Account Closed");
        }
        else {
            this.balance = this.balance + amount;
            transaction = String.format("%d : Credit : +%.2f", transactionId++, amount);
            transactions.add(transaction);
            System.out.printf("Deposit successful, the new balance is: %.2f\n", balance);
        }

    }
    public boolean isOpen() {
        return this.accountOpen;
    }
    public void closeAccount() {
        this.accountOpen=false;
        System.out.printf("Account closed, current balance is %.2f, deposits are no longer possible\n", balance);
    }
    public int getAccountNumber(){ return accountNumber;}
    public void printStatement(){
        for (String s : transactions) {
            System.out.println(s);
        }
        System.out.printf("Balance: %.2f\n\n", balance);
    }
    public String toString() {
        String str = String.format("%d", accountNumber) + "(";
        str += type + ") : ";
        str += accountHolder.toString() + " : ";
        str += String.format("%.2f", balance) + " : ";
        str += isOpen() ? "Account Open" : "Account Closed";
        return str;
    }
}
