package com.decorpot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("mailService")
public class ApplicationMailer 
{
    @Autowired
    @Qualifier("getMailSender")
    private MailSender mailSender;
     
    @Autowired
    private SimpleMailMessage preConfiguredMessage;
    
    @Autowired
    @Qualifier("getGMailSender")
    private MailSender gmailSender;
    
 
    /**
     * This method will send compose and send the message 
     * */
    public void sendMail(String to, String subject, String body) 
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("sales@decorpot.com");
        mailSender.send(message);
    }
    
    public void sendViaGMail(String to, String subject, String body) 
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("sales@decorpot.com");
        gmailSender.send(message);
    }
 
    /**
     * This method will send a pre-configured message
     * */
    public void sendPreConfiguredMail(String message) 
    {
    	try{
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		e.printStackTrace();
    	}
    }
}
