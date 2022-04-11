package proj.run;

import org.apache.log4j.Logger;
import proj.Exception.*;
import proj.factory.Factory;
import proj.factory.command_interface.Command;
import java.util.Scanner;


public class Run {
    private static final Logger logger = Logger.getLogger(Run.class);
    public void run(Scanner scanner){
        Factory factory = Factory.getFactory();
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
                logger.error(e);
                System.err.println(e.toString());
                System.err.println("You can change command. Write new command below or 'n' for a shutdown:");
                Scanner inScanner = new Scanner(System.in);
                while (true) {
                    String comm = inScanner.nextLine();
                    if ("n".equals(comm)) {
                        System.exit(0);
                    } else {
                        commandArgs = comm.split(" ");
                        try{
                            command = factory.getCommand(commandArgs[0]);
                            break;
                        } catch (UndefinedCommandException ex){
                            System.err.println(ex.getLocalizedMessage());
                        }
                    }
                }
            }
            try {
                command.exec(commandArgs);
            } catch (StackIsEmptyException | WrongArgumentsQuantity | UndefinedVariable | InvalidVariableName e){
                System.err.println(e.toString());
            }
        }
    }
}
