package com.pikaqiu.familybucket.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/4 21:29
 */
@Data
public class CarouselPicDTO {

    private String picUrl;
    private String rediectUrl;
    private String title;
    @NotNull(message = "isOpen parameter cannot be empty")
    private Boolean isOpen;
    private Integer sort;

}
