package cn.oss.ssm3.spitter.mvc;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
			BindingResult bindingResult,
			@RequestParam(value="image", required=false) //接收文件上传
				MultipartFile image) {
		
		if (bindingResult.hasErrors()) {
			return "spitters/edit";
		}
		spitterService.saveSpitter(spitter);
		
		try{
			if(!image.isEmpty()) {
				validateImage(image);
				saveImage(spitter.getId()+".jpg", image);	//保存图片文件
			}
		} catch(ImageUploadException e) {
			bindingResult.reject(e.getMessage());
			return "spitters/edit";
		}
		
		return "redirect:/spitters/" + spitter.getUsername(); 
	}

	private void saveImage(String filename, MultipartFile image) 
			throws ImageUploadException {
		//refer to page 213
//		try {
//			File file = new File(webRootPath + "/resources/" + filename);
//			FileUtils.writeByteArraryToFile(file, image.getBytes());
//		} catch (IOException e) {
//			throw new ImageUploadException("Unable to save image", e)
//		}
		
	}

	private void validateImage(MultipartFile image) {
		if (!image.getContentType().equals("image/jpeg")) {
			throw new ImageUploadException("Only JPG images accepted");
		}
		
	}
	
}
