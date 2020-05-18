package com.company.directoryparser.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController()
@RequestMapping(value = "/web" )
public class WebController {
	
	@RequestMapping(value = "/home" )	
	@ResponseBody
public ModelAndView getHome()	
{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("FileDirectoryParser");
		
	return mav;
}

}
