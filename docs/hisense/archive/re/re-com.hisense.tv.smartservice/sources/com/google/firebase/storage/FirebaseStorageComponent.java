package com.google.firebase.storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.inject.Provider;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseStorageComponent {
    public final FirebaseApp app;

    @Nullable
    public final Provider<InternalAppCheckTokenProvider> appCheckProvider;

    @Nullable
    public final Provider<InternalAuthProvider> authProvider;
    public final Map<String, FirebaseStorage> instances = new HashMap();

    public FirebaseStorageComponent(@NonNull FirebaseApp firebaseApp, @Nullable Provider<InternalAuthProvider> provider, @Nullable Provider<InternalAppCheckTokenProvider> provider2) {
        this.app = firebaseApp;
        this.authProvider = provider;
        this.appCheckProvider = provider2;
    }

    @VisibleForTesting
    public synchronized void clearInstancesForTesting() {
        this.instances.clear();
    }

    @NonNull
    public synchronized FirebaseStorage get(@Nullable String str) {
        FirebaseStorage firebaseStorage;
        firebaseStorage = this.instances.get(str);
        if (firebaseStorage == null) {
            firebaseStorage = new FirebaseStorage(str, this.app, this.authProvider, this.appCheckProvider);
            this.instances.put(str, firebaseStorage);
        }
        return firebaseStorage;
    }
}
