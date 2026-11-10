package com.hisense.udpserver;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import b.b.a.d;
import b.b.a.e;

/* JADX INFO: loaded from: classes.dex */
public class AIDLUdpScanService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Handler f11a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public HandlerThread f12b;
    public final d c = new a();

    public class a extends d {
        public a() {
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        e.a("AIDLUdpScanService", String.format("on bind,intent = %s", intent.toString()));
        return this.c;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 0;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        StringBuilder sbC = b.a.a.a.a.c("onUnbind()   mHandlerThread:");
        sbC.append(this.f12b);
        e.a("AIDLUdpScanService", sbC.toString());
        return super.onUnbind(intent);
    }
}
