public class AccountClosedException extends Exception{
    public AccountClosedException(String error_cannot_deposit) {
        super(error_cannot_deposit);
    }
}
