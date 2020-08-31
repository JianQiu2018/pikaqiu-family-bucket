package com.pikaqiu.familybucket.enums;

public enum NoticeEnum {
    /**
     * 注册
     */
    REGISTER("register","用戶注冊成功~"),
    /**
     * 登录
     */
    LOGIN("login","用戶登录成功~");

    public  String moduleType;
    public  String moduleName;


    public String getModuleType() {
        return moduleType;
    }

    public String getModuleName() {
        return moduleName;
    }

    NoticeEnum(String moduleType, String moduleName) {
        this.moduleType = moduleType;
        this.moduleName = moduleName;
    }
}
