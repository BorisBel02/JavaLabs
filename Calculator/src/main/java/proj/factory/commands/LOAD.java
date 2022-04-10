package proj.factory.commands;

import proj.Exception.WrongArgumentsQuantity;
import proj.factory.command_interface.Command;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class LOAD implements Command {
    @Override
    public void exec(String[] args) throws WrongArgumentsQuantity {
        if(args.length != 2) {
            logger.error(this + " - wrong number of arguments. Must be 1, have: " + (args.length-1));
            throw new WrongArgumentsQuantity(" - wrong number of arguments. Must be 1, have: " + (args.length-1));
        }
        try {
            Scanner scanner = new Scanner(new FileInputStream(args[1]));
            while (scanner.hasNextDouble()){
                stack.push(scanner.nextDouble());
            }
            logger.info(this + " - DONE");
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
            System.err.println("Data was not loaded.");
        }


    }
}
