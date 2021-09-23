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
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
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
        this.userService.register("Vladimir", "1234pass", "vladimir.j008@gmail.com");
        this.userService.register("Gjoko", "1234pass", "gjanko11@gmail.com");
        this.userService.register("Teofil", "1234pass", "t.budoski@hotmail.com");
        this.userService.register("Bojan", "1234pass", "bgavreski@gmail.com");
        candidateRepository.save(new Candidate(1L, "Kandidat1", "try", 0L));
        candidateRepository.save(new Candidate(2L, "Kandidat2", "try", 0L));
        candidateRepository.save(new Candidate(3L, "Kandidat3", "try", 0L));

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.office365.com");
        mailSender.setPort(587);
        mailSender.setUsername("eglasanje21@outlook.com");
        mailSender.setPassword("infobezb21");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");


        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("eglasanje21@outlook.com");
        helper.setTo("gjanko11@gmail.com");

        helper.setSubject("Сертификат и детали за гласање");
        helper.setText("UserName: \"Gjoko\"              Password: \"1234pass\"              Password for certificate: \"infobezb\"       Најпрво документот се зачувува со екстензија \".crt\". Во прелистувачот се внесува сертификатот откако ќе се извршат следните чекори. 1 чекор - во url се пишува \"about:preferences\". 2 чекор - Во полето за пребарување пишувате \"certificates\". 3 чекор - Притискате на \"View Certificates\". 4. чекор - Во делот \"Your Certificates\" го внесувате претходно спуштениот сертификат");

        FileSystemResource file
                = new FileSystemResource(new File("C:\\Users\\Ljubco\\Desktop\\FINKI\\Bezbednost\\certificates\\clientBob.crt"));
        helper.addAttachment("Certificates", file);
        mailSender.send(message);

        helper.setText("UserName: \"Teofil\"               Password: \"1234pass\"            Password for certificate: \"infobezb\"        Најпрво документот се зачувува со екстензија \".crt\". Во прелистувачот се внесува сертификатот откако ќе се извршат следните чекори. 1 чекор - во url се пишува \"about:preferences\". 2 чекор - Во полето за пребарување пишувате \"certificates\". 3 чекор - Притискате на \"View Certificates\". 4. чекор - Во делот \"Your Certificates\" го внесувате претходно спуштениот сертификат");
        helper.setTo("t.budoski@hotmail.com.com");
        mailSender.send(message);

        helper.setTo("vladimir.j008@gmail.com");
        helper.setText("UserName: \"Vladimir\"            Password: \"1234pass\"             Password for certificate: \"infobezb\"        Најпрво документот се зачувува со екстензија \".crt\". Во прелистувачот се внесува сертификатот откако ќе се извршат следните чекори. 1 чекор - во url се пишува \"about:preferences\". 2 чекор - Во полето за пребарување пишувате \"certificates\". 3 чекор - Притискате на \"View Certificates\". 4. чекор - Во делот \"Your Certificates\" го внесувате претходно спуштениот сертификат");
        mailSender.send(message);

        helper.setTo("bgavreski@gmail.com");
        helper.setText("UserName: \"Bojan\"               Password: \"1234pass\"             Password for certificate: \"infobezb\"         Најпрво документот се зачувува со екстензија \".crt\". Во прелистувачот се внесува сертификатот откако ќе се извршат следните чекори. 1 чекор - во url се пишува \"about:preferences\". 2 чекор - Во полето за пребарување пишувате \"certificates\". 3 чекор - Притискате на \"View Certificates\". 4. чекор - Во делот \"Your Certificates\" го внесувате претходно спуштениот сертификат");
        mailSender.send(message);



    }
}