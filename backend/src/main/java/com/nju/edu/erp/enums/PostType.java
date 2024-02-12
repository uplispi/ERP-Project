package com.nju.edu.erp.enums;

/**
 * /*@Author: Han
 * /*@Description:岗位薪资类型枚举类
 * /*@Date: Created in 9:36 2022/6/29
 * /*@Version:1.0
 */
public enum PostType  implements BaseEnum<CustomerType, String>{

    MONTHLY_WITH_PUSH("月薪提成制"),
    MONTHLY("月薪固定薪资制"),
    YEARLY_WITH_PUSH("年薪提成制"),
    YEARLY("年薪固定薪资制");

    private final String value;

    PostType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
