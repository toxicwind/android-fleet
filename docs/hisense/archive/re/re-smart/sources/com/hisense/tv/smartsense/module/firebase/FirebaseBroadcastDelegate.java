package com.hisense.tv.smartsense.module.firebase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import b.f.b.b.b.b.c;
import b.f.b.b.b.b.d;
import b.f.b.b.b.b.d.a.a;
import b.f.b.b.b.b.d.a.b;
import com.hisense.tv.smartsense.core.base.BroadcastDelegate;
import com.hisense.tv.smartsense.module.firebase.FirebaseBroadcastDelegate;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class FirebaseBroadcastDelegate implements BroadcastDelegate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1962a = "FirebaseBroadcastDelegate";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f1963b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BroadcastReceiver f1964c = new c(this);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BroadcastReceiver f1965d = new d(this);

    @Override // com.hisense.tv.smartsense.core.base.BroadcastDelegate
    public void onRegisterBroadcastReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.hisense.tv.ACTIVITY_STATE.appCrash");
        intentFilter.addAction("com.hisense.tv.ACTIVITY_STATE.appAnr");
        context.registerReceiver(this.f1965d, intentFilter, "com.hisense.tv.permission.ACTIVITY_STATE", null);
        a(context);
    }

    @Override // com.hisense.tv.smartsense.core.base.BroadcastDelegate
    public void onUnRegisterBroadcastReceiver(Context context) {
        try {
            context.unregisterReceiver(this.f1965d);
            b(context);
        } catch (Exception e2) {
            Log.e(f1962a, e2.toString());
        }
    }

    public void a(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        context.registerReceiver(this.f1964c, intentFilter);
    }

    public void b(Context context) {
        try {
            context.unregisterReceiver(this.f1964c);
        } catch (Exception e2) {
            Log.e(f1962a, e2.toString());
        }
    }

    public final void b(final Context context, final Bundle bundle) {
        b.f.b.b.a.b.c.a().execute(new Runnable() { // from class: b.f.b.b.b.b.a
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseBroadcastDelegate.b(bundle, context);
            }
        });
    }

    public static /* synthetic */ void b(Bundle bundle, Context context) {
        if (bundle == null) {
            return;
        }
        HashMap map = new HashMap();
        String string = bundle.getString("processName");
        int i = bundle.getInt("pid");
        String string2 = bundle.getString("stackTrace");
        String string3 = bundle.getString("shortMsg");
        long j = bundle.getLong("timeMills");
        map.put("processName", string);
        map.put("pid", String.valueOf(i));
        map.put("shortMsg", string3);
        map.put("timeMills", String.valueOf(j));
        if (b.f.b.b.b.b.a.c.a().d()) {
            b.f.b.b.b.b.e.d.a(context).b("app_crash", map);
            b bVar = new b();
            bVar.f724a.put("pid", Integer.valueOf(i));
            bVar.f724a.put("stackTrace", string2);
            bVar.f724a.put("processName", string);
            b.f.b.b.b.b.d.b.a(context, bVar);
        }
    }

    public final void a(final Context context, final Bundle bundle) {
        b.f.b.b.a.b.c.a().execute(new Runnable() { // from class: b.f.b.b.b.b.b
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseBroadcastDelegate.a(bundle, context);
            }
        });
    }

    public static /* synthetic */ void a(Bundle bundle, Context context) {
        if (bundle == null) {
            return;
        }
        HashMap map = new HashMap();
        String string = bundle.getString("processName");
        int i = bundle.getInt("pid");
        String string2 = bundle.getString("processStats");
        map.put("processName", string);
        map.put("pid", String.valueOf(i));
        if (b.f.b.b.b.b.a.c.a().b()) {
            b.f.b.b.b.b.e.d.a(context).b("app_anr", map);
            a aVar = new a();
            aVar.f724a.put("pid", Integer.valueOf(i));
            aVar.f724a.put("processName", string);
            aVar.f724a.put("processStates", string2);
            b.f.b.b.b.b.d.a.a(context, aVar);
        }
    }
}
