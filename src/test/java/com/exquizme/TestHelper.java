package com.exquizme;

import com.exquizme.domain.user.UserDto;

/**
 * Created by godong9 on 2017. 7. 22..
 */
public class TestHelper {
    public static UserDto getTestUserDto(Long fbId) {
        UserDto userDto = new UserDto();
        userDto.setFbId(fbId);
        return userDto;
    }
}
