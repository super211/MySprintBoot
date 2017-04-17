package com.my.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("jobone")
public class MyJobOne {
	
	@Autowired
	RestTemplate _RestTemplate;
	
	@Value("${email.host}")
	private String emailHost;
	
	@Value("${email.to}")
	private String emailTo;
	
	@Value("${email.from}")
	private String emailFrom;
	
	@Value("${email.title}")
	private String emailTitle;
	
    protected void myTask() {
    	System.out.println("This is my task");
    	//String responseEntity = _RestTemplate.getForObject(systemHealthCheckUrl, String.class);
    	String responseEntity = "Testing Email";
		SendEmail.send(emailTo, emailFrom, emailHost, emailTitle, responseEntity);
		System.out.println("Response Msg: " + responseEntity);
    }
}
