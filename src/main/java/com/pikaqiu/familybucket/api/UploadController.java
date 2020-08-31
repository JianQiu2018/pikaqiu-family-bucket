package com.pikaqiu.familybucket.api;

import com.pikaqiu.familybucket.constants.Constants;
import com.pikaqiu.familybucket.dto.HttpResponse;
import com.pikaqiu.familybucket.service.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;


/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/4 0:11
 */
@Slf4j
@RestController
public class UploadController {

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * @api {POST} /api/admin/upload 上传文件
     * @apiGroup Upload
     * @apiUse GlobalErrorCode
     * @apiParam {formData} file 对象
     */
    @PostMapping(value = Constants.URL_API_ADMIN_PREFIX + "/upload")
    public Mono<HttpResponse<String>> fileUpload(MultipartFile file) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Request /upload [POST].");
        }
        return uploadFileService.save(file).map(data -> new HttpResponse<String>().setData(data));
    }


}
