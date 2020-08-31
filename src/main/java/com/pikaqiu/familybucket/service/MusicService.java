package com.pikaqiu.familybucket.service;

import com.pikaqiu.familybucket.dto.MusicThirdPartDTO;
import com.pikaqiu.familybucket.entities.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Mono;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/9/2 21:13
 */
public interface MusicService {


    Page<Music> getList(MusicThirdPartDTO musicDto, PageRequest pageRequest);

    Mono<Boolean> getMusicListFromThirdPart(MusicThirdPartDTO musicThirdPartDto) throws Exception;


}
