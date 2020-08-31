package com.pikaqiu.familybucket.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/11/10 17:56
 */
@Setter
@Getter
public class ArticleInfoDTO {

    private String title;
    private String content;
    private String coverImage;
    private Integer moduleType;

}
