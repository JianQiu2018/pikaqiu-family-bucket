package com.pikaqiu.familybucket.service;

import com.pikaqiu.familybucket.dto.CarouselPicDTO;
import com.pikaqiu.familybucket.entities.CarouselPic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/4 21:11
 */
public interface CarouselPicService {

    /**
     * 获取轮播列表
     *
     * @param pageRequest
     * @param carouselPicDto
     * @return
     */
    Page<CarouselPic> getList(CarouselPicDTO carouselPicDto, Pageable pageRequest);

    /**
     * 添加轮播信息
     *
     * @param carouselPicDto
     * @return
     */
    Mono<String> addCarouselPic(CarouselPicDTO carouselPicDto);

    /**
     * 是否启用/停用
     *
     * @param carouselPicDto
     * @return
     */
    Mono<Boolean> isOpen(Long id, CarouselPicDTO carouselPicDto);

    /**
     * 数据回显
     *
     * @param id
     * @return
     */
    Mono<CarouselPic> getInfo(Long id);

    /**
     * 数据更新
     *
     * @param id
     * @return
     */
    Mono<String> update(Long id, CarouselPicDTO carouselPicDto);


}
