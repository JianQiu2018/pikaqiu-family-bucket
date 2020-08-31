package com.pikaqiu.familybucket.dto;

import lombok.Data;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/4 21:23
 */
@Data
public class PageRequestDTO {

    /**
     *
     * @apiDefine page
     * @apiParam {int} page  当前页
     */
    private int page;
    /**
     * @apiDefine size
     * @apiParam {int} size  当前条数
     */
    private int size;

}
