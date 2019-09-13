package com.studycool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//https://memorynotfound.com/spring-mail-sending-email-inline-attachment-example/
@Service
public class EmailServiceImp   {
	
	@Autowired
    private static JavaMailSender javaMailSender;
	
	/*
	 * public static void main(String[] args) {
	 * 
	 * try { sendSimpleMessage("aravindkumark1997@gmail.com","hi","hi"); } catch
	 * (Exception e) { e.printStackTrace(); // TODO: handle exception } }
	 */
	
	    public static void sendSimpleMessage(String toEmail, String subject, String message) {
	    	try {
	    		
	    		SimpleMailMessage msg = new SimpleMailMessage();
		        msg.setTo(toEmail);

		        msg.setSubject(subject);
		        msg.setText(message);

		        javaMailSender.send(msg);
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
	    }
}
