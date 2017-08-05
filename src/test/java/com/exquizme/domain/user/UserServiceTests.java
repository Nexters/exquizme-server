package com.exquizme.domain.user;

import com.exquizme.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by godong9 on 2017. 7. 22..
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    public void fail_findOne_when_invalid_id() {
        // given
        UserDto testUserDto = TestHelper.getTestUserDto(1234567890L);
        userService.create(testUserDto);

        // when
        User user = userService.findOne(-1L);

        // then
        assertEquals(null, user);
    }

    @Test
    public void success_findOne() {
        // given
        Long testFbId = 1234567890L;
        UserDto testUserDto = TestHelper.getTestUserDto(testFbId);
        User testUser = userService.create(testUserDto);

        // when
        User user = userService.findOne(testUser.getId());

        // then
        assertThat(user.getFbId(), is(testFbId));
    }
}
