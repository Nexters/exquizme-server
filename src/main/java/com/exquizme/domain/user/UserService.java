package com.exquizme.domain.user;

import com.exquizme.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by godong9 on 2017. 7. 22..
 */

@Transactional(readOnly = true)
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = false)
    public User create(UserDto userDto) {
        return userRepository.save(User.builder()
                .fbId(userDto.getFbId())
                .email(userDto.getEmail())
                .nickname(userDto.getNickname())
                .profileImg(userDto.getProfileImg())
                .build());
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public User findByFbId(Long fbId) {
        return userRepository.findByFbId(fbId);
    }

    @Transactional(readOnly = false)
    public User getCurrentUser(Principal principal) {
        if (Objects.isNull(principal)) {
            throw new UserException("Need login!");
        }

        HashMap userDetails = (HashMap)((OAuth2Authentication)principal).getUserAuthentication().getDetails();
        Long fbId = Long.parseLong((String) userDetails.get("id"));
        String nickname = (String) userDetails.get("name");

        User user = findByFbId(fbId);
        if (Objects.isNull(user)) {
            UserDto userDto = new UserDto();
            userDto.setFbId(fbId);
            userDto.setNickname(nickname);
            user = create(userDto);
        }
        return user;
    }

    public User getTestUser() {
        return User.builder().id(1L).build();
    }
}
