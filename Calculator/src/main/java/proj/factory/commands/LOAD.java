package proj.factory.commands;

import org.apache.log4j.Logger;
import proj.Exception.CommandException;
import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;
import proj.values_stack.Stack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class LOAD implements Command {
    private static final Logger logger = Logger.getLogger(LOAD.class);

    @Override
    public void exec(String[] args, Stack stack) throws CommandException {
        if(args.length != 2) {
            WrongArgumentsQuantity e = new WrongArgumentsQuantity("wrong number of arguments. Must be 1, have: " + (args.length-1));
            logger.error(e);
            throw new CommandException("LOAD", e);
        }
        try {
            Scanner scanner = new Scanner(new FileInputStream(args[1]));
            while (scanner.hasNextDouble()){
                stack.push(scanner.nextDouble());
            }
            logger.info("DONE");
        } catch (FileNotFoundException e) {
            logger.error(e);
            System.err.println(e);
            System.err.println("Data was not loaded.");
            throw new RuntimeException("LOAD", e);
        }


    }
}
