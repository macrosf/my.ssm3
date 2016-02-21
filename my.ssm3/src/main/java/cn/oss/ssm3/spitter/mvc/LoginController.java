package cn.oss.ssm3.spitter.mvc;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
  @RequestMapping(method=GET)
  public void showLoginForm() {}
  
//  @RequestMapping(method=POST)
//  public String showSpittersNew() {
//	  return "admin";
//  }  
}
