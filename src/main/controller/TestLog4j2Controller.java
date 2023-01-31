package main.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestLog4j2Controller {	
	
	   private static final Logger lOGGER = LogManager.getLogger(TestLog4j2Controller.class);

	   @RequestMapping(path = "/testlog", method = RequestMethod.GET)
	   public String printLog4j2(ModelMap model) {
		   lOGGER.info("printLog4j2 started.");

	      //logs debug message
	      if( lOGGER.isDebugEnabled()){
	    	  lOGGER.debug("Inside: printLog4j2");
	      }
	      //logs exception
	      lOGGER.error("Logging a sample exception", new Exception("Testing"));

	      model.addAttribute("message", "Hello! Log4j2 is awesome!");
	      lOGGER.info("printLog4j2 ended.");
	      return "test-log4j2";
	   }

}
