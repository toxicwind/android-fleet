package com.google.firebase.firestore;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseAppLifecycleListener;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.inject.Deferred;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class FirestoreMultiDbComponent implements FirebaseAppLifecycleListener, FirebaseFirestore.InstanceRegistry {
    public final FirebaseApp app;
    public final Deferred<InternalAppCheckTokenProvider> appCheckProvider;
    public final Deferred<InternalAuthProvider> authProvider;
    public final Context context;
    public final Map<String, FirebaseFirestore> instances = new HashMap();
    public final GrpcMetadataProvider metadataProvider;

    public FirestoreMultiDbComponent(@NonNull Context context, @NonNull FirebaseApp firebaseApp, @NonNull Deferred<InternalAuthProvider> deferred, @NonNull Deferred<InternalAppCheckTokenProvider> deferred2, @Nullable GrpcMetadataProvider grpcMetadataProvider) {
        this.context = context;
        this.app = firebaseApp;
        this.authProvider = deferred;
        this.appCheckProvider = deferred2;
        this.metadataProvider = grpcMetadataProvider;
        this.app.addLifecycleEventListener(this);
    }

    @NonNull
    public synchronized FirebaseFirestore get(@NonNull String str) {
        FirebaseFirestore firebaseFirestoreNewInstance;
        firebaseFirestoreNewInstance = this.instances.get(str);
        if (firebaseFirestoreNewInstance == null) {
            firebaseFirestoreNewInstance = FirebaseFirestore.newInstance(this.context, this.app, this.authProvider, this.appCheckProvider, str, this, this.metadataProvider);
            this.instances.put(str, firebaseFirestoreNewInstance);
        }
        return firebaseFirestoreNewInstance;
    }

    @Override // com.google.firebase.FirebaseAppLifecycleListener
    public synchronized void onDeleted(String str, FirebaseOptions firebaseOptions) {
        for (Map.Entry entry : new ArrayList(this.instances.entrySet())) {
            ((FirebaseFirestore) entry.getValue()).terminate();
            Assert.hardAssert(!this.instances.containsKey(entry.getKey()), "terminate() should have removed its entry from `instances` for key: %s", entry.getKey());
        }
    }

    @Override // com.google.firebase.firestore.FirebaseFirestore.InstanceRegistry
    public synchronized void remove(@NonNull String str) {
        this.instances.remove(str);
    }
}
