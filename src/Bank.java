//Erick Zagal (ezagal2@toromail.csudh.edu)
import java.util.ArrayList;

public class Bank {
    private static ArrayList<Account> accounts=new ArrayList<>();
    private static int accountNumber =100;
    private Bank() {}

    public static Account openAccount(String firstName, String lastName, String SSN, String accountType,
                                      double overdraftLimit) {
        Person customer = new Person(firstName, lastName, SSN);
        Account account = new Account(accountNumber++, accountType, customer, overdraftLimit);
        accounts.add(account);
        return account;
    }
    public static void printAccounts() {
        for(Account a: accounts) { System.out.println(a); }
    }
    public static Account findAccount(int accountNumber) throws NoSuchAccountException {
        for (Account account : accounts) { if (account.getAccountNumber() == accountNumber) return account; }
        throw new NoSuchAccountException("Account does not exist");
    }


}
