package proj;


import proj.run.Run;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.log4j.Logger;


public class Main {

    public static void main(String[] args) {
        try {
            Scanner scanner = null;
            if (args.length == 0) {
                scanner = new Scanner(System.in);
            } else if (args.length == 1) {
                scanner = new Scanner(new FileInputStream(args[0]));
            }

            Run run = new Run();
            assert scanner != null;
            run.run(scanner);

        } catch (FileNotFoundException e){
            e.getLocalizedMessage();
        }
    }
}
