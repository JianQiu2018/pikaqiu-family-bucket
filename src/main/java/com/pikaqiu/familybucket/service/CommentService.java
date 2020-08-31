package com.pikaqiu.familybucket.service;

import com.pikaqiu.familybucket.dto.CommentDTO;
import com.pikaqiu.familybucket.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

/**
 * 功能描述：
 * @Author qiujian
 * @Date 2020/6/4
 */
public interface CommentService {


    Mono<Long> addComment(Comment comment);

    Page<Comment> getCommentList(CommentDTO commentDto , Pageable pageRequest);

    Mono<Boolean> delComment(Long commentId);

}
