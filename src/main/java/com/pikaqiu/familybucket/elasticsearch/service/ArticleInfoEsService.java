package com.pikaqiu.familybucket.elasticsearch.service;

import com.pikaqiu.familybucket.elasticsearch.entities.ArticleInfoEs;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ArticleInfoEsService {

    Mono<Boolean> save(ArticleInfoEs articleInfoEs);

    List<ArticleInfoEs> findByTitle(String title);

    List<ArticleInfoEs> getNewArticleList();


}
