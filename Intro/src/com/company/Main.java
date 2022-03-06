package com.company;


public class Main {

    public static void main(String[] args) {
	    if(args.length != 1){
            System.out.println("bad input");
            System.exit(0);
        }

        ReadFile readFile = new ReadFile();
        readFile.fillMap(args[0]);
    }

}
