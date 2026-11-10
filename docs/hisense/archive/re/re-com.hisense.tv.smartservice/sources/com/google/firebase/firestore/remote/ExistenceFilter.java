package com.google.firebase.firestore.remote;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class ExistenceFilter {
    public final int count;

    public ExistenceFilter(int i) {
        this.count = i;
    }

    public int getCount() {
        return this.count;
    }

    public String toString() {
        StringBuilder sbA = a.a("ExistenceFilter{count=");
        sbA.append(this.count);
        sbA.append('}');
        return sbA.toString();
    }
}
