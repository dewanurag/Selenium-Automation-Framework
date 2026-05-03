package com.utility;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import com.constants.Environments;

public class PropertiesUtil {

    private static Properties properties = new Properties();

    public static String getProperty(String key, Environments env) {
        try {
        	
        	String environment = "QA.properties" ;
        	if(env.name().equalsIgnoreCase("DEV")) {
        		environment = "DEV.properties";
        	}
        	else if(env.name().equalsIgnoreCase("PROD")) {
        		environment = "PROD.properties";
        	}
        	
            File configFile = new File(System.getProperty("user.dir") 
                + File.separator + "config" 
                + File.separator + environment);
            FileReader fileReader = new FileReader(configFile);
            properties.load(fileReader);   // ← This line was missing!
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}