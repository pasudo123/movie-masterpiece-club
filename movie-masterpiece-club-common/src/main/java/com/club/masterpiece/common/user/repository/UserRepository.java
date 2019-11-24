package com.club.masterpiece.common.user.repository;

import com.club.masterpiece.common.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by pasudo123 on 2019-09-22
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findOneById(String id);

}
