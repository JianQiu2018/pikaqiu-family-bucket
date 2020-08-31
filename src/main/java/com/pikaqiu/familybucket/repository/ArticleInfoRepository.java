package com.pikaqiu.familybucket.repository;

import com.pikaqiu.familybucket.entities.ArticleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/7/29 23:32
 */
public interface ArticleInfoRepository extends JpaRepository<ArticleInfo, Long>, QuerydslPredicateExecutor<ArticleInfo> {



}
