package proj.factory.commands;

import org.apache.log4j.Logger;
import proj.Exception.CommandException;
import proj.Exception.StackIsEmptyException;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;
import proj.values_stack.Stack;

public class POP  implements Command {
    private static final Logger logger = Logger.getLogger(POP.class);

    @Override
    public void exec(String[] args, Stack stack) throws CommandException {
        if(args.length != 1) {
            WrongArgumentsQuantity e = new WrongArgumentsQuantity("wrong number of arguments. Must be 1, have: " + (args.length-1));
            logger.error(e);
            throw new CommandException("POP", e);
        }
        try {
            stack.pop();
        } catch (StackIsEmptyException e){
            throw new CommandException("POP", e);
        }

        logger.info("DONE");
    }
}
