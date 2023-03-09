public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(String error_cannot_deposit){
        super(error_cannot_deposit);
    }
}
