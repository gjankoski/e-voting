package com.infobezb.demo.init;


import com.infobezb.demo.model.Candidate;
import com.infobezb.demo.model.Role;
import com.infobezb.demo.model.User;
import com.infobezb.demo.repository.CandidateRepository;
import com.infobezb.demo.repository.UserRepository;
import com.infobezb.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    UserService userService;
    CandidateRepository candidateRepository;

    public DataInitializer(UserService userService, CandidateRepository candidateRepository) {
        this.userService = userService;
        this.candidateRepository = candidateRepository;
    }

    @PostConstruct
    void init() {
        this.userService.register("testuser", "testpassword", "testemail@gmail.com");
        this.userService.register("Vladimir", "1234pass", "t.budoski@hotmail.com");
        this.userService.register("Gjoko", "1234pass", "t.budoski@hotmail.com");
        this.userService.register("Teofil", "1234pass", "t.budoski@hotmail.com");
        this.userService.register("Bojan", "1234pass", "t.budoski@hotmail.com");
        candidateRepository.save(new Candidate(1L, "try", "try", 10L));
        candidateRepository.save(new Candidate(2L, "Try1", "try", 11L));
    }

}