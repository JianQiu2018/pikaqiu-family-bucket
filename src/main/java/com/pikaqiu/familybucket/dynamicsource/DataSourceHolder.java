package com.pikaqiu.familybucket.dynamicsource;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据源本地线程操作类
 */
public class DataSourceHolder {

    private static final ThreadLocal<String> dataSourceThreadLocal = new ThreadLocal<>();

    /**
     * 存储已经注册的数据源的key
     */
    public static List<String> dataSourceIds = new ArrayList<>();

    /**
     * 从当前线程中获取一个数据源名称
     * @return
     */
    public static String getDataSource() {
        return dataSourceThreadLocal.get();
    }

    /**
     * 设置线程数据源名称
     * @param dataSource
     */
    public static void setDataSource(String dataSource) {
        dataSourceThreadLocal.set(dataSource);
    }

    /**
     * 清线程除数据源名称
     */
    public static void clearDataSource() {
        dataSourceThreadLocal.remove();
    }

    /**
     * 判断指定DataSrouce当前是否存在
     *
     * @param dataSourceId
     * @return
     */
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }

}
