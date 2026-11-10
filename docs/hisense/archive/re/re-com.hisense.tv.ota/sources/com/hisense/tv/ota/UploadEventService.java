package com.hisense.tv.ota;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public class UploadEventService extends IntentService {
    public UploadEventService() {
        super("UploadEventService");
    }

    private void a(String str, Context context) {
        new Thread(new S(str, context)).start();
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            a(intent.getStringExtra("event"), this);
        }
    }
}
