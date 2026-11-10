package com.google.firebase.firestore.auth;

import android.annotation.SuppressLint;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Listener;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.InternalTokenResult;

/* JADX INFO: loaded from: classes.dex */
public final class FirebaseAuthCredentialsProvider extends CredentialsProvider<User> {
    public static final String LOG_TAG = "FirebaseAuthCredentialsProvider";

    @Nullable
    @GuardedBy("this")
    public Listener<User> changeListener;

    @GuardedBy("this")
    public boolean forceRefresh;
    public final IdTokenListener idTokenListener = new IdTokenListener() { // from class: b.e.b.d.a.f
        @Override // com.google.firebase.auth.internal.IdTokenListener
        public final void onIdTokenChanged(InternalTokenResult internalTokenResult) {
            this.f262a.a(internalTokenResult);
        }
    };

    @Nullable
    @GuardedBy("this")
    public InternalAuthProvider internalAuthProvider;

    @GuardedBy("this")
    public int tokenCounter;

    @SuppressLint({"ProviderAssignment"})
    public FirebaseAuthCredentialsProvider(Deferred<InternalAuthProvider> deferred) {
        deferred.whenAvailable(new Deferred.DeferredHandler() { // from class: b.e.b.d.a.e
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                this.f261a.a(provider);
            }
        });
    }

    private synchronized User getUser() {
        String uid;
        uid = this.internalAuthProvider == null ? null : this.internalAuthProvider.getUid();
        return uid != null ? new User(uid) : User.UNAUTHENTICATED;
    }

    private synchronized void onIdTokenChanged() {
        this.tokenCounter++;
        if (this.changeListener != null) {
            this.changeListener.onValue(getUser());
        }
    }

    public /* synthetic */ void a(InternalTokenResult internalTokenResult) {
        onIdTokenChanged();
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized Task<String> getToken() {
        if (this.internalAuthProvider == null) {
            return Tasks.forException(new FirebaseApiNotAvailableException("auth is not available"));
        }
        Task<GetTokenResult> accessToken = this.internalAuthProvider.getAccessToken(this.forceRefresh);
        this.forceRefresh = false;
        final int i = this.tokenCounter;
        return accessToken.continueWithTask(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: b.e.b.d.a.d
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f259a.a(i, task);
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
        if (this.internalAuthProvider != null) {
            this.internalAuthProvider.removeIdTokenListener(this.idTokenListener);
        }
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized void setChangeListener(@NonNull Listener<User> listener) {
        this.changeListener = listener;
        listener.onValue(getUser());
    }

    public /* synthetic */ void a(Provider provider) {
        synchronized (this) {
            this.internalAuthProvider = (InternalAuthProvider) provider.get();
            onIdTokenChanged();
            this.internalAuthProvider.addIdTokenListener(this.idTokenListener);
        }
    }

    public /* synthetic */ Task a(int i, Task task) {
        synchronized (this) {
            if (i != this.tokenCounter) {
                Logger.debug(LOG_TAG, "getToken aborted due to token change", new Object[0]);
                return getToken();
            }
            if (task.isSuccessful()) {
                return Tasks.forResult(((GetTokenResult) task.getResult()).getToken());
            }
            return Tasks.forException(task.getException());
        }
    }
}
