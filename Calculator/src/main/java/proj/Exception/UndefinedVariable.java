package proj.Exception;

public class UndefinedVariable extends CommandException{
    public UndefinedVariable(String message){
        super(message);
    }
    public UndefinedVariable(String message, Throwable cause){
        super(message, cause);
    }
}
