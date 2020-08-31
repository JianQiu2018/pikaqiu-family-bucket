package com.pikaqiu.familybucket.service.impl;

import com.pikaqiu.familybucket.dto.CommentDTO;
import com.pikaqiu.familybucket.entities.Comment;
import com.pikaqiu.familybucket.entities.QComment;
import com.pikaqiu.familybucket.idworker.IdGenerator;
import com.pikaqiu.familybucket.repository.CommentRepository;
import com.pikaqiu.familybucket.service.CommentService;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * 功能描述：
 * @Author qiujian
 * @Date 2020/6/4
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Mono<Long> addComment(Comment comment) {
        comment.setId(idGenerator.nextId());
        comment.setCreateTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        return Mono.just(commentRepository.save(comment).getId());
    }

    @Override
    public Page<Comment> getCommentList(CommentDTO commentDto, Pageable pageRequest) {
        QComment qComment = QComment.comment;
        Predicate predicate = qComment.id.isNotNull();
        if (commentDto.getModuleType() != null) {
            predicate = ExpressionUtils.allOf(predicate, qComment.moduleType.eq(commentDto.getModuleType()));
        }
        if (commentDto.getResourceId() != null) {
            predicate = ExpressionUtils.allOf(predicate, qComment.resourceId.eq(commentDto.getResourceId()));
        }
        for (Comment comment : commentRepository.findAll(predicate, pageRequest).getContent()) {
            System.out.println(comment.getCreateTime().getYear()+"--"+comment.getUpdateTime().getMonthValue());
        }
        return commentRepository.findAll(predicate, pageRequest);
    }


    @Override
    public Mono<Boolean> delComment(Long commentId) {
        commentRepository.deleteById(commentId);
        return Mono.just(true);
    }

}
