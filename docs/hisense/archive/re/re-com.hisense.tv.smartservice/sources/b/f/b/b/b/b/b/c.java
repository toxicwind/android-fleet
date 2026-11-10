package b.f.b.b.b.b.b;

import android.content.Context;
import androidx.annotation.NonNull;
import b.f.b.b.b.b.e.d;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f720a;

    static {
        c.class.getSimpleName();
    }

    public c(@NonNull Context context) {
        this.f720a = context.getApplicationContext();
    }

    public void a(Map<String, Object> map) {
        if (map == null) {
            return;
        }
        final String string = map.get("processName").toString();
        Integer numValueOf = Integer.valueOf(map.get("pid").toString());
        final String string2 = map.get("processStats").toString();
        final int iIntValue = numValueOf.intValue();
        b.f.b.b.a.b.c.a().execute(new Runnable() { // from class: b.f.b.b.b.b.b.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f712a.a(string, iIntValue, string2);
            }
        });
    }

    public void b(Map<String, Object> map) {
        if (map == null) {
            return;
        }
        final String string = map.get("processName").toString();
        Integer numValueOf = Integer.valueOf(map.get("pid").toString());
        final String string2 = map.get("stackTrace").toString();
        final int iIntValue = numValueOf.intValue();
        b.f.b.b.a.b.c.a().execute(new Runnable() { // from class: b.f.b.b.b.b.b.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f716a.b(string, iIntValue, string2);
            }
        });
    }

    public /* synthetic */ void a(String str, int i, String str2) {
        HashMap map = new HashMap();
        map.put("processName", str);
        if (b.f.b.b.b.b.a.c.a().b()) {
            d.a(this.f720a).b("app_anr", map);
            b.f.b.b.b.b.d.a.a aVar = new b.f.b.b.b.b.d.a.a();
            aVar.f724a.put("pid", Integer.valueOf(i));
            aVar.f724a.put("processName", str);
            aVar.f724a.put("processStates", str2);
            b.f.b.b.b.b.d.a.a(this.f720a, aVar);
        }
    }

    public /* synthetic */ void b(String str, int i, String str2) {
        HashMap map = new HashMap();
        map.put("processName", str);
        map.put("pid", String.valueOf(i));
        if (b.f.b.b.b.b.a.c.a().d()) {
            d.a(this.f720a).b("app_crash", map);
            b.f.b.b.b.b.d.a.b bVar = new b.f.b.b.b.b.d.a.b();
            bVar.f724a.put("pid", Integer.valueOf(i));
            bVar.f724a.put("stackTrace", str2);
            bVar.f724a.put("processName", str);
            b.f.b.b.b.b.d.b.a(this.f720a, bVar);
        }
    }
}
