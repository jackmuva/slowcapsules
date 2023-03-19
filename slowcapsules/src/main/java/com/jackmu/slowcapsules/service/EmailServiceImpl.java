package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.EntryEmailDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;

import java.util.List;
import java.util.logging.Logger;

@Service
public class EmailServiceImpl implements EmailService{
    private Boolean localMode = false;
    private static final Logger LOGGER = Logger.getLogger(EmailServiceImpl.class.getName());

    @Override
    public void setLocalMode(Boolean bool){
        localMode = bool;
    }

    @Override
    public void sendEmails(List<EntryEmailDTO> entryEmailDTOList){
        if(localMode){
            sendLogEmails(entryEmailDTOList);
        }
        else {
            sendRealEmail(entryEmailDTOList);
        }
    }

    @Override
    public void sendLogEmails(List<EntryEmailDTO> entryEmailDTOList){
        for(EntryEmailDTO entryEmail : entryEmailDTOList){
            String sender = "sender@gmail.com";
            String host = "127.0.0.1";
            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", host);
            Session session = Session.getDefaultInstance(properties);
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(sender));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(entryEmail.getSubscriberEmail()));
                message.setSubject(entryEmail.getTitle());
                message.setContent(entryEmail.getEntryText(),"text/html");

                LOGGER.info(message.getRecipients(Message.RecipientType.TO).toString());
                LOGGER.info(message.getContent().toString());
            }
            catch (Exception e) {
                LOGGER.warning("Failed to create message");
            }
        }
    }

    @Override
    public void sendRealEmail(List<EntryEmailDTO> entryEmailDTOList){
        for(EntryEmailDTO entryEmail : entryEmailDTOList){
            String sender = "sender@gmail.com";
            String host = "127.0.0.1";
            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", host);
            Session session = Session.getDefaultInstance(properties);
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(sender));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(entryEmail.getSubscriberEmail()));
                message.setSubject(entryEmail.getTitle());
                message.setContent(entryEmail.getEntryText(),"text/html");

                Transport.send(message);
            }
            catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
    }
}
