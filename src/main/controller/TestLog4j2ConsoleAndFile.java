package main.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog4j2ConsoleAndFile {

    //private static final Logger logger = LogManager.getLogger(Log4j2ConsoleAndFile.class);
	private static final Logger logger = LogManager.getLogger();
	
    public static void main(String[] args) {
        logger.info("log level-info message!");
        logger.debug("log level-debug message!");
        
        testLogInMethod();
    }
    
    static void testLogInMethod() {
        logger.info("log level-info message in write()!");
        logger.debug("log level-debug message in write()!");    	
    }
}
