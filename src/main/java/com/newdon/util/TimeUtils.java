package com.newdon.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: LeiGang
 * @create: 2019-02-11 19:08
 * @description:
 **/
public class TimeUtils {
    /**
     * 获取指定日期所在月份开始的时间戳
     *
     * @param date 指定日期
     * @return
     */
    public static Long getMonthBegin(Long date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));

        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * 获取指定日期所在月份结束的时间戳
     *
     * @param date 指定日期
     * @return
     */
    public static Long getMonthEnd(Long date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND, 59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        return c.getTimeInMillis();
    }

    public static List<Long> getMonthBetween(Long minDate, Long maxDate){
        ArrayList<Long> result = new ArrayList<Long>();

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(new Date(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(new Date(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(curr.getTimeInMillis());
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }

    public static void main(String[] args){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");//格式化为年月
        List<Long> monthBetween = getMonthBetween(1548402554809L, 1549881242726L);
        System.out.println(sdf.format(1548402554809L));
        System.out.println(sdf.format(1549881242726L));
        System.out.println(monthBetween);
        for (Long date : monthBetween) {
            Long monthBegin = getMonthBegin(date);
            Long monthEnd = getMonthEnd(date);
            System.out.println(monthBegin + "==" + monthEnd);
            sdf.format(monthBegin);
            System.out.println(sdf.format(monthBegin)+"==="+sdf.format(monthEnd));
        }
    }
}
