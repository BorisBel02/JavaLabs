package proj.factory.commands;

import org.apache.log4j.Logger;
import proj.Exception.CommandException;
import proj.Exception.StackIsEmptyException;
import proj.Exception.UndefinedVariable;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;
import proj.values_stack.Stack;

public class DIV implements Command {
    private static final Logger logger = Logger.getLogger(DIV.class);

    @Override
    public void exec(String[] args, Stack stack) throws CommandException {
        if(args.length != 2) {
            WrongArgumentsQuantity e = new WrongArgumentsQuantity("wrong number of arguments. Must be 1, have: " + (args.length-1));
            logger.error(e);
            throw new CommandException("DIV", e);
        }
        double count;
        try{
            count = Double.parseDouble(args[1]);
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
            System.err.println(e);
            logger.error(e);
            return;
        }
        double res = 0;
        try {
            res = stack.pop();
            for(int i = 1; i < count; ++i){
                res /= stack.pop();
                stack.push(res);
            }

        } catch (StackIsEmptyException e) {
            throw new CommandException("DIV", e);
        }
        logger.info("DONE");

    }
}
