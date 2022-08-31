package com.scarit.exerciseEnum;

public enum DeleteFlagEnum {

    //YES代表逻辑上已经删除，NO反之
    YSE("1"), NO("0");

    private String value;

    DeleteFlagEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
