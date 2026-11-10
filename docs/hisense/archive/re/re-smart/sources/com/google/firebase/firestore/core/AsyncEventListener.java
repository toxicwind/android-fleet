package com.google.firebase.firestore.core;

import androidx.annotation.Nullable;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class AsyncEventListener<T> implements EventListener<T> {
    public final EventListener<T> eventListener;
    public final Executor executor;
    public volatile boolean muted = false;

    public AsyncEventListener(Executor executor, EventListener<T> eventListener) {
        this.executor = executor;
        this.eventListener = eventListener;
    }

    public /* synthetic */ void a(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
        if (this.muted) {
            return;
        }
        this.eventListener.onEvent(obj, firebaseFirestoreException);
    }

    public void mute() {
        this.muted = true;
    }

    @Override // com.google.firebase.firestore.EventListener
    public void onEvent(@Nullable final T t, @Nullable final FirebaseFirestoreException firebaseFirestoreException) {
        this.executor.execute(new Runnable() { // from class: b.e.b.d.b.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f274a.a(t, firebaseFirestoreException);
            }
        });
    }
}
