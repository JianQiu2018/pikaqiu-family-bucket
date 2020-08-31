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
 * Description: 音乐
 *
 * @author PikaQiu
 * @date 2019/9/2 20:57
 */

@Entity
@Setter
@Getter
public class Music {

    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String title;
    private String author;
    private String url;
    private String pic;
    private String lrc;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    @JsonFormat(pattern= DateUtility.FULL_DATE_TIME_FORMAT)
    private LocalDateTime createTime;

}
