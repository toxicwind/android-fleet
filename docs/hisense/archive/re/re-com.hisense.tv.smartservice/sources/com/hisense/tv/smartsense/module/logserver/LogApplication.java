package com.hisense.tv.smartsense.module.logserver;

import android.content.Context;
import android.util.Log;
import b.f.b.b.a.a.a;
import com.hisense.tv.smartsense.core.base.IApplication;
import com.ju.lib.datacommunication.network.http.HttpManager;

/* JADX INFO: loaded from: classes2.dex */
public class LogApplication implements IApplication {
    @Override // com.hisense.tv.smartsense.core.base.IApplication
    public void onCreate(Context context) {
        a aVar = new a();
        HttpManager.setupHttpApi(HttpManager.obtainHttpConfig().sslSocketFactory(aVar, aVar.f676d));
        Log.i("LogApplication", "HTTPS allowedCiphers init done.");
    }

    @Override // com.hisense.tv.smartsense.core.base.IApplication
    public void onTerminate(Context context) {
    }
}
