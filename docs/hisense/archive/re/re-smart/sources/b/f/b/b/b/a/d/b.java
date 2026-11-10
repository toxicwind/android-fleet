package b.f.b.b.b.a.d;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.hmct.cloud.sdk.utils.DeviceConfig;
import com.ju.lib.datareport.ReportManager;
import com.ju.lib.datareport.Reporter;
import com.ju.lib.datareport.StrategyBean;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile b f694a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f695b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f696c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f697d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f698e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f699f;
    public String g;
    public Context h;

    public b(Context context) {
        this.f695b = "";
        this.f696c = "";
        this.f697d = "";
        this.f698e = "";
        this.f699f = "";
        this.g = "";
        this.h = context.getApplicationContext();
        this.f696c = b.f.b.a.a.b(context);
        this.f697d = b.f.b.a.a.b();
        this.f698e = b.f.b.a.a.a(context);
        this.f699f = b.f.b.a.a.h(context);
        this.g = b.f.b.a.a.a();
        this.f695b = b.f.b.a.a.m(context);
    }

    public static b a(Context context) {
        if (f694a == null) {
            synchronized (b.class) {
                if (f694a == null) {
                    f694a = new b(context);
                }
            }
        }
        return f694a;
    }

    public String a(Map map) {
        String strSubstring;
        String strSubstring2;
        boolean z;
        if (map != null) {
            int i = 1;
            if (map.size() >= 1) {
                int rawOffset = TimeZone.getDefault().getRawOffset() / 3600000;
                String strValueOf = String.valueOf(System.currentTimeMillis() / 1000);
                String deviceId = DeviceConfig.getDeviceId(this.h);
                StringBuilder sbA = b.a.a.a.a.a("https://api-gps-");
                sbA.append(b.f.b.a.a.f());
                sbA.toString();
                String strA = b.a.a.a.a.a("https://", b.f.b.b.c.a.a.a(this.h, "jrnl", StrategyBean.Columns.POLICY, "policy-jrnl.intsmarthub.com"));
                Log.d("LogReportUtils", "u4 host=" + strA);
                String string = map.get("eventCode").toString();
                if (string.length() >= 3) {
                    strSubstring = string.substring(string.length() - 3, string.length());
                    strSubstring2 = string.substring(0, 3);
                } else {
                    Log.i("LogReportUtils", "eventcode's length is < 3");
                    strSubstring = "";
                    strSubstring2 = strSubstring;
                }
                Log.i("LogReportUtils", "deviceId is " + deviceId);
                if (deviceId == null) {
                    deviceId = "-1";
                }
                int i2 = Settings.Global.getInt(this.h.getContentResolver(), "use_mode", -1);
                if (i2 == 1 || i2 == 2) {
                    Log.d("LogReportUtils", "tvMode:StoreMode");
                    z = false;
                    i = 0;
                } else {
                    z = false;
                    if (Settings.Global.getInt(this.h.getContentResolver(), "hotel_mode", 0) == 1) {
                        Log.d("LogReportUtils", "tvMode:HotelMode");
                    } else {
                        i = 2;
                    }
                }
                String strValueOf2 = String.valueOf(i);
                map.put("Version", "3.0");
                map.put(Reporter.KEY_EVENT_TYPE, strSubstring2);
                map.put(Reporter.KEY_EVENT_POS, strSubstring);
                map.put("eventCode", string);
                map.put("DeviceId", deviceId);
                map.put("Os", "Android");
                map.put("Capabilitycode", this.g);
                map.put("Zone", Integer.toString(rawOffset));
                map.put("CountryCode", this.f696c);
                map.put("ChipPlatform", this.f697d);
                map.put("Brand", this.f698e);
                map.put("DeviceMsg", this.f699f);
                map.put("tvmode", strValueOf2);
                map.put("tvversion", this.f695b);
                map.put("eventtime", strValueOf);
                map.put("logstamp", "170");
                if (TextUtils.isEmpty(strSubstring)) {
                    Log.i("LogReportUtils", "eventcode is null...");
                    return "";
                }
                StringBuilder sbA2 = b.a.a.a.a.a("The Exception map sent out is:");
                sbA2.append(map.toString());
                Log.i("LogReportUtils", sbA2.toString());
                String strG = b.f.b.a.a.g();
                if (strG == null || strG.equals("0")) {
                    Log.e("LogReportUtils", "usage state is off!!");
                } else {
                    z = true;
                }
                if (!map.isEmpty() && z) {
                    try {
                        ReportManager.getInstance(this.h, deviceId, "2.0", strA).getReporter("1178487606").report((Map<String, String>) map);
                        Log.i("LogReportUtils", "HiReport end send out");
                    } catch (Exception e2) {
                        Log.e("LogReportUtils", "++++++++++++++++++++++++++++++++++++++++++++++++");
                        Log.e("LogReportUtils", "onReceive: app run Unexpected Exception inserting data", e2);
                        Log.e("LogReportUtils", "++++++++++++++++++++++++++++++++++++++++++++++++");
                    }
                }
                return map.toString();
            }
        }
        Log.i("LogReportUtils", "logMap is  null!");
        return "";
    }
}
