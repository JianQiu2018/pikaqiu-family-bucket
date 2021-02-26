package com.pikaqiu.familybucket.api;

//import com.pikaqiu.starter.autoconfiguration.CommonPropertyService;
import com.pikaqiu.familybucket.annotation.DupRequestAnnotation;
import com.pikaqiu.familybucket.annotation.RedisLockAnnotation;
import com.pikaqiu.familybucket.dto.CommentDTO;
import com.pikaqiu.familybucket.entities.AuthUser;
import com.pikaqiu.familybucket.enums.RedisLockTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


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

    @DupRequestAnnotation(times = 3000, excludeKeys = {"moduleType", "resourceId"})
    @PostMapping(value = "/api/home/dupRequest")
    public String getUserInfos(@RequestBody CommentDTO commentDTO) {
        String str = "pikaqius";
        log.info("Request getUserInfo:{}", str);
        return str;
    }

    @DupRequestAnnotation(times = 3000)
    @GetMapping(value = "/api/home/dupRequest2")
    public String getUserInfos2(CommentDTO commentDTO) {
        String str = "pikaqius";
        log.info("Request getUserInfo:{}", str);
        return str;
    }

    @GetMapping("/api/home/testRedisLock")
    @RedisLockAnnotation(typeEnum = RedisLockTypeEnum.ONE, lockTime = 3)
    public String testRedisLock(@RequestParam("userId") Long userId) {
        /*try {
            log.info("睡眠执行前");
            Thread.sleep(4000);
            log.info("睡眠执行后");
        } catch (Exception e) {
            // log error
            log.info("has some error", e);
        }*/
        return "2344";
    }


}
