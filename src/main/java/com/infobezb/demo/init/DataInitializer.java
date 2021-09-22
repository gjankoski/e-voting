package com.infobezb.demo.init;


import com.infobezb.demo.model.Candidate;
import com.infobezb.demo.model.Role;
import com.infobezb.demo.model.User;
import com.infobezb.demo.repository.CandidateRepository;
import com.infobezb.demo.repository.UserRepository;
import com.infobezb.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;



@Component
public class DataInitializer {

    @Autowired
    private JavaMailSender javaMailSender;

    UserService userService;
    CandidateRepository candidateRepository;

    public DataInitializer(UserService userService, CandidateRepository candidateRepository) {
        this.userService = userService;
        this.candidateRepository = candidateRepository;
    }

    @PostConstruct
    void init() throws MessagingException, IOException {
        this.userService.register("testuser", "testpassword", "testemail@gmail.com");
        this.userService.register("Vladimir", "1234pass", "t.budoski@hotmail.com");
        this.userService.register("Gjoko", "1234pass", "t.budoski@hotmail.com");
        this.userService.register("Teofil", "1234pass", "t.budoski@hotmail.com");
        this.userService.register("Bojan", "1234pass", "t.budoski@hotmail.com");
        candidateRepository.save(new Candidate(1L, "try", "try", 10L));
        candidateRepository.save(new Candidate(2L, "Try1", "try", 11L));

        /*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime fiks = LocalDateTime.parse("22/09/2021 17:42");

        if (now.isAfter(fiks)) {
            System.out.println("hadbjadn asd");
        }*/

       /* LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime electionDate = LocalDateTime.of(2021, 9, 22, 10, 15, 45); //Da napisime soodvete datum XD

        if(date1.isAfter(electionDate)) {
            sendEmailWithAttachment();
        }*/

}

   /* void sendEmailWithAttachment() throws MessagingException, IOException {


        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        //helper.setTo("to_@email");

        //helper.setText("<h1>Check attachment for image!</h1>", true);

        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));
        helper.addAttachment("Вашиот сертификат !", new ClassPathResource("cert")); // кај cert да се написит кој серификат се пуштат

        javaMailSender.send(msg);

    }*/
}