package com.pikaqiu.familybucket.api;

import com.pikaqiu.familybucket.constants.Constants;
import com.pikaqiu.familybucket.dto.CommentDTO;
import com.pikaqiu.familybucket.dto.HttpResponse;
import com.pikaqiu.familybucket.dto.PageRequestDTO;
import com.pikaqiu.familybucket.entities.Comment;
import com.pikaqiu.familybucket.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * 功能描述： 添加评论
 * @Author qiujian
 * @Date 2020/6/4
 */
@Slf4j
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * @api {POST} /api/admin/comment/addComment 添加评论
     * @apiGroup Article
     * @apiUse GlobalErrorCode
     * @apiParam {number} resourceId 资源ID
     * @apiParam {Long} userId 回复人ID
     * @apiParam {Long} replyUserId 被回复人ID
     * @apiParam {String} content 评论内容
     * @apiParam {number} moduleType 模块类型
     */
    @PostMapping(value = Constants.URL_API_ADMIN_PREFIX + "/comment/addComment")
    public Mono<HttpResponse<Long>> addComment(@RequestBody Comment Comment) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/comment/addComment [Post].");
        }
        return commentService.addComment(Comment).map(data -> new HttpResponse<Long>().setData(data));
    }

    /**
     * @api {GET} /api/admin/comment/getCommentList 首页每篇文章评论列表
     * @apiDescription  文章展示评论列表，下滑到底自动加载数据
     * @apiGroup Comment
     * @apiUse GlobalErrorCode
     * @apiParam {number} moduleType 模块类型 1 文章模块，2 博客模块
     * @apiParam {Long} resourceId 资源ID
     * @apiUse page
     * @apiUse size
     */
    @GetMapping(value = Constants.URL_API_ADMIN_PREFIX + "/comment/getCommentList")
    public Mono<HttpResponse<Page<Comment>>> getCommentList(CommentDTO commentDto, PageRequestDTO pageRequestDto) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/comment/getCommentList [GET].");
        }
        return Mono.just(new HttpResponse<Page<Comment>>().setData(commentService.getCommentList(commentDto, PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize(), Sort.by(Sort.Direction.ASC, "createTime")))));
    }

    /**
     * @api {DELETE} /api/admin/comment/delComment/{commentId} 删除评论
     * @apiGroup Comment
     * @apiUse GlobalErrorCode
     * @apiParam {Long} commentId 评论ID
     */
    @DeleteMapping(value = Constants.URL_API_ADMIN_PREFIX + "/comment/delComment/{commentId}")
    public Mono<HttpResponse<Boolean>> delComment(@PathVariable Long commentId) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/comment/delComment [Delete].");
        }
        return commentService.delComment(commentId).map(data -> new HttpResponse<Boolean>().setData(data));
    }


}
