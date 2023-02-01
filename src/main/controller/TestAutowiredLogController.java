package main.controller;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestAutowiredLogController {

	@Autowired
	private Logger logger;
	//不能設 static, 會被認定是 null，報 NPE
	//不能設 final, 因為沒辦法 initialize

	@RequestMapping("/test-log-autowired")
	public String printAutowiredLog() {

		logger.info("info from an autowired logger in controller");
		return "redirect:/";
	}
}