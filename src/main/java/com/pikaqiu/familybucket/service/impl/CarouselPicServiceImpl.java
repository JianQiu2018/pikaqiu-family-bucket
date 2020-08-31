package com.pikaqiu.familybucket.service.impl;

import com.pikaqiu.familybucket.dto.CarouselPicDTO;
import com.pikaqiu.familybucket.entities.CarouselPic;
import com.pikaqiu.familybucket.entities.QCarouselPic;
import com.pikaqiu.familybucket.idworker.IdGenerator;
import com.pikaqiu.familybucket.repository.CarouselPicRepository;
import com.pikaqiu.familybucket.service.CarouselPicService;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/4 21:11
 */
@Service
public class CarouselPicServiceImpl implements CarouselPicService {

    @Autowired
    private CarouselPicRepository carouselPicRepository;

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public Page<CarouselPic> getList(CarouselPicDTO carouselPicDto, Pageable pageRequest) {
        QCarouselPic qCarouselPic = QCarouselPic.carouselPic;
        Predicate predicate = qCarouselPic.id.isNotNull();
        if(StringUtils.isNotEmpty(carouselPicDto.getTitle())){
            predicate = ExpressionUtils.allOf(predicate, qCarouselPic.title.like(carouselPicDto.getTitle() + "%"));
        }
        if(carouselPicDto.getIsOpen() != null){
            predicate = ExpressionUtils.allOf(predicate, qCarouselPic.isOpen.eq(carouselPicDto.getIsOpen()));
        }
        return carouselPicRepository.findAll(predicate, pageRequest);
    }

    @Override
    public Mono<String> addCarouselPic(CarouselPicDTO carouselPicDto) {
        CarouselPic carouselPic = new CarouselPic();
        BeanUtils.copyProperties(carouselPicDto, carouselPic);
        carouselPic.setId(idGenerator.nextId());
        carouselPic.setCreateTime(LocalDateTime.now());
        carouselPic.setLastModifyTime(LocalDateTime.now());
        return Mono.just(carouselPicRepository.save(carouselPic).getId().toString());
    }

    @Override
    public Mono<Boolean> isOpen(Long id, CarouselPicDTO carouselPicDto) {
        Optional<CarouselPic> carouselPicOptional = carouselPicRepository.findById(id);
        if(carouselPicOptional.isPresent()){
            CarouselPic carouselPic = carouselPicOptional.get();
            carouselPic.setIsOpen(carouselPicDto.getIsOpen());
            carouselPicRepository.save(carouselPic);
        }
        return Mono.just(carouselPicDto.getIsOpen());
    }

    @Override
    public Mono<CarouselPic> getInfo(Long id) {
        return Mono.just(carouselPicRepository.findById(id).get());
    }

    @Override
    public Mono<String> update(Long id, CarouselPicDTO carouselPicDto) {
        Optional<CarouselPic> carouselPicOptional = carouselPicRepository.findById(id);
        if(carouselPicOptional.isPresent()){
            CarouselPic carouselPic = carouselPicOptional.get();
            BeanUtils.copyProperties(carouselPicDto, carouselPic);
            carouselPic.setLastModifyTime(LocalDateTime.now());
            carouselPicRepository.save(carouselPic);
        }
        return Mono.just(id.toString());
    }


}
