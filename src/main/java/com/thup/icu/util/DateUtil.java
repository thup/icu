package com.thup.icu.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 时间工具类
 */
@Slf4j
public class DateUtil {

    /**
     * 定义一些long类型的时间毫秒值
     */
    public final static long MINUTE = 60 * 1000;
    public final static long HOUR = MINUTE * 60;
    public final static long DAY = HOUR * 24;
    public final static long WEEK = DAY * 7;

    public final static String U_MINUTE = "MINUTE";
    public final static String U_HOUR = "HOUR";
    public final static String U_DAY = "DAY";
    public final static String U_WEEK = "WEEK";
    public final static String U_MONTH = "MONTH";
    public final static String U_YEAR = "YEAR";

    public final static String first_second = " 00:00:00";
    public final static String last_second = " 23:59:59";

    /**
     * 定义一些常用的 format 类型
     */
    public final static String format_default_date = "yyyy-MM-dd";
    public final static String format_default_datetime = "yyyy-MM-dd HH:mm:ss";
    public final static String yyyyMMdd = "yyyyMMdd";
    public final static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public final static String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

    /**
     * @return String
     * @throws Exception
     * @author
     * @author
     * get Date format Example：2008-05-15
     */
    public static String getDateLong(Date date) {
        String nowDate = "";
        try {
            if (date != null)
                nowDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getDateLong 方法异常："+e);
            return "";
        }
    }


    /**
     * @return String
     * @throws Exception
     * @author
     * @author
     * get Date format Example：2008-05-15
     */
    public static String getDateUtcStr() {
        TimeZone tz  = TimeZone.getDefault() ;
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Calendar cal_Three = Calendar.getInstance();
        return cal_Three.getTime().toString();
    }

