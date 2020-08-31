package com.pikaqiu.familybucket.repository;

import com.pikaqiu.familybucket.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/6 23:41
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findByUserId(Long userId);

}
