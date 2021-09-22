package com.infobezb.demo.xcontroller;

import com.infobezb.demo.model.Candidate;
import com.infobezb.demo.model.User;
import com.infobezb.demo.repository.CandidateRepository;
import com.infobezb.demo.repository.UserRepository;
import com.infobezb.demo.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private AuthService authService;

    public LoginController(AuthService authService,
                           UserRepository userRepository, CandidateRepository candidateRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.candidateRepository = candidateRepository;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        User user = null;
        try{
            user = this.authService.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession(true).setAttribute("user", user);
            return "redirect:/vote";
        }
        catch (RuntimeException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }

    @GetMapping("/voting")
    public String goOnVoting(){
        return "vote";
    }



    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/auth/login";
    }
}
