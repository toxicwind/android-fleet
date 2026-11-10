package com.google.firebase.auth;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseException;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseAuthException extends FirebaseException {
    public final String zza;

    public FirebaseAuthException(@NonNull String str, @NonNull String str2) {
        super(str2);
        Preconditions.checkNotEmpty(str);
        this.zza = str;
    }

    @NonNull
    public String getErrorCode() {
        return this.zza;
    }
}
