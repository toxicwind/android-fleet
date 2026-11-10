package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.Document;

/* JADX INFO: loaded from: classes.dex */
public abstract class Filter {
    public abstract String getCanonicalId();

    public abstract boolean matches(Document document);
}
