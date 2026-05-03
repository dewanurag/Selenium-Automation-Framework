package com.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;

import java.io.File;

public class JSONReaderUtil {

    private static Config config;

    static {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File configFile = new File(System.getProperty("user.dir") 
                + File.separator + "config" 
                + File.separator + "config.json");
            config = objectMapper.readValue(configFile, Config.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getURL(String env) {
        Environment environment = config.getEnvironments().get(env);
        if (environment == null) {
            throw new RuntimeException("Environment '" + env + "' not found in config.json");
        }
        return environment.getURL();
    }
    
    public static void tryOut() {
    	ObjectMapper objMapper = new ObjectMapper();
    	File configFile = new File(System.getProperty("user.dir")+File.separator+"config"+File.separator+"config.json");
    	try {
    	config = objMapper.readValue(configFile, Config.class);
    	System.out.println(config.getEnvironments().get("QA").getURL());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}