package proj.factory.commands;

import proj.Exception.StackIsEmptyException;
import proj.Exception.UndefinedVariable;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;

import java.util.List;


public class AVG implements Command {
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
            logger.error(this + " - attempt to read more values than stack has.");
            throw new StackIsEmptyException("There is no " + count + " elements in stack.\nStack have only " + stack.size() + " elements.");
        }

        double sum = 0;
        List<Double> list = stack.returnStack();
        for(int i = 0; i < count; ++i){
            sum += list.get(i);
        }
        System.out.println("Average of " + count + " stack elements: " + sum/count);
        logger.info(this + " - DONE");
    }
}
