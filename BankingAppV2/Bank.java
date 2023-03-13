//Erick Zagal (ezagal2@toromail.csudh.edu)
import java.util.HashMap;

public class Bank {
    private static int accountNumber =100;
    private static HashMap<Integer, Account> accounts = new HashMap<>();
    //Chose HashMap because I do not need the elements ordered. Which would be less optimal.
    private Bank() {}

    public static Account openAccount(String firstName, String lastName, String SSN, String accountType,
                                      double overdraftLimit) {
        Person customer = new Person(firstName, lastName, SSN);
        Account account = new Account(accountNumber++, accountType, customer, overdraftLimit);
        accounts.put(account.getAccountNumber(), account);
        return account;
    }
    public static void printAccounts() {
        for(Account a: accounts.values()) { System.out.println(a); }
    }
    public static Account findAccount(int accountNumber) throws NoSuchAccountException {
        Account a = accounts.get(accountNumber);
        if (a == null ){
            throw new NoSuchAccountException("Account does not exist");
        }
        return a;
    }


}
