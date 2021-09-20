package com.infobezb.demo.init;


import com.infobezb.demo.repository.UserRepository;
import com.infobezb.demo.service.UserService;
import lombok.AllArgsConstructor;

import javax.annotation.PostConstruct;

@AllArgsConstructor
public class DataInitializer {

    UserService userService;

    @PostConstruct
    void init() {
        this.userService.register("testuser", "testpassword", "testemail@gmail.com");
    }

}