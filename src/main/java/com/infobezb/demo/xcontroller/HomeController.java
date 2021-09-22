package com.infobezb.demo.xcontroller;

import com.infobezb.demo.model.User;
import com.infobezb.demo.repository.UserRepository;
import com.infobezb.demo.service.AuthService;
import org.hibernate.event.spi.PreInsertEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Controller
public class HomeController {

    UserRepository userRepository;
    AuthService authService;
    PasswordEncoder passwordEncoder;

    public HomeController(UserRepository userRepository, AuthService authService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getHomePage() {
        return "login";
    }

    @PostMapping
    public String vote (@RequestParam String userName, @RequestParam String password, HttpServletRequest request){
        User user = null;
            user = this.authService.login(request.getParameter("userName"),
                    request.getParameter("password"));
            request.getSession(true).setAttribute("user", user);
        Optional<User> korisnik = this.userRepository.findByUsername(userName);

        if (korisnik.isEmpty())
        {
            return "redirect:/auth/login";
        }
        else {
            if (!korisnik.get().getDidVote()) {
                return "redirect:/voting";
            }
            return "redirect:/auth/login";
        }
    }
}
