package proj.factory.commands;

import proj.Exception.StackIsEmptyException;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;

public class PRINT implements Command {

    @Override
    public void exec(String[] args) throws StackIsEmptyException, WrongArgumentsQuantity {
        if(args.length != 1) {
            logger.error(this + " - wrong number of arguments. Must be 0, have: " + (args.length-1));
            throw new WrongArgumentsQuantity(" - wrong number of arguments. Must be 0, have: " + (args.length-1));
        }
        System.out.println(stack.pseudo_pop());
        logger.info(this + " - DONE");
    }
}
