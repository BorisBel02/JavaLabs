package proj.factory.commands;

import proj.Exception.InvalidVariableName;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;


public class DEFINE implements Command {
    @Override
    public void exec(String[] args) throws WrongArgumentsQuantity, InvalidVariableName {
        if(args.length != 3) {
            logger.error(this + " - wrong number of arguments. Must be 2, have: " + (args.length-1));
            throw new WrongArgumentsQuantity(" - wrong number of arguments. Must be 2, have: " + (args.length-1));
        }
        if(args[1].matches("^[a-zA-Z]+$")){
            stack.addVar(args[1], Double.parseDouble(args[2]));
        }
        else{
            logger.error(this + "InvalidVariableName");
            throw new InvalidVariableName(args[1]);
        }

        logger.info(this + " - DONE");
    }
}
