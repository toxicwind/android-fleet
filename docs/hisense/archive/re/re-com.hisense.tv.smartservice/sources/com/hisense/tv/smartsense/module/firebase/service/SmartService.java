package com.hisense.tv.smartsense.module.firebase.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.Nullable;
import b.b.a.c.a;
import b.f.b.b.b.b.a.c;

/* JADX INFO: loaded from: classes2.dex */
public class SmartService extends IntentService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1969a = "SmartService";

    public SmartService() {
        super(f1969a);
    }

    @Override // android.app.IntentService
    public void onHandleIntent(@Nullable Intent intent) {
        Log.d(f1969a, "Smartsevice execute...");
        a.a(getApplicationContext());
        c.a().e();
    }
}
