package b.f.b.b.b.b.e;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f730a = "d";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile d f731b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f732c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public FirebaseAnalytics f733d;

    public d(@NonNull Context context) {
        this.f732c = context.getApplicationContext();
        this.f733d = FirebaseAnalytics.getInstance(this.f732c);
    }

    public static d a(Context context) {
        if (f731b == null) {
            synchronized (d.class) {
                if (f731b == null) {
                    f731b = new d(context);
                }
            }
        }
        return f731b;
    }

    public void b(String str, Map map) {
        if (TextUtils.isEmpty(str)) {
            Log.i(f730a, "eventName is null");
            return;
        }
        if (map == null || map.size() < 1) {
            Log.i(f730a, "map is null!");
            return;
        }
        String strA = b.f.b.a.a.a("persist.sys.tvusage.switch", "0");
        if (strA == null || strA.equals("0")) {
            Log.e(f730a, "usage state is off!!can not report log.");
            return;
        }
        if (map.isEmpty()) {
            return;
        }
        String str2 = f730a;
        StringBuilder sbB = b.a.a.a.a.b("The Firebase Log [", str, "] ever map sent out is:");
        sbB.append(map.toString());
        Log.d(str2, sbB.toString());
        Bundle bundle = new Bundle();
        map.forEach(new a(bundle));
        this.f733d.logEvent(str, bundle);
        Log.d(f730a, "The Firebase Log send out;");
    }

    public void a(String str, Map map) {
        if (TextUtils.isEmpty(str)) {
            Log.i(f730a, "eventName is null");
            return;
        }
        if (map != null && map.size() >= 1) {
            if (!b.f.b.b.b.b.a.c.a().c()) {
                Log.e(f730a, "remoteEnable is disable!!can not report log.");
                return;
            }
            String strA = b.f.b.a.a.a("persist.sys.tvusage.switch", "0");
            if (strA != null && !strA.equals("0")) {
                if (map.isEmpty()) {
                    return;
                }
                String str2 = f730a;
                StringBuilder sbB = b.a.a.a.a.b("The Firebase Log [", str, "] map sent out is:");
                sbB.append(map.toString());
                Log.d(str2, sbB.toString());
                Bundle bundle = new Bundle();
                map.forEach(new a(bundle));
                this.f733d.logEvent(str, bundle);
                Log.d(f730a, "The Firebase Log send out;");
                return;
            }
            Log.e(f730a, "usage state is off!!can not report log.");
            return;
        }
        Log.i(f730a, "map is null!");
    }

    public static /* synthetic */ void a(Bundle bundle, Object obj, Object obj2) {
        String string;
        if (obj2 == null) {
            string = "";
        } else {
            try {
                string = obj2.toString();
            } catch (Exception e2) {
                Log.e(f730a, e2.toString());
                return;
            }
        }
        bundle.putString(obj.toString(), string);
    }
}
