package com.pikaqiu.familybucket.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.pikaqiu.familybucket.utils.DateUtility;
import com.pikaqiu.familybucket.utils.LocalDateTimePersistenceConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Description: 轮播图配置
 *
 * @author PikaQiu
 * @date 2019/8/4 20:57
 */

@Entity
@Data
public class CarouselPic {

    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String title;
    private String picUrl;
    private String rediectUrl;
    private Boolean isOpen;
    private Integer sort;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    @JsonFormat(pattern= DateUtility.FULL_DATE_TIME_FORMAT)
    private LocalDateTime createTime;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    @JsonFormat(pattern= DateUtility.FULL_DATE_TIME_FORMAT)
    protected LocalDateTime lastModifyTime;

}
