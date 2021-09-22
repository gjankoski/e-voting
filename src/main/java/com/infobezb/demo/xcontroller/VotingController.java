package com.infobezb.demo.xcontroller;


import com.infobezb.demo.model.Candidate;
import com.infobezb.demo.model.User;
import com.infobezb.demo.repository.CandidateRepository;
import com.infobezb.demo.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/voting")
public class VotingController {
    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;

    public VotingController(CandidateRepository candidateRepository, UserRepository userRepository) {
        this.candidateRepository = candidateRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/voteSucc")
    public String successfulVote(){
        return "successfulVote";
    }

    @RequestMapping("/backOnLogin")
    public String backOnLogin(){
        return "login";
    }

    @GetMapping
    public String getPage (Model model){
        model.addAttribute("candidate",this.candidateRepository.findAll());
        return "vote";
    }

    @PostMapping
    public String vote (@RequestParam String flexRadioDefault, HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession(true).getAttribute("user");
        User korisnik = this.userRepository.findByUsername(user.getUsername()).get();

        if(!korisnik.getDidVote()){
            Candidate kandidat = this.candidateRepository.findById(Long.parseLong(flexRadioDefault)).get();
            kandidat.setNumberOfVotes(kandidat.getNumberOfVotes()+1);
            this.candidateRepository.save(kandidat);
            korisnik.setDidVote(true);
            this.userRepository.save(korisnik);
            return "redirect:/voting/voteSucc";
        }
        if(korisnik.getDidVote()){
            return "redirect:/voting/backOnLogin";
        }
        return "redirect:/voting/backOnLogin";
    }
}
