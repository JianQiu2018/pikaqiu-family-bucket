package com.pikaqiu.familybucket.feign;

import com.pikaqiu.familybucket.constants.Constants;
import com.pikaqiu.familybucket.dto.HttpResponse;
import com.pikaqiu.familybucket.dto.NoticeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/23 23:59
 */
//@FeignClient(name = "notice-service", fallbackFactory = FeignNoticeServiceFallbackImpl.class)
public interface FeignNoticeService {

    @PostMapping(Constants.URL_INTERNAL_PREFIX + Constants.URL_API_PREFIX + "/notice/publish")
    HttpResponse<String> publishNotice(@RequestBody NoticeDTO noticeDto);

    @PostMapping(Constants.URL_INTERNAL_PREFIX + Constants.URL_API_PREFIX + "/notice/byteTcc")
    HttpResponse<String> testByteTcc(@RequestParam Integer id, @RequestParam BigDecimal money);

}
