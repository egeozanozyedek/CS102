package com.has.base;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtils {
   
   private static String USER_NAME = "s.homeautomationsystem";  // GMail user name (just the part before "@gmail.com")
   private static String PASSWORD = "has12345"; // GMail password
   // private static String RECIPIENT = "s.homeautomationsystem@gmail.com";
   
   public static void sendFromGMail( String to, String subject, String body)
   {
      Properties props = System.getProperties();
      String host = "smtp.gmail.com";
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.user", USER_NAME);
      props.put("mail.smtp.password", PASSWORD);
      props.put("mail.smtp.port", "587");
      props.put("mail.smtp.auth", "true");
      
      Session session = Session.getDefaultInstance(props);
      MimeMessage message = new MimeMessage(session);
      
      try {
         message.setFrom(new InternetAddress(USER_NAME));
         InternetAddress toAddress = new InternetAddress( to );
         message.addRecipient(Message.RecipientType.TO, toAddress);
         
         message.setSubject(subject);
         message.setText(body);
         Transport transport = session.getTransport("smtp");
         transport.connect(host, USER_NAME, PASSWORD);
         transport.sendMessage(message, message.getAllRecipients());
         transport.close();
      }
      catch (AddressException ae) {
         ae.printStackTrace();
      }
      catch (MessagingException me) {
         me.printStackTrace();
      }
   }
}