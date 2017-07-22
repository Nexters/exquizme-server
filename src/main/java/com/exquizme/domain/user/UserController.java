package com.exquizme.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by godong9 on 2017. 7. 22..
 */

@Slf4j
@Controller
public class UserController {
    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    public UserController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @Autowired
    private UserService userService;

    @GetMapping("/users/test")
    public void test() {
        log.info("TEST");
    }

    @GetMapping("/login")
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        String [] fields = { "id", "email" };
        User userProfile = facebook.fetchObject("me", User.class, fields);
        facebook.userOperations().getUserProfile();
        return "user";
    }

}
