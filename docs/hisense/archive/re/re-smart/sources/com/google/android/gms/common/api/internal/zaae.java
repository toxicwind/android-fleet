package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes.dex */
public final class zaae {
    public final ApiKey<?> zaft;
    public final TaskCompletionSource<Boolean> zafu = new TaskCompletionSource<>();

    public zaae(ApiKey<?> apiKey) {
        this.zaft = apiKey;
    }

    public final ApiKey<?> getApiKey() {
        return this.zaft;
    }

    public final TaskCompletionSource<Boolean> zaaj() {
        return this.zafu;
    }
}
