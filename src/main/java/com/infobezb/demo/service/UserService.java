package com.infobezb.demo.service;

import com.infobezb.demo.model.Role;
import com.infobezb.demo.model.User;
import com.infobezb.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(RuntimeException::new);
    }

    public void register(String username, String password, String email) {
        User user = new User(username, passwordEncoder.encode(password), Role.ROLE_USER ,email);
        this.userRepository.save(user);
    }

}
