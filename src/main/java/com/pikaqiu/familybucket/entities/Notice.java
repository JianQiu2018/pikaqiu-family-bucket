package com.pikaqiu.familybucket.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pikaqiu.familybucket.utils.DateUtility;
import com.pikaqiu.familybucket.utils.LocalDateTimePersistenceConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/23 23:16
 */
@Setter
@Getter
@Entity
public class Notice {

    @Id
    private Long id;
    private Long noticeId;
    private String title;
    private String content;
    private String moduleType;
    private Integer type;
    private String url;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    @JsonFormat(pattern= DateUtility.FULL_DATE_TIME_FORMAT)
    private LocalDateTime createTime;

}
