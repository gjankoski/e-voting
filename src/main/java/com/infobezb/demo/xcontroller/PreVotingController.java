/*
package com.infobezb.demo.xcontroller;
import com.infobezb.demo.repository.UserRepository;
import com.infobezb.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.security.Principal;


@Controller
@RequestMapping("/PreVote")
public class PreVotingController {
    private final UserService userService;
    private final UserRepository userRepository;

    public PreVotingController(UserService userService, UserRepository userRepository){
        this.userService=userService;
        this.userRepository=userRepository;
    }

    @GetMapping("/CertificateInformation")
    public String getInfo(){
        return "CertificateInformation";
    }

    @GetMapping
    public String getVote(){
        return "vote";
    }
}
*/
