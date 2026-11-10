package com.hisense.tv.ota.W;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.os.FileUtils;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.hisense.tv.ota.C0000a;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class l {
    private static HashMap b;
    private static String c;
    private static String d;
    private static String e;
    private static String f;
    private static String g;
    private static String h;
    private static String i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f30a;

    static {
        o();
    }

    public l(Context context) {
        this.f30a = context;
    }

    private static void e() {
        String str = SystemProperties.get("ro.vt.product.model");
        Log.i("TvInfoUtils", "getDefaultMachineType getDefaultMachineType: " + str);
        if (TextUtils.isEmpty(str)) {
            Log.e("TvInfoUtils", "getDefaultMachineType failed, machine name is empty");
            str = "default";
        }
        c = str + "0000123";
    }

    private static void f() {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        Date date = new Date(Build.TIME);
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        int i2 = calendar.get(1);
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append((char) (((i2 - 2010) % 26) + 65));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMdd");
        simpleDateFormat.setTimeZone(timeZone);
        stringBuffer.append(simpleDateFormat.format(date));
        d = stringBuffer.toString();
    }

    public static int g(String str) {
        String str2;
        Log.i("TvInfoUtils", "get language id: " + str);
        if ("zh".equals(str)) {
            String country = Locale.getDefault().getCountry();
            Log.i("TvInfoUtils", "get country is: " + country);
            if ("CN".equals(country)) {
                str = "zh_cn";
            } else if ("TW".equals(country)) {
                str = "zh_tw";
            } else if ("HK".equals(country)) {
                str = "zh_hk";
            }
        }
        Log.i("TvInfoUtils", "get language is: " + str);
        int iIntValue = 0;
        if (str != null && str.length() >= 2) {
            if (!b.containsKey(str)) {
                str = str.substring(0, 2);
                if (!b.containsKey(str)) {
                    str2 = "unknown language";
                }
                Log.i("TvInfoUtils", "language id: " + iIntValue);
                return iIntValue;
            }
            iIntValue = ((Integer) b.get(str)).intValue();
            Log.i("TvInfoUtils", "language id: " + iIntValue);
            return iIntValue;
        }
        str2 = "invalid language";
        Log.i("TvInfoUtils", str2);
        Log.i("TvInfoUtils", "language id: " + iIntValue);
        return iIntValue;
    }

    private void k() {
        ContentResolver contentResolver = this.f30a.getContentResolver();
        if (contentResolver == null) {
            Log.e("TvInfoUtils", "initBrandCode failed, content resolver is null");
            return;
        }
        String string = Settings.Global.getString(contentResolver, "tv_user_setup_logo");
        Log.i("TvInfoUtils", "brand code from FTE =: " + string);
        if (TextUtils.isEmpty(string)) {
            Log.i("TvInfoUtils", "unknown brand code, use default");
            string = C0000a.c;
        }
        i = string;
    }

    private void l() {
        ContentResolver contentResolver = this.f30a.getContentResolver();
        if (contentResolver == null) {
            Log.e("TvInfoUtils", "initCountryCode failed, content resolver is null");
            return;
        }
        String string = Settings.Global.getString(contentResolver, "tv_user_setup_country");
        Log.i("TvInfoUtils", "country code from FTE: " + string);
        if (TextUtils.isEmpty(string)) {
            Log.i("TvInfoUtils", "unknown country code, use default");
            string = C0000a.d;
        }
        h = string;
    }

    private void m() {
        f = SystemProperties.get("ro.boot.slot_suffix");
    }

    private void n() {
        e = SystemProperties.get("ro.vt.data.version");
    }

    private static void o() {
        HashMap map = new HashMap();
        b = map;
        map.put("en", 1);
        b.put("fr", 2);
        b.put("es", 6);
        b.put("pt", 14);
        b.put("ar", 9);
        b.put("ru", 4);
        b.put("zh_cn", 0);
        b.put("zh", 8);
        b.put("vi", 29);
        b.put("th", 11);
        b.put("my", 30);
        b.put("uz", 61);
        b.put("hi", 31);
        b.put("uk", 62);
        b.put("ms", 20);
        b.put("iw", 21);
        b.put("zh_hk", 8);
        b.put("zh_tw", 8);
        b.put("de", 7);
        b.put("it", 12);
        b.put("sv", 26);
        b.put("da", 27);
        b.put("fi", 28);
        b.put("nb", 25);
        b.put("cs", 15);
        b.put("sk", 63);
        b.put("pl", 64);
        b.put("hu", 16);
        b.put("bg", 18);
        b.put("tr", 59);
        b.put("fa", 10);
        b.put("in", 60);
        b.put("hr", 39);
        b.put("sr", 36);
        b.put("mk", 37);
        b.put("sq", 38);
        b.put("lv", 40);
        b.put("et", 41);
        b.put("lt", 42);
        b.put("ku", 57);
        b.put("sl", 43);
        b.put("nl", 13);
        b.put("ro", 19);
        b.put("el", 17);
        b.put("hy", 65);
        b.put("ka", 66);
        b.put("ur", 67);
        b.put("am", 68);
        b.put("ky", 69);
        b.put("kk", 70);
        b.put("az", 58);
        b.put("bs", 73);
        b.put("is", 76);
        b.put("be", 44);
        b.put("mo", 82);
        b.put("bn", 71);
        b.put("mn", 83);
        b.put("eu", 49);
        b.put("bi", 72);
        b.put("br", 74);
        b.put("ca", 47);
        b.put("co", 53);
        b.put("dz", 75);
        b.put("gl", 50);
        b.put("ga", 45);
        b.put("ja", 5);
        b.put("km", 77);
        b.put("rw", 78);
        b.put("ko", 3);
        b.put("lo", 79);
        b.put("la", 80);
        b.put("lb", 56);
        b.put("mh", 81);
        b.put("na", 85);
        b.put("ne", 86);
        b.put("rn", 87);
        b.put("sm", 88);
        b.put("si", 89);
        b.put("sw", 90);
        b.put("ss", 91);
        b.put("to", 93);
        b.put("tn", 94);
        b.put("tk", 95);
        b.put("cy", 46);
        b.put("ta", 96);
        b.put("te", 97);
        b.put("ml", 98);
        b.put("kn", 99);
        b.put("mr", 100);
        b.put("gu", 102);
        b.put("bh", 103);
        b.put("pa", 104);
        b.put("as", 106);
        b.put("ks", 107);
    }

    private void p() {
        try {
            g = FileUtils.readTextFile(new File("/sys/class/net/eth0/address"), 17, null);
        } catch (IOException e2) {
            Log.e("TvInfoUtils", "read mac file failed", e2);
        }
    }

    private void q() {
        String strSubstring;
        String str;
        String str2 = SystemProperties.get("ro.vt.product.model");
        Log.i("TvInfoUtils", "initMachineType machine name: " + str2);
        if (TextUtils.isEmpty(str2)) {
            str = "initMachineType failed, machine name is empty";
        } else {
            String str3 = SystemProperties.get("ro.vt.build.softversion");
            Log.i("TvInfoUtils", "initMachineType swVersion: " + str3);
            if (TextUtils.isEmpty(str3)) {
                str = "initMachineType failed, sw version is empty";
            } else {
                try {
                    strSubstring = str3.substring(1, str3.indexOf("."));
                } catch (Exception unused) {
                    strSubstring = null;
                }
                if (strSubstring == null || strSubstring.length() != 4) {
                    str = "initMachineType failed, sw version invalid format";
                } else {
                    String str4 = SystemProperties.get("ro.vt.product.plmnumber");
                    Log.i("TvInfoUtils", "plm number: " + str4);
                    if (TextUtils.isEmpty(str4)) {
                        str = "initMachineType failed, plm number is empty";
                    } else {
                        if (str4.length() >= 3) {
                            c = str2 + strSubstring + str4.substring(str4.length() - 3);
                            return;
                        }
                        str = "initMachineType failed, invalid plm number";
                    }
                }
            }
        }
        Log.e("TvInfoUtils", str);
    }

    private void r() {
        String str = SystemProperties.get("ro.vt.build.softversion");
        Log.i("TvInfoUtils", "initSysVersion swVersion: " + str);
        if (TextUtils.isEmpty(str)) {
            Log.e("TvInfoUtils", "initSysVersion failed, sw version is empty");
            return;
        }
        String strSubstring = str.substring(str.indexOf(".") + 1, str.length());
        if (strSubstring != null) {
            String strReplace = strSubstring.replace(".", "");
            Log.i("TvInfoUtils", "initSysVersion format swVersion: " + strReplace);
            if (strReplace != null) {
                d = strReplace + SystemProperties.get("ro.vt.build.softdate");
                Log.i("TvInfoUtils", "OTA mSysVersion: " + d);
                return;
            }
        }
        Log.e("TvInfoUtils", "initSysVersion failed, sw version invalid format");
    }

    public String a() {
        k();
        return i;
    }

    public String b() {
        l();
        return h;
    }

    public String c() {
        if (f == null) {
            m();
        }
        return f;
    }

    public String d() {
        if (e == null) {
            n();
        }
        return e;
    }

    public String h() {
        p();
        return g;
    }

    public String i() {
        if (c == null) {
            q();
            if (c == null) {
                e();
                Log.i("TvInfoUtils", "use default MachineType: " + c);
            }
        }
        return c;
    }

    public String j() {
        if (d == null) {
            r();
            if (d == null) {
                f();
                Log.i("TvInfoUtils", "use default Version: " + d);
            }
        }
        return d;
    }
}
