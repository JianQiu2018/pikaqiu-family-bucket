package com.pikaqiu.familybucket.service;

import com.pikaqiu.familybucket.dto.ArticleInfoDTO;
import com.pikaqiu.familybucket.entities.ArticleInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/7/29 23:40
 */
public interface ArticleInfoService {

    Page<ArticleInfo> getHomeArticleList(ArticleInfoDTO articleInfoDto, Pageable pageRequest);

    Page<ArticleInfo> getPersonArticleList(ArticleInfoDTO articleInfoDto, Pageable pageRequest);

    Mono<Boolean> addArticle(ArticleInfoDTO articleInfoDto);

    Mono<Boolean> delArticle(Long articleId);

    Mono<Boolean> updateArticle(Long commentId, ArticleInfoDTO articleInfoDto);

    List<ArticleInfo> getNewArticleList();

}
