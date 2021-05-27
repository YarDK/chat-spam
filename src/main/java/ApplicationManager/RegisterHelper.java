package ApplicationManager;

import Models.RegisterData;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    private File fileInit(){
        if(this.file_properties == null) {
            this.file_properties = new File(String.format("src/main/resources/%s.properties", environment));
        }
        return file_properties;
    }

    public RegisterData registerData(String environment, String username, String password) {

        this.environment = environment;

        try {
            properties.load(new FileReader(fileInit()));
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

    public  String getLocalProperty(String property){
        try {
            properties.load(new FileReader(fileInit()));
            return properties.getProperty(property);
        } catch (IOException e){
            e.printStackTrace();
            return "";
        }

    }

    public void setLocalProperty(String property_key, String property_value, String whats_changed){
        try {
            properties.load(new FileReader(fileInit()));
            properties.setProperty(property_key, property_value);
            properties.store(new FileWriter(fileInit()),whats_changed);
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
