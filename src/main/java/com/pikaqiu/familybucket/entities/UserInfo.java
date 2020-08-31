package com.pikaqiu.familybucket.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pikaqiu.familybucket.utils.DateUtility;
import com.pikaqiu.familybucket.utils.LocalDateTimePersistenceConverter;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/6 23:39
 */
@Entity
@Data
public class UserInfo {

    @Id
    private Long id;
    private Long userId;
    private String userName;
    private String gender;
    private String personalProfile;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    @JsonFormat(pattern= DateUtility.FULL_DATE_TIME_FORMAT)
    private LocalDateTime createTime;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    @JsonFormat(pattern= DateUtility.FULL_DATE_TIME_FORMAT)
    protected LocalDateTime lastModifyTime;



}
