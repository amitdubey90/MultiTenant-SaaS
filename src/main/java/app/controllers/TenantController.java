package app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.data.UserInfo;
import app.services.TenantServiceIfc;

@RestController
public class TenantController {

	@Autowired
	TenantServiceIfc service;

	@RequestMapping(method = RequestMethod.POST, value = "/createTenant/{sdlcType}")
	public @ResponseBody UserInfo createTenant(@RequestBody UserInfo user,
			@PathVariable String sdlcType) {
		if(user!= null && service.createTenant(user.getUserId(), sdlcType) ){
			user.setSdlc(sdlcType);
			return user; 	
		} else {
			return null;
		}
	}
	
	@RequestMapping(value="/signup")
	public @ResponseBody UserInfo userSignup(@RequestBody UserInfo signUpUser){	

		return service.userSignup(signUpUser);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/login")
	public @ResponseBody UserInfo userLogin(@RequestBody UserInfo signUpUser){	

		return service.login(signUpUser);
	}

}
