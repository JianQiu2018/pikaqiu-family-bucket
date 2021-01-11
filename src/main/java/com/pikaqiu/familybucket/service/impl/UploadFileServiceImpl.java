package com.pikaqiu.familybucket.service.impl;

import com.pikaqiu.familybucket.entities.UploadFile;
import com.pikaqiu.familybucket.idworker.IdGenerator;
import com.pikaqiu.familybucket.repository.UploadFileRepository;
import com.pikaqiu.familybucket.service.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/4 17:13
 */
@Slf4j
@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private UploadFileRepository uploadFileRepository;

    @Autowired
    private IdGenerator idGenerator;

    @Value("${file.uploadPath}")
    private String fileUploadPath;

    @Value("${file.domain}")
    private String domain;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public Mono<String> save(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        String realFileName = fileName.substring(0, fileName.indexOf("."));
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        file.transferTo(new File(fileUploadPath + fileName));
        log.debug("imagePath:{}", fileUploadPath + fileName);
        UploadFile uploadFile = new UploadFile();
        uploadFile.setId(idGenerator.nextId());
        /*String userId = stringRedisTemplate.opsForValue().get(USERID_TOKEN + token);
        uploadFile.setUserId(Long.valueOf(userId));*/
        uploadFile.setFileName(realFileName);
        uploadFile.setFileType(fileType);
        uploadFile.setUrl(domain + fileName);
        uploadFile.setMountUrl(fileUploadPath);
        uploadFile.setCreateTime(LocalDateTime.now());
        return Mono.just(uploadFileRepository.save(uploadFile).getUrl());
    }

    @Override
    public Mono<String> excelUpload(MultipartFile file) throws IOException {


        return null;
    }

}
