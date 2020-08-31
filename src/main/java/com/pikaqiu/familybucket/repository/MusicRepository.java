package com.pikaqiu.familybucket.repository;

import com.pikaqiu.familybucket.entities.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/9/2 21:12
 */
public interface MusicRepository extends JpaRepository<Music, Long>, QuerydslPredicateExecutor<Music> {
}
