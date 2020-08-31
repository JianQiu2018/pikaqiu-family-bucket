package com.pikaqiu.familybucket.elasticsearch.service.impl;

import com.google.common.collect.Lists;
import com.pikaqiu.familybucket.elasticsearch.entities.ArticleInfoEs;
import com.pikaqiu.familybucket.elasticsearch.repository.ArticleInfoEsRepository;
import com.pikaqiu.familybucket.elasticsearch.service.ArticleInfoEsService;
import com.pikaqiu.familybucket.idworker.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ArticleInfoEsServiceImpl implements ArticleInfoEsService {

    @Autowired
    private ArticleInfoEsRepository articleInfoEsRepository;

    @Autowired
    private IdGenerator idGenerator;


    @Override
    public Mono<Boolean> save(ArticleInfoEs articleInfoEs2) {
        List<ArticleInfoEs> list = new ArrayList<>(50);
        for (int i = 0; i < 5; i++) {
            ArticleInfoEs articleInfoEs = new ArticleInfoEs();
            BeanUtils.copyProperties(articleInfoEs2, articleInfoEs);
            Long id2 = idGenerator.nextId();
            String id = String.valueOf(idGenerator.nextId());
            log.debug("idGenerator.nextId();{}",id);
            articleInfoEs.setIdLong(id2);
            articleInfoEs.setIdLong2(id2);
            articleInfoEs.setId(id);
            list.add(articleInfoEs);
//        articleInfoEs.setCreateTime(new Date());
        }
        articleInfoEsRepository.saveAll(list);
        return Mono.just(true);
    }

    @Override
    public List<ArticleInfoEs> findByTitle(String title) {
        //其中QueryBuilder是一个接口，常用的实现类有MatchQueryBuilder、RangeQueryBuilder范围、FuzzyQueryBuilder模糊、WildcardQueryBuilder模糊。
        List<ArticleInfoEs> list = new ArrayList<>();
        TermQueryBuilder termQuery = new TermQueryBuilder("title", title);
        Iterable<ArticleInfoEs> iterable = articleInfoEsRepository.search(termQuery);
        iterable.forEach(e -> list.add(e));
        return list;
    }

    @Override
    public List<ArticleInfoEs> getNewArticleList() {
        List<ArticleInfoEs> list = articleInfoEsRepository.findByTitles("共和国国歌");
        List<ArticleInfoEs> list2 = articleInfoEsRepository.findByTitleLike("共和国国歌");
        List<ArticleInfoEs> list3 = articleInfoEsRepository.findByContents("共和国国歌");
//        List<ArticleInfoEs> list4 = articleInfoEsRepository.findByContentLike("共和国国歌");
        return Lists.newArrayList(articleInfoEsRepository.findAll());
    }


}
