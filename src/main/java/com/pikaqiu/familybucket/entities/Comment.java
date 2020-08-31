package com.pikaqiu.familybucket.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
 * @date 2019/8/31 16:28
 */
@Entity
@Setter
@Getter
public class Comment {

    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private Long userId;
    private Long replyUserId;
    private String content;
    private Long resourceId;
    private Integer moduleType;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    /**
     * 数据库使用timestamp(4字节) 减少空间存储，但有数据范围的约束，有时区问题；像出生日期这种不能使用timestamp，必须使用datetime（8字节）
     */
    @JsonFormat(pattern= DateUtility.FULL_DATE_TIME_FORMAT)
    private LocalDateTime createTime;
    @JsonFormat(pattern= DateUtility.FULL_DATE_TIME_FORMAT)
    private LocalDateTime updateTime;


}
