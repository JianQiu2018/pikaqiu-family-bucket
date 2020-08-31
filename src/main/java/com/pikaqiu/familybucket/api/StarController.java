package com.pikaqiu.familybucket.api;

import com.pikaqiu.familybucket.constants.Constants;
import com.pikaqiu.familybucket.dto.HttpResponse;
import com.pikaqiu.familybucket.dto.StarDTO;
import com.pikaqiu.familybucket.service.StarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * 功能描述： 点赞相关功能
 * @Author qiujian
 * @Date 2020/6/9
 */
@Slf4j
@RestController
public class StarController {

    @Autowired
    private StarService starService;

    /**
     * 点赞or取消赞
     * @param starDTO
     * @return
     */
    @PostMapping(value = Constants.URL_API_ADMIN_PREFIX + "/star/brightStar")
    public Mono<HttpResponse<Long>> brightStar(@RequestBody StarDTO starDTO) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/star/brightStar [Post].");
        }
        return starService.brightStar(starDTO).map(data -> new HttpResponse<Long>().setData(data));
    }

}
