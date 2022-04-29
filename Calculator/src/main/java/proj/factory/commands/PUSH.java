package proj.factory.commands;

import org.apache.log4j.Logger;
import proj.Exception.CommandException;
import proj.Exception.UndefinedVariable;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;
import proj.values_stack.Stack;

public class PUSH implements Command {
    private static final Logger logger = Logger.getLogger(PUSH.class);

    @Override
    public void exec(String[] args, Stack stack) throws CommandException{
        if(args.length != 2) {
            WrongArgumentsQuantity e = new WrongArgumentsQuantity("wrong number of arguments. Must be 1, have: " + (args.length-1));
            logger.error(e);
            throw new CommandException("PUSH", e);
        }
        double digit;
        try{
            digit = Double.parseDouble(args[1]);
        }catch (NumberFormatException e) {
            try {
                digit = stack.getVar(args[1]);
            }catch (UndefinedVariable ex){
                logger.error(ex);
                throw new CommandException("PUSH", ex);
            }
        }
        stack.push(digit);
        logger.info("DONE");
    }
}
