package com.hisense.tv.smartsense.core.base;

import android.content.Context;
import androidx.annotation.MainThread;

/* JADX INFO: loaded from: classes2.dex */
public interface IApplication {
    @MainThread
    void onCreate(Context context);

    @MainThread
    void onTerminate(Context context);
}
