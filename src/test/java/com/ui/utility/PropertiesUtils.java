package com.ui.utility;

import com.ui.constants.ENV;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
    public static String readProperty(ENV env,String propertyName) {
        File propFile = new File(System.getProperty("user.dir") + "/" + "config/" + env + ".properties");
        FileReader fileReader = null;
        Properties properties = new Properties();
        try {
            fileReader = new FileReader(propFile);
            properties.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(propertyName.toUpperCase());
    }
}
