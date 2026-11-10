package com.google.gson.internal.bind.util;

import b.a.a.a.a;
import com.google.common.net.InetAddresses;
import com.google.firebase.installations.Utils;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class ISO8601Utils {
    public static final String UTC_ID = "UTC";
    public static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);

    public static boolean checkOffset(String str, int i, char c2) {
        return i < str.length() && str.charAt(i) == c2;
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '0' || cCharAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }

    public static void padInt(StringBuilder sb, int i, int i2) {
        String string = Integer.toString(i);
        for (int length = i2 - string.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(string);
    }

    /* JADX WARN: Code duplicated, block: B:48:0x00c9 A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b6, NumberFormatException -> 0x01b8, IndexOutOfBoundsException -> 0x01ba, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b6, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:46:0x00c1, B:48:0x00c9, B:50:0x00d0, B:74:0x017d, B:54:0x00da, B:55:0x00f5, B:56:0x00f6, B:60:0x0112, B:62:0x011f, B:65:0x0128, B:67:0x0147, B:70:0x0156, B:71:0x0178, B:73:0x017b, B:59:0x0101, B:76:0x01ae, B:77:0x01b5, B:40:0x00b0, B:41:0x00b3), top: B:93:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:50:0x00d0 A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b6, NumberFormatException -> 0x01b8, IndexOutOfBoundsException -> 0x01ba, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b6, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:46:0x00c1, B:48:0x00c9, B:50:0x00d0, B:74:0x017d, B:54:0x00da, B:55:0x00f5, B:56:0x00f6, B:60:0x0112, B:62:0x011f, B:65:0x0128, B:67:0x0147, B:70:0x0156, B:71:0x0178, B:73:0x017b, B:59:0x0101, B:76:0x01ae, B:77:0x01b5, B:40:0x00b0, B:41:0x00b3), top: B:93:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:58:0x0100  */
    /* JADX WARN: Code duplicated, block: B:59:0x0101 A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b6, NumberFormatException -> 0x01b8, IndexOutOfBoundsException -> 0x01ba, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b6, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:46:0x00c1, B:48:0x00c9, B:50:0x00d0, B:74:0x017d, B:54:0x00da, B:55:0x00f5, B:56:0x00f6, B:60:0x0112, B:62:0x011f, B:65:0x0128, B:67:0x0147, B:70:0x0156, B:71:0x0178, B:73:0x017b, B:59:0x0101, B:76:0x01ae, B:77:0x01b5, B:40:0x00b0, B:41:0x00b3), top: B:93:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:73:0x017b A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b6, NumberFormatException -> 0x01b8, IndexOutOfBoundsException -> 0x01ba, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b6, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:46:0x00c1, B:48:0x00c9, B:50:0x00d0, B:74:0x017d, B:54:0x00da, B:55:0x00f5, B:56:0x00f6, B:60:0x0112, B:62:0x011f, B:65:0x0128, B:67:0x0147, B:70:0x0156, B:71:0x0178, B:73:0x017b, B:59:0x0101, B:76:0x01ae, B:77:0x01b5, B:40:0x00b0, B:41:0x00b3), top: B:93:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x01ae A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b6, NumberFormatException -> 0x01b8, IndexOutOfBoundsException -> 0x01ba, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01b6, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0035, B:13:0x003b, B:17:0x0050, B:19:0x0060, B:20:0x0062, B:22:0x006e, B:23:0x0070, B:25:0x0076, B:29:0x0080, B:34:0x0090, B:36:0x0098, B:46:0x00c1, B:48:0x00c9, B:50:0x00d0, B:74:0x017d, B:54:0x00da, B:55:0x00f5, B:56:0x00f6, B:60:0x0112, B:62:0x011f, B:65:0x0128, B:67:0x0147, B:70:0x0156, B:71:0x0178, B:73:0x017b, B:59:0x0101, B:76:0x01ae, B:77:0x01b5, B:40:0x00b0, B:41:0x00b3), top: B:93:0x0004 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:59:0x0101, please report this as an issue */
    public static Date parse(String str, ParsePosition parsePosition) throws ParseException {
        String str2;
        int i;
        int i2;
        int i3;
        int i4;
        int iIndexOfNonDigit;
        int i5;
        int i6;
        char cCharAt;
        String strSubstring;
        int length;
        TimeZone timeZone;
        char cCharAt2;
        try {
            int index = parsePosition.getIndex();
            int i7 = index + 4;
            int i8 = parseInt(str, index, i7);
            if (checkOffset(str, i7, '-')) {
                i7++;
            }
            int i9 = i7 + 2;
            int i10 = parseInt(str, i7, i9);
            if (checkOffset(str, i9, '-')) {
                i9++;
            }
            int i11 = i9 + 2;
            int i12 = parseInt(str, i9, i11);
            boolean zCheckOffset = checkOffset(str, i11, 'T');
            if (!zCheckOffset && str.length() <= i11) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(i8, i10 - 1, i12);
                parsePosition.setIndex(i11);
                return gregorianCalendar.getTime();
            }
            if (zCheckOffset) {
                int i13 = i11 + 1;
                int i14 = i13 + 2;
                i2 = parseInt(str, i13, i14);
                if (checkOffset(str, i14, InetAddresses.IPV6_DELIMITER)) {
                    i14++;
                }
                int i15 = i14 + 2;
                i3 = parseInt(str, i14, i15);
                if (checkOffset(str, i15, InetAddresses.IPV6_DELIMITER)) {
                    i15++;
                }
                if (str.length() <= i15 || (cCharAt2 = str.charAt(i15)) == 'Z' || cCharAt2 == '+' || cCharAt2 == '-') {
                    i = i15;
                } else {
                    i = i15 + 2;
                    i4 = parseInt(str, i15, i);
                    if (i4 > 59 && i4 < 63) {
                        i4 = 59;
                    }
                    if (checkOffset(str, i, InetAddresses.IPV4_DELIMITER)) {
                        int i16 = i + 1;
                        iIndexOfNonDigit = indexOfNonDigit(str, i16 + 1);
                        int iMin = Math.min(iIndexOfNonDigit, i16 + 3);
                        i5 = parseInt(str, i16, iMin);
                        int i17 = iMin - i16;
                        if (i17 == 1) {
                            i5 *= 100;
                        } else if (i17 == 2) {
                            i5 *= 10;
                        }
                    } else {
                        iIndexOfNonDigit = i;
                        i5 = 0;
                    }
                }
                i6 = i5;
                if (str.length() > iIndexOfNonDigit) {
                    throw new IllegalArgumentException("No time zone indicator");
                }
                cCharAt = str.charAt(iIndexOfNonDigit);
                if (cCharAt == 'Z') {
                    timeZone = TIMEZONE_UTC;
                    length = iIndexOfNonDigit + 1;
                } else {
                    if (cCharAt != '+' && cCharAt != '-') {
                        throw new IndexOutOfBoundsException("Invalid time zone indicator '" + cCharAt + "'");
                    }
                    strSubstring = str.substring(iIndexOfNonDigit);
                    if (strSubstring.length() >= 5) {
                        strSubstring = strSubstring + "00";
                    }
                    length = iIndexOfNonDigit + strSubstring.length();
                    if (!"+0000".equals(strSubstring) || "+00:00".equals(strSubstring)) {
                        timeZone = TIMEZONE_UTC;
                    } else {
                        String str3 = "GMT" + strSubstring;
                        TimeZone timeZone2 = TimeZone.getTimeZone(str3);
                        String id = timeZone2.getID();
                        if (!id.equals(str3) && !id.replace(Utils.APP_ID_IDENTIFICATION_SUBSTRING, "").equals(str3)) {
                            throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str3 + " given, resolves to " + timeZone2.getID());
                        }
                        timeZone = timeZone2;
                    }
                }
                GregorianCalendar gregorianCalendar2 = new GregorianCalendar(timeZone);
                gregorianCalendar2.setLenient(false);
                gregorianCalendar2.set(1, i8);
                gregorianCalendar2.set(2, i10 - 1);
                gregorianCalendar2.set(5, i12);
                gregorianCalendar2.set(11, i2);
                gregorianCalendar2.set(12, i3);
                gregorianCalendar2.set(13, i4);
                gregorianCalendar2.set(14, i6);
                parsePosition.setIndex(length);
                return gregorianCalendar2.getTime();
            }
            i = i11;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            iIndexOfNonDigit = i;
            i5 = 0;
            i6 = i5;
            if (str.length() > iIndexOfNonDigit) {
                throw new IllegalArgumentException("No time zone indicator");
            }
            cCharAt = str.charAt(iIndexOfNonDigit);
            if (cCharAt == 'Z') {
                timeZone = TIMEZONE_UTC;
                length = iIndexOfNonDigit + 1;
            } else {
                if (cCharAt != '+') {
                    throw new IndexOutOfBoundsException("Invalid time zone indicator '" + cCharAt + "'");
                }
                strSubstring = str.substring(iIndexOfNonDigit);
                if (strSubstring.length() >= 5) {
                    strSubstring = strSubstring + "00";
                }
                length = iIndexOfNonDigit + strSubstring.length();
                if ("+0000".equals(strSubstring)) {
                    timeZone = TIMEZONE_UTC;
                } else {
                    timeZone = TIMEZONE_UTC;
                }
            }
            GregorianCalendar gregorianCalendar3 = new GregorianCalendar(timeZone);
            gregorianCalendar3.setLenient(false);
            gregorianCalendar3.set(1, i8);
            gregorianCalendar3.set(2, i10 - 1);
            gregorianCalendar3.set(5, i12);
            gregorianCalendar3.set(11, i2);
            gregorianCalendar3.set(12, i3);
            gregorianCalendar3.set(13, i4);
            gregorianCalendar3.set(14, i6);
            parsePosition.setIndex(length);
            return gregorianCalendar3.getTime();
        } catch (IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException e2) {
            if (str == null) {
                str2 = null;
            } else {
                str2 = '\"' + str + '\"';
            }
            String message = e2.getMessage();
            if (message == null || message.isEmpty()) {
                StringBuilder sbA = a.a("(");
                sbA.append(e2.getClass().getName());
                sbA.append(")");
                message = sbA.toString();
            }
            ParseException parseException = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException.initCause(e2);
            throw parseException;
        }
    }

    public static int parseInt(String str, int i, int i2) {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int iDigit = Character.digit(str.charAt(i), 10);
            if (iDigit < 0) {
                StringBuilder sbA = a.a("Invalid number: ");
                sbA.append(str.substring(i, i2));
                throw new NumberFormatException(sbA.toString());
            }
            i3 = -iDigit;
        } else {
            i3 = 0;
            i4 = i;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int iDigit2 = Character.digit(str.charAt(i4), 10);
            if (iDigit2 < 0) {
                StringBuilder sbA2 = a.a("Invalid number: ");
                sbA2.append(str.substring(i, i2));
                throw new NumberFormatException(sbA2.toString());
            }
            i3 = (i3 * 10) - iDigit2;
            i4 = i5;
        }
        return -i3;
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(InetAddresses.IPV6_DELIMITER);
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(InetAddresses.IPV6_DELIMITER);
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append(InetAddresses.IPV4_DELIMITER);
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int iAbs = Math.abs(i / 60);
            int iAbs2 = Math.abs(i % 60);
            sb.append(offset >= 0 ? '+' : '-');
            padInt(sb, iAbs, 2);
            sb.append(InetAddresses.IPV6_DELIMITER);
            padInt(sb, iAbs2, 2);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }
}
