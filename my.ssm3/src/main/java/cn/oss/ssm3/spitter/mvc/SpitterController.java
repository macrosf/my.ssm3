package cn.oss.ssm3.spitter.mvc;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.oss.ssm3.spitter.domain.Spitter;
import cn.oss.ssm3.spitter.service.SpitterService;

@Controller
@RequestMapping("/spitters")	//root path
public class SpitterController {

	private final SpitterService spitterService;
	
	@Inject
	public SpitterController(SpitterService spitterService) {
		this.spitterService = spitterService;
	}
	
	//page 205
	//process GET request for URL /spitter/{username}
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public String listSpittleForSpitter(
			//@RequestParam("spitter") String username, 
			@PathVariable String username,
			Model model) {
		Spitter spitter = spitterService.getSpitter(username);
		model.addAttribute(spitter);	//key="spitter"
		model.addAttribute(spitterService.getSpittlesForSpitter(username)); //key=spittleList
		return "spittles/list";
//		return "home";
	}
	
	//page 202
	@RequestMapping(method=RequestMethod.GET, params="new")
	public String createSpitterProfile(Model model) {
		Spitter spitter = new Spitter();
		spitter.setFullName("input your name here");
		model.addAttribute(spitter);
		return "spitters/edit";
	}
	
	//page 205
	@RequestMapping(method=RequestMethod.POST)
	public String addSpitterFromForm(@Valid Spitter spitter,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "spitters/edit";
		}
		spitterService.saveSpitter(spitter);
		
		return "redirect:/spitters/" + spitter.getUsername(); 
	}
}
