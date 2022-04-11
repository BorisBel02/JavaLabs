package proj.factory.commands;

import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SAVE implements Command {
    @Override
    public void exec(String[] args) throws WrongArgumentsQuantity {
        if(args.length != 2) {
            logger.error(this + " - wrong number of arguments. Must be 1, have: " + (args.length-1));
            throw new WrongArgumentsQuantity(" - wrong number of arguments. Must be 1, have: " + (args.length-1));
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
            logger.info(this + " - DONE");
        } catch (IOException e) {
            System.err.println(e.toString());
            System.err.println("Saving was unsuccessful");
        }
    }
}
