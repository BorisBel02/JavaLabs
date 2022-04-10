package proj.factory.commands;

import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;

public class PUSH implements Command {
    @Override
    public void exec(String[] args) throws WrongArgumentsQuantity {
        if(args.length != 2) {
            logger.error(this + " - wrong number of arguments. Must be 1, have: " + (args.length-1));
            throw new WrongArgumentsQuantity(" - wrong number of arguments. Must be 1, have: " + (args.length-1));
        }
        stack.push(Double.parseDouble(args[1]));
        logger.info(this + " - DONE");
    }
}
