package proj.factory.commands;

import org.apache.log4j.Logger;
import proj.Exception.CommandException;
import proj.Exception.StackIsEmptyException;
import proj.Exception.UndefinedVariable;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;
import proj.values_stack.Stack;

public class ADD implements Command {
    private static final Logger logger = Logger.getLogger(ADD.class);
    @Override
    public void exec(String[] args, Stack stack) throws CommandException {
        if(args.length != 2) {
            WrongArgumentsQuantity e = new WrongArgumentsQuantity("wrong number of arguments. Must be 1, have: " + (args.length-1));
            logger.error(e);
            throw new CommandException("ADD", e);
        }
        double count;
        try{
            count = Integer.parseInt(args[1]);
        }catch (NumberFormatException e) {
            try {
                count = stack.getVar(args[1]);
            }catch (UndefinedVariable ex){
                logger.error(ex);
                throw new CommandException("ADD", e);
            }

        }
        if(stack.size() < count){
            StackIsEmptyException e = new StackIsEmptyException("There is no " + args[1] + " elements in stack.\nStack have only " + stack.size() + "elements.");
            logger.info(e);
            throw new CommandException("ADD", e);
        }
        double sum = 0;
        for (int i = 0; i < count; ++i) {
            sum += stack.pop();
        }
        stack.push(sum);
        logger.info(this + " - DONE");
    }
}
