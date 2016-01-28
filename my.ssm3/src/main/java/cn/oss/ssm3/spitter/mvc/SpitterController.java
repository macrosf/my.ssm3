package cn.oss.ssm3.spitter.mvc;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.oss.ssm3.spitter.service.SpitterService;

@Controller
@RequestMapping("/spitters")	//root path
public class SpitterController {

	private final SpitterService spitterService;
	
	@Inject
	public SpitterController(SpitterService spitterService) {
		this.spitterService = spitterService;
	}
	
	//process GET request for URL /spitter/spittles
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public String listSpittleForSpitter(
			//@RequestParam("spitter") String username, 
			@PathVariable String username,
			Model model) {
//		Spitter spitter = spitterService.getSpitter(username);
//		model.addAttribute(spitter);	//key="spitter"
		model.addAttribute(spitterService.getSpittlesForSpitter(username)); //key=spittleList
		return "spittles/list";
//		return "home";
	}
}
