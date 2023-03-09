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
    public void withdraw(double amount) throws AccountClosedException, InsufficientBalanceException, LessThanZeroException {
        if (amount <= 0) throw new LessThanZeroException("Error: amount cannot be zero or less");
        else if (!isOpen() && balance <= 0) {
            throw new AccountClosedException("Cannot withdraw amount, you have no money left in the account");
        } else if ((isOpen() && ((type.equals("Savings") && balance < amount) || balance - amount < overdraftLimit * -1))) {
            throw new InsufficientBalanceException(String.format("not enough funds to make a withdrawal, the balance is: %.2f\n", balance));
        } else {
            balance -= amount;
            transaction = String.format("%d : Debit : -%.2f", transactionId++, amount);
            transactions.add(transaction);
        }
    }

    public void deposit(double amount) throws AccountClosedException, LessThanZeroException {
        if(amount<=0) throw new LessThanZeroException("Error: amount cannot be zero or less");
        else if (!isOpen() && balance+amount > 0) throw new AccountClosedException("Error Cannot deposit, Account Closed");
        else {
            this.balance = this.balance + amount;
            transaction = String.format("%d : Credit : +%.2f", transactionId++, amount);
            transactions.add(transaction);
        }

    }
    public boolean isOpen() {
        return this.accountOpen;
    }
    public void closeAccount() {
        this.accountOpen=false;
    }
    public int getAccountNumber(){ return accountNumber;}
    public String printStatement(){
        String str = "";
        for (String s : transactions) {
            str += s + "\n";
        }
        str += String.format("Balance: %.2f\n\n", balance);
        return str;
    }
    public String getTransactions(){
        String t = "-----------------------------------\n";
        t += accountHolder.toString() + "\n";
        t += printStatement();
        return t;
    }
    public String toString() {
        String str = String.format("%d", accountNumber) + "(";
        str += type + ") : ";
        str += accountHolder.toString() + " : ";
        str += String.format("%.2f", balance) + " : ";
        str += isOpen() ? "Account Open" : "Account Closed";
        return str;
    }

    public double getBalance() {
        return balance;
    }
}
