package com.pikaqiu.familybucket.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/23 23:19
 */
@Setter
@Getter
public class NoticeDTO {

    private String title;
    private String content;
    private String moduleType;
    private Integer type;
    private String url;
    private Long[] userIds;

    public NoticeDTO(String title, String content, String moduleType, Integer type, String url, Long[] userIds) {
        this.title = title;
        this.content = content;
        this.moduleType = moduleType;
        this.type = type;
        this.url = url;
        this.userIds = userIds;
    }

}
