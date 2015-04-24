package app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    
	@RequestMapping(value="/hello", method=RequestMethod.GET)
    public @ResponseBody String hello(/*@RequestParam("eventName") String eventName,
    		@RequestParam("year") String year,
            @RequestParam("file") MultipartFile file*/){
    	
    	return "hello";
    }
}
