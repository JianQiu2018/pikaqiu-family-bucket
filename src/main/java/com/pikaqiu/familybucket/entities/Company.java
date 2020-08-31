package com.pikaqiu.familybucket.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
public class Company {

    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private Integer id;
    private BigDecimal money;
    private BigDecimal frozen;

}
