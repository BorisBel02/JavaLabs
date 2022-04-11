package proj.factory.commands;

import proj.Exception.UndefinedVariable;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;

public class PUSH implements Command {
    @Override
    public void exec(String[] args) throws WrongArgumentsQuantity, UndefinedVariable {
        if(args.length != 2) {
            logger.error(this + " - wrong number of arguments. Must be 1, have: " + (args.length-1));
            throw new WrongArgumentsQuantity(" - wrong number of arguments. Must be 1, have: " + (args.length-1));
        }
        double digit;
        try{
            digit = Double.parseDouble(args[1]);
        }catch (NumberFormatException e) {

            digit = stack.getVar(args[1]);
        }
        stack.push(digit);
        logger.info(this + " - DONE");
    }
}
