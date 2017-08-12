package com.exquizme.domain.user;

import com.exquizme.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Objects;

/**
 * Created by godong9 on 2017. 7. 22..
 */

@Slf4j
@RestController
public class UserController {

    /**
     * @api {get} /api/login Login page
     * @apiName LoginPage
     * @apiGroup User
     *
     * @apiDescription 로그인 페이지. 페이지를 로그인 페이지로 직접 이동해야 함!
     */

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public RedirectView main(Principal principal) {
        RedirectView redirectView = new RedirectView();
        if (Objects.isNull(principal)) {
            redirectView.setUrl("/login");
            return redirectView;
        }
        log.info("User: {}", userService.getCurrentUser(principal).toString());

        // TODO: login 성공 후 이동할 클라이언트 페이지로 리다이렉트
        redirectView.setUrl("/user");
        return redirectView;
    }

    @RequestMapping("/user")
    public ServerResponse user(Principal principal) {
        return ServerResponse.success(userService.getCurrentUser(principal));
    }
}
