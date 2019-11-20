package com.example.mylibrary.util;

import android.text.TextUtils;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author fly Young2014年10月29日
 */
public class Util_time {
    public static final SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public static boolean isWeekend( Date bdate) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(bdate);
            if(cal.get(Calendar.DAY_OF_WEEK)== Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return false;
    }
    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取截止目前的开市分钟数
     *
     * @return
     */
    public static long getSubMinute() {
//            代码获取的是GMT+0的小时数。而中国在GMT+8
        Date date = new Date();

        long currentTimeMillis = date.getTime()+8*3600000;
        long startTime1 = dateToStamp(getDateStr(date) + " 9:30:00");
        long startTime2 = dateToStamp(getDateStr(date) + " 11:30:00");
        long startTime3 = dateToStamp(getDateStr(date) + " 13:00:00");
        long startTime4 = dateToStamp(getDateStr(date) + " 15:00:00");
        if (currentTimeMillis < startTime1) {
            return 0;
        }
        if (currentTimeMillis > startTime4) {
            return 240;
        }
        if (currentTimeMillis <= startTime2) {
            return (currentTimeMillis - startTime1) / 60000;
        }
        int amSubMinute = 120;
        if (currentTimeMillis > startTime3) {
            return (currentTimeMillis - startTime3) / 60000 + amSubMinute;
        } else {
            return amSubMinute;
        }
    }

