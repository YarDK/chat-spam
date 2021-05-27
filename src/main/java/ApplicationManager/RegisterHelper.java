package ApplicationManager;

import Models.RegisterData;

import java.io.*;
import java.util.Properties;

public class RegisterHelper{
    private final Properties properties = new Properties();
    private String environment;
    private File file_properties;

    public RegisterHelper(String environment){
        this.environment = environment;
    }

    public RegisterHelper(){

    }

    private File fileInit(String core){
        if(this.file_properties == null) {
            this.file_properties = new File(String.format("%s/src/main/resources/%s.properties", core, environment));
        }
        return file_properties;
    }


    public RegisterData registerData(String environment, String username, String password, String core_path) {

        this.environment = environment;

        try {
            properties.load(new FileReader(fileInit(core_path)));
            return new RegisterData()
                    .withUsername(username)
                    .withPassword(password)
                    .withDevice_id(properties.getProperty("device_id"))
                    .withDevice_name(properties.getProperty("device_name"))
                    .withOs(properties.getProperty("os"))
                    .withDefault_status(properties.getProperty("default_status"))
                    .withTextStatus(properties.getProperty("textStatus"))
                    .withModel(properties.getProperty("model"))
                    .withApplication(properties.getProperty("application"))
                    .withUrl_auth(properties.getProperty("url.auth"))
                    .withUrl_4talk(properties.getProperty("url.4talk"))
                    .withUrl_mobigate(properties.getProperty("url.mobigate"))
                    .withUrl_apivks(properties.getProperty("url.apivks"))
                    .withUrl_clientapi(properties.getProperty("url.clientapi"))
                    .withUrl_fs(properties.getProperty("url.fs"))
                    .withUrl_ab(properties.getProperty("url.ab"));
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}
