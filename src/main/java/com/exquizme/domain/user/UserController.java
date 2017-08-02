package com.exquizme.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.HashMap;
import java.util.Objects;

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
        RedirectView redirectView = new RedirectView();
        if (Objects.isNull(principal)) {
            redirectView.setUrl("/login");
            return redirectView;
        }
        HashMap userDetails = (HashMap)((OAuth2Authentication)principal).getUserAuthentication().getDetails();
        Long fbId = (Long) userDetails.get("id");
        String nickname = (String) userDetails.get("name");

        // TODO: 회원가입 처리


        // TODO: login 성공 후 이동할 클라이언트 페이지로 리다이렉트
        redirectView.setUrl("/user");
        return redirectView;
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
