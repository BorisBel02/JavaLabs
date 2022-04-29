package proj.Exception;

public class StackIsEmptyException extends CommandException{
    public StackIsEmptyException(String message){
        super(message);
    }
    public StackIsEmptyException(String message, Throwable cause){
        super(message, cause);
    }
}
