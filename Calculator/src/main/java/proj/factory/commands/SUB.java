package proj.factory.commands;

import proj.Exception.StackIsEmptyException;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;

public class SUB implements Command {
    @Override
    public void exec(String[] args) throws StackIsEmptyException, WrongArgumentsQuantity {
        if(args.length != 2) {
            logger.error(this + " - wrong number of arguments. Must be 1, have: " + (args.length-1));
            throw new WrongArgumentsQuantity(" - wrong number of arguments. Must be 1, have: " + (args.length-1));
        }
        int count = Integer.parseInt(args[1]);
        if(stack.size() < count){
            System.err.println("There is no " + args[1] + " elements in stack.\nStack have only " + stack.size() + "elements.");
            return;
        }
        double res = 0;

        res = stack.pop();
        for (int i = 0; i < count - 1; ++i) {
            res -= stack.pop();
        }
        stack.push(res);
        logger.info(this + " - DONE");
    }
}
