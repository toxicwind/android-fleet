package com.hisense.tv.ota;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.hmct.cloud.sdk.bean.global.ErrorInfo;
import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class S implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f20a;
    public Context b;

    public S(String str, Context context) {
        this.b = context;
        this.f20a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        Log.i("UploadEventService", "upload event: " + this.f20a);
        if (TextUtils.isEmpty(this.f20a)) {
            Log.w("UploadEventService", "event is empty, won't upload");
            return;
        }
        String strB = com.hisense.tv.ota.W.d.b(this.b);
        if (TextUtils.isEmpty(strB)) {
            str = "uploadEvent failed, token is empty";
        } else {
            a.b.a.a.c.c cVarC = com.hisense.tv.ota.W.d.c(strB, this.b);
            if (cVarC != null) {
                String strA = com.hisense.tv.ota.W.d.a(2, this.b);
                HashMap map = new HashMap();
                map.put("eventCode", this.f20a);
                map.put("accessToken", strB);
                for (int i = 0; i < 2; i++) {
                    ReplyInfo replyInfoG = cVarC.g(strA, true, map);
                    if (replyInfoG == null) {
                        Log.e("UploadEventService", "uploadEvent failed, reply info is null");
                        SystemClock.sleep(3000L);
                    } else if (replyInfoG.b() != 0) {
                        ErrorInfo errorInfoA = replyInfoG.a();
                        Log.e("UploadEventService", errorInfoA == null ? "uploadEvent failed, error info is empty" : "uploadEvent failed" + String.format("\nErrorCode:%s\nErrorName:%s", errorInfoA.a(), errorInfoA.b()));
                        SystemClock.sleep(3000L);
                        Log.e("UploadEventService", "uploadEvent failed uploadtimes " + i);
                    } else if (C0000a.a()) {
                        Log.d("UploadEventService", "uploadEvent successfully");
                        return;
                    }
                }
                return;
            }
            str = "uploadEvent failed, hicloud upgrade service is empty";
        }
        Log.e("UploadEventService", str);
    }
}
