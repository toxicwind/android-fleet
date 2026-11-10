package com.google.firebase.firestore;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public interface EventListener<T> {
    void onEvent(@Nullable T t, @Nullable FirebaseFirestoreException firebaseFirestoreException);
}
