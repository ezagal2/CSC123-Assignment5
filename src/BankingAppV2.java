//Erick Zagal (ezagal2@toromail.csudh.edu)
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingAppV2 {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args){
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
                    8 – Exit

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
                        return;
                    default:
                        break;
                }
            } catch (InputMismatchException e) {
                scan.nextLine();
            }
        } while (true);

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
        Account account = Bank.openAccount(firstName, lastName, ssn, "Savings", 0);
        System.out.println("\nThank you, the account number is " + account.getAccountNumber());
    }
    public static void accountStatement(){
        int accountNum;
        scan.nextLine();
        try {
            System.out.print("Enter account number: ");
            accountNum = scan.nextInt();
            Bank.printStatement(accountNum);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer account number.");
            scan.nextLine();
        }
    }
    public static void depositFunds(){
        int accountNum;
        double amount;
        scan.nextLine();
        System.out.print("Enter account number: ");
        accountNum = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter the amount to deposit: ");
        amount = scan.nextDouble();
        Bank.deposit(accountNum, amount);

    }
    public static void withdrawFunds(){
        int accountNum;
        double amount;
        scan.nextLine();
        System.out.print("Enter account number: ");
        accountNum = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter the amount to withdraw: ");
        amount = scan.nextDouble();
        Bank.withdraw(accountNum, amount);
    }
    public static void closeAccount(){
        int accountNum;
        scan.nextLine();
        System.out.print("Enter account number to close: ");
        accountNum = scan.nextInt();
        Bank.closeAccount(accountNum);
    }
}
