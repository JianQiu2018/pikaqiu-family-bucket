package com.pikaqiu.familybucket.repository;

import com.pikaqiu.familybucket.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/31 16:40
 */
public interface CommentRepository extends JpaRepository<Comment, Long>, QuerydslPredicateExecutor<Comment> {
}
