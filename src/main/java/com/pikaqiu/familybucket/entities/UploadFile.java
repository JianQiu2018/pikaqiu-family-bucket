package com.pikaqiu.familybucket.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pikaqiu.familybucket.utils.DateUtility;
import com.pikaqiu.familybucket.utils.LocalDateTimePersistenceConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/4 17:11
 */
@Entity
@Data
public class UploadFile {

    @Id
    private Long id;
    private Long userId;
    private String fileName;
    private String fileType;
    private String url;
    private String mountUrl;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    @JsonFormat(pattern= DateUtility.FULL_DATE_TIME_FORMAT)
    private LocalDateTime createTime;

}
