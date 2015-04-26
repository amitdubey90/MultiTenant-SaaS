package app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.data.ProjectData;

@RestController
public class AppController {
    
	@RequestMapping(value="/hello", method=RequestMethod.GET)
    public @ResponseBody ProjectData hello(@RequestParam("input") String input){
		ProjectData data = new ProjectData();
		data.setProjectDesc("sample project");
		data.setProjectName("Jvalant");
    	return data;
    }
}
