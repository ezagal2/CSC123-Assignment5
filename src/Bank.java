//Erick Zagal (ezagal2@toromail.csudh.edu)
import java.util.ArrayList;

public class Bank {
    private static ArrayList<Account> accounts=new ArrayList<>();
    private static int accountNumber =100;
    private Bank() {}
    public static Account openAccount(String firstName, String lastName, String SSN, String accountType) {
        return openAccount(firstName, lastName, SSN, accountType, 0);
    }
    public static Account openAccount(String firstName, String lastName, String SSN, String accountType,
                                      double overdraftLimit) {
        Person customer = new Person(firstName, lastName, SSN);
        Account account = new Account(accountNumber++, accountType, customer, overdraftLimit);
        accounts.add(account);
        return account;
    }
    public static void printAccounts() {
        for(Account a: accounts) {
            System.out.println(a);
        }
    }
    public static Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) return account;
        }
        return null;
    }
    public static void deposit(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found");
        } else {
            try{
                account.deposit(amount);
            } catch (AccountClosedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void withdraw(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found");
        } else {
            try{
                account.withdraw(amount);
            } catch (AccountClosedException | InsufficientBalanceException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void closeAccount(int accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found");
        } else {
            account.closeAccount();
        }
    }
    public static void printStatement(int accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found");
        } else {
            account.printStatement();
        }
    }


}
