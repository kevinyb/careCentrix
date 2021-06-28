package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    //1- we created properties object
    private static Properties properties = new Properties();

    static{
        //2- get the path and store in the string
        String path = "configuration.properties";
        //3- we need to open the file
        try {
            FileInputStream file = new FileInputStream(path);
            //4- finally we need to load  the file to properties object
            properties.load(file);
            //5- close the file
            file.close();
        } catch (IOException e) {
            System.out.println("Properties file not found");
        }

    }
    public static String getProperty (String keyWord) {
    return properties.getProperty(keyWord);
    }
}
