package com.pikaqiu.familybucket.redis.lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单机版本系统使用(库存秒杀)
 */
public class HashMapLock {

    // ConcurrentHashMap实现高效线程安全
    private static ConcurrentHashMap<Long, Boolean> BUSINESS_FLAG_MAP = new ConcurrentHashMap<>();
    // 由于AtomicInteger本身具备原子性，因此这里可以直接使用HashMap
    private static ConcurrentHashMap<Long, AtomicInteger> BUSINESS_HASHMAP = new ConcurrentHashMap<>();

    static {
        //通过消息通知初始化数据
        BUSINESS_HASHMAP.put(123L, new AtomicInteger(1999));
        BUSINESS_FLAG_MAP.put(123L, true);
        System.out.println("第一次初始化数据");
    }

    private static int handleLockBusiness(Long businessId){
        if(!BUSINESS_FLAG_MAP.get(businessId)){
            System.out.println("业务异常");
            System.exit(1);
        }
        if(BUSINESS_HASHMAP.get(businessId).decrementAndGet() < 0){
            BUSINESS_FLAG_MAP.put(businessId, false);
            System.out.println("库存不足");
            System.exit(1);
        }
        // 生成订单
        return 0;
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();
        AtomicInteger integer = new AtomicInteger(1);
        map.put("key", integer);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
//                    map.get("key").incrementAndGet();
                    handleLockBusiness(123L);
            });
        }
        Thread.sleep(3000); //等待执行结束
//        System.out.println("------" + map.get("key") + "------");
        System.out.println(BUSINESS_HASHMAP.get(123L).get() + "*******");
        executorService.shutdown();
    }

}
