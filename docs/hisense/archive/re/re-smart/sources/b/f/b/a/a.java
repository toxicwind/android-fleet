package b.f.b.a;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.hisense.support.tv.HisenseCountryLanInfo;
import com.hisense.support.tv.HisenseTvAPI;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f665a = "a";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static SparseArray<String> f666b = new SparseArray<>();

    static {
        f666b.put(1, HisenseCountryLanInfo.LOGO_HISENSE_INDEX);
        f666b.put(2, "wel");
        f666b.put(3, "nob");
        f666b.put(4, "jvc");
        f666b.put(5, "san");
        f666b.put(6, "phi");
        f666b.put(7, "ilo");
        f666b.put(8, "pio");
        f666b.put(9, "sma");
        f666b.put(10, "bgh");
        f666b.put(11, "blu");
        f666b.put(12, "dev");
        f666b.put(13, "kal");
        f666b.put(14, "cha");
        f666b.put(15, "pan");
        f666b.put(16, MtkTvConfigTypeBase.S639_CFG_LANG_TEL);
        f666b.put(17, "riv");
        f666b.put(18, "con");
        f666b.put(19, "gen");
        f666b.put(20, "haa");
        f666b.put(21, "tit");
        f666b.put(22, "kmc");
        f666b.put(23, "vuz");
        f666b.put(24, "llo");
        f666b.put(25, "inn");
        f666b.put(26, "sho");
        f666b.put(27, "enf");
        f666b.put(28, "dlc");
        f666b.put(29, "hya");
        f666b.put(30, "dex");
        f666b.put(31, "sup");
        f666b.put(32, "oke");
        f666b.put(33, "rol");
        f666b.put(34, "sha");
        f666b.put(35, "har");
        f666b.put(36, "ezy");
        f666b.put(37, "ame");
        f666b.put(38, "dik");
        f666b.put(39, "cyb");
        f666b.put(40, "hyu");
        f666b.put(41, "gfo");
        f666b.put(42, "sei");
        f666b.put(43, "ton");
        f666b.put(44, "psc");
        f666b.put(45, "tho");
        f666b.put(46, "ora");
        f666b.put(47, "vtb");
        f666b.put(48, "ros");
        f666b.put(49, "shp");
        f666b.put(50, "del");
        f666b.put(51, "str");
        f666b.put(52, "dam");
        f666b.put(53, "cor");
        f666b.put(54, "mtx");
        f666b.put(55, "gua");
        f666b.put(56, "bec");
        f666b.put(57, "idh");
        f666b.put(58, "viv");
        f666b.put(59, "tat");
        f666b.put(60, "ben");
        f666b.put(61, "cmo");
        f666b.put(62, "oro");
        f666b.put(63, "sch");
        f666b.put(64, "rca");
        f666b.put(65, "pro");
        f666b.put(66, "nik");
        f666b.put(67, "fam");
        f666b.put(68, "mag");
        f666b.put(69, "xvi");
        f666b.put(70, "aba");
        f666b.put(71, "aki");
        f666b.put(72, "bpl");
        f666b.put(73, "eco");
        f666b.put(74, MtkTvConfigTypeBase.S639_CFG_LANG_GLE);
        f666b.put(75, "liv");
        f666b.put(76, "mtc");
        f666b.put(77, "sir");
        f666b.put(78, "sol");
        f666b.put(79, "wal");
        f666b.put(80, "alm");
        f666b.put(81, MtkTvConfigTypeBase.S639_CFG_LANG_NOR);
        f666b.put(82, MtkTvConfigTypeBase.S639_CFG_LANG_POL);
        f666b.put(83, "meo");
        f666b.put(84, "avi");
        f666b.put(85, "adm");
        f666b.put(86, "alh");
        f666b.put(87, "toh");
        f666b.put(88, "aka");
        f666b.put(89, "vig");
        f666b.put(90, "vis");
        f666b.put(91, "ran");
        f666b.put(92, "dof");
        f666b.put(93, "oni");
        f666b.put(94, "ohi");
        f666b.put(95, HisenseCountryLanInfo.LOGO_TOSHIBA_INDEX);
        f666b.put(96, "kio");
        f666b.put(97, MtkTvConfigTypeBase.S639_CFG_LANG_MUL);
        f666b.put(98, "mar");
        f666b.put(99, "ota");
        f666b.put(100, MtkTvConfigTypeBase.S639_CFG_SUBTITLE_LANG_OFF);
        f666b.put(101, "oth");
        f666b.put(105, "ika");
        f666b.put(106, "glo");
        f666b.put(107, "nex");
        f666b.put(108, "mas");
        f666b.put(109, "vit");
        f666b.put(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_END, "sig");
        f666b.put(111, MtkTvConfigTypeBase.S639_CFG_LANG_IND);
        f666b.put(112, "brn");
    }

    public static String a(Context context) {
        if (context != null) {
            return Settings.Global.getString(context.getContentResolver(), "tv_user_setup_logo");
        }
        Log.e(f665a, "initBrandCode failed, context is null");
        return null;
    }

    public static String b(Context context) {
        return Settings.Global.getString(context.getContentResolver(), "tv_user_setup_country");
    }

    public static String c(Context context) {
        return HisenseTvAPI.getInstance(context).getEmmcFwrev();
    }

    public static int[] d() {
        return new int[15];
    }

    public static int[] e(Context context) {
        return HisenseTvAPI.getInstance(context).getEmmcLifeTime();
    }

    public static String f(Context context) {
        return HisenseTvAPI.getInstance(context).getEmmcCid();
    }

    public static long g(Context context) {
        return HisenseTvAPI.getInstance(context).getEmmcWrittenSize();
    }

    public static String h(Context context) {
        String strSubstring;
        HisenseTvAPI hisenseTvAPI = HisenseTvAPI.getInstance(context);
        String machineName = hisenseTvAPI.getMachineName(MtkTvConfigTypeBase.CFG_FACTORY_FAC_MACHINE_NAME);
        if (TextUtils.isEmpty(machineName)) {
            Log.e(f665a, "initMachineType failed, machine name is empty");
            return null;
        }
        String softwareVersion = hisenseTvAPI.getSoftwareVersion();
        if (TextUtils.isEmpty(softwareVersion)) {
            Log.e(f665a, "initMachineType failed, sw version is empty");
            return null;
        }
        try {
            strSubstring = softwareVersion.substring(1, softwareVersion.indexOf("."));
        } catch (Exception unused) {
            strSubstring = null;
        }
        if (strSubstring == null || strSubstring.length() != 4) {
            Log.e(f665a, "initMachineType failed, sw version invalid format");
            return null;
        }
        String strA = a("ro.vt.product.plmnumber", null);
        Log.i(f665a, "plm number: " + strA);
        if (TextUtils.isEmpty(strA)) {
            Log.e(f665a, "initMachineType failed, plm number is empty");
            return null;
        }
        if (strA.length() >= 3) {
            return b.a.a.a.a.a(machineName, strSubstring, strA.substring(strA.length() - 3));
        }
        Log.e(f665a, "initMachineType failed, invalid plm number");
        return null;
    }

    public static String i(Context context) {
        return HisenseTvAPI.getInstance(context).getMachineName(MtkTvConfigTypeBase.CFG_FACTORY_FAC_MACHINE_NAME);
    }

    public static long j(Context context) {
        return HisenseTvAPI.getInstance(context).getEmmcWrittenSizeByDriver();
    }

    public static int k(Context context) {
        return HisenseTvAPI.getInstance(context).getStrPowerOffCount();
    }

    public static int l(Context context) {
        return HisenseTvAPI.getInstance(context).getTvRunTime(MtkTvConfigTypeBase.CFG_FACTORY_RUN_TIME);
    }

    public static String m(Context context) {
        String softwareVersion = HisenseTvAPI.getInstance(context).getSoftwareVersion();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
        long j = Build.TIME;
        b.a();
        long jLongValue = 0;
        try {
            jLongValue = ((Long) b.f672f.invoke(b.f668b, "ro.data.build.date.utc", 0L)).longValue();
        } catch (Exception e2) {
            Log.e(b.f667a, e2.toString());
        }
        long j2 = jLongValue * 1000;
        if (j < j2) {
            j = j2;
        }
        calendar.setTime(new Date(j));
        String str = String.format("%s%02d%02d", String.valueOf((char) ((Math.abs(calendar.get(1) - 2010) % 26) + 65)), Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(calendar.get(5)));
        if (!TextUtils.isEmpty(softwareVersion)) {
            return b.a.a.a.a.a(softwareVersion, ".", str);
        }
        Log.e(f665a, "getTvVersion failed, sw version is empty");
        return null;
    }

    public static boolean n(Context context) {
        return HisenseTvAPI.getInstance(context).getScreenLessMode() == 1;
    }

    public static String b() {
        return a("ro.product.board", "");
    }

    public static String d(Context context) {
        return HisenseTvAPI.getInstance(context).getEmmcHwrev();
    }

    public static String a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class, String.class).invoke(cls, str, str2);
        } catch (Exception e2) {
            b.a.a.a.a.c("reflect setPlayerType exception", e2, f665a);
            return str2;
        }
    }

    public static String c() {
        try {
            Class.forName("android.os.SystemProperties").getMethod("get", String.class);
            String strA = a("ro.vt.product.model", "");
            String strA2 = a("ro.vt.build.softversion", "");
            if (!TextUtils.isEmpty(strA2)) {
                strA2 = strA2.substring(1, strA2.indexOf("."));
            }
            String strA3 = a("ro.vt.product.plmnumber", "");
            if (!TextUtils.isEmpty(strA3)) {
                strA3 = strA3.substring(strA3.length() - 3);
            }
            Log.i(f665a, "softVersion=" + strA2 + ",plmNumber=" + strA3);
            return strA + strA2 + strA3;
        } catch (Exception e2) {
            Log.e(f665a, e2.toString());
            return "";
        }
    }

    public static String e() {
        return a("ro.vt.market", "");
    }

    public static String f() {
        return a("ro.domain.name", "na.hismarttv.com");
    }

    public static String g() {
        return a("persist.sys.tvusage.switch", "0");
    }

    public static String a() {
        return a("ro.product.capabilitycode", "0");
    }
}
