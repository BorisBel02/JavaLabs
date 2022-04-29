package proj.factory.commands;

import org.apache.log4j.Logger;
import proj.Exception.CommandException;
import proj.Exception.StackIsEmptyException;
import proj.Exception.UndefinedVariable;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;
import proj.values_stack.Stack;

import java.util.ArrayList;
import java.util.List;

public class ABS implements Command {
    private static final Logger logger = Logger.getLogger(ADD.class);
    @Override
    public void exec(String[] args, Stack stack) throws CommandException {
        if(args.length != 2) {
            WrongArgumentsQuantity e = new WrongArgumentsQuantity("wrong number of arguments. Must be 1, have: " + (args.length-1));
            logger.error(e);
            throw new CommandException("ABS", e);
        }

        double count;
        try{
            count = Integer.parseInt(args[1]);
        }catch (NumberFormatException e) {
            try {
                count = stack.getVar(args[1]);
            }catch (UndefinedVariable ex){
                throw new CommandException("ABS", ex);
            }
        }
        if(stack.size() < count){
            StackIsEmptyException e = new StackIsEmptyException("There is no " + count + " elements in stack.\nStack have only " + stack.size() + " elements.");
            logger.error(e);
            throw new CommandException("ABS", e);
        }
        List<Double> list = new ArrayList<>();
        int i;
        try {
            for (i = 0; i < count; ++i) {
                list.add(Math.abs(stack.pop()));
            }

            for (int j = 0; j < i; ++j) {
                stack.push(list.get((i - 1) - j));
            }
        } catch (StackIsEmptyException e){
            logger.info(e);
            throw new CommandException("ABS", e);
        }
        logger.info("DONE");
    }
}
