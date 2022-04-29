package proj.Exception;

public class InvalidVariableName extends CommandException{
    public InvalidVariableName(String message){
        super(message);
    }
    public InvalidVariableName(String message, Throwable cause){
        super(message, cause);
    }
}
