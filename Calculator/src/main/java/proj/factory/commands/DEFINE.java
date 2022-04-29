package proj.factory.commands;

import org.apache.log4j.Logger;
import proj.Exception.CommandException;
import proj.Exception.InvalidVariableName;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;
import proj.values_stack.Stack;


public class DEFINE implements Command {
    private static final Logger logger = Logger.getLogger(DEFINE.class);

    @Override
    public void exec(String[] args, Stack stack) throws CommandException {
        if(args.length != 3) {
            WrongArgumentsQuantity e = new WrongArgumentsQuantity("wrong number of arguments. Must be 1, have: " + (args.length-1));
            logger.error(e);
            throw new CommandException("DEFINE", e);
        }
        if(args[1].matches("^[a-zA-Z]+$")){
            stack.addVar(args[1], Double.parseDouble(args[2]));
        }
        else{
            InvalidVariableName e = new InvalidVariableName(args[1]);
            logger.error(e);
            throw new CommandException("DEFINE", e);
        }

        logger.info("DONE");
    }
}
