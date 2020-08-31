package com.pikaqiu.familybucket.elasticsearch.repository;

import com.pikaqiu.familybucket.elasticsearch.entities.ArticleInfoEs;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/26 23:32
 */
@Repository
public interface ArticleInfoEsRepository extends ElasticsearchRepository<ArticleInfoEs, String> {

    @Query("{\"match\": {\"title\": {\"query\": \"?0\"}}}")
    List<ArticleInfoEs> findByTitles(String title);

    List<ArticleInfoEs> findByTitleLike(String title);

    @Query("{\"match\": {\"content\": {\"query\": \"?0\"}}}")
    List<ArticleInfoEs> findByContents(String content);

    List<ArticleInfoEs> findByContentLike(String content);

}
