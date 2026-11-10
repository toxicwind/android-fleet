package com.hisense.tv.smartsense.core.base;

import android.app.Application;
import android.preference.PreferenceManager;
import android.util.Log;
import b.b.a.c.a;
import b.b.a.d;
import b.b.a.e;
import b.f.b.b.a.b.h;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class HApplication extends Application {
    public static final String TAG = "HApplication";

    public void initLogUtils() {
        d dVar = e.f35b;
        dVar.f29b = true;
        dVar.f32e = 2;
        dVar.f30c = "Smartsense";
        dVar.f31d = true;
        dVar.g = "%d{HH:mm:ss:SSS} %t %c{-5}";
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        h.a(getApplicationContext());
        a.f25a = getApplicationContext().getApplicationContext();
        a.f26b = PreferenceManager.getDefaultSharedPreferences(a.f25a);
        a.f27c = a.f26b.edit();
        initLogUtils();
        onModuleCreate();
    }

    public void onModuleCreate() {
        List listA = b.f.b.b.a.b.a.a(getApplicationContext(), IApplication.class, "com.hisense.tv.smartsense");
        Log.i(TAG, String.format("Smartsense onModuleCreate find %d IApplication,starting...", Integer.valueOf(listA.size())));
        long jCurrentTimeMillis = System.currentTimeMillis();
        Iterator it = listA.iterator();
        while (it.hasNext()) {
            try {
                ((IApplication) it.next()).onCreate(getApplicationContext());
            } catch (Exception e2) {
                Log.e(TAG, e2.toString());
            }
        }
        Log.i(TAG, String.format("All onModuleCreate has executed,cost %d ms", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
    }

    public void onModuleTerminate() {
        List listA = b.f.b.b.a.b.a.a(getApplicationContext(), IApplication.class, "com.hisense.tv.smartsense");
        Log.i(TAG, String.format("Smartsense onModuleTerminate find %d IApplication,starting...", Integer.valueOf(listA.size())));
        long jCurrentTimeMillis = System.currentTimeMillis();
        Iterator it = listA.iterator();
        while (it.hasNext()) {
            try {
                ((IApplication) it.next()).onTerminate(getApplicationContext());
            } catch (Exception e2) {
                Log.e(TAG, e2.toString());
            }
        }
        Log.i(TAG, String.format("All onModuleTerminate has executed,cost %d ms", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        onModuleTerminate();
    }
}
