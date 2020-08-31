package com.pikaqiu.familybucket.api;

import com.pikaqiu.familybucket.constants.Constants;
import com.pikaqiu.familybucket.dto.CarouselPicDTO;
import com.pikaqiu.familybucket.dto.HttpResponse;
import com.pikaqiu.familybucket.dto.PageRequestDTO;
import com.pikaqiu.familybucket.entities.CarouselPic;
import com.pikaqiu.familybucket.service.CarouselPicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/4 21:02
 */
@RestController
@Slf4j
public class CarouselPicController {

    @Autowired
    private CarouselPicService carouselPicService;

    /**
     * @api {GET} /api/admin/carouselPic/getList 轮播列表
     * @apiGroup Carousel
     * @apiUse GlobalErrorCode
     * @apiParam {String} title 名称, 支持向后模糊搜索
     * @apiParam {Boolean} isOpen 是否启用(false 停用，true 启用)
     * @apiUse page
     * @apiUse size
     */
    @GetMapping(Constants.URL_API_ADMIN_PREFIX + "/carouselPic/getList")
    public Mono<HttpResponse<Page<CarouselPic>>> getList(CarouselPicDTO carouselPicDto, PageRequestDTO pageRequestDto) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/carouselPic/getList [GET].");
        }
        return Mono.just(new HttpResponse<Page<CarouselPic>>().setData(carouselPicService.getList(carouselPicDto, PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize(), Sort.by(Sort.Direction.ASC, "sort")))));
    }

    /**
     * @api {POST} /api/admin/carouselPic/add 新增轮播信息
     * @apiGroup Carousel
     * @apiUse GlobalErrorCode
     * @apiParam {String} title 标题
     * @apiParam {String} picUrl 图片地址
     * @apiParam {String} rediectUrl 跳转地址
     * @apiParam {Boolean} isOpen 是否启用
     */
    @PostMapping(Constants.URL_API_ADMIN_PREFIX + "/carouselPic/add")
    public Mono<HttpResponse<String>> addCarouselPic(@RequestBody CarouselPicDTO carouselPicDto) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/carouselPic/add [POST].");
        }
        return carouselPicService.addCarouselPic(carouselPicDto).map(data -> new HttpResponse<String>().setData(data));
    }

    /**
     * @api {PUT} /api/admin/carouselPic/isOpen/{id} 启用/禁用轮播
     * @apiGroup Carousel
     * @apiUse GlobalErrorCode
     * @apiParam {Boolean} carouselPicDto.isOpen 是否启用
     */
    @PutMapping(Constants.URL_API_ADMIN_PREFIX + "/carouselPic/isOpen/{id}")
    public Mono<HttpResponse<Boolean>> isOpen(@Validated @RequestBody CarouselPicDTO carouselPicDto, @PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/carouselPic/isOpen [PUT].");
        }
        return carouselPicService.isOpen(id, carouselPicDto).map(data -> new HttpResponse<Boolean>().setData(data));
    }

    /**
     * @api {GET} /api/admin/carouselPic/getInfo/{id} 点击某一条，返回数据信息
     * @apiGroup Carousel
     * @apiUse GlobalErrorCode
     */
    @GetMapping(Constants.URL_API_ADMIN_PREFIX + "/carouselPic/getInfo/{id}")
    public Mono<HttpResponse<CarouselPic>> isOpen(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/carouselPic/getInfo/{id} [GET].");
        }
        return carouselPicService.getInfo(id).map(data -> new HttpResponse<CarouselPic>().setData(data));
    }

    /**
     * @api {PUT} /api/admin/carouselPic/update/{id} 更新轮播信息
     * @apiGroup Carousel
     * @apiUse GlobalErrorCode
     * @apiParam {String} title 标题
     * @apiParam {String} picUrl 图片地址
     * @apiParam {String} rediectUrl 跳转地址
     * @apiParam {Boolean} isOpen 是否启用
     */
    @PutMapping(Constants.URL_API_ADMIN_PREFIX + "/carouselPic/update/{id}")
    public Mono<HttpResponse<String>> update(@PathVariable("id") Long id, @RequestBody CarouselPicDTO carouselPicDto) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/carouselPic/getInfo/{id} [GET].");
        }
        return carouselPicService.update(id, carouselPicDto).map(data -> new HttpResponse<String>().setData(data));
    }

}
