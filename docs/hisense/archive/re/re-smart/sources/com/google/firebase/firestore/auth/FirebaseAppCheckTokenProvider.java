package com.google.firebase.firestore.auth;

import android.annotation.SuppressLint;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.AppCheckTokenListener;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.firestore.auth.FirebaseAppCheckTokenProvider;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Listener;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public final class FirebaseAppCheckTokenProvider extends CredentialsProvider<String> {
    public static final String LOG_TAG = "FirebaseAppCheckTokenProvider";

    @Nullable
    @GuardedBy("this")
    public Listener<String> changeListener;

    @GuardedBy("this")
    public boolean forceRefresh;

    @Nullable
    @GuardedBy("this")
    public InternalAppCheckTokenProvider internalAppCheckTokenProvider;
    public final AppCheckTokenListener tokenListener = new AppCheckTokenListener() { // from class: b.e.b.d.a.a
        @Override // com.google.firebase.appcheck.interop.AppCheckTokenListener
        public final void onAppCheckTokenChanged(AppCheckTokenResult appCheckTokenResult) {
            this.f256a.a(appCheckTokenResult);
        }
    };

    @SuppressLint({"ProviderAssignment"})
    public FirebaseAppCheckTokenProvider(Deferred<InternalAppCheckTokenProvider> deferred) {
        deferred.whenAvailable(new Deferred.DeferredHandler() { // from class: b.e.b.d.a.c
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                this.f258a.a(provider);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onTokenChanged, reason: merged with bridge method [inline-methods] */
    public synchronized void a(@NonNull AppCheckTokenResult appCheckTokenResult) {
        if (appCheckTokenResult.getError() != null) {
            Logger.warn(LOG_TAG, "Error getting App Check token; using placeholder token instead. Error: " + appCheckTokenResult.getError(), new Object[0]);
        }
        if (this.changeListener != null) {
            this.changeListener.onValue(appCheckTokenResult.getToken());
        }
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized Task<String> getToken() {
        if (this.internalAppCheckTokenProvider == null) {
            return Tasks.forException(new FirebaseApiNotAvailableException("AppCheck is not available"));
        }
        Task<AppCheckTokenResult> token = this.internalAppCheckTokenProvider.getToken(this.forceRefresh);
        this.forceRefresh = false;
        return token.continueWithTask(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: b.e.b.d.a.b
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirebaseAppCheckTokenProvider.a(task);
            }
        });
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized void invalidateToken() {
        this.forceRefresh = true;
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized void removeChangeListener() {
        this.changeListener = null;
        if (this.internalAppCheckTokenProvider != null) {
            this.internalAppCheckTokenProvider.removeAppCheckTokenListener(this.tokenListener);
        }
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized void setChangeListener(@NonNull Listener<String> listener) {
        this.changeListener = listener;
    }

    public /* synthetic */ void a(Provider provider) {
        synchronized (this) {
            this.internalAppCheckTokenProvider = (InternalAppCheckTokenProvider) provider.get();
            if (this.internalAppCheckTokenProvider != null) {
                this.internalAppCheckTokenProvider.addAppCheckTokenListener(this.tokenListener);
            }
        }
    }

    public static /* synthetic */ Task a(Task task) {
        if (task.isSuccessful()) {
            return Tasks.forResult(((AppCheckTokenResult) task.getResult()).getToken());
        }
        return Tasks.forException(task.getException());
    }
}
