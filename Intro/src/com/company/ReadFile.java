package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import  java.lang.StringBuilder;

public class ReadFile {
    public void fillMap(String fileName){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Integer counter = 0;
        Reader reader = null;
        try{
            reader =  new InputStreamReader(new FileInputStream(fileName));

            StringBuilder word = new StringBuilder();
            while(true){
                int code = reader.read();


                if(!Character.isLetterOrDigit((char)code)){
                    String wordStr = word.toString();
                    if(!map.containsKey(wordStr) && word.length() > 0){
                        map.put(wordStr, 1);
                        ++counter;
                        word.delete(0, word.length());
                    }
                    else if(word.length() > 0){
                        map.put(wordStr, map.get(wordStr) + 1);
                        ++counter;
                        word.delete(0, word.length());
                    }
                    if(code == -1){
                        break;
                    }
                    continue;
                }
                word.append((char)code);
            }
            PrintStats print = new PrintStats();
            print.printStats(map, counter);
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
    }
}
