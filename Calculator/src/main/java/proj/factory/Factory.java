package proj.factory;
import org.apache.log4j.Logger;
import proj.Exception.UndefinedCommandException;
import proj.factory.command_interface.Command;
import proj.run.Run;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Properties;


public class Factory {
    private static final Logger logger = Logger.getLogger(Factory.class);
    private static volatile Factory Instance;
    private Factory() throws IOException{
        loadCommands();
    }


    Map<String, Class<? extends Command>> loadedCommands;
    public static Factory getFactory() throws IOException{
        if(Instance == null){
            synchronized (Factory.class){
                if(Instance == null){
                    Instance = new Factory();
                }
            }
        }
        return Instance;
    }

    public void loadCommands() throws IOException {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(String.valueOf(Factory.class.getResourceAsStream("Commands.properties"))));

            loadedCommands = new HashMap<String, Class<? extends Command>>();
            props.forEach((key, value) -> {
                if(loadedCommands.containsKey(key)){
                    logger.error("Command " + value + " already have been loaded.");
                    System.err.println("Command " + value + " already have been loaded.");
                }
                else{
                    Class commandClass  = null;
                    try {
                        commandClass = Class.forName(value.toString());
                        loadedCommands.put(key.toString(), commandClass);
                        logger.info( key + " loaded");
                    } catch (ClassNotFoundException e) {
                        logger.error(key + "was not loaded");
                    }

                }

            });
        } catch (IOException e) {
            logger.error(e);
            System.err.println(e.toString());
            throw e;
        }
    }

    public Command getCommand(String name) throws UndefinedCommandException{
        if(!loadedCommands.containsKey(name)){
            throw new UndefinedCommandException("Undefined command " + name);
        }
        Command command = null;

        try {
            command = loadedCommands.get(name).getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e){
            logger.error(e);
            throw new RuntimeException("Factory.getCommand", e);
        }

        return command;
    }


}
