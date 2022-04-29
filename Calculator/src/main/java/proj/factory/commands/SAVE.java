package proj.factory.commands;

import org.apache.log4j.Logger;
import proj.Exception.CommandException;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;
import proj.values_stack.Stack;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SAVE implements Command {
    private static final Logger logger = Logger.getLogger(SAVE.class);

    @Override
    public void exec(String[] args, Stack stack) throws CommandException {
        if(args.length != 2) {
            WrongArgumentsQuantity e = new WrongArgumentsQuantity("wrong number of arguments. Must be 1, have: " + (args.length-1));
            logger.error(e);
            throw new CommandException("SAVE", e);
        }
        try {
            FileWriter writer = new FileWriter(args[1]);
            List<Double> copy = stack.returnStack();
            copy.forEach(e -> {
                try {
                    writer.write(e.toString() + '\n');
                } catch (IOException ex) {
                    System.err.println("Error while writing file: "+ex.getLocalizedMessage());
                }
            });
            logger.info("DONE");
        } catch (IOException e) {
            System.err.println(e.toString());
            System.err.println("Saving was unsuccessful");
        }
    }
}
