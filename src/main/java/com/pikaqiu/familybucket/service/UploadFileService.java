package com.pikaqiu.familybucket.service;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/4 17:13
 */
public interface UploadFileService {

    /**
     * 用户基本信息添加
     * @param file
     * @return
     */
    Mono<String> save(MultipartFile file) throws IOException;

}
