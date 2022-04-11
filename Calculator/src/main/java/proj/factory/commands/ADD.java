package proj.factory.commands;

import proj.Exception.StackIsEmptyException;
import proj.Exception.UndefinedVariable;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;

public class ADD implements Command {
    @Override
    public void exec(String[] args) throws StackIsEmptyException, WrongArgumentsQuantity, UndefinedVariable {
        if(args.length != 2) {
            logger.error(this + " - wrong number of arguments. Must be 1, have: " + (args.length-1));
            throw new WrongArgumentsQuantity(" - wrong number of arguments. Must be 1, have: " + (args.length-1));
        }
        double count;
        try{
            count = Integer.parseInt(args[1]);
        }catch (NumberFormatException e) {

            count = stack.getVar(args[1]);
        }
        if(stack.size() < count){
            System.err.println("There is no " + args[1] + " elements in stack.\nStack have only " + stack.size() + "elements.");
            return;
        }
        double sum = 0;
        for (int i = 0; i < count; ++i) {
            sum += stack.pop();
        }
        stack.push(sum);
        logger.info(this + " - DONE");
    }
}
