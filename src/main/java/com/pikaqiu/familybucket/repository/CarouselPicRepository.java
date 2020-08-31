package com.pikaqiu.familybucket.repository;

import com.pikaqiu.familybucket.entities.CarouselPic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CarouselPicRepository extends JpaRepository<CarouselPic,Long> , QuerydslPredicateExecutor<CarouselPic> {


}
