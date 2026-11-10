package com.hisense.tv.smartsense.core.base;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public interface BroadcastDelegate {
    void onRegisterBroadcastReceiver(Context context);

    void onUnRegisterBroadcastReceiver(Context context);
}
