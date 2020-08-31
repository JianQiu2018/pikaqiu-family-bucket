package com.pikaqiu.familybucket.repository;

import com.pikaqiu.familybucket.entities.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/6 22:32
 */
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByPhoneNumber(String phoneNumber);

}
