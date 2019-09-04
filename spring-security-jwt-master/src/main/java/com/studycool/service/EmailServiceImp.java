package com.studycool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

//https://memorynotfound.com/spring-mail-sending-email-inline-attachment-example/
@Component
public class EmailServiceImp   {
	
	public static void main(String[] args) {
		
		try {
			sendSimpleMessage("aravindkumark1997@gmail.com","hi","hi");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

    @Autowired
    public static JavaMailSender emailSender;
 
    public static void sendSimpleMessage(String to, String subject, String text) {
       
    	try {
    	   SimpleMailMessage message = new SimpleMailMessage(); 
           message.setTo(to); 
           message.setSubject(subject); 
           message.setText(text);
           emailSender.send(message);
		
			} catch (Exception e) {
				e.printStackTrace();
				
				// TODO: handle exception
			}
      
    }
		
}
