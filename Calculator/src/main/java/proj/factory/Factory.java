package proj.factory;
import proj.Exception.UndefinedCommandException;
import proj.factory.command_interface.Command;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Properties;


public class Factory {
    private static volatile Factory Instance;
    private Factory(){
        loadCommands();
    }


    Map<String, Command> loadedCommands;
    public static Factory getFactory(){
        if(Instance == null){
            synchronized (Factory.class){
                if(Instance == null){
                    Instance = new Factory();
                }
            }
        }
        return Instance;
    }

    public void loadCommands(){
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("C:\\Users\\borik\\IdeaProjects\\JavaLabs\\Calculator\\src\\main\\java\\proj\\factory\\Commands.properties"));

            loadedCommands = new HashMap<String, Command>();
            props.forEach((key, value) -> {
                if(loadedCommands.containsKey(key)){
                    System.err.println("Command " + value + " already have been loaded.");
                }
                else{
                    Class clas  = null;
                    try {
                        clas = Class.forName(value.toString());
                        Command command = (Command) clas.getDeclaredConstructor().newInstance();
                        loadedCommands.put(key.toString(), command);
                    } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }

            });
        } catch (IOException e) {
            System.err.println(e.toString());
            System.exit(0);
        }
    }

    public Command getCommand(String name) throws UndefinedCommandException{
        if(!loadedCommands.containsKey(name)){
            throw new UndefinedCommandException("Undefined command " + name);
        }
        Command command = null;
        command = loadedCommands.get(name);

        return command;
    }


}
