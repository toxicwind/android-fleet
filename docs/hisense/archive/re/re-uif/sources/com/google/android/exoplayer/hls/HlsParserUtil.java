package com.google.android.exoplayer.hls;

import android.util.Log;
import com.google.android.exoplayer.ParserException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
final class HlsParserUtil {
    public static String parseStringAttr(String str, Pattern pattern, String str2) throws ParserException {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find() && matcher.groupCount() == 1) {
            return matcher.group(1);
        }
        throw new ParserException("Couldn't match " + str2 + " tag in " + str);
    }

    public static int parseIntAttr(String str, Pattern pattern, String str2) throws ParserException {
        return Integer.parseInt(parseStringAttr(str, pattern, str2));
    }

    public static double parseDoubleAttr(String str, Pattern pattern, String str2) throws ParserException {
        return Double.parseDouble(parseStringAttr(str, pattern, str2));
    }

    public static String parseOptionalStringAttr(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static long compileProgramDateTime(String str, String str2) throws ParserException {
        String strSubstring = str.substring(str.indexOf(":") + 1);
        Log.d("HlsParserUtil", "dateTime[" + strSubstring.toString() + "]");
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(strSubstring).getTime();
        } catch (ParseException e) {
            Log.d("HlsParserUtil", "Parse Date failed! " + e.toString());
            return 0L;
        }
    }
}
