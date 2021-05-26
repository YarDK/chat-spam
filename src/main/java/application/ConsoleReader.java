package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader implements Runnable {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            try {
                String line = reader.readLine();
                if(line.equals("stop")){
                    reader.close();
                    ApplicationController.finish = true;
                    Thread.currentThread().interrupt();
                } else {
                    System.out.println("For finish enter stop command");
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
