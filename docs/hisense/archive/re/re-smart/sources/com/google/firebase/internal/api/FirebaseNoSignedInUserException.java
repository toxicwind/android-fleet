package com.google.firebase.internal.api;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.FirebaseException;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public class FirebaseNoSignedInUserException extends FirebaseException {
    @KeepForSdk
    public FirebaseNoSignedInUserException(@NonNull String str) {
        super(str);
    }
}
