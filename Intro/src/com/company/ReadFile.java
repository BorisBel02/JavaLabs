package com.company;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import  java.lang.StringBuilder;

public class ReadFile {
    public Integer fillMap(String fileName){
        HashMap<StringBuilder, Integer> map = new HashMap<StringBuilder, Integer>();
        Integer counter = 0;
        Reader reader = null;
        try{
            reader =  new InputStreamReader(new FileInputStream(fileName));

            StringBuilder word = new StringBuilder();
            while(true){
                int code = reader.read();
                if(code == -1){
                    break;
                }

                if(!Character.isLetterOrDigit((char)code)){
                    if(!map.containsKey(word)){
                        map.put(word, 1);
                    }
                    else{
                        map.put(word, map.get(word) + 1);
                    }
                    word.delete(0, word.length() - 1);
                    ++counter;
                    continue;
                }

                word.append((char)code);
            }
            PrintStats print = new PrintStats();
            print.printStats(map);
        }
        catch (IOException e){
                System.err.println("Error while reading file: "+e.getLocalizedMessage());
        }
        finally {
            if(null != reader){
                try{
                   reader.close();
                }
                catch (IOException e){
                    e.printStackTrace(System.err);
                }

            }
        }
        return counter;
    }
}
