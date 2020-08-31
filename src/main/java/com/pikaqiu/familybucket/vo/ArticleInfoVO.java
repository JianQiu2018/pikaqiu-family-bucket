package com.pikaqiu.familybucket.vo;

import com.pikaqiu.familybucket.entities.ArticleInfo;
import lombok.Data;

import java.util.List;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/7/29 23:55
 */
@Data
public class ArticleInfoVO {

    public ArticleInfoVO(List<ArticleInfo> list) {
        this.list = list;
    }

    private List<ArticleInfo> list;

}
