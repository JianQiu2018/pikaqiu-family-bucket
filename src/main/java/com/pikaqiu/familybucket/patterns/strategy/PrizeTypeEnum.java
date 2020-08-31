package com.pikaqiu.familybucket.patterns.strategy;


public enum PrizeTypeEnum {
    /**
     * 积分
     */
    POINT(1, "***"),
    CASH(2, "***");

    private Integer key;
    private String value;

    PrizeTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }}


