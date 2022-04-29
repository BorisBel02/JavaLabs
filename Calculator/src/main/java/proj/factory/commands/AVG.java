package proj.factory.commands;

import org.apache.log4j.Logger;
import proj.Exception.CommandException;
import proj.Exception.StackIsEmptyException;
import proj.Exception.UndefinedVariable;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;
import proj.values_stack.Stack;

import java.util.List;


public class AVG implements Command {
    private static final Logger logger = Logger.getLogger(AVG.class);
    @Override
    public void exec(String[] args, Stack stack) throws CommandException {
        if(args.length != 2) {
            WrongArgumentsQuantity e = new WrongArgumentsQuantity("wrong number of arguments. Must be 1, have: " + (args.length-1));
            logger.error(e);
            throw new CommandException("AVG", e);
        }
        double count;
        try{
            count = Integer.parseInt(args[1]);
        }catch (NumberFormatException e) {
            try {
                count = stack.getVar(args[1]);
            }catch (UndefinedVariable ex){
                logger.error(ex);
                throw new CommandException("AVG", e);
            }
        }
        if(stack.size() < count){
            StackIsEmptyException e = new StackIsEmptyException("There is no " + count + " elements in stack.\nStack have only " + stack.size() + " elements.");
            logger.error(e);
            throw new CommandException("AVG", e);
        }

        double sum = 0;
        List<Double> list = stack.returnStack();
        for(int i = 0; i < count; ++i){
            sum += list.get(i);
        }
        System.out.println("Average of " + count + " stack elements: " + sum/count);
        logger.info("DONE");
    }
}
