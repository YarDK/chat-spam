package application;

import ApplicationManager.MainApplication;
import com.codepine.api.testrail.model.Run;
import com.google.gson.JsonObject;
import org.testng.Assert;


public class ApplicationController implements Runnable {
    protected static MainApplication app = new MainApplication();

    private String user;
    private String password;
    private String target;
    private long speed_flood; // Message to minute
    private long amount; // Amount messages will send
    private String environment;

    public static boolean finish = false;

    public ApplicationController(String user, String password, String target, long speed_flood, long time, String environment) {
        this.user = user;
        this.password = password;
        this.target = target;
        this.speed_flood = speed_flood;
        this.amount = speed_flood * time;
        this.environment = environment;
    }

    public ApplicationController() {
    }

    public void setUp(){
        if(user != null && password != null)
            app.init(System.getProperty("environment",environment), user, password);

    }

    public void tearDown(){
        app.stop();
    }

    public void sendMassage(String account){
        String local_id = app.local_id_generate();

        JsonObject response_personal_chat_create = app.chat().sendMessage(account, local_id, body_generate());
        int status_cod = response_personal_chat_create.get("statusCode").getAsInt();

        if(status_cod != 200)
            Assert.fail("Message not send, status code " + status_cod);

    }

    private String body_generate() {
        return "Spam message " + System.currentTimeMillis();
    }

    public void run(){
        setUp();
        while (!Thread.currentThread().isInterrupted() && amount != 0 && !finish){
            sendMassage(target);
            try {
                Thread.sleep(60000 / speed_flood);
            } catch (InterruptedException e){
                e.printStackTrace();
                System.out.println("Thread interrupted");
                tearDown();
            }
            amount--;
        }
        tearDown();
    }

}