    /**
     * @return String
     * @throws Exception
     * @author
     * get Date format Example：2008-05-15 18:00
     */
    public static String getDateLongHHmm(Date date) {
        String nowDate = "";
        try {
            if (date != null)
                nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:00").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getDateLongHHmm 方法异常："+e);
            return "";
        }
    }

    /**
     * @return String
     * @throws Exception
     * @author
     * get Date format Example：05.15
     */
    public static String getDateMonthDay(Date date) {
        String nowDate = "";
        try {
            if (date != null)
                nowDate = new SimpleDateFormat("MM.dd").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getDateMonthDay 方法异常："+e);
            return "";
        }
    }

    /**
     *   日是否在指定 区间内
     * @param date
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetweenDays(Date date,int start,int end){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        if(day>=start && day<=end) {
            return true;
        }
        return false;
    }
    /**
     *   小时是否在当天的指定区间内
     * @param date
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetweenHours(Date date,int start,int end){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int day=calendar.get(Calendar.HOUR_OF_DAY);
        if(day>=start && day<=end) {
            return true;
        }
        return false;
    }

    /**
     * @return String
     * @throws Exception
     * @author
     * get Date format Example：2008-05
     */
    public static String getDateYearMonth(Date date) {
        String nowDate = "";
        try {
            if (date != null)
                nowDate = new SimpleDateFormat("yyyy-MM").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getDateYearMonth 方法异常："+e);
            return "";
        }
    }


    /**
     * @return String
     * @throws Exception
     * @author
     * get Date format Example：20080515
     */
    public static String getDateShort(Date date) {
        String nowDate = "";
        try {
            if (date != null)
                nowDate = new SimpleDateFormat("yyyyMMdd").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getDateShort 方法异常："+e);
            return "";
        }
    }

    /**
     * @return String
     * @throws Exception
     * @authorn
     * get Date format Example：0515
     */
    public static String getDateShortMMdd(Date date) {
        String nowDate = "";
        try {
            if (date != null)
                nowDate = new SimpleDateFormat("MMdd").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getDateShortMMdd 方法异常："+e);
            return "";
        }
    }

    /**
     * @return String
     * @throws Exception
     * @author
     * get Date format Example：2008年05月15日
     */
    public static String getDateLongCn(Date date) {
        String nowDate = "";
        try {
            if (date != null)
                nowDate = new SimpleDateFormat("yyyy年MM月dd日").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getDateLongCn 方法异常："+e);
            return "";
        }
    }

    /**
     * @return String
     * @throws Exception
     * @author
     * get Date format Example：2008年-05月-15日 11:05
     */
    public static String getDateShortLongTimeCn(Date date) {
        String nowDate = "";
        try {
            if (date != null)
                nowDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getDateShortLongTimeCn 方法异常："+e);
            return "";
        }
    }

    /**
     * get current date,fomart:YYYYMMDDHHMISS
     *
     * @return String
     * @throws Exception
     */
    public static String getNowLongTime() throws Exception {
        String nowTime = "";
        try {
            java.sql.Date date = null;
            date = new java.sql.Date(DateUtil.getNowDate().getTime());
            nowTime = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
            return nowTime;
        } catch (Exception e) {
            log.error("类DateUtil.getNowLongTime 方法异常："+e);
            throw e;
        }
    }

    /**
     * get current date,fomart:YYYYMMDD
     *
     * @return String
     * @throws Exception
     */
    public static String getNowShortDate() throws Exception {
        String nowDate = "";
        try {
            java.sql.Date date = null;
            date = new java.sql.Date(DateUtil.getNowDate().getTime());
            nowDate = new SimpleDateFormat("yyyyMMdd").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getNowShortDate 方法异常："+e);
            throw e;
        }
    }

    /**
     * get current date,fomart:YYYY-MM-DD
     *
     * @return String
     */
    public static String getNowFormateDate() {
        String nowDate = "";
        try {
            java.sql.Date date = null;
            date = new java.sql.Date(DateUtil.getNowDate().getTime());
            nowDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getNowFormateDate 方法异常："+e);
            return null;
        }
    }

    /**
     * 获取系统开始时间
     *
     * @return String
     */
    public static String getSystemStartTime() {
            return "2019-08-01 00:00:00";
    }

    /**
     * get current date,fomart:YYYY-MM-DD
     *
     * @return String
     */
    public static String getNowFormateYMDhmsDate() {
        String nowDate = "";
        try {
            java.sql.Date date = null;
            date = new java.sql.Date(DateUtil.getNowDate().getTime());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //设置时区
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            nowDate = sdf.format(date);

            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getNowFormateYMDhmsDate 方法异常："+e);
            return null;
        }
    }

    /**
     * 获取当月第一天
     *
     * @return String
     */
    public static String getCurrentMonthFirstDay() {

        String nowDate = "";
        try {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, 0);
            c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
            nowDate = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(c.getTime().getTime());
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getCurrentMonthFirstDay 方法异常："+e);
            return null;
        }
    }
    public static String getFormateYMDhmsDate(Date date) {
        if(date==null){
            return null;
        }
        String nowDate = "";
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //设置时区
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            nowDate = sdf.format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getFormateYMDhmsDate 方法异常："+e);
            return null;
        }
    }


    /**
     *
     * @return 当前时间
     */
    public static Date getNowDate() {

        int offsetDays = 0;
        int offsetMinutes = 0;
        if(offsetDays==0 && offsetMinutes == 0){
            return new Date();
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)+offsetDays);
            calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE)+offsetMinutes);
            return calendar.getTime();
        }
    }

    /**
     * 获取指定
     * @param offsetDays
     * @return
     */
    public static String getSpecialDate2Str(Integer offsetDays){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)+offsetDays);
        calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime());
    }


    /**
     *
     * @return 年月日
     */
    public static Date getNowDay() {
        Calendar calo = Calendar.getInstance();
        calo.setTime(DateUtil.getNowDate());
        calo.set(Calendar.HOUR_OF_DAY,0);
        calo.set(Calendar.MINUTE,0);
        calo.set(Calendar.SECOND,0);
        calo.set(Calendar.MILLISECOND,0);
        return calo.getTime();
    }

    /**
     *
     * @return 年月日
     */
    public static Date getMonthStartDate(Date date) {
        Calendar calo = Calendar.getInstance();
        calo.setTime(date);
        calo.set(Calendar.DAY_OF_MONTH, 1);
        calo.set(Calendar.HOUR_OF_DAY,0);
        calo.set(Calendar.MINUTE,0);
        calo.set(Calendar.SECOND,0);
        calo.set(Calendar.MILLISECOND,0);
        return calo.getTime();
    }

    /**
     *
     * @return 年月日
     */
    public static Date getDate(Date date,int months) {
        Calendar calo = Calendar.getInstance();
        calo.setTime(date);
        calo.set(Calendar.DAY_OF_MONTH, months);
        calo.set(Calendar.HOUR_OF_DAY,0);
        calo.set(Calendar.MINUTE,0);
        calo.set(Calendar.SECOND,0);
        calo.set(Calendar.MILLISECOND,0);
        return calo.getTime();
    }


    private static final SimpleDateFormat formatterUTC = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+08:00");
    public static synchronized String getUTCString(Date date) {
        String formatDate = formatterUTC.format(date);
        return formatDate;
    }

    public static String getShortCurrentTimeStamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd日HH:mm");
        String formatDate = dateFormat.format(DateUtil.getNowDate());
        return formatDate;
    }

    /**
     * get current date,fomart:yyyy-MM-dd HH:mm:ss
     *
     * @return String
     * @throws Exception
     */
    public static String getNowPlusTime(){
        String nowDate = "";
        try {
            java.sql.Date date = null;
            date = new java.sql.Date(DateUtil.getNowDate().getTime());
            nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getNowPlusTime 方法异常："+e);
            throw e;
        }
    }

    public static String getNowPlusMSTime(){

        String nowDate = "";
        try {
            java.sql.Date date = null;
            date = new java.sql.Date(DateUtil.getNowDate().getTime());
            nowDate = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getNowPlusMSTime 方法异常："+e);
            throw e;
        }

    }

    /**
     * get specified date,fomart:yyyy-MM-dd HH:mm:ss
     *
     * @return String
     * @throws Exception
     */
    public static String getPlusTime(Date date) {
        if (date == null) return null;
        try {
            String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getPlusTime 方法异常："+e);
            return "";
        }
    }

    /**
     * get specified date,fomart:yyyy-MM-dd HH:mm:ss
     *
     * @return String
     * @throws Exception
     */
    public static String getPlusTimeEmpty(Date date) {
        if (date == null) return "";
        try {
            String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getPlusTimeEmpty 方法异常："+e);
            return "";
        }
    }

    /**
     * difference between months
     *
     * @param dealMonth
     * @param alterMonth
     * @return alterMonth
     */
    public static int calBetweenTwoMonth(String dealMonth, String alterMonth) {
        int length = 0;
        if ((dealMonth.length() != 6) || (alterMonth.length() != 6)) {
            length = -1;
        } else {
            int dealInt = Integer.parseInt(dealMonth);
            int alterInt = Integer.parseInt(alterMonth);
            if (dealInt < alterInt) {
                length = -2;
            } else {
                int dealYearInt = Integer.parseInt(dealMonth.substring(0, 4));
                int dealMonthInt = Integer.parseInt(dealMonth.substring(4, 6));
                int alterYearInt = Integer.parseInt(alterMonth.substring(0, 4));
                int alterMonthInt = Integer.parseInt(alterMonth.substring(4, 6));
                length = (dealYearInt - alterYearInt) * 12 + (dealMonthInt - alterMonthInt);
            }
        }
        return length;
    }


    /**
     * 2个日期的月份差
     * @param newDate
     * @param oldDate
     * @return
     */
    public static int monthBetweenDates(Date newDate,Date oldDate){

        Calendar c = Calendar.getInstance();
        c.setTime(newDate);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);

        c.setTime(oldDate);
        int yearOld = c.get(Calendar.YEAR);
        int monthOld = c.get(Calendar.MONTH);

        int result;
        if(year==yearOld)
        {

            result=month-monthOld;//两个日期相差几个月，即月份差
        }
        else
        {
            result=12*(year-yearOld)+month-monthOld;//两个日期相差几个月，即月份差
        }

        return result;

    }
    public static int hoursBetweenDates(Date newDate,Date oldDate){

        int days = 0;
        Calendar calo = Calendar.getInstance();
        Calendar caln = Calendar.getInstance();
        calo.setTime(oldDate);
        caln.setTime(newDate);
        int oday = calo.get(Calendar.DAY_OF_YEAR);
        int nyear = caln.get(Calendar.YEAR);
        int oyear = calo.get(Calendar.YEAR);
        while (nyear > oyear) {
            calo.set(Calendar.MONTH, 11);
            calo.set(Calendar.DATE, 31);
            days = days + calo.get(Calendar.DAY_OF_YEAR);
            oyear = oyear + 1;
            calo.set(Calendar.YEAR, oyear);
        }
        int nday = caln.get(Calendar.DAY_OF_YEAR);
        days = days + nday - oday;

        int hours = caln.get(Calendar.HOUR_OF_DAY) -calo.get(Calendar.HOUR_OF_DAY);

        int gap = days * 24 + hours;
        return gap;

    }

    /**
     * difference between days
     *
     * @param newDate
     * @param oldDate
     * @return newDate-oldDate
     */
    public static int daysBetweenDates(Date newDate, Date oldDate) {
        int days = 0;
        Calendar calo = Calendar.getInstance();
        Calendar caln = Calendar.getInstance();
        calo.setTime(oldDate);
        caln.setTime(newDate);
        int oday = calo.get(Calendar.DAY_OF_YEAR);
        int nyear = caln.get(Calendar.YEAR);
        int oyear = calo.get(Calendar.YEAR);
        while (nyear > oyear) {
            calo.set(Calendar.MONTH, 11);
            calo.set(Calendar.DATE, 31);
            days = days + calo.get(Calendar.DAY_OF_YEAR);
            oyear = oyear + 1;
            calo.set(Calendar.YEAR, oyear);
        }
        int nday = caln.get(Calendar.DAY_OF_YEAR);
        days = days + nday - oday;

        return days;
    }

    /**
     * difference between days
     *
     * @param newDate
     * @param oldDate
     * @return newDate-oldDate
     */
    public static int weeksBetweenDates(Date newDate, Date oldDate) {
        return daysBetweenDates(newDate, oldDate) / 7;
    }

    public static int getLastDayOfMonth(Date date){
        Calendar cDay1 = Calendar.getInstance();
        cDay1.setTime(date);
        int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
        return lastDay;
    }

    public static Date getFirstDayOfMonth(Date date){
        Date newDate = setDay(date, 1);
        newDate = getStartDate(newDate);
        return newDate;
    }


    /**
     *  校验表达式 通过 true 未通过 false
     * @param startDate 校验点(期望值)
     * @param endDate 被校验点(实际值)
     * @return 通过 true 未通过 false
     */
    public static boolean checkDateBetween(Date startDate, Date endDate, Date actualDate){
        int startDiff = DateUtil.daysBetweenDates(actualDate, startDate);
        int endDiff = DateUtil.daysBetweenDates(actualDate, endDate);

        //startDiff>0且endDiff<0，当前时间才正好在开始时间和结束时间内
        if(startDiff>0&&endDiff<0){
            return true;
        }
        //其他情况均为未通过
        return false;
    }


    public static int getDayOfMonth(Date date){
        Calendar cDay1 = Calendar.getInstance();
        cDay1.setTime(date);
        int day = cDay1.get(Calendar.DAY_OF_MONTH);
        return day;
    }


    public static int getWeekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, getYear(date));
        c.set(Calendar.WEEK_OF_YEAR, getWeekOfYear(date));
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//设置周一
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    //秒
    public static long secondsBetweenDates(Date newDate, Date oldDate) {
        return (newDate.getTime() - oldDate.getTime())/1000;
    }

    //毫秒
    public static long secondsBetweenDates2(Date newDate, Date oldDate) {
        return (newDate.getTime() - oldDate.getTime());
    }

    public static String getFormattedDateUtil(Date dtDate, String strFormatTo) {
        if (dtDate == null) {
            return "";
        }
        strFormatTo = strFormatTo.replace('/', '-');
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(strFormatTo);
            return formatter.format(dtDate);
        } catch (Exception e) {
            log.error("类DateUtil.getFormattedDateUtil 方法异常："+e);
            return "";
        }
    }

    /**
     * 将一个字符串转化成日期对象，如果转化失败，返回null
     *
     * @param sourceString
     * @param pattern
     * @return
     */
    public static Date parseDateFromString(String sourceString, String pattern) {
        Date re = null;

        if (sourceString != null && !sourceString.equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            try {
                re = dateFormat.parse(sourceString);
            } catch (ParseException e) {
                log.error("类DateUtil.parseDateFromString 方法异常："+e);
                return null;
            }
        }

        return re;
    }

    /**
     * 将某指定的字符串转换为型如：yyyy-MM-dd HH:mm:ss的时间
     *
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2LineDate(String str) {
        return parseDateFromString(str, "yyyy-MM-dd_HH:mm");
    }

    /**
     * 将某指定的字符串转换为型如：yyyy-MM-dd HH:mm:ss的时间
     *
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2LDate(String str) {
        return parseDateFromString(str, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将某指定的字符串转换为型如：yyyyMMddHHmmss的时间
     * @param str
     * @return
     */
    public static Date getStr2LDate2(String str) {
        return parseDateFromString(str, "yyyyMMddHHmmss");
    }

    /**
     * 将某指定的字符串转换为型如：yyyymmdd 的时间
     *
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2ShortDate(String str) {
        return parseDateFromString(str, "yyyyMMdd");
    }

    /**
     * 将某指定的字符串转换为型如：yyyy-MM-dd HH:mm的时间
     *
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2LDateWithoutSecond(String str) {
        return parseDateFromString(str, "yyyy-MM-dd HH:mm");
    }

    /**
     * 将某指定的字符串转换为型如：yyyy-MM-dd
     *
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2SDate(String str) {
        return parseDateFromString(str, "yyyy-MM-dd");
    }

    /**
     * 将某指定的字符串转换为型如：yyyy-M
     *
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2YearMonth(String str) {
        return parseDateFromString(str, "yyyy-M");
    }

    /**
     * 得到平台开始时间
     */
    public static Date getPlatformStartDate() {
        return parseDateFromString("2013-01-01", "yyyy-MM-dd");
    }

    /**
     * 得到平台最大时间
     */
    public static Date getPlatformMaxDate() {
        return parseDateFromString("2200-01-01", "yyyy-MM-dd");
    }

    /**
     * 得到平台上线时间
     */
    public static Date getPlatformOnboardDate() {
        return parseDateFromString("2013-08-07", "yyyy-MM-dd");
    }

    /**
     * 返回日期加X天后的日期
     *
     * @param date
     * @param i
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static String addDays(String date, int i) {
        try {
            GregorianCalendar gCal = new GregorianCalendar(
                    Integer.parseInt(date.substring(0, 4)),
                    Integer.parseInt(date.substring(5, 7)) - 1,
                    Integer.parseInt(date.substring(8, 10)));
            gCal.add(GregorianCalendar.DATE, i);
            return new SimpleDateFormat("yyyy-MM-dd").format(gCal.getTime());
        } catch (Exception e) {
            log.error("类DateUtil.addDays 方法异常："+e);
            return "";
        }
    }

    /**
     * 返回日期加X月后的日期
     *
     * @param date
     * @param i
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static String addMonths(String date, int i) {
        try {
            GregorianCalendar gCal = new GregorianCalendar(
                    Integer.parseInt(date.substring(0, 4)),
                    Integer.parseInt(date.substring(5, 7)) - 1,
                    Integer.parseInt(date.substring(8, 10)));
            gCal.add(GregorianCalendar.MONTH, i);
            return new SimpleDateFormat("yyyy-MM-dd").format(gCal.getTime());
        } catch (Exception e) {
            log.error("类DateUtil.addMonths 方法异常："+e);
            return "";
        }
    }

    /**
     * 返回日期加X年后的日期
     *
     * @param date
     * @param i
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static String addYears(String date, int i) {
        try {
            GregorianCalendar gCal = new GregorianCalendar(
                    Integer.parseInt(date.substring(0, 4)),
                    Integer.parseInt(date.substring(5, 7)) - 1,
                    Integer.parseInt(date.substring(8, 10)));
            gCal.add(GregorianCalendar.YEAR, i);
            return new SimpleDateFormat("yyyy-MM-dd").format(gCal.getTime());
        } catch (Exception e) {
            log.error("类DateUtil.addYears 方法异常："+e);
            return "";
        }
    }

    /**
     * 获取基准日的前后偏移日
     *
     * @param count 偏移数量
     * @param date  基准日期
     * @return Date
     */
    public static Date addDays(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, count);
        return calendar.getTime();
    }

    /**
     * 获取基准日的前后偏移日
     *
     * @param count 偏移数量
     * @param date  基准日期
     * @return Date
     */
    public static Date addHours(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, count);
        return calendar.getTime();
    }

    /**
     * 获取基准日的前后偏移毫秒
     *
     * @param count 偏移数量
     * @param date  基准日期
     * @return Date
     */
    public static Date addMilliseconds(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MILLISECOND, count);
        return calendar.getTime();
    }



    /**
     * 获取基准日的前后偏移秒
     *
     * @param count 偏移数量
     * @param date  基准日期
     * @return Date
     */
    public static Date addSeconds(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, count);
        return calendar.getTime();
    }

    public static Date addMinutes(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, count);
        return calendar.getTime();
    }

    /**
     * 获取基准日的前后偏移周
     *
     * @param count 偏移数量
     * @param date  基准周数
     * @return Date
     */
    public static Date addWeeks(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, count);
        return calendar.getTime();
    }

    /**
     * 友好的方式显示时间
     */
    public static String getFriendlyFormat(Date date){
        if (date == null) {
            return "";
        }

        Date now = DateUtil.getNowDate();
        String time = DateUtil.getFormattedDateUtil(date, "HH:mm");

        int days = DateUtil.daysBetweenDates(now, date);

        if (days < 0) {
            return DateUtil.getFormattedDateUtil(date, "yyyy-MM-dd HH:mm");
        } else if (days == 0) {
            int hour = (int) ((now.getTime() - date.getTime()) / 3600000);
            if (hour > 0) {
                return hour + "小时前";
            }
            int minute = (int) ((now.getTime() - date.getTime()) / 60000);
            if (minute < 1) {
                minute = 1;
            }
            return minute + "分钟前";
        } else if (days == 1) {
            return "昨天" + time;
        } else if (days == 2) {
            return "前天" + time;
        } else {
            return days + "天前";
        }
    }

    /**
     * 获取基准日的前后偏移月
     *
     * @param count 偏移数量
     * @param date  基准月数
     * @return Date
     */

    public static Date addYears(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, count);
        return calendar.getTime();
    }

    public static Date addMonths(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, count);
        return calendar.getTime();
    }

    public static Date getOneDayOfNextMonth(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE,day);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static int plusYears(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, i);
        return calendar.get(Calendar.YEAR);
    }

    public static int plusMonths(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, count);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    public static Integer getWeekOfYear(String today){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(today);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        if(date !=null){
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.setTime(date);
            return calendar.get(Calendar.WEEK_OF_YEAR);
        }
        return -1;
    }

    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    //in calendar.get(Calendar.MONTH): return month is base from 0, so natural_month = month_base_zero + 1
    public static int getNaturalMonthNumber(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month_base_zero = calendar.get(Calendar.MONTH);
        return month_base_zero + 1;
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    public static Date getNowDateAddMinutes(int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    public static String getHHMMDateFormat(Date date) {
        return new SimpleDateFormat("HH:mm").format(date);
    }

    public static String getHHMMSSDateFormat(Date date) {
        return new SimpleDateFormat("HH:mm:ss").format(date);
    }


    public static boolean DateEquals(Date left, Date right) {
        Calendar calendarLeft = Calendar.getInstance();
        calendarLeft.setTime(left);

        Calendar calendarRight = Calendar.getInstance();
        calendarRight.setTime(right);
        if (calendarLeft.get(Calendar.YEAR) != calendarRight.get(Calendar.YEAR)) {
            return false;
        }
        if (calendarLeft.get(Calendar.MONTH) != calendarRight.get(Calendar.MONTH)) {
            return false;
        }
        if (calendarLeft.get(Calendar.DAY_OF_MONTH) != calendarRight.get(Calendar.DAY_OF_MONTH)) {
            return false;
        }
        return true;
    }

    public static int DateCompare(Date left, Date right) {
        Calendar calendarLeft = Calendar.getInstance();
        calendarLeft.setTime(left);

        Calendar calendarRight = Calendar.getInstance();
        calendarRight.setTime(right);

        return calendarLeft.compareTo(calendarRight);
    }

    public static boolean twoDateEquals(Date left, Date right) {
        if(left == null && right == null)
        {
            return true;
        }
        if(left == null || right == null)
        {
            return false;
        }
        return left.equals(right);
    }


    public static int getDaysBetweenDates(Date left,Date right) {
        return getDay(left) - getDay(right);
    }


    public static boolean isFullMonth(Date start,Date end){
        int days = DateUtil.daysBetweenDates(end, start);
        //首次还款日往前推一个月
        int monthDays = DateUtil.daysBetweenDates(end, DateUtil.addMonths(end, -1));

        Date oneMonthAgo = DateUtil.addMonths(end, -1);

        //如果首次还款日为月末并且当期天数大于自然月天数且小于等于还款日往前推一月总天数，则为一个完整还款月
        //如1.29,1.30,1.31号借款，2.28号还款都算一整个还款月
        if (DateUtil.getDayOfMonth(end) == DateUtil.getLastDayOfMonth(end)
                && days > DateUtil.getLastDayOfMonth(end) && days <= monthDays) {
            return true;
        }
        //如果首次还款日往前推一个月的日期与投标截止日期相等，则为一个完整还款月
        //如2.15号借款，3.15号还款，不满30天，3.15号借款，4.15号还款，共31天。
        else if (DateUtil.DateEquals(oneMonthAgo, start)) {
            return true;
        } else {
            return  false;
        }
    }

    public static Date getNowWithYMD() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.getNowDate());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    //in calendar.set(xxx): month is base from 0, so must use :natural_month - 1
    public static Date getDateWithYearMonthDate(int year, int natural_month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, natural_month - 1, day , 0 , 0 , 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getDate(int year, int natural_month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, natural_month - 1, day, hour, minute, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    public static Date setDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public static Date getStartDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }


    public static  String dateDiffDate(Date startTime, Date endTime) {
        //按照传入的格式生成一个simpledateformate对象

        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数
        long diff;
        String difftime = "";

        //获得两个时间的毫秒时间差异
        diff = startTime.getTime() - endTime.getTime();
        long day = diff / nd;//计算差多少天
        long hour = diff % nd / nh;//计算差多少小时
        long min = diff % nd % nh / nm;//计算差多少分钟
        long sec = diff % nd % nh % nm / ns;//计算差多少秒
        //输出结果
        difftime = day + "天" + hour + "小时" + min + "分钟" + sec + "秒";


        return difftime;
    }

    public static  String dateDiffDateMin(Date startTime, Date endTime) {
        //按照传入的格式生成一个simpledateformate对象

        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数

        //获得两个时间的毫秒时间差异
        long diff = startTime.getTime() - endTime.getTime();
        return dateDiffDateSec(diff);
    }


    public static  String dateDiffDateSec(long diff) {
        //按照传入的格式生成一个simpledateformate对象

        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数
        String difftime = "";

        long day = diff / nd;//计算差多少天
        long hour = diff % nd / nh;//计算差多少小时
        long min = diff % nd % nh / nm;//计算差多少分钟
        long sec = diff % nd % nh % nm / ns;//计算差多少秒

        //输出结果
        if(day >0) {
            difftime += day + "天";
        }
        if(hour >0) {
            difftime += hour + "小时";
        }
        if(min >0) {
            difftime += min + "分";
        }
        if(sec >0) {
            difftime += sec + "秒";
        }

        return difftime;
    }

    /**
     * 两个日期的天数差
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByDate(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

    /**
     * format the data as 'yyyy年MM月dd日'
     * @param dt
     * @return
     */
    public static String toChineseDateString(Date dt) {
        if(dt == null) return "";
        return new SimpleDateFormat("yyyy年MM月dd日").format(dt);
    }

    /**
     * 是否合法日期
     * @param sDate
     * @return
     */
    public static boolean isValidDate(String sDate) {
        try {
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


    /**
     * judge the date whether between start and end with YMD
     * @param judgeTime
     * @param start
     * @param end
     * @return
     */
    public static int isBetweenDuration(Date judgeTime,
                                        Date start, Date end){
        Date startTime =  getDateWithYearMonthDate(getYear(start),  getMonth(start), getDay(start));
        Date endTime = getDateWithYearMonthDate(getYear(end),  getMonth(end), getDay(end));
        Date formatJudgeTime = getDateWithYearMonthDate(getYear(judgeTime),  getMonth(judgeTime), getDay(judgeTime));
        if(formatJudgeTime.compareTo(startTime) >= 0 && formatJudgeTime.compareTo(endTime) <= 0) {
            return 0;
        }
        if(formatJudgeTime.compareTo(startTime) < 0){
            return -1;
        }
        if(formatJudgeTime.compareTo(endTime) > 0){
            return 1;
        }
        return 0;
    }

    /**
     * judge the date whether between start and end with YMD
     * @param judgeTime
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isBetweenTimeDuration(Date judgeTime,
                                                Date startTime, Date endTime){
        if(judgeTime.compareTo(startTime) >= 0 && judgeTime.compareTo(endTime) <= 0) {
            return true;
        }
        return false;
    }


    public static Date getYMDDate(Date date){
        return getDateWithYearMonthDate(getYear(date), getMonth(date) + 1, getDay(date));
    }

    public static int getOnboardDays() {
        return daysBetweenDates(getNowDate(), getPlatformOnboardDate());
    }


    /**
     * 下一个付息日
     * @param from
     * @param dayEveryMonth
     * @return
     */
    public static Date NextInterestDay(Date from, int dayEveryMonth) {

        return NextXMonthInterestDay(from, dayEveryMonth, 1);
    }

    public static Date NextXMonthInterestDay(Date from,int dayEveryMonth,int month){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        calendar.set(Calendar.DAY_OF_MONTH,1);

        Date nextMonth = DateUtil.addMonths(calendar.getTime(), month);
        Calendar cal = Calendar.getInstance();
        cal.setTime(nextMonth);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //如果每月还款日大于这个月的最后一天，则取这个月的最后一天。
        if (dayEveryMonth >= lastDay) {
            cal.set(Calendar.DAY_OF_MONTH, lastDay);
        } else {
            cal.set(Calendar.DAY_OF_MONTH, dayEveryMonth);
        }
        return cal.getTime();
    }

    public static Date getUTCDate(String str){
        return parseDateFromString(str, "yyyy-MM-dd'T'HH:mm:ss.SSS+08:00");
    }


    /**
     * 计算两个日期间隔天数
     * @param beforeDate
     * @param afterDate
     * @return
     */
    public static int getIntervalDays(Date beforeDate, Date afterDate) {
        if (null == beforeDate || null == afterDate) {
//            throw new Exception("参数错误");
        }
        long intervalMilli = afterDate.getTime() - beforeDate.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }

    public static String convertPrettyTime(String time){
        if(StringUtils.isEmpty(time)){
            return "";
        }
        Long t = Long.parseLong(time);
        Date d = new Date(t);
        Double minutes = secondsBetweenDates(DateUtil.getNowDate(), d) / 60.0;
        String offset = String.format("时间：%s  (%.2f 分钟前 = %.2f 小时前 = %.2f 天前)",
                DateUtil.getPlusTime(d),
                minutes.doubleValue(),
                minutes.doubleValue() / 60.0,
                minutes.doubleValue() / 60.0 / 24.0);
        return offset;
    }

    public static String getNowFormateYMDhmsDateString() {
        String nowDate = "";
        try {
            java.sql.Date date = null;
            date = new java.sql.Date(DateUtil.getNowDate().getTime());
            nowDate = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getNowFormateYMDhmsDateString 方法异常："+e);
            return null;
        }
    }

    /**
     * 将某指定的字符串转换为型如：yyyy-MM-dd HH:mm:ss的时间
     *
     * @param str 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2LSDate(String str) {
        return parseDateFromString(str, "yyyyMMddHHmmss");
    }

    public static String getNowFormateShortYMDhmsDateString() {
        String nowDate = "";
        try {
            java.sql.Date date = null;
            date = new java.sql.Date(DateUtil.getNowDate().getTime());
            nowDate = new SimpleDateFormat("yyMMddHHmmss").format(date);
            return nowDate;
        } catch (Exception e) {
            log.error("类DateUtil.getNowFormateShortYMDhmsDateString 方法异常："+e);
            return null;
        }
    }


    /**
     * get current date,fomart:YYYYMMDDHHMISS
     *
     * @return String
     * @throws Exception
     */
    public static String getDateLongTime(Date dateTime){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(dateTime);
    }

    /**
     * 获取当前时间的GMT时间格式
     * @return
     */
    public static String getGMTTimeByNow(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        calendar.set(Calendar.HOUR,calendar.get(Calendar.HOUR)-8);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss'Z'");
        return sdf.format(date);
    }

    /**
     * 获取指定时间的GMT时间格式
     * @param tim
     * @return
     */
    public static String getGMTTimeBySetTime(Long tim){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(tim*1000);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        calendar.set(Calendar.HOUR,calendar.get(Calendar.HOUR)-8);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss'Z'");
        return sdf.format(date);
    }

    /**
     * 获取指定时间的GMT时间格式
     * @param tim
     * @return
     */
    public static String getTimeBySetTime(Long tim){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(tim*1000);
        calendar.set(Calendar.HOUR,calendar.get(Calendar.HOUR));
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static Long getCurrentTimeStemp(){
        return  System.currentTimeMillis()/1000;
    }

    /*
     * 将时间戳转换为时间  10位的秒级别的时间戳
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static Long getTodayStartTimeStemp(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        Long now = getCurrentTimeStemp();
        Long today = now - calendar.get(Calendar.HOUR_OF_DAY)*60*60 - calendar.get(Calendar.MINUTE)*60 - calendar.get(Calendar.SECOND);
        return today;
    }


    /**
     * 当前日期的前一天开始时间
     * @return
     */
    public static Date beforeDayStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)-5);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 当前日期的往前推几个小时
     * @return
     */
    public static Date beforeHourStart(int hourOfDays) {
        return addHours(getNowHourTime(),hourOfDays);
    }
    /**
     * 当前时间
     * @return
     */
    public static Date getNowHourTime() {
        String dateTime = DateUtil.getFormateYMDhmsDate(new Date());
        return getStr2LDate(dateTime);
    }

    /**
     * 当前时间
     * @return
     */
    public static String getNowHourTimeStr() {
        String dateTime = DateUtil.getDatetime();
        return dateTime;
    }

    /**
     *
     * @param startTime
     * @param endTime
     * @return  -1 不查今天，只查历史， 1 查今天和历史，0 只查今天，不查历史
     */
    public static int dateBeforeToday(String startTime, String endTime){
        /*startTime = "2018-12-26 00:00:00";
        endTime = "2018-12-27 23:59:59";*/

        Date beforeToDayStart = DateUtil.beforeToDayStart();
        String todayStart = DateUtil.getFormateYMDhmsDate(beforeToDayStart);

        Long startTimeLong = Long.parseLong(startTime.replace(" ","")
                .replace("-","")
                .replace(":",""));
        Long endTimeLong = Long.parseLong(endTime.replace(" ","")
                .replace("-","")
                .replace(":",""));
        Long todayStartLong = Long.parseLong(todayStart.replace(" ","")
                .replace("-","")
                .replace(":",""));

        if(endTimeLong < todayStartLong){
            return -1;
        }
        if(startTimeLong < todayStartLong){
            return 1;
        }
        return 0;
    }

    /**
     * 今天的开始时间
     * @return
     */
    public static Date beforeToDayStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 当前日期的前一天结束时间
     * @return
     */
    public static Date beforeDayEnd(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)-1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 20小时以前
     * @return
     */
    public static Date get20HoursBefore(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis() - 20 * 60 * 60 * 1000);
        return calendar.getTime();
    }

    public static String getNowDateYMD(){
        Date date = new Date();
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

      /**
      * 日期格式字符串转换成时间戳
      * @param date_str 字符串日期
      * @param format 如：yyyy-MM-dd HH:mm:ss
      * @return
      */
    public static Long date2TimeStamp(String date_str,String format){
         try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                 return sdf.parse(date_str).getTime()/1000;
            } catch (Exception e) {
             log.error("类DateUtil.date2TimeStamp 方法异常："+e);
         }
         return 0L;
     }

    //字符串转时间
    public static Date getDateYMH(String str){
       return getDateYMH(str,"");
    }
    public static Date getDateYMH(String str,String tips){
        if(StringUtils.isEmpty(str)){
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(str);
        }catch (Exception e){
            log.error(tips+"==str +"+str+"+=类DateUtil.getDateYMH 方法异常===："+e);
            return null;
        }
    }
    //获取当前年份
    public static String getSysYear() {

        Calendar date = Calendar.getInstance();

        String year = String.valueOf(date.get(Calendar.YEAR));

        return year;

    }

    public static String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); //设置时区
        return sdf.format(new Date());
    }

    public static String getDatetime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); //设置时区
        return sdf.format(new Date());
    }
    public static String getDatetime2(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); //设置时区
        return sdf.format(new Date());
    }

    public static String getDate(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    public static String getDate(long millisecond){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(millisecond));
    }

    public static String getDatetime(long millisecond){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(millisecond));
    }

    public static String getDate(long millisecond, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(millisecond));
    }

    public static String getDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String getDatetime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String getSqlDatetime(Date date){
        return ""+getDatetime(date)+"";
    }

    public static String getCurYearSqlDatetime(){
        return ""+getSysYear() + "-01-01 00:00:00"+"";
    }

    public static String getCurMonthSqlDatetime() throws Exception {
        return ""+getCurrentMonthFirstDay()+"";
    }

    public static String getCurDaySqlDatetime() throws Exception {
        return ""+getDatetime(DateUtil.beforeToDayStart())+"";
    }

    public static String getDate(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date getDateByStr(String datestr) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(datestr);
    }

    public static Date getDateByStr(String datestr, String format) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(datestr);
    }

    /**
     * @author 张志刚  2017年8月24日 上午19:40:45
     * @param  date		开始日期
     * @param  unit		计算单位
     * @param  amount	单位数量
     * @param  format	返回的日期格式
     * @return 返回指定格式的日期字符串
     * @功能 :
     *  根据传入的时间，单位，数量，格式——计算得到返回的时间字符串
     *  例如：
     *    如果传入的时间是day，而 amount 为 1， 则返回传入的时间+1天后的时间，如果amount为-1，则返回传入的时间-1天后的时间
     *    如果传入的时间为其他单位，则同理
     *
     */
    public static String getNext(Date date, String unit, int amount, String format) {
        if(U_MINUTE.equalsIgnoreCase(unit)) {
            date = DateUtils.addMinutes(date, amount);
        } else if(U_HOUR.equalsIgnoreCase(unit)) {
            date = DateUtils.addHours(date, amount);
        } else if(U_DAY.equalsIgnoreCase(unit)) {
            date = DateUtils.addDays(date, amount);
        } else if(U_WEEK.equalsIgnoreCase(unit)){
            date = DateUtils.addDays(date, amount * 7);
        } else if(U_MONTH.equalsIgnoreCase(unit)) {
            date = DateUtils.addMonths(date, amount);
        } else if(U_YEAR.equalsIgnoreCase(unit)) {
            date = DateUtils.addYears(date, amount);
        }
        return getDate(date, format);
    }

    public static String getNext(String unit, int amount, String format) {
        return getNext(new Date(), unit, amount, format);
    }

    public static String getNextDate(Date date, String unit, int amount) {
        return getNext(date, unit, amount, format_default_date);
    }

    public static String getNextDate(String unit, int amount) {
        return getNext(new Date(), unit, amount, format_default_date);
    }

    public static String getNextDatetime(Date date, String unit, int amount) {
        return getNext(date, unit, amount, format_default_datetime);
    }

    public static String getNextDatetime(String unit, int amount) {
        return getNext(new Date(), unit, amount, format_default_datetime);
    }

    /**
     * @author 张志刚  2017年8月25日 上午10:39:13
     * @param  date 	保单的开始日期
     * @param  unit 	时间单位
     * @param  amount	时间数量
     * @return	返回日期格式
     * @throws Exception
     * @功能 : 专项测试专用方法, 根据开始时间获取结束时间
     *  1. 将当前时间减去一天
     *  2. 将得到的时间字符串转换成日期
     *  3. 将日期按照指定的单位和数量计算，然后获取当前天的最后一秒
     */
    public static String getEndDate(Date date, String unit, int amount) throws Exception {
        String datestr = getNextDatetime(date, U_DAY, -1);
        Date prexdate = getDateByStr(datestr);
        return getNextDate(prexdate, unit, amount);
    }

    public static String getEndDate(String datestr, String unit, int amount) throws Exception {
        Date date = getDateByStr(datestr);
        return getEndDate(date, unit, amount);
    }
    public static String getEndDate(Date date, String unit, int amount, int dateAmount) throws Exception {
        String datestr = getNextDatetime(date, U_DAY, dateAmount);
        Date prexdate = getDateByStr(datestr);
        return getNextDate(prexdate, unit, amount);
    }

    /**
     * @author 张志刚  2017年8月25日 上午10:44:21
     * @return 返回固定格式的字符串
     */
    public static String getEndDatetime(Date date, String unit, int amount) throws Exception {
        return getEndDate(date, unit, amount) + " 23:59:59";
    }

    public static String getEndDatetime(String datestr, String unit, int amount) throws Exception {
        Date date = getDateByStr(datestr);
        return getEndDate(date, unit, amount) + " 23:59:59";
    }
    public static String getEndDatetime(Date date, String unit, int amount, int dateAmount) throws Exception {
        return getEndDate(date, unit, amount,dateAmount) + " 23:59:59";
    }

    /**
     * yyyyMMddHHmmss--> yyyy-MM-dd HH:mm:ss
     */
    public static String date14to19(String pDate) {
        if (null==pDate || "".equals(pDate)) {
            return pDate;
        }

        char[] mChars = pDate.toCharArray();

        return new StringBuilder()
                .append(mChars, 0, 4).append('-')
                .append(mChars, 4, 2).append('-')
                .append(mChars, 6, 2).append(' ')
                .append(mChars, 8, 2).append(':')
                .append(mChars, 10, 2).append(':')
                .append(mChars, 12, 2).toString();
    }

    /**0123456789012345678
     * yyyy-MM-dd HH:mm:ss-->yyyyMMddHHmmss
     */
    public static String date19to14(String pDate) {
        if (null==pDate || "".equals(pDate)) {
            return pDate;
        }

        return pDate.substring(0, 4) + pDate.substring(5, 7) + pDate.substring(8,10) + pDate.substring(11,13) + pDate.substring(14,16) + pDate.substring(17);
    }

    /**
     * yyyy-MM-dd(yyyy/MM/dd, yyyy.MM.dd) --> yyyyMMdd
     */
    public static String date10to8(String pDate) {
        if (null==pDate || "".equals(pDate)) {
            return pDate;
        }

        return pDate.substring(0, 4) + pDate.substring(5, 7) + pDate.substring(8);
    }

    /**
     * yyyyMMdd --> yyyy-MM-dd
     */
    public static String date8to10(String pDate) {
        if (null==pDate || "".equals(pDate)) {
            return pDate;
        }

        char[] mChars = pDate.toCharArray();

        return new StringBuilder()
                .append(mChars, 0, 4).append('-')
                .append(mChars, 4, 2).append('-')
                .append(mChars, 6, 2).toString();
    }

    /**
     * HH:mm:ss --> HHmmss
     */
    public static String time8to6(String pTime) {
        if (null==pTime || "".equals(pTime)) {
            return pTime;
        }
        for (int i = pTime.length(); i < 8; i++) {	//9:34:23 --> 093423
            pTime = '0' + pTime;
        }

        return pTime.substring(0, 2) + pTime.substring(3, 5) + pTime.substring(6);
    }

    /**
     * HHmmss --> HH:mm:ss
     */
    public static String time6to8(String pTime) {
        if (null==pTime || "".equals(pTime)) {
            return pTime;
        }
        for (int i = pTime.length(); i < 6; i++) {	//93423 --> 9:34:23
            pTime = '0' + pTime;
        }

        char[] mChars = pTime.toCharArray();
        return new StringBuilder()
                .append(mChars, 0, 2).append(':')
                .append(mChars, 2, 2).append(':')
                .append(mChars, 4, 2).toString();
    }

    public static void main(String[] args) throws Exception{
        String insBeginDate = DateUtil.getNextDate(DateUtil.U_DAY, 5) + " 00:00:00";
        String insEndDate = DateUtil.getEndDate(insBeginDate, DateUtil.U_YEAR, 1) + " 23:59:59";
        System.out.println(insBeginDate);
        System.out.println(insEndDate);

        System.out.println(date19to14(insBeginDate));

    }

}
