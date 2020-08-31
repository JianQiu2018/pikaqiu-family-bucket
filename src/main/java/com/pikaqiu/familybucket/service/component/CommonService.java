package com.pikaqiu.familybucket.service.component;

import com.pikaqiu.familybucket.dto.NoticeDTO;
import org.springframework.stereotype.Component;

/**
 * Description: 公共接口
 *
 * @author PikaQiu
 * @date 2019/8/24 10:36
 */
@Component
public class CommonService {

    /**
     * 发布通知
     *
     * @param title
     * @param content
     * @param moduleType
     * @param type
     * @param url
     * @param userIds
     * @return
     */
    public NoticeDTO publishNotice(String title, String content, String moduleType, Integer type, String url, Long[] userIds) {
        return new NoticeDTO(title, content, moduleType, type, url, userIds);
    }

}
