package application;

import java.util.ArrayList;
import java.util.List;

public class ConsoleCommands {

    private static final List<String> log = new ArrayList<>();

    public static void status(){
        log.forEach(System.out::println);
    }

    public static void addLog(String text){
        log.add(text);
    }

}
