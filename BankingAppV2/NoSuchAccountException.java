public class NoSuchAccountException extends Exception{
    public NoSuchAccountException(String error_cannot_deposit){
        super(error_cannot_deposit);
    }
}
