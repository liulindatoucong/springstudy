package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/"})
public class HomeController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="loginagain",method=RequestMethod.GET)
	public String loginAgain() {
		return "login";
	}
}
