package com.utility;

import org.apache.logging.log4j.*;

import org.apache.logging.log4j.Logger;

public class LoggerUtility {
	
	//can you create a private constructor 
	//yes
	//we can only create the object of LoggerUtility inside the class
	private LoggerUtility() {
		
	}
	public static Logger getLogger(Class<?> clazz) {
		Logger logger = null;
		if(logger==null) {
		logger = LogManager.getLogger(clazz);
		}
		return logger;
	}
}
