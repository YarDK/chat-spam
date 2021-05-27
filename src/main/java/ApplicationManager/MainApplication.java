package ApplicationManager;

import Models.ExecuteData;
import Models.RegisterData;
import Requests.*;
import Requests.ChatRequests;

public class MainApplication extends MainMethods{

    private RegisterData registerData;
    private ExecuteData executeData;
    private ChatRequests chatRequests;
    private SessionsRequests sessionsRequests;


    public void init(String environment, String user, String password) {
        System.out.printf("ENVIRONMENT - Tests running on environment: %s\n%n",environment);
        registerData = new RegisterHelper().registerData(environment, user, password);
        executeData = new ExecuteData();
        sessionsRequests = new SessionsRequests(registerData, executeData);
        sessionsRequests.authorisation();
        sessionsRequests.registration();
        sessionsRequests.startSession();
        //sessionsRequests.execute();

        chatRequests = new ChatRequests(registerData);

    }


    public void stop(){
        sessionsRequests.unregister();
    }
    public RegisterData data(){
        return registerData;
    }
    public ExecuteData execute_data(){
        return executeData;
    }
    public SessionsRequests session(){
        return sessionsRequests;
    }
    public ChatRequests chat(){
        return chatRequests;
    }


}
