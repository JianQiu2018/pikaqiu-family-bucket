package com.pikaqiu.familybucket.service;

/**
 *
 * @apiDefine GlobalErrorCode
 *
 * @apiVersion 0.0.0
 * @apiError 1000 用户不存在
 * @apiError 1001 密码不匹配
 * @apiSuccessExample {json} 返回成功示例:
 *  {"resultCode":0,"message": null,"data": "返回的数据集"}
 * @apiErrorExample  {json} 返回失败示例:
 *  {"resultCode":1000,"message": "密码不匹配","data": null}
 */
@Deprecated
public interface ApiDoc {

}
