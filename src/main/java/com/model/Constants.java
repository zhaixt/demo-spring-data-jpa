package com.model;

/**
 * Created by zhaixiaotong on 2016-9-17.
 */
public class Constants {
    /*
    *Role
    */
    public final static String ROLE_ADMIN = "01";// 管理员权限
    public final static String ROLE_USER = "02";// 注册用户权限，暂时用不上
    public final static String ROLE_TRUCK = "03";// 此角色提供给卡车司机，可以发布车辆信息，找货，找集装箱
    public final static String ROLE_CARGO = "04";// 此角色提供给货物商家，可以发布货源，找车
    public final static String ROLE_CONTAINER = "05";// 目前只用04，这个暂时不用，此角色提供给货物商家，可以发布集装箱，找车
    public final static String ROLE_VISITOR= "06";// 游客权限

}
