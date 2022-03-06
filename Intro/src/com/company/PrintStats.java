package com.company;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;

public class PrintStats {
    Writer writer = null;

    {
        try {
            writer = new OutputStreamWriter(new FileOutputStream("out.txt"));
        } catch (IOException e) {
            System.err.println("Error while writing file: "+e.getLocalizedMessage());
        }
    }

    public void printStats(HashMap<String, Integer> map, Integer counter){
        //map.entrySet().stream().sorted(HashMap.Entry.<String, Integer>comparingByValue().reversed()).forEach(System.out::println);
        map.entrySet().stream().sorted(HashMap.Entry.<String, Integer>comparingByValue().reversed()).forEach
            (e -> {
              try {
                  writer.write(e.getKey() + ' ');
                  writer.write(e.getValue().toString() + ' ');
                  Float freq = (((float)e.getValue() / counter) * 100);
                  writer.write(freq + "%\n");
              } catch (IOException ex) {
                  System.err.println("Error while writing file: "+ex.getLocalizedMessage());
              }
            });
        try {
            writer.close();
        } catch (IOException ex) {
            System.err.println("Error while closing file: "+ex.getLocalizedMessage());
        }
    }
}
