package com.club.masterpiece.web.model;

import com.club.masterpiece.web.user.model.Role;
import com.club.masterpiece.web.user.model.User;
import com.club.masterpiece.web.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by pasudo123 on 2019-09-22
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@Transactional
public class UserDbTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Commit
    public void 유저를_삽입한다() {

        User user1 = User.builder()
                .id(UUID.randomUUID().toString())
                .password("test_pw")
                .username("홍길동")
                .role(Role.GENERAL_USER)
                .lastLoginDate(LocalDateTime.now())
                .email("test@gmail.com")
                .profile("test_profile")
                .uDescription("test_desc")
                .build();

        User user2 = User.builder()
                .id(UUID.randomUUID().toString())
                .password("test_pw")
                .username("이순신")
                .lastLoginDate(LocalDateTime.now())
                .role(Role.GENERAL_USER)
                .email("test@gmail.com")
                .profile("test_profile")
                .uDescription("test_desc")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

    }

    @Test
    public void 유저_조회_테스트() {

        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .password("test_pw")
                .username("브래드피트")
                .role(Role.GENERAL_USER)
                .lastLoginDate(LocalDateTime.of(2019, 1, 1, 22, 10))
                .email("test@gmail.com")
                .profile("test_profile")
                .uDescription("test_desc")
                .build();

        // 영속화, 디비저장, 준영속 전환
        entityManager.persist(user);
        entityManager.flush();
        entityManager.detach(user);

        System.out.println("\nflush() ==> detach()\n");

        // select 쿼리.
        User mergedUser = entityManager.merge(user);

        assertThat(user.getId(), is(mergedUser.getId()));
        assertThat(user.getRegDate(), is(mergedUser.getRegDate()));
    }

    @Test
    public void 자바_API_시간_테스트() {

        System.out.println(LocalDateTime.of(2019, 1, 1, 22, 10));

    }
}
