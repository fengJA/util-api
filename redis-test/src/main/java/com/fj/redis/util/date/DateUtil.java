package com.fj.redis.util.date;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author liubin
 * @DESC date工具类
 * @createTime 2020-05-27
 */

public class DateUtil {

    /* ************工具方法***************   */


    /**
     * 获取当前月的第一天
     * @return
     */
    public static Date getMonthFirstDay(){
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return cale.getTime();
    }

    /**
     * 获取本月最后一天
     * @return
     */
    public static Date getMonthLastDay(){
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return cale.getTime();
    }

    /**
     * 格式化Date时间
     * @param time Date类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat){
        DateFormat dateFormat=new SimpleDateFormat(timeFromat);
        return dateFormat.format(time);
    }

    /**
     * 获取某年开始时间
     * @param year
     * @return year-01-01 00:00:00
     */
    public static String getMinTimeByYear(int year) {
        return LocalDateTime.of(year, 1, 1, 0, 0, 0)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 获取某年结束时间
     * @param year
     * @return year-12-31 23:59:59
     */
    public static String getMaxTimeByYear(int year) {
        return LocalDateTime.of(year, 12, 31, 23, 59, 59)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getNowTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 获取当天开始时间
     * @return
     */
    public static String getNowTimeMin() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String getNowTimeStart(String parttern){
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN).format(DateTimeFormatter.ofPattern(parttern));
    }

    /**
     * 获取当天结束时间
     * @return
     */
    public static String getNowTimeMax() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     *
     * 根据年月日获取10位数的时间戳
     * time (2020-10-15)
     * @return
     */
    public static long getTenCurrentTimeMillis(String time) {
        long ret = 0l;
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dt = formatter.parse(time);
            ret = dt.getTime() / 1000;
        }catch (Exception e){}
        return ret;
    }

    /**
     * 时间转换为时间戳
     * @param time
     * @return
     */
    public static Long changTime(String time, String partten){
        long timeStemp = 0;
        try {
            SimpleDateFormat sf = new SimpleDateFormat(partten);
            Date parse = sf.parse(time);
            timeStemp = parse.getTime();
            String timeLength = String.valueOf(timeStemp);
            if (timeLength.length() > 10){
                timeStemp = Long.parseLong(timeLength.substring(0,10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeStemp;
    }

    /**
     * 时间戳转(秒)换为时间
     * @param timeStamp
     * @return
     */
    public static String changTimeDate(String timeStamp, String partern){
        SimpleDateFormat sdf=new SimpleDateFormat(partern);
        return sdf.format(new Date(Long.parseLong(String.valueOf(new BigDecimal(timeStamp).multiply(new BigDecimal(1000))))));      // 时间戳转换成时间
    }

    /**
     * 时间戳转换为时间
     * @param timeStamp
     * @return
     */
    public static String changTimeToDate(String timeStamp, String partern){
        SimpleDateFormat sdf=new SimpleDateFormat(partern);
        return sdf.format(new Date(Long.parseLong(timeStamp)));      // 时间戳转换成时间
    }

    /**
     * timeStamp 转换为：LocalDateTime
     * @param timeStamp
     * @return
     */
    public static LocalDateTime changStrTimeDate(String timeStamp){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(timeStamp,df);
        return ldt;
    }

    /**
     * 获取指定日期的前一天
     * @param dayTime yyyy-MM-dd
     * @return
     */
    public static String getAppointDayBeforeDay(String dayTime) {
        String preDate = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(dayTime);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day1 = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day1 - 1);
        preDate = sdf.format(c.getTime());
        return preDate;
    }

    /**
     *
     * @return
     */
    public static long changeStrToTimeStamp(String strDate,String pattern){
        Date orderDateEnd = null;
        try{
            orderDateEnd = new SimpleDateFormat(pattern).parse(strDate);
        }catch (Exception e){}
        String dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderDateEnd);
        return getTenCurrentTimeMillis(dateEnd);
    }

    /**
     * 查询几天前的日期
     */
    public static String beforeTodayTime(Integer time){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DATE, time);
        return sdf.format(calendar.getTime());
    }

    public static void main(String [] args){
        try {
            System.out.println(  (System.currentTimeMillis()/1000> 1591175689)  +   "= new Date().getTime()" + System.currentTimeMillis()/1000);
            System.out.println("表格第3".length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
