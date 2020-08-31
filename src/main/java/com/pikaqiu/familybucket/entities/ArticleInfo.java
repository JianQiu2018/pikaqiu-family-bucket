package com.pikaqiu.familybucket.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pikaqiu.familybucket.utils.DateUtility;
import com.pikaqiu.familybucket.utils.LocalDateTimePersistenceConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/7/29 23:32
 */
@Entity
@Setter
@Getter
public class ArticleInfo {

    @Id
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private Integer pageView;
    private Integer moduleType;
    private Integer heatNum;
    private Integer commentNum;
    private String coverImage;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    @JsonFormat(pattern= DateUtility.FULL_DATE_TIME_FORMAT)
    private LocalDateTime createTime;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    @JsonFormat(pattern= DateUtility.FULL_DATE_TIME_FORMAT)
    private LocalDateTime lastModifyTime;


}
