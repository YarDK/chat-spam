package application;

import java.util.Arrays;
import java.util.List;

public class MainApplication {


    public static void main(String[] args) {
        if (args.length == 0) {
            info();
            return;
        }

        if (args[0].equals("info")) {
            info();
            return;
        }

        // mango3294@mangosip.ru 123456aB 402353119@mtalker.mangotele.com 10 10 prod


        // Убрать все лишнее, убрать выводы в консоль (сделать статус команду!),  сделать jar файл.

        long speed_flood = Integer.parseInt(args[3]); // Message to minute
        long time = Integer.parseInt(args[4]); // Time to flood in minute

        ApplicationController app = new ApplicationController(
                args[0], args[1], args[2], speed_flood, time, environmentCheck(args[5].toLowerCase())
        );
        ConsoleReader console = new ConsoleReader();

        Thread app_thread = new Thread(app);
        Thread console_thread = new Thread(console);
        app_thread.start();
        console_thread.start();

        try {
            console_thread.join(time * 1000);
        } catch (InterruptedException e) {
            System.out.println("Time Out!");
            e.printStackTrace();
        }


    }

    private static String environmentCheck(String env) {
        List<String> environment = Arrays.asList("pres", "prod", "dev");
        if (!environment.contains(env)) throw new IllegalArgumentException("");
        return env;
    }

    private static void info() {
        System.out.println("INFO:");
        System.out.println("args[user, password, target, speed_flood, time, environment]");
        System.out.println("user and password - authorized user");
        System.out.println("target - chat or channel id");
        System.out.println("speed_flood - Message to minute");
        System.out.println("time - Time to flood in minute");
        System.out.println("environment - prod, pres or dev");
    }


}
