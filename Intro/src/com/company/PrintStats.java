package com.company;
import java.util.HashMap;

public class PrintStats {
    public void printStats(HashMap<StringBuilder, Integer> map){
        map.entrySet().stream().sorted(HashMap.Entry.<StringBuilder, Integer>comparingByValue().reversed()).forEach(System.out::println);
    }
}
