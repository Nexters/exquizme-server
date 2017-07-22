package com.exquizme.domain.user;

import lombok.Data;

/**
 * Created by godong9 on 2017. 7. 22..
 */

@Data
public class UserDto {
    private Long id;
    private String fbId;
    private String nickname;
    private String email;
    private String profileImg;
}
