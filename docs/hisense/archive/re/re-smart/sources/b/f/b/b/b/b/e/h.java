package b.f.b.b.b.b.e;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile h f736a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f737b = "h";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f738c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f739d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f740e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f741f;
    public String g;
    public String h;
    public Context i;

    public h(@NonNull Context context) {
        this.f738c = "";
        this.f739d = "";
        this.f740e = "";
        this.f741f = "";
        this.g = "";
        this.h = "";
        this.i = context.getApplicationContext();
        Context context2 = this.i;
        this.f739d = b.f.b.a.a.b(context2);
        this.f740e = b.f.b.a.a.b();
        this.f741f = b.f.b.a.a.a(context2);
        this.g = b.f.b.a.a.i(context2);
        this.h = b.f.b.a.a.a();
        this.f738c = b.f.b.a.a.m(context2);
    }

    public final String b() {
        int i = Settings.Global.getInt(this.i.getContentResolver(), "use_mode", -1);
        int i2 = 2;
        if (i == 1 || i == 2) {
            Log.d(f737b, "tvMode:StoreMode");
            i2 = 0;
        } else {
            if (Settings.Global.getInt(this.i.getContentResolver(), "hotel_mode", 0) == 1) {
                Log.d(f737b, "tvMode:HotelMode");
                i2 = 1;
            }
        }
        return String.valueOf(i2);
    }

    public static h a(Context context) {
        if (f736a == null) {
            synchronized (h.class) {
                if (f736a == null) {
                    f736a = new h(context);
                }
            }
        }
        return f736a;
    }

    public void a(String str, Map map) {
        if (TextUtils.isEmpty(str)) {
            Log.e(f737b, "tableName can not be null!");
            return;
        }
        if (map != null && map.size() >= 1) {
            String strA = b.f.b.a.a.a("persist.sys.tvusage.switch", "0");
            if (strA != null && !strA.equals("0")) {
                try {
                    int rawOffset = TimeZone.getDefault().getRawOffset() / 3600000;
                    String strValueOf = String.valueOf(System.currentTimeMillis() / 1000);
                    String serial = Build.getSerial();
                    String strB = b();
                    map.put("DeviceId", serial);
                    map.put("Os", "Android");
                    map.put("Capabilitycode", this.h);
                    map.put("Zone", Integer.toString(rawOffset));
                    map.put("CountryCode", this.f739d);
                    map.put("ChipPlatform", this.f740e);
                    map.put("Brand", this.f741f);
                    map.put("DeviceMsg", this.g);
                    map.put("tvmode", strB);
                    map.put("tvversion", this.f738c);
                    map.put("eventtime", strValueOf);
                    Log.d(f737b, "add [" + str + "]");
                    FirebaseFirestore.getInstance().collection(str).add(map).addOnSuccessListener(new g(this, str)).addOnFailureListener(new f(this, str));
                    return;
                } catch (Exception e2) {
                    Log.e(f737b, e2.toString());
                    return;
                }
            }
            Log.e(f737b, "usage state is off!!can not report log.");
            return;
        }
        Log.e(f737b, "map can not be null!");
    }
}
