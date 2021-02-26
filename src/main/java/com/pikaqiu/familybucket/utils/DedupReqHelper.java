package com.pikaqiu.familybucket.utils;

import com.alibaba.fastjson.JSON;
import com.pikaqiu.familybucket.aop.RedisLockAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class DedupReqHelper {

    private static final Logger logger = LoggerFactory.getLogger(RedisLockAspect.class);

    /**
     *
     * @param reqJSON 请求的参数，这里通常是JSON
     * @param excludeKeys 请求参数里面要去除哪些字段再求摘要
     * @return 去除参数的MD5摘要
     */
    public String dedupParamMD5(final String reqJSON, String... excludeKeys) {
        String decreptParam = reqJSON;
        TreeMap paramTreeMap = JSON.parseObject(decreptParam, TreeMap.class);
        if (excludeKeys != null) {
            List<String> dupExcludeKeys = Arrays.asList(excludeKeys);
            if (!dupExcludeKeys.isEmpty()) {
                for (String dedupExcludeKey : dupExcludeKeys) {
                    paramTreeMap.remove(dedupExcludeKey);
                }
            }
        }
        String paramTreeMapJSON = JSON.toJSONString(paramTreeMap);
        String md5deDupParam = jdkMD5(paramTreeMapJSON);
        logger.debug("md5deDupParam = {}, excludeKeys = {} {}", md5deDupParam, Arrays.deepToString(excludeKeys), paramTreeMapJSON);
        return md5deDupParam;
    }

    public static String jdkMD5(String src) {
        String res = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] mdBytes = messageDigest.digest(src.getBytes());
            res = DatatypeConverter.printHexBinary(mdBytes);
        } catch (Exception e) {
            logger.error("",e);
        }
        return res;
    }

}
