package cn.oss.ssm3.spitter.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
  @RequestMapping("/admin")
  public void showAdminPage() {
  }
}
