package app.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.data.SignUpUser;
import app.services.SignUpUserService;

@RestController
public class SignUpUserController {

	SignUpUserService su = new SignUpUserService();
	
	@RequestMapping(value="/usersignup")
	public @ResponseBody int userSignup(@RequestBody SignUpUser signUpUser){	

		return su.addUser(signUpUser);
	}
	
	@RequestMapping(value="/login")
	public @ResponseBody boolean userLogin(@RequestBody SignUpUser signUpUser){	

		return su.login(signUpUser);
	}
}