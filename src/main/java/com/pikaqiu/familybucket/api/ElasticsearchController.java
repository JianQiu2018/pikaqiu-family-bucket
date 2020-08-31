package com.pikaqiu.familybucket.api;

import com.pikaqiu.familybucket.constants.Constants;
import com.pikaqiu.familybucket.dto.HttpResponse;
import com.pikaqiu.familybucket.elasticsearch.entities.ArticleInfoEs;
import com.pikaqiu.familybucket.elasticsearch.service.ArticleInfoEsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Slf4j
public class ElasticsearchController {

    @Autowired
    private ArticleInfoEsService articleInfoEsService;

    @PutMapping(value = Constants.URL_API_ADMIN_PREFIX + "/articleEs/addArticle")
    public Mono<HttpResponse<Boolean>> addArticle(@RequestBody ArticleInfoEs articleInfoEs) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/articleEs/addArticle [Post].");
        }
        return articleInfoEsService.save(articleInfoEs).map(data -> new HttpResponse<Boolean>().setData(data));
    }

    @GetMapping(value = Constants.URL_API_ADMIN_PREFIX + "/articleEs/getNewArticleList")
    public Mono<HttpResponse<List<ArticleInfoEs>>> getNewArticleList() {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/articleEs/getNewArticleList [GET].");
        }
        return Mono.just(new HttpResponse<List<ArticleInfoEs>>().setData(articleInfoEsService.getNewArticleList()));
    }

}
