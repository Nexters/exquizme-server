package com.exquizme.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

/**
 * Created by godong9 on 2017. 7. 22..
 */

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public RedirectView main(Principal principal) {
        // TODO: login 성공 후 이동할 클라이언트 페이지로 리다이렉트
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("");
        return redirectView;
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
