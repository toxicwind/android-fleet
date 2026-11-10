package com.ju.lib.utils.glide;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface IGlideExceptionHandler {
    void onLoadByteArrayFailed(String str, int i, @Nullable Exception exc);

    void onLoadLocalResFailed(int i, int i2, @Nullable Exception exc);

    void onLoadUrlFailed(String str, int i, @Nullable Exception exc);
}
