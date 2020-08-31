package com.pikaqiu.familybucket.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/31 19:30
 */
@Setter
@Getter
public class CommentDTO {

    private Integer moduleType;
    private Long resourceId;
    private Long replyUserId;
    private String content;

}
