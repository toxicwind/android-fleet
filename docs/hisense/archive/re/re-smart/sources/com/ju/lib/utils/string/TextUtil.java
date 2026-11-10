package com.ju.lib.utils.string;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class TextUtil {
    public static String DeleteString(String str, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            str = str.replace(list.get(i), "");
        }
        return str;
    }

    public static SpannableString ProcessHighlightWord(int i, String str, List<String> list) {
        SpannableString spannableString = new SpannableString(str);
        for (int i2 = 0; i2 < list.size(); i2++) {
            Matcher matcher = Pattern.compile(Pattern.quote(list.get(i2))).matcher(spannableString);
            while (matcher.find()) {
                spannableString.setSpan(new ForegroundColorSpan(i), matcher.start(), matcher.end(), 33);
            }
        }
        return spannableString;
    }

    public static boolean isEmailNumber(String str) {
        try {
            return Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").matcher(str).matches();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isHttpUrl(String str) {
        if (str == null) {
            return false;
        }
        return str.contains("http") || str.contains("https");
    }

    public static boolean isMobilePhoneNumber(String str) {
        if (str == null || str.equals("") || str.length() != 11 || !isNumber(str)) {
            return false;
        }
        return str.substring(0, 2).equals("13") || str.substring(0, 2).equals(MtkTvRatingConvert2Goo.RATING_STR_15) || str.substring(0, 2).equals("18");
    }

    public static boolean isNumber(String str) {
        return str != null && str.length() > 0 && Pattern.compile("^[0-9\\-\\-]+$").matcher(str).find();
    }

    public static List<String> match(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile(str2 + "([\\W\\w]*?)" + str3).matcher(str);
        while (!matcher.hitEnd() && matcher.find()) {
            arrayList.add(matcher.group(1));
        }
        return arrayList;
    }
}
