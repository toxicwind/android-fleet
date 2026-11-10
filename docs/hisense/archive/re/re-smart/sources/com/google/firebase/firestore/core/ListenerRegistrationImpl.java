package com.google.firebase.firestore.core;

import com.google.firebase.firestore.ListenerRegistration;

/* JADX INFO: loaded from: classes.dex */
public class ListenerRegistrationImpl implements ListenerRegistration {
    public final AsyncEventListener<ViewSnapshot> asyncEventListener;
    public final FirestoreClient client;
    public final QueryListener queryListener;

    public ListenerRegistrationImpl(FirestoreClient firestoreClient, QueryListener queryListener, AsyncEventListener<ViewSnapshot> asyncEventListener) {
        this.client = firestoreClient;
        this.queryListener = queryListener;
        this.asyncEventListener = asyncEventListener;
    }

    @Override // com.google.firebase.firestore.ListenerRegistration
    public void remove() {
        this.asyncEventListener.mute();
        this.client.stopListening(this.queryListener);
    }
}
