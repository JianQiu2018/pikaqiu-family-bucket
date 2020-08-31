package com.pikaqiu.familybucket.api;

//import com.pikaqiu.starter.autoconfiguration.CommonPropertyService;
import com.pikaqiu.familybucket.entities.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class HomeController {

//    @Autowired
//    private CommonPropertyService commonPropertyService;

    @Value("${qiujian.test:123}")
    private String testValue;

    @GetMapping(value = "/api/home/getUserInfo")
    public AuthUser getUserInfo() {
//        System.out.println(commonPropertyService.getOutInfo());
        String str = "pikaqiu";
        log.info("Request getUserInfo:{}", str);
        log.info("Request testValue:{}", testValue);
        return new AuthUser();
    }

    @GetMapping(value = "/home/getUserInfos")
    public String getUserInfos() {
        String str = "pikaqius";
        log.info("Request getUserInfo:{}", str);
        return str;
    }


}
