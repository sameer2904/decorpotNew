package com.decorpot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.decorpot.services.ApplicationMailer;

@RestController
@RequestMapping(value = "/email")
public class EmailController {
	
	@Autowired
	private ApplicationMailer appMailer;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String sendEmail(){
		try{
			appMailer.sendPreConfiguredMail("hi");
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "succ";
	}

}
