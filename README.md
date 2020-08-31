# pikaqiu-family-bucket  

#个性签名  
****楚虽三户，亡秦必楚****

前言  
pikaqiu-family-bucket项目致力于打造一个完整的分布式系统，采用现阶段流行技术实现。  
目前只有后端接口，前端需自行开发;欢迎大家一起学习,共同进步！  
PS:启动类有很多注释,动态数据源与ByteTcc框架引入都已注释，需要的话可以私聊，只需简单几步即可使用  
PS:需要容器打包的同学，Dockerfile已写好，可修改下邮箱地址即可使用  
PS:项目中配置文件中含有xx or xxxx 的地方，需要自己去配置 
PS:想学习手写starter的同学，将pom.xml文件注释打开后，在自己的maven仓库下建地址\com\pikaqiu\starter\common-property-spring-boot-starter\1.0-SNAPSHOT 文件；将依赖jar已经放在了 /resoures/static文件下,然后放入该地址中即可。


#项目启动流程  
1.你需要先启动一个服务注册中心,这个不需要手写，网上很多已经存在的eureka服务下载下来，启动起来即可；学习springcloud第一步就是启动eureka-server,不清楚的需要先了解这部分知识  
2.修改/resoures文件下的application.yml文件配置，把mysql，redis,elasticsearch先关xxxx的地方改掉即可  
3.正常启动，启动后还有问题的可以在git上提问  

# 项目介绍  
该项目是基于springcloud + springboot + mysql实现。  
后台包括：音乐模块、文章模块、登录模块、评论模块、用户模块、上传模块  

#项目功能  
SpringSecurity + JWT 框架实现权限及登录  
QueryDsl + JPA 框架实现操作数据层  
Redis 实现延迟加载、点赞、TOKEN过期续签、数据缓存等等  
Elasticsearch 实现数据搜索  
Common-property-spring-boot-starter 自定义starter实现  
zxing 实现二维码生成  
DynamicDataSource 实现动态数据源自动切换  
@ExceptionHandler 全局异常处理  
Snowflake 实现雪花算法分布式ID  
Bytetcc 实现分布式事务管理  

#项目外技术栈  
Docker 实现项目的打包部署  
Nginx 实现静态服务搭建  
Minio 实现分布式文件服务器搭建  
GateWay 实现网关服务  
Music 实现第三方音乐服务  
Redis 实现缓存服务器（主从备份模式）  
Eureka 实现服务发现  

#项目版本  
springcloud: Hoxton.SR7  
springboot: 2.3.3.RELEASE  
elasticsearch: 7.6.2  
bytetcc: 0.5.9  
lombok: 1.18.4  
easypoi: 4.0.0  

#项目相关地址  
接口文档地址：http://pkqing.cn:8000/apidoc/index.html  
文件服务地址：http://pkqing.cn:9001/
