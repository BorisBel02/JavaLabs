package proj.factory.commands;

import proj.Exception.StackIsEmptyException;
import proj.Exception.UndefinedVariable;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;

public class DIV implements Command {
    @Override
    public void exec(String[] args) throws WrongArgumentsQuantity, UndefinedVariable {
        if(args.length != 2) {
            logger.error(this + " - wrong number of arguments. Must be 1, have: " + (args.length-1));
            throw new WrongArgumentsQuantity(" - wrong number of arguments. Must be 1, have: " + (args.length-1));
        }
        double count;
        try{
            count = Double.parseDouble(args[1]);
        }catch (NumberFormatException e) {
            count = stack.getVar(args[1]);
        }
        if(stack.size() < count){
            System.err.println("There is no " + args[1] + " elements in stack.\nStack have only " + stack.size() + "elements.");
            return;
        }
        double res = 0;
        try {
            res = stack.pop();
            for(int i = 1; i < count; ++i){
                res /= stack.pop();
            }

        } catch (StackIsEmptyException e) {
            e.printStackTrace();
        } finally {
            stack.push(res);
        }
        logger.info(this + " - DONE");

    }
}
