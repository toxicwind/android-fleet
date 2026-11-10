package com.hisense.tv.smartsense.core.base;

import android.content.Context;
import androidx.annotation.MainThread;

/* JADX INFO: loaded from: classes2.dex */
public interface AppDelegate {
    @MainThread
    void onStart(Context context);

    @MainThread
    void onStop(Context context);
}
