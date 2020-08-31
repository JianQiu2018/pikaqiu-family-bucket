package com.pikaqiu.familybucket.dynamicsource;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-10)//保证该AOP在@Transactional之前执行
@Component
public class DataSourceAspect {

    private Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    /**
     *
     * @annotation(targetDataSource)：
     * 会拦截注解targetDataSource的方法，否则不拦截;
     */
    @Before("@annotation(dataSourceExchange)")
    public void changeDataSource(JoinPoint point, DataSourceExchange dataSourceExchange) throws Throwable {
        //获取当前的指定的数据源;
        String dsId = dataSourceExchange.value();
        //如果不在我们注入的所有的数据源范围之内，那么输出警告信息，系统自动使用默认的数据源。
        if (!DataSourceHolder.containsDataSource(dsId)) {
            logger.error("数据源[{}]不存在，使用默认数据源 > [{}]", dsId , dataSourceExchange.value() );
        } else {
            logger.debug("Use DataSource : [{}] > Use Method [{}]", dataSourceExchange.value(), point.getSignature());
            //找到的话，那么设置到动态数据源上下文中。
            DataSourceHolder.setDataSource(dataSourceExchange.value());
        }
    }

    @After("@annotation(dataSourceExchange)")
    public void restoreDataSource(JoinPoint point, DataSourceExchange dataSourceExchange) {
        logger.debug("Clear DataSource : [{}] ", dataSourceExchange.value());
        //方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
        logger.debug("当前的数据源是： {}", DataSourceHolder.getDataSource());
        DataSourceHolder.clearDataSource();
    }


}
