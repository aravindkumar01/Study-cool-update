package com.studycool.service;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.studycool.model.Mail;
//https://memorynotfound.com/spring-mail-sending-email-inline-attachment-example/
@Service
public class EmailService {
		@Autowired
	    private static JavaMailSender emailSender;

	    public static void sendSimpleMessage(Mail mail) throws MessagingException {

	        MimeMessage message = emailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message,
	                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
	                StandardCharsets.UTF_8.name());

	        helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));
	        String inlineImage = "<img src=\"cid:D:\\img\\69.png\"></img><br/>";

	        helper.setText(inlineImage + mail.getContent(), true);
	        helper.setSubject(mail.getSubject());
	        helper.setTo(mail.getTo());
	        helper.setFrom(mail.getFrom());

	        emailSender.send(message);
	    }
	    
	    public static void main(String[] args)
	    {
	    	// log.info("Spring Mail - Sending Email with Inline Attachment Example");

	         Mail mail = new Mail();
	         mail.setFrom("aravindkumark1997@gmail.com");
	         mail.setTo("aravindkumark1997@gmail.com");
	         mail.setSubject("Sending Email with Inline Attachment Example");
	         mail.setContent("This tutorial demonstrates how to send an email with inline attachment using Spring Framework.");

	         try {
				sendSimpleMessage(mail);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
			}
	    }
}
