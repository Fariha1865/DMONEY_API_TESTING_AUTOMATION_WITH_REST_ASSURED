package utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import com.github.javafaker.Faker;
import java.io.IOException;

public class Utils {
    public static void setEnvVariable(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key, value);
        config.save();
    }

    public static String name;
    public   static String email;
    public static String password;


    public static String id;

    public static String phone;
    public static String Agent_Number;
    public static String Customer_Number;




    public String getName() {


        return name;
    }

    public void setName(String Name) {


        this.name = Name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }
    public void generateRandomData()
    {
        String rand = String.valueOf(Utils.generateRandomNumber(10000, 9999));
        phone = "077320" + rand;

        Faker faker = new Faker();
        setName(faker.name().firstName());
        setEmail(faker.name().firstName()+"@gmail.com");
        setPassword(faker.name().nameWithMiddle());





    }

    public static int generateRandomNumber(int min, int max)
    {
        int random = (int) Math.floor(Math.random()*(max-min)*min);
        return  random;
    }

}
