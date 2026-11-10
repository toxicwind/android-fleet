package com.google.firebase.firestore.core;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ViewChange {
    public final List<LimboDocumentChange> limboChanges;
    public final ViewSnapshot snapshot;

    public ViewChange(ViewSnapshot viewSnapshot, List<LimboDocumentChange> list) {
        this.snapshot = viewSnapshot;
        this.limboChanges = list;
    }

    public List<LimboDocumentChange> getLimboChanges() {
        return this.limboChanges;
    }

    public ViewSnapshot getSnapshot() {
        return this.snapshot;
    }
}
