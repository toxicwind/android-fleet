package com.hisense.tv.smartsense.module.firebase;

import android.content.Context;
import b.b.a.c.a;
import b.b.a.e;
import b.f.b.b.b.b.a.c;
import com.hisense.tv.smartsense.core.base.AppDelegate;

/* JADX INFO: loaded from: classes2.dex */
public class FirebaseApp implements AppDelegate {
    @Override // com.hisense.tv.smartsense.core.base.AppDelegate
    public void onStart(Context context) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        a.a(context);
        c.a().e();
        e.f34a.a(String.format("Module Firebase has started,cost %d ms", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
    }

    @Override // com.hisense.tv.smartsense.core.base.AppDelegate
    public void onStop(Context context) {
    }
}
