package com.util;

public class NumberUtil {
    /**
     * 获取minValue到maxValue间的随机数
     * 
     * @param minValue
     *            int
     * @param maxValue
     *            int
     * @return
     */
    public static String getRandomCnt(int minValue, int maxValue) {
        return "" + (int) (minValue + Math.random() * (maxValue - minValue + 1));
    }

    /**
     * 格式化param，左补0
     * 
     * @param param
     *            源字符串
     * @param totalLength
     *            总长度
     * @return
     */
    public static String addLeftZero(String param, int totalLength) {
        while (param != null && param.length() < totalLength) {
            param = "0" + param;
        }
        return param;
    }

    /**
     * 获取8位随机数
     * 
     * @return
     */
    public static String getEightBitRandom() {
        return addLeftZero(getRandomCnt(1, 100000000), 8);
    }

//    public static void main(String[] args) {
//        String param = "001";
//        int index = Integer.parseInt(param);
//
//        String str = addLeftZero((index + 1) + "", 3);
//        System.out.println(str);
//    }
}