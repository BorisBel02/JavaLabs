package proj.run;

import org.apache.log4j.Logger;
import proj.Exception.*;
import proj.factory.Factory;
import proj.factory.command_interface.Command;
import proj.values_stack.Stack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Run {
    private static final Logger logger = Logger.getLogger(Run.class);
    public void run(String fileName) throws IOException, UndefinedCommandException, CommandException {
        Scanner scanner = null;
        if (fileName.length() == 0) {
            scanner = new Scanner(System.in);
        } else{
            scanner = new Scanner(new FileInputStream(fileName));
        }


        Factory factory = Factory.getFactory();
        Stack stack = new Stack();
        while(scanner.hasNextLine()){
            String str = scanner.nextLine();
            if(str.length() == 0){
                continue;
            }
            if(str.charAt(0) == '#'){
                continue;
            }
            String[] commandArgs = str.split(" ");
            Command command = null;

            try {
                command = factory.getCommand(commandArgs[0]);
            } catch (UndefinedCommandException e) {
                logger.error(e.getMessage());
                throw e;
            }
            try {
                command.exec(commandArgs, stack);
            } catch (CommandException e){
                logger.error(e.getMessage(), e.getCause());
                throw e;
            }
        }
    }
}
