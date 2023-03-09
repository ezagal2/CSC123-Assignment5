//Erick Zagal (ezagal2@toromail.csudh.edu)
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DriverClass {
    static Scanner scan = new Scanner(System.in);
    static File transactions = new File("transactions.txt");
    static FileWriter fileWriter;
    public static void main(String[] args) {
        int input;
        do {
            System.out.print("""
                    1 – Open a Checking account
                    2 – Open Saving Account
                    3 – List Accounts
                    4 – Account Statement
                    5 – Deposit funds
                    6 – Withdraw funds
                    7 – Close an account
                    8 - Save Transactions
                    9 – Exit

                    Please enter your choice:\s""");
            try {
                input = scan.nextInt();
                switch (input) {
                    case 1:
                        openCheckingAccount();
                        break;
                    case 2:
                        openSavingAccount();
                        break;
                    case 3:
                        Bank.printAccounts();
                        break;
                    case 4:
                        accountStatement();
                        break;
                    case 5:
                        depositFunds();
                        break;
                    case 6:
                        withdrawFunds();
                        break;
                    case 7:
                        closeAccount();
                        break;
                    case 8:
                        saveTransactions();
                        break;
                    case 9:
                        return;
                    default:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nEnter a valid choice");
                scan.nextLine();
            } catch (NoSuchAccountException | LessThanZeroException | InsufficientBalanceException | IOException |
                     AccountClosedException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private static void saveTransactions() throws IOException, NoSuchAccountException {
        int accountNum;
        scan.nextLine();
        System.out.print("Enter account Number: ");
        accountNum = scan.nextInt();
        Account a = Bank.findAccount(accountNum);
        fileWriter = new FileWriter(transactions, true);
        fileWriter.append(a.getTransactions());
        fileWriter.close();
        System.out.println("\nDone\n");
    }
    public static void openCheckingAccount(){
        scan.nextLine();
        System.out.print("\nEnter first name: ");
        String firstName = scan.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scan.nextLine();
        System.out.print("Enter social security number: ");
        String ssn = scan.nextLine();
        System.out.print("Enter overdraft limit: ");
        double overdraftLimit = scan.nextDouble();
        Account account = Bank.openAccount(firstName, lastName, ssn, "Checking",overdraftLimit);
        System.out.println("\nThank you, the account number is " + account.getAccountNumber());
    }
    public static void openSavingAccount(){
        scan.nextLine();
        System.out.print("\nEnter first name: ");
        String firstName = scan.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scan.nextLine();
        System.out.print("Enter social security number: ");
        String ssn = scan.nextLine();
        Account a = Bank.openAccount(firstName, lastName, ssn, "Savings", 0);
        System.out.println("\nThank you, the account number is " + a.getAccountNumber());
    }
    public static void accountStatement() throws NoSuchAccountException {
        int accountNum;
        scan.nextLine();
        System.out.print("Enter account number: ");
        accountNum = scan.nextInt();
        Account a = Bank.findAccount(accountNum);
        System.out.println(a.printStatement());

    }
    public static void depositFunds() throws NoSuchAccountException, LessThanZeroException, AccountClosedException {
        int accountNum;
        double amount;
        scan.nextLine();
        System.out.print("Enter account number: ");
        accountNum = scan.nextInt();
        scan.nextLine();
        Account a = Bank.findAccount(accountNum);
        System.out.print("Enter the amount to deposit: ");
        amount = scan.nextDouble();
        a.deposit(amount);
        System.out.printf("Deposit successful, the new balance is: %.2f\n", a.getBalance());
    }
    public static void withdrawFunds() throws NoSuchAccountException, LessThanZeroException, InsufficientBalanceException, AccountClosedException {
        int accountNum;
        double amount;
        scan.nextLine();
        System.out.print("Enter account number: ");
        accountNum = scan.nextInt();
        scan.nextLine();
        Account a = Bank.findAccount(accountNum);
        System.out.print("Enter the amount to withdraw: ");
        amount = scan.nextDouble();
        a.withdraw(amount);
        System.out.printf("Withdrawal successful, the balance is: %.2f\n", a.getBalance());
    }
    public static void closeAccount() throws NoSuchAccountException {
        int accountNum;
        scan.nextLine();
        System.out.print("Enter account number to close: ");
        accountNum = scan.nextInt();
        Account a = Bank.findAccount(accountNum);
        a.closeAccount();
        System.out.printf("Account closed, current balance is %.2f\n", a.getBalance());
    }
}
