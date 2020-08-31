package com.pikaqiu.familybucket.api;

import com.pikaqiu.familybucket.constants.Constants;
import com.pikaqiu.familybucket.dto.ArticleInfoDTO;
import com.pikaqiu.familybucket.dto.HttpResponse;
import com.pikaqiu.familybucket.dto.PageRequestDTO;
import com.pikaqiu.familybucket.entities.ArticleInfo;
import com.pikaqiu.familybucket.service.ArticleInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * Description: 文章接口
 *
 * @author PikaQiu
 * @date 2019/7/29 23:33
 */
@RestController
@Slf4j
public class ArticleInfoController {

    @Autowired
    private ArticleInfoService articleInfoService;

    /**
     * @api {GET} /api/admin/article/getHomeArticleList 首页文章列表
     * @apiDescription  首页_文章列表，下滑到底自动加载数据
     * @apiGroup Article
     * @apiParam {String} title 文章标题
     * @apiUse GlobalErrorCode
     * @apiUse page
     * @apiUse size
     */
    @GetMapping(value = Constants.URL_API_ADMIN_PREFIX + "/article/getHomeArticleList")
    public Mono<HttpResponse<Page<ArticleInfo>>> getHomeArticleList(PageRequestDTO pageRequestDto, ArticleInfoDTO articleInfoDto) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/article/getHomeArticleList [GET].");
        }
        return Mono.just(new HttpResponse<Page<ArticleInfo>>().setData(articleInfoService.getHomeArticleList(articleInfoDto, PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize(), Sort.by(Sort.Direction.ASC, "createTime")))));
    }

    /**
     * @api {GET} /api/admin/article/getPersonArticleList 个人空间文章列表
     * @apiDescription  个人空间_文章列表，下滑到底自动加载数据
     * @apiGroup Article
     * @apiParam {String} title 文章标题
     * @apiUse GlobalErrorCode
     * @apiUse page
     * @apiUse size
     */
    @GetMapping(value = Constants.URL_API_ADMIN_PREFIX + "/article/getPersonArticleList")
    public Mono<HttpResponse<Page<ArticleInfo>>> getPersonArticleList(PageRequestDTO pageRequestDto, ArticleInfoDTO articleInfoDto) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/article/getPersonArticleList [GET].");
        }
        return Mono.just(new HttpResponse<Page<ArticleInfo>>().setData(articleInfoService.getPersonArticleList(articleInfoDto, PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize(), Sort.by(Sort.Direction.ASC, "createTime")))));
    }

    /**
     * @api {GET} /api/admin/article/getNewArticleList 个人空间文章列表
     * @apiDescription  首页_最新文章列表（只展示五条）
     * @apiGroup Article
     * @apiUse GlobalErrorCode
     * @apiUse page
     * @apiUse size
     */
    @GetMapping(value = Constants.URL_API_ADMIN_PREFIX + "/article/getNewArticleList")
    public Mono<HttpResponse<List<ArticleInfo>>> getNewArticleList() {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/article/getNewArticleList [GET].");
        }
        return Mono.just(new HttpResponse<List<ArticleInfo>>().setData(articleInfoService.getNewArticleList()));
    }

    /**
     * @api {POST} /api/admin/article/addArticle 发布动态/博客文章
     * @apiGroup Article
     * @apiUse GlobalErrorCode
     * @apiParam {number} moduleType 模块类型 1 动态模块，2 博客模块
     * @apiParam {String} coverImage 封面图片
     * @apiParam {String} content 发布内容
     * @apiParam {String} title 发布标题
     */
    @PostMapping(value = Constants.URL_API_ADMIN_PREFIX + "/article/addArticle")
    public Mono<HttpResponse<Boolean>> addArticle(@RequestBody ArticleInfoDTO articleInfoDto) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/article/addArticle [Post].");
        }
        return articleInfoService.addArticle(articleInfoDto).map(data -> new HttpResponse<Boolean>().setData(data));
    }

    /**
     * @api {PUT} /api/admin/article/updateArticle/{commentId} 编辑文章内容
     * @apiGroup Article
     * @apiUse GlobalErrorCode
     * @apiParam {number} moduleType 模块类型 1 动态模块，2 博客模块
     * @apiParam {String} coverImage 封面图片
     * @apiParam {String} content 发布内容
     * @apiParam {String} title 发布标题
     */
    @PutMapping(value = Constants.URL_API_ADMIN_PREFIX + "/article/updateArticle/{commentId}")
    public Mono<HttpResponse<Boolean>> updateArticle(@PathVariable Long commentId, @RequestBody ArticleInfoDTO articleInfoDto) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/article/updateArticle/{commentId} [Post].");
        }
        return articleInfoService.updateArticle(commentId, articleInfoDto).map(data -> new HttpResponse<Boolean>().setData(data));
    }

    /**
     * @api {DELETE} /api/admin/article/delComment/{articleId} 删除动态/博客文章
     * @apiGroup Article
     * @apiUse GlobalErrorCode
     * @apiParam {Long} articleId 文章ID
     */
    @DeleteMapping(value = Constants.URL_API_ADMIN_PREFIX + "/article/delArticle/{articleId}")
    public Mono<HttpResponse<Boolean>> delArticle(@PathVariable Long articleId) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/article/delArticle [Delete].");
        }
        return articleInfoService.delArticle(articleId).map(data -> new HttpResponse<Boolean>().setData(data));
    }


}
