package com.infobezb.demo.xcontroller;

import com.infobezb.demo.repository.CandidateRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/test")
public class TestController {

    CandidateRepository candidateRepository;

    public TestController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @GetMapping
    public String test(Model model) {
        return "login";
    }
}