    /**
     * 判断时间是否在a股交易时间内
     *
     * @return
     */
    public static boolean isStockDealTime() {
        Date date = new Date();
        boolean isWeekend = Util_time.isWeekend(date);
        if (isWeekend) {
            return false;
        }
        String dateStr = getDateStr(date);
        long currentTimeMillis = date.getTime();
        long startTime1 = dateToStamp( dateStr+ " 9:23:00");
//        long endTime1 = dateToStamp(getDateStr(date) + " 11:30:00");
//        if (currentTimeMillis >= startTime1 && currentTimeMillis <= endTime1) {
//            return true;
//        }
//        long startTime2 = dateToStamp(getDateStr(date) + " 13:00:00");
        long endTime2 = dateToStamp(dateStr + " 15:00:00");
        if (currentTimeMillis >= startTime1 && currentTimeMillis <= endTime2) {
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public static int getStockDealType() {
        Date date = new Date();
        boolean isWeekend = Util_time.isWeekend(date);
        if (isWeekend) {
            return -2;
        }
        String dateStr = getDateStr(date);
        long currentTimeMillis = date.getTime();
        long startTime1 = dateToStamp(dateStr + " 9:20:00");
//        long endTime1 = dateToStamp(getDateStr(date) + " 11:30:00");
//        if (currentTimeMillis >= startTime1 && currentTimeMillis <= endTime1) {
//            return true;
//        }
//        long startTime2 = dateToStamp(getDateStr(date) + " 13:00:00");
        long subMinites = (currentTimeMillis - startTime1) / 60000;
        if (subMinites > 0) {
            if (subMinites <= 10)
                return 1;//集合竞价
            long endTime2 = dateToStamp(dateStr + " 15:00:00");
            if ( currentTimeMillis <= endTime2) {
                return 2;//盘中
            }else{
                return 3;//盘后
            }
        }
        return -1;
    }
    /**
     * 判断时间是否在a股尾盘交易时间
     *
     * @return
     */
    public static boolean isStockLateDealTime() {
        Date date = new Date();
        boolean isWeekend = Util_time.isWeekend(date);
        if (isWeekend) {
            return false;
        }
        long currentTimeMillis = date.getTime();
        String dateStr = getDateStr(date);
        long startTime1 = dateToStamp(dateStr+ " 14:30:00");
        long endTime2 = dateToStamp(dateStr + " 24:00:00");
        if (currentTimeMillis >= startTime1 && currentTimeMillis <= endTime2) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间是否在当天交易结束后
     *
     * @return
     */
    public static boolean isPassStockDealTime() {
        Date date = new Date();
        long currentTimeMillis = System.currentTimeMillis();
        long endTime2 = dateToStamp(getDateStr(date) + " 15:00:00");
        if (currentTimeMillis > endTime2) {
            return true;
        }
        return false;
    }

    public static String getDateStr(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//        TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");//获取时区 这句加上，很关键。
//        sdf.setTimeZone(timeZoneChina);//设置系统时区
        return sdf.format(date);
    }

    public static String getDateStr(Date date) {
        return getDateStr(date, "yyyy-MM-dd");
    }

    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /*
        * 将时间转换为时间戳
        */
    public static long dateToStamp(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*
        */
    public static String currentDate(String pattern) {
        return getDateString(pattern,new Date());
    }
    public static String getDateString(String pattern, Date date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /*
     * 将当前日期加减n天数。 如传入整型-5 意为将当前日期减去5天的日期 如传入整型5 意为将当前日期加上5天后的日期 返回字串 例(19990203)
     */
    public static Date dateAdd(int days) {
        // 日期处理模块 (将日期加上某些天或减去天数)返回字符串
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
        return canlendar.getTime();
    }

    /*
        */
    public static String currentDate() {
        return currentDate("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取消息的时间显示
     *
     * @param timeMillis 单位:毫秒
     * @return
     */
    public static String getMsgTimeShow(long timeMillis) {
        int[] resplyTimeFields = Util_time.getTimeFields(timeMillis);
        String dateText = "";
        //今天
        if (DateUtils.isToday(timeMillis)) {
            if (resplyTimeFields[3] < 12) {
                //上午
                dateText = Util_time.getStringDate(timeMillis, "上午hh:mm");
            } else {
                //下午
                dateText = Util_time.getStringDate(timeMillis, "下午hh:mm");
            }
        } else {
            long nowTimeMillis = System.currentTimeMillis();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(Util_time.resetDayOfTime(nowTimeMillis));
            c.add(Calendar.DAY_OF_MONTH, -1);
            if (c.getTimeInMillis() == Util_time.resetDayOfTime(timeMillis)) {
                //昨天
                if (resplyTimeFields[3] < 12) {
                    //上午
                    dateText = Util_time.getStringDate(timeMillis, "昨天 上午hh:mm");
                } else {
                    //下午
                    dateText = Util_time.getStringDate(timeMillis, "昨天 下午hh:mm");
                }
            } else {
                //其他时间
                if (resplyTimeFields[3] < 12) {
                    //上午
                    dateText = Util_time.getStringDate(timeMillis, "yyyy-MM-dd 上午hh:mm");
                } else {
                    //下午
                    dateText = Util_time.getStringDate(timeMillis, "yyyy-MM-dd 下午hh:mm");
                }

            }
        }
        return dateText;
    }

    /**
     * @param startTime
     * @param endTime
     * @return 二个日期相减的天数
     */
    public static int getSubTime(long startTime, long endTime) {
        long sub = endTime - startTime;
        long ss = sub / (1000); // 共计秒数
        int MM = (int) ss / 60; // 共计分钟数
        int hh = (int) ss / 3600; // 共计小时数
        int dd = hh / 24; // 共计天数
        return dd;
    }

    public static String getStringDate(long time, String dataFormat_want) {
        return getStringDate(new Date(time), dataFormat_want);
    }

    public static String getStringDate(Date data, String dataFormat_want) {
        SimpleDateFormat formatter = new SimpleDateFormat(dataFormat_want);
        return formatter.format(data);
    }

    public static Date getDate(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDate(String strDate, String dataFormat_want) {
        SimpleDateFormat sdf = new SimpleDateFormat(dataFormat_want);
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param
     * @return
     * @描述 —— 指定时间距离当前时间的中文信息
     */
    public static String getFriendlyTime(String dataFormat_want,
                                         String dataFormat, String str_date) {
        String friendlyTime = "";
        if (TextUtils.isEmpty(str_date))
            return "";
        long time = getLongTime(str_date, dataFormat);
        long subTime = System.currentTimeMillis() - time;
        if (subTime / 1000 < 60) {
            friendlyTime = "刚刚";
        } else if (subTime / 1000 / 60 < 60) {
            friendlyTime = subTime / 1000 / 60 + "分钟前";
        } else if (subTime / 1000 / 60 / 60 < 24) {
            friendlyTime = subTime / 1000 / 60 / 60 + "小时前";
        } else {
            friendlyTime = getWantDate(dataFormat_want, dataFormat, str_date);
        }
        return friendlyTime;
    }

    /**
     * @return
     * @描述 —— 指定时间距离当前时间的中文信息
     */
    public static String getFriendlyTime(String dataFormat_want,
                                         String dataFormat, String str_date, String postfix) {
        String friendlyTime = getFriendlyTime(dataFormat_want, dataFormat, str_date);
        return friendlyTime + postfix;
    }

    /**
     * @param time
     * @return
     * @描述 —— 指定时间距离当前时间的中文信息
     */
    public static String getFriendlyTime(String dataFormat_want, long time) {
        String friendlyTime = "";
        if (time == 0)
            return "";
        long subTime = System.currentTimeMillis() - time;
        if (subTime / 1000 < 60) {
            friendlyTime = "刚刚";
        } else if (subTime / 1000 / 60 < 60) {
            friendlyTime = subTime / 1000 / 60 + "分钟前";
        } else if (subTime / 1000 / 60 / 60 < 24) {
            friendlyTime = subTime / 1000 / 60 / 60 + "小时前";
        } else {
            friendlyTime = new SimpleDateFormat(dataFormat_want)
                    .format(new Date(time));
        }
        return friendlyTime;
    }

    /**
     * @param time
     * @return
     * @描述 —— 指定时间距离当前时间的中文信息
     */
    public static String getFriendlyTime(String dataFormat_want, long time, String postfix) {
        String friendlyTime = getFriendlyTime(dataFormat_want, time);
        return friendlyTime + postfix;
    }

    /**
     * 获得想要时间格式的字符串
     *
     * @param dataFormat_want
     * @param dataFormat
     * @param str_date
     * @return
     */
    public static String getWantDate(String dataFormat_want, String dataFormat,
                                     String str_date) {
        String wantData = "";
        try {
            wantData = new SimpleDateFormat(dataFormat_want)
                    .format(new SimpleDateFormat(dataFormat).parse(str_date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return wantData;
    }

    /**
     * 字符串转毫秒
     *
     * @return
     */
    public static long getLongTime(String str_date, String str_dataPattern) {
        if (TextUtils.isEmpty(str_date))
            return 0;
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(new SimpleDateFormat(str_dataPattern).parse(str_date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTimeInMillis();
    }

    /**
     * 返回，年，月，日，时，分，秒,毫秒
     *
     * @param time
     * @return
     */
    public static final int[] getTimeFields(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return new int[]{
                c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND),
                c.get(Calendar.MILLISECOND)
        };
    }

    /**
     * 将时间重置为一天的开始
     *
     * @param time
     * @return
     */
    public static final long resetDayOfTime(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    /**
     * 将时间重置为一天的开始，并返回，年，月，日，时，分，秒,毫秒
     *
     * @param time
     * @return
     */
    public static final int[] resetDayAndGetFields(long time) {
        return getTimeFields(resetDayOfTime(time));
    }

}
