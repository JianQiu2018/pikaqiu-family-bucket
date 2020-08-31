package com.pikaqiu.familybucket.service.impl;

import com.pikaqiu.familybucket.dto.ArticleInfoDTO;
import com.pikaqiu.familybucket.entities.ArticleInfo;
import com.pikaqiu.familybucket.entities.QArticleInfo;
import com.pikaqiu.familybucket.idworker.IdGenerator;
import com.pikaqiu.familybucket.repository.ArticleInfoRepository;
import com.pikaqiu.familybucket.repository.CommentRepository;
import com.pikaqiu.familybucket.service.ArticleInfoService;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/7/29 23:40
 */
@Service
public class ArticleInfoServiceImpl implements ArticleInfoService {

    @Autowired
    private ArticleInfoRepository articleInfoRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public Page<ArticleInfo> getHomeArticleList(ArticleInfoDTO articleInfoDto, Pageable pageRequest) {
        QArticleInfo qArticleInfo = QArticleInfo.articleInfo;
        Predicate predicate = qArticleInfo.id.isNotNull();
        if(StringUtils.isNotBlank(articleInfoDto.getTitle())){
            predicate = ExpressionUtils.allOf(predicate, qArticleInfo.title.like("%"+ articleInfoDto.getTitle() + "%"));
        }
        return articleInfoRepository.findAll(predicate, pageRequest);
    }

    @Override
    public Page<ArticleInfo> getPersonArticleList(ArticleInfoDTO articleInfoDto, Pageable pageRequest) {
        QArticleInfo qArticleInfo = QArticleInfo.articleInfo;
        Predicate predicate = qArticleInfo.id.isNotNull();
//        stringRedisTemplate.opsForValue().get("userId_"+)
        Long userId = null;
        if(StringUtils.isNotBlank(articleInfoDto.getTitle())){
            predicate = ExpressionUtils.allOf(predicate, qArticleInfo.title.like("%"+ articleInfoDto.getTitle() + "%"));
        }
        if (userId != null) {
            predicate = ExpressionUtils.allOf(predicate, qArticleInfo.userId.eq(userId));
        }
        return articleInfoRepository.findAll(predicate, pageRequest);
    }



    @Override
    public Mono<Boolean> addArticle(ArticleInfoDTO articleInfoDto) {
//        String userId = stringRedisTemplate.opsForValue().get(USERID_TOKEN + token);
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setId(idGenerator.nextId());
        articleInfo.setTitle(articleInfoDto.getTitle());
        articleInfo.setContent(articleInfoDto.getContent());
        articleInfo.setCoverImage(articleInfoDto.getCoverImage());
        articleInfo.setModuleType(articleInfoDto.getModuleType());
//        articleInfo.setUserId(Long.valueOf(userId));
        articleInfo.setCreateTime(LocalDateTime.now());
        articleInfo.setLastModifyTime(LocalDateTime.now());
        articleInfoRepository.save(articleInfo);
        return Mono.just(true);
    }

    @Override
    public Mono<Boolean> delArticle(Long articleId) {
        articleInfoRepository.deleteById(articleId);
        return Mono.just(true);
    }

    @Override
    public Mono<Boolean> updateArticle(Long commentId, ArticleInfoDTO articleInfoDto) {
        articleInfoRepository.findById(commentId).ifPresent(articleInfo -> {
            articleInfo.setTitle(articleInfoDto.getTitle());
            articleInfo.setModuleType(articleInfoDto.getModuleType());
            articleInfo.setCoverImage(articleInfoDto.getCoverImage());
            articleInfo.setContent(articleInfoDto.getContent());
            articleInfo.setLastModifyTime(LocalDateTime.now());
            articleInfoRepository.save(articleInfo);
        });
        return Mono.just(true);
    }

    @Override
    public List<ArticleInfo> getNewArticleList() {
        return articleInfoRepository.findAll(PageRequest.of(0,5, Sort.by(Sort.Direction.DESC, "heatNum"))).getContent();
    }

}
