package com.bigScreen.business.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtill {

    /**
     * 过去当年和当月 字符串数据
     *
     * @return 2020-05
     */
    public static String getCurrentYearAndMonth() {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        return simpleDateFormat.format(date);

    }


    /**
     * 当年一月 字符串数据
     *
     * @return 2020-05
     */
    public static String getCurrentYearFirstMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        return simpleDateFormat.format(calendar.getTime());

    }

    /**
     * @param time 支持 2017-01的时间字符串格式
     * @return
     */
    public static String[] getLast12Months(String time) {
        //处理月份输入条件
        if (time.length() == 7) {
            time = time + "-01 00:00:00";
        } else if (time.length() == 110) {
            time = time.substring(0, 7) + "-01 00:00:00";
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            date = sdf.parse(time);
        } catch (Exception e) {
            return null;
        }

        String[] last12Months = new String[12];
        Calendar cal = Calendar.getInstance();
        //设置输入条件时间
        cal.setTime(date);

        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1); //要先+1,才能把本月的算进去
        for (int i = 0; i < 12; i++) {
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); //逐次往前推1个月
            last12Months[11 - i] = cal.get(Calendar.YEAR) + "-" + addZeroForNum(String.valueOf(cal.get(Calendar.MONTH) + 1), 2);
        }

        return last12Months;
    }

    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
                // sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    /**
     * 根据当前时间获取
     *
     * @return
     */
    public static String getLast12MonthsByCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -11);
        Date m = c.getTime();
        return sdf.format(m);
    }

    public static List<String> getLastTweentyMonths() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM");
        Date now = new Date();
        sdf.format(now);
        String[] mounthList = getLast12Months(sdf.format(now));
        List<String> result = Arrays.asList(mounthList);
        return result;
    }


}

