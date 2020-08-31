package com.pikaqiu.familybucket.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Description: 音乐
 *
 * @author PikaQiu
 * @date 2019/9/2 20:57
 */

@Entity
@Setter
@Getter
public class MusicThirdPart {

    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String title;
    private String author;
    private String url;
    private String pic;
    private String lrc;


}
