package com.pikaqiu.familybucket.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pikaqiu.familybucket.utils.DateUtility;
import com.pikaqiu.familybucket.utils.LocalDateTimePersistenceConverter;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class AuthUser{

    @Id
    private Long id;
    private String phoneNumber;
    private String password;
    private Integer tokenVersion;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    @JsonFormat(pattern= DateUtility.FULL_DATE_TIME_FORMAT)
    private LocalDateTime createTime;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    @JsonFormat(pattern= DateUtility.FULL_DATE_TIME_FORMAT)
    private LocalDateTime lastLoginTime;

}
