package com.pikaqiu.familybucket.api;

import com.pikaqiu.familybucket.constants.Constants;
import com.pikaqiu.familybucket.dto.HttpResponse;
import com.pikaqiu.familybucket.dto.MusicThirdPartDTO;
import com.pikaqiu.familybucket.dto.PageRequestDTO;
import com.pikaqiu.familybucket.entities.Music;
import com.pikaqiu.familybucket.service.MusicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Description: 改类音乐数据全部来自第三方
 *
 * @author PikaQiu
 * @date 2019/9/2 20:49
 */

@RestController
@Slf4j
public class MusicController {

    @Autowired
    private MusicService musicThirdPartService;

    /**
     * @api {GET} /api/admin/music/getList 音乐列表
     * @apiGroup Music
     * @apiUse GlobalErrorCode
     * @apiParam {String} title 标题, 支持向后模糊搜索
     * @apiParam {String} author 作者, 支持向后模糊搜索
     * @apiUse page
     * @apiUse size
     */
    @GetMapping(Constants.URL_API_ADMIN_PREFIX + "/music/getList")
    public Mono<HttpResponse<Page<Music>>> getList(MusicThirdPartDTO musicDto, PageRequestDTO pageRequestDto) {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/music/getList [GET].");
        }
        return Mono.just(new HttpResponse<Page<Music>>().setData(musicThirdPartService.getList(musicDto, PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize(), Sort.by(Sort.Direction.ASC, "createTime")))));
    }

    /**
     * @api {GET} /api/admin/music/getMusicListFromThirdPart 获取第三方音乐接口
     * @apiGroup MusicThirdPart
     * @apiUse GlobalErrorCode
     * @apiParam {String} type song 单曲，songlist 歌单，so 搜索，url 链接，pic 专辑图，lrc 歌词
     * @apiParam {String} id 歌曲/歌单的id，当type=so(搜索音乐)时，id的值为关键词，必须对中文进行URL编码
     * @apiParam {String} cache 默认=0不缓存，当cache=1时开启缓存，提高解析速度70%，数据不更新时，可把参数值改成cache=0访问一次即可清除缓存
     * @apiParam {String} nu 当type=so(搜索音乐)时有效，定义搜索结果数量，默认100
     */
    @GetMapping(Constants.URL_API_ADMIN_PREFIX + "/music/getMusicListFromThirdPart")
    public Mono<HttpResponse<Boolean>> getMusicListFromThirdPart(MusicThirdPartDTO musicThirdPartDto) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Request /api/admin/music/getMusicListFromThirdPart [GET].");
        }
        return musicThirdPartService.getMusicListFromThirdPart(musicThirdPartDto).map(data -> new HttpResponse<Boolean>().setData(data));
    }

}
