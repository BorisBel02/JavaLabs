package proj.Exception;

public class WrongArgumentsQuantity extends CommandException{
    public WrongArgumentsQuantity(String message){
        super(message);
    }
    public WrongArgumentsQuantity(String message, Throwable cause){
        super(message, cause);
    }
}
