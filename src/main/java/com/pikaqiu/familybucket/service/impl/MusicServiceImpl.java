package com.pikaqiu.familybucket.service.impl;

import com.alibaba.fastjson.JSON;
import com.pikaqiu.familybucket.dto.HttpResponse;
import com.pikaqiu.familybucket.dto.MusicThirdPartDTO;
import com.pikaqiu.familybucket.entities.Music;
import com.pikaqiu.familybucket.entities.MusicThirdPart;
import com.pikaqiu.familybucket.entities.Notice;
import com.pikaqiu.familybucket.entities.QMusic;
import com.pikaqiu.familybucket.repository.MusicRepository;
import com.pikaqiu.familybucket.repository.MusicThirdPartRepository;
import com.pikaqiu.familybucket.repository.NoticeRepository;
import com.pikaqiu.familybucket.service.MusicService;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.util.List;


/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/9/2 21:13
 */
@Slf4j
@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicThirdPartRepository musicThirdPartRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private NoticeRepository noticeRepository;


    @Override
    public Page<Music> getList(MusicThirdPartDTO musicThirdPartDto, PageRequest pageRequest) {
        QMusic qMusic = QMusic.music;
        Predicate predicate = qMusic.id.isNotNull();
        if (musicThirdPartDto.getTitle() != null) {
            predicate = ExpressionUtils.allOf(predicate, qMusic.title.like(musicThirdPartDto.getTitle() + "%"));
        }
        if (musicThirdPartDto.getAuthor() != null) {
            predicate = ExpressionUtils.allOf(predicate, qMusic.author.eq(musicThirdPartDto.getAuthor() + "%"));
        }
        //动态数据源测试通过
        List<Notice> list = noticeRepository.findByNoticeId(1165120205312081920L);
        System.out.println("--------list"+ list.size());
        return null;//musicRepository.findAll(predicate, pageRequest);
    }

    @Override
    public Mono<Boolean> getMusicListFromThirdPart(MusicThirdPartDTO musicThirdPartDto) throws Exception {
        // 创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String type = "so";
        StringBuilder params = new StringBuilder();
        params.append("type=" + musicThirdPartDto.getType()).append("&cache="+ musicThirdPartDto.getCache());
        if(type.equals(musicThirdPartDto.getType())){
            params.append("&id=" + URLEncoder.encode(musicThirdPartDto.getId(),"utf-8"));
            if(StringUtils.isNotEmpty(musicThirdPartDto.getNu())){
                params.append("&nu="+musicThirdPartDto.getNu());
            }
        }else {
            params.append("&id=" + musicThirdPartDto.getId());
        }
        URIBuilder uriBuilder = new URIBuilder("https://api.mlwei.com/music/api/wy/?key=523077333&"+ params);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        // 创建httpResponse对象
        CloseableHttpResponse closeableHttpResponse = null;
        HttpResponse httpResponse = getHttpClientResult(closeableHttpResponse, httpClient, httpGet);
        if (httpResponse.getData() != null) {
            List<MusicThirdPart> musicList = JSON.parseArray(JSON.parseObject(httpResponse.getData().toString()).getString("Body"), MusicThirdPart.class);
            musicThirdPartRepository.saveAll(musicList);
            log.debug("from third part music :{}", musicList.size());
        }
        return Mono.just(true);
    }


    /**
     * Description: 获得响应结果
     *
     * @param httpResponse
     * @param httpClient
     * @param httpMethod
     * @return
     * @throws Exception
     */
    public static HttpResponse getHttpClientResult(CloseableHttpResponse httpResponse,
                                                   CloseableHttpClient httpClient, HttpRequestBase httpMethod) throws Exception {
        // 执行请求
        httpResponse = httpClient.execute(httpMethod);
        // 获取返回结果
        if (httpResponse != null && httpResponse.getStatusLine() != null) {
            String content = "";
            if (httpResponse.getEntity() != null) {
                log.debug("httpResponse.content:{}", httpResponse.getEntity());
                content = EntityUtils.toString(httpResponse.getEntity());
            }
            return new HttpResponse().setData(content);
        }
        return new HttpResponse().setErrorMessage(String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR));
    }

}
