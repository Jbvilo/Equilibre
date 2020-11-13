package com.example.accessingdatarest.Controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Array;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.example.accessingdatarest.Databases.EmailRepository;
import com.example.accessingdatarest.Databases.PersonRepository;
import com.example.accessingdatarest.Models.Email;
import com.example.accessingdatarest.Models.Person;






@RestController
@RequestMapping("api")
@CrossOrigin
public class PublicRestApiController {
    private PersonRepository userRepository;
    private EmailRepository emailRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public List<String> emailist;
    int i = 0;
    String redirec ="false";

 public PublicRestApiController(PersonRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }



    @RequestMapping(value = "/sendemail")
    public String sendEmail(@RequestBody String mail) throws AddressException, MessagingException, IOException {
      sendmail(mail);
        return "Email sent successfully to "+mail;   
     } 

     @RequestMapping(value = "/loginredirection")
     public String loginredirection(@RequestBody String mail) {

  emailRepo.save(new Email(mail));

         return mail;
     }

    // Available to all authenticated users
    @GetMapping("test")
    public String test1(){
        return "API Test";
    }




    @GetMapping("admin/users")
    public List<Person> users(){
        return (List<Person>) this.userRepository.findAll();
    }

    @PostMapping("/sign-up")
    public Person signUp(@RequestBody Person user) {
        
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setFirstName(user.getFirstName());
        user.setEmail((user.getEmail()));
        user.setPhonenumber((user.getPhonenumber()));
        user.setLicencenumber((user.getLicencenumber()));
        user.setRole((user.getRole()));
        user.setLastName((user.getLastName()));

        userRepository.save(user);

        return user;
    }

    
    private void sendmail(String mail) throws AddressException, MessagingException, IOException {
   Properties props = new Properties();
   props.put("mail.smtp.auth", "true");
   props.put("mail.smtp.starttls.enable", "true");
   props.put("mail.smtp.host", "smtp-mail.outlook.com");
   props.put("mail.smtp.port", "587");
   
   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
       //  return new PasswordAuthentication("equilibretest@hotmail.com", "equilibre9494");
       return new PasswordAuthentication("vilo@et.esiea.fr", "Ar3X7Lsns");
      }
   });
   Message msg = new MimeMessage(session);
   msg.setFrom(new InternetAddress("vilo@et.esiea.fr", false));

   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
   msg.setSubject("Mot de passe oublié");
   msg.setContent("Tutorials point email", "text/html");
   msg.setSentDate(new Date());

   MimeBodyPart messageBodyPart = new MimeBodyPart();
   messageBodyPart.setContent("Tutorials point email", "text/html");
   messageBodyPart.setText("Cliquez sur ce lien pour créer un nouveau mot de passe http://localhost:4200/change-password/:80806");
   
   Multipart multipart = new MimeMultipart();
   multipart.addBodyPart(messageBodyPart);
  
   msg.setContent(multipart);
   Transport.send(msg);   
}
}