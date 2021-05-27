import ApplicationManager.RegisterHelper;
import org.testng.annotations.Test;

import java.io.File;

public class test {

    @Test
    public void test(){
        System.out.println(getClass().getResourceAsStream(String.format("/%s.properties", "prod")));
        String file =  new File(RegisterHelper.class.getProtectionDomain().getCodeSource().getLocation().toString()).getPath();
        System.out.println(file);
        //getClass().getResourceAsStream(String.format("/%s.properties", "prod"));
    }
}
