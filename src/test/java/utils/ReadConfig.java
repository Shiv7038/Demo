package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

    public static Properties getProperties(){
        Properties properties = new Properties();

        try {
            String path = System.getProperty("user.dir")+"\\src\\test\\resources\\Config\\config.properties";
            // Load the properties from the file
            FileInputStream fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
           // fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        // Retrieve values using keys
        String databaseUrl = properties.getProperty("testsiteurl");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        // Use the retrieved values as needed
        System.out.println("Database URL: " + databaseUrl);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        return properties;
    }
}

