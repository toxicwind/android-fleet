package com.google.firebase.appcheck.interop;

import androidx.annotation.NonNull;
import com.google.firebase.appcheck.AppCheckTokenResult;

/* JADX INFO: loaded from: classes.dex */
public interface AppCheckTokenListener {
    void onAppCheckTokenChanged(@NonNull AppCheckTokenResult appCheckTokenResult);
}
