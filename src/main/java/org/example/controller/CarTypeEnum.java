package org.example.controller;

/**
 * @author huangbc
 */
public enum CarTypeEnum {

    CAR_TYPE_BC("BC","奔驰"),
    CAR_TYPE_BMW("BMW","宝马"),
    CAR_TYPE_AUDI("AUDI","奥迪"),
    CAR_TYPE_BYD("BYD","比亚迪");

    private String code;
    private String desc;

    CarTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String  getDesc(String code){
        CarTypeEnum[] carTypeEnums= values();
        for (CarTypeEnum carTypeEnum : carTypeEnums) {
            if (carTypeEnum.getCode().equals(code)){
                return carTypeEnum.getDesc();
            }
        }
        return null;
    }

    public static String  getCode(String desc){
        CarTypeEnum[] carTypeEnums= values();
        for (CarTypeEnum carTypeEnum : carTypeEnums) {
            if (carTypeEnum.getDesc().equals(desc)){
                return carTypeEnum.getCode();
            }
        }
        return null;
    }
}
