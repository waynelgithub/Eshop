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
		   //lOGGER.trace("printLog4j2 started.");
		   lOGGER.traceEntry();

	      //logs debug message
	      if( lOGGER.isDebugEnabled()){
	    	  lOGGER.debug("debug example message -> Inside: printLog4j2");
	      }
	      //logs exception
	      lOGGER.error("err message:Logging a example exception", new Exception("example exception name"));

	      model.addAttribute("message", "Hello! Log4j2 is awesome!");
	      
	      //lOGGER.trace("printLog4j2 ended.");
	      lOGGER.traceExit();
	      
	      return "test-log4j2";
	   }

}
