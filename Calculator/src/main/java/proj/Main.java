package proj;


import proj.Exception.CommandException;
import proj.Exception.UndefinedCommandException;
import proj.run.Run;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {

        String srcName = "";
        if(args.length == 1){
            srcName = args[0];
        }
        else if(args.length > 1){
            System.err.println("Wrong arguments quantity");
            logger.error("Execution failed due to wrong arguments quantity");
            System.exit(-1);
        }
        try {
            Run run = new Run();
            run.run(srcName);
        } catch (IOException | UndefinedCommandException e){
            logger.error(e.getMessage());
        }catch (CommandException | RuntimeException e){
            logger.error(e.getMessage(), e.getCause());
        }
    }
}
