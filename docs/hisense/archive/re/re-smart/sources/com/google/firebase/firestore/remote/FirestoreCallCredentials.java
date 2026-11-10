package com.google.firebase.firestore.remote;

import c.a.AbstractC0218e;
import c.a.C0219ea;
import c.a.za;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.remote.FirestoreCallCredentials;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import com.google.firebase.storage.network.NetworkRequest;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class FirestoreCallCredentials extends AbstractC0218e {
    public static final String LOG_TAG = "FirestoreCallCredentials";
    public final CredentialsProvider<String> appCheckProvider;
    public final CredentialsProvider<User> authProvider;
    public static final C0219ea.e<String> AUTHORIZATION_HEADER = C0219ea.e.a(HttpHeaders.AUTHORIZATION, C0219ea.f1798a);
    public static final C0219ea.e<String> X_FIREBASE_APPCHECK = C0219ea.e.a(NetworkRequest.X_FIREBASE_APPCHECK, C0219ea.f1798a);

    public FirestoreCallCredentials(CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2) {
        this.authProvider = credentialsProvider;
        this.appCheckProvider = credentialsProvider2;
    }

    public static /* synthetic */ void a(Task task, AbstractC0218e.a aVar, Task task2, Task task3) {
        C0219ea c0219ea = new C0219ea();
        if (task.isSuccessful()) {
            String str = (String) task.getResult();
            Logger.debug(LOG_TAG, "Successfully fetched auth token.", new Object[0]);
            if (str != null) {
                c0219ea.a(AUTHORIZATION_HEADER, "Bearer " + str);
            }
        } else {
            Exception exception = task.getException();
            if (exception instanceof FirebaseApiNotAvailableException) {
                Logger.debug(LOG_TAG, "Firebase Auth API not available, not using authentication.", new Object[0]);
            } else {
                if (!(exception instanceof FirebaseNoSignedInUserException)) {
                    Logger.warn(LOG_TAG, "Failed to get auth token: %s.", exception);
                    aVar.a(za.h.b(exception));
                    return;
                }
                Logger.debug(LOG_TAG, "No user signed in, not using authentication.", new Object[0]);
            }
        }
        if (task2.isSuccessful()) {
            String str2 = (String) task2.getResult();
            if (str2 != null && !str2.isEmpty()) {
                Logger.debug(LOG_TAG, "Successfully fetched AppCheck token.", new Object[0]);
                c0219ea.a(X_FIREBASE_APPCHECK, str2);
            }
        } else {
            Exception exception2 = task2.getException();
            if (!(exception2 instanceof FirebaseApiNotAvailableException)) {
                Logger.warn(LOG_TAG, "Failed to get AppCheck token: %s.", exception2);
                aVar.a(za.h.b(exception2));
                return;
            }
            Logger.debug(LOG_TAG, "Firebase AppCheck API not available.", new Object[0]);
        }
        aVar.a(c0219ea);
    }

    @Override // c.a.AbstractC0218e
    public void applyRequestMetadata(AbstractC0218e.b bVar, Executor executor, final AbstractC0218e.a aVar) {
        final Task<String> token = this.authProvider.getToken();
        final Task<String> token2 = this.appCheckProvider.getToken();
        Tasks.whenAll((Task<?>[]) new Task[]{token, token2}).addOnCompleteListener(executor, new OnCompleteListener() { // from class: b.e.b.d.f.k
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FirestoreCallCredentials.a(token, aVar, token2, task);
            }
        });
    }

    @Override // c.a.AbstractC0218e
    public void thisUsesUnstableApi() {
    }
}
