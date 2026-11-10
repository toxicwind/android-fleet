package com.ju.lib.utils.datetime;

import b.a.a.a.a;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ju.lib.utils.constants.Constants;
import com.ju.lib.utils.log.LogUtil;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class DateTimeHelper {
    public static final String TAG = "util.DateTimeHelper";

    public static String constructDateString(int i, int i2, int i3, String str) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        calendar.set(2, i2);
        calendar.set(5, i3);
        return new SimpleDateFormat(str, Constants.LOCALE.DEFAULT_LOCALE).format(calendar.getTime());
    }

    public static Date constructDateWithString(String str, String str2) {
        if (str != null && str2 != null) {
            try {
                return new SimpleDateFormat(str2, Constants.LOCALE.DEFAULT_LOCALE).parse(str);
            } catch (ParseException e2) {
                LogUtil.e(TAG, "constructDateWithString", e2.getMessage());
            }
        }
        return null;
    }

    public static Date getDateWithDayHourAndMinutes(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i / 10000);
        calendar.set(2, ((i / 100) % 100) - 1);
        calendar.set(5, i % 100);
        calendar.set(11, i2);
        calendar.set(12, i3);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getDateWithStamp(long j) {
        return new Date(j);
    }

    public static String getDayAfterSpecifiedDay(Date date, int i, DateFormat dateFormat) {
        return getDayBeforeSpecifiedDay(date, i * (-1), dateFormat);
    }

    public static String getDayBeforeSpecifiedDay(Date date, int i, DateFormat dateFormat) {
        Date dayBeforeSpecifiedDay = getDayBeforeSpecifiedDay(date, i);
        return dayBeforeSpecifiedDay == null ? "" : dateFormat.format(dayBeforeSpecifiedDay);
    }

    public static long getDayBegin(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 1);
        return calendar.getTimeInMillis();
    }

    public static int getDayCountOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(5);
    }

    public static int getDayCountOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(6);
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(1);
        calendar.setTime(date);
        calendar.set(7, calendar.getFirstDayOfWeek());
        return calendar.getTime();
    }

    public static Date getFirstDayOfWeekAfterSpecifiedDay(Date date, int i) {
        return getFirstDayOfWeekBeforeSpecifiedDay(date, i * (-1));
    }

    public static Date getFirstDayOfWeekBeforeSpecifiedDay(Date date, int i) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.get(5) - (i * 7));
        return getFirstDayOfWeek(calendar.getTime());
    }

    public static Date getFirstDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(6, 1);
        return calendar.getTime();
    }

    public static int getGapDayCount(Date date, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        calendar2.set(11, 0);
        calendar2.set(12, 0);
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        return (int) ((calendar2.getTime().getTime() - calendar.getTime().getTime()) / 86400000);
    }

    public static int getGapMonthCount(Date date, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return ((calendar2.get(1) - calendar.get(1)) * 12) + (calendar2.get(2) - calendar.get(2));
    }

    public static int getGapWeekCount(Date date, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getFirstDayOfWeek(date));
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(getFirstDayOfWeek(date2));
        calendar2.set(11, 0);
        calendar2.set(12, 0);
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        double time = ((calendar2.getTime().getTime() - calendar.getTime().getTime()) / 8.64E7d) / 7.0d;
        int i = (int) time;
        return time - ((double) i) > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? i + 1 : i;
    }

    public static int getGapYearCount(Date date, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar2.get(1) - calendar.get(1);
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.getActualMaximum(5));
        return calendar.getTime();
    }

    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(1);
        calendar.setTime(date);
        calendar.set(7, calendar.getFirstDayOfWeek() + 6);
        return calendar.getTime();
    }

    public static Date getLastDayOfWeekAfterSpecifiedDay(Date date, int i) {
        return getLastDayOfWeekBeforeSpecifiedDay(date, i * (-1));
    }

    public static Date getLastDayOfWeekBeforeSpecifiedDay(Date date, int i) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.get(5) - (i * 7));
        return getLastDayOfWeek(calendar.getTime());
    }

    public static Date getLastDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(6, calendar.getActualMaximum(6));
        return calendar.getTime();
    }

    public static String getMonthAfterSpecifiedDay(Date date, int i, DateFormat dateFormat) {
        return getMonthBeforeSpecifiedDay(date, i * (-1), dateFormat);
    }

    public static String getMonthBeforeSpecifiedDay(Date date, int i, DateFormat dateFormat) {
        Date monthBeforeSpecifiedDay = getMonthBeforeSpecifiedDay(date, i);
        return monthBeforeSpecifiedDay == null ? "" : dateFormat.format(monthBeforeSpecifiedDay);
    }

    public static int getPositonOfDayOnMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(5);
    }

    public static Date getSpecialOclock(Date date, int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, i3);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static long getStampWithDayHourAndMinutes(int i, int i2, int i3) {
        return getDateWithDayHourAndMinutes(i, i2, i3).getTime();
    }

    public static long getStampWithRoundingMinute(long j) {
        return (j / 60000) * 60000;
    }

    public static String getTimeZone() {
        int rawOffset = TimeZone.getDefault().getRawOffset();
        return String.valueOf(((rawOffset / 60000) % 60) + ((rawOffset / 3600000) * 100));
    }

    public static int getValueOfTime(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(i);
    }

    public static Date getWeekAfterSpecifiedDay(Date date, int i) {
        return getWeekBeforeSpecifiedDay(date, i * (-1));
    }

    public static Date getWeekBeforeSpecifiedDay(Date date, int i) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.get(5) - (i * 7));
        return calendar.getTime();
    }

    public static int getWeekCountOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(8);
    }

    public static Date getYearAfterSpecifiedDay(Date date, int i) {
        return getYearBeforeSpecifiedDay(date, i * (-1));
    }

    public static Date getYearBeforeSpecifiedDay(Date date, int i) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(1, i * (-1));
        return calendar.getTime();
    }

    public static boolean isInSameDay(Date date, Date date2) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(date);
        calendar2.setTime(date2);
        return calendar.get(1) == calendar2.get(1) && calendar.get(2) == calendar2.get(2) && calendar.get(5) == calendar2.get(5);
    }

    public static boolean isInSameMinute(long j, long j2) {
        return j / 60000 == j2 / 60000;
    }

    public static boolean isInSameMonth(Date date, Date date2) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(date);
        calendar2.setTime(date2);
        return calendar.get(1) == calendar2.get(1) && calendar.get(2) == calendar2.get(2);
    }

    public static boolean isInSameWeek(Date date, Date date2) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(date);
        calendar2.setTime(date2);
        return calendar.get(1) == calendar2.get(1) && calendar.get(3) == calendar2.get(3);
    }

    public static double stampConvertToHours(long j) {
        return ((j / 1000.0d) / 60.0d) / 60.0d;
    }

    public static double stampConvertToMinutes(long j) {
        return (j / 1000.0d) / 60.0d;
    }

    public static Date getDayAfterSpecifiedDay(Date date, int i) {
        return getDayBeforeSpecifiedDay(date, i * (-1));
    }

    public static Date getMonthAfterSpecifiedDay(Date date, int i) {
        return getMonthBeforeSpecifiedDay(date, i * (-1));
    }

    public static String getWeekAfterSpecifiedDay(Date date, int i, DateFormat dateFormat) {
        return getWeekBeforeSpecifiedDay(date, i * (-1), dateFormat);
    }

    public static Date getDayBeforeSpecifiedDay(Date date, int i) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.get(5) - i);
        return calendar.getTime();
    }

    public static Date getMonthBeforeSpecifiedDay(Date date, int i) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, i * (-1));
        return calendar.getTime();
    }

    public static Date constructDateWithString(String str, DateFormat dateFormat) {
        if (str != null && dateFormat != null) {
            try {
                return dateFormat.parse(str);
            } catch (ParseException e2) {
                LogUtil.e(TAG, "constructDateWithString", e2.getMessage());
            }
        }
        return null;
    }

    public static String getWeekBeforeSpecifiedDay(Date date, int i, DateFormat dateFormat) {
        if (dateFormat == null || date == null) {
            return "";
        }
        Date weekBeforeSpecifiedDay = getWeekBeforeSpecifiedDay(date, i);
        return a.a(dateFormat.format(getFirstDayOfWeek(weekBeforeSpecifiedDay)), "~", dateFormat.format(getLastDayOfWeek(weekBeforeSpecifiedDay)));
    }
}
