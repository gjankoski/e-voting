package com.infobezb.demo.xcontroller;

import com.infobezb.demo.model.Candidate;
import com.infobezb.demo.model.User;
import com.infobezb.demo.repository.CandidateRepository;
import com.infobezb.demo.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/auth")
public class LoginController {

    private UserRepository userRepository;
    private CandidateRepository candidateRepository;

    public LoginController(UserRepository userRepository, CandidateRepository candidateRepository) {
        this.userRepository = userRepository;
        this.candidateRepository = candidateRepository;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
    @RequestMapping("/voting")
    public String goOnVoting(){
        return "vote";
    }

    @PostMapping
    public String vote (@RequestParam String userName,String password){
        User korisnik = this.userRepository.findById(userName).get();

        if (korisnik.getPassword().equals(password))
        {
            if (!korisnik.getDidVote()){
                return "redirect:/auth/voting";
            }
        }
        return "redirect:/auth/login";
    }

    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/auth/login";
    }
}
