package com.google.firebase.firestore.core;

/* JADX INFO: loaded from: classes.dex */
public final class QueryView {
    public final Query query;
    public final int targetId;
    public final View view;

    public QueryView(Query query, int i, View view) {
        this.query = query;
        this.targetId = i;
        this.view = view;
    }

    public Query getQuery() {
        return this.query;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public View getView() {
        return this.view;
    }
}
