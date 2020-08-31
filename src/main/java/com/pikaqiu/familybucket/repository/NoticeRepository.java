package com.pikaqiu.familybucket.repository;

import com.pikaqiu.familybucket.dynamicsource.DataSourceExchange;
import com.pikaqiu.familybucket.entities.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/23 22:57
 */
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @DataSourceExchange(value = "slave")
    List<Notice> findByNoticeId(Long noticeId);

}
