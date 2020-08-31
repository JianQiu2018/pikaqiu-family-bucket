package com.pikaqiu.familybucket.feign;

import com.pikaqiu.familybucket.dto.HttpResponse;
import com.pikaqiu.familybucket.dto.NoticeDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/24 0:02
 */
@Component
public class FeignNoticeServiceFallbackImpl implements FeignNoticeService {

    @Override
    public HttpResponse<String> publishNotice(NoticeDTO noticeDto) {
        return new HttpResponse<>("发布消息远程调用接口异常~");
    }

    @Override
    public HttpResponse<String> testByteTcc(Integer id, BigDecimal money) {
        System.out.println("fail romote **********");
        return null;
    }

}
