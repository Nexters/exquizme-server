package com.exquizme.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
        User user = userRepository.save(User.builder()
                .fbId(userDto.getFbId())
                .email(userDto.getEmail())
                .nickname(userDto.getNickname())
                .profileImg(userDto.getProfileImg())
                .build());

        return user;
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }
}
