package proj.factory.command_interface;

import org.apache.log4j.Logger;
import proj.Exception.InvalidVariableName;
import proj.Exception.StackIsEmptyException;
import proj.Exception.UndefinedVariable;
import proj.Exception.WrongArgumentsQuantity;
import proj.values_stack.Stack;

import java.util.Vector;


public interface Command {
    public Stack stack = Stack.getStackInstance();
    public static final Logger logger = Logger.getLogger(Command.class);
    public void exec(String[] args) throws StackIsEmptyException, WrongArgumentsQuantity, UndefinedVariable, InvalidVariableName;
}
