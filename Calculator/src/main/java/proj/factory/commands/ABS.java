package proj.factory.commands;

import proj.Exception.StackIsEmptyException;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;

import java.util.ArrayList;
import java.util.List;

public class ABS implements Command {
    @Override
    public void exec(String[] args) throws StackIsEmptyException, WrongArgumentsQuantity {
        if(args.length != 2) {
            logger.error(this + " - wrong number of arguments. Must be 1, have: " + (args.length-1));
            throw new WrongArgumentsQuantity(" - wrong number of arguments. Must be 1, have: " + (args.length-1));
        }

        int count = Integer.parseInt(args[1]);
        if(stack.size() < count){
            logger.error(this + " - attempt to read more values than stack has.");
            throw new StackIsEmptyException("There is no " + count + " elements in stack.\nStack have only " + stack.size() + " elements.");
        }
        List<Double> list = new ArrayList<>();
        int i;

        for (i = 0; i < count; ++i) {
            list.add(Math.abs(stack.pop()));
        }
        for(int j = 0; j < i; ++j){
            stack.push(list.get((i - 1) - j));
        }
        logger.info(this + " - DONE");
    }
}
