package com.google.firebase.firestore.core;

import androidx.annotation.Nullable;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class QueryListener {
    public final EventListener<ViewSnapshot> listener;
    public final EventManager.ListenOptions options;
    public final Query query;

    @Nullable
    public ViewSnapshot snapshot;
    public boolean raisedInitialEvent = false;
    public OnlineState onlineState = OnlineState.UNKNOWN;

    public QueryListener(Query query, EventManager.ListenOptions listenOptions, EventListener<ViewSnapshot> eventListener) {
        this.query = query;
        this.listener = eventListener;
        this.options = listenOptions;
    }

    private void raiseInitialEvent(ViewSnapshot viewSnapshot) {
        Assert.hardAssert(!this.raisedInitialEvent, "Trying to raise initial event for second time", new Object[0]);
        ViewSnapshot viewSnapshotFromInitialDocuments = ViewSnapshot.fromInitialDocuments(viewSnapshot.getQuery(), viewSnapshot.getDocuments(), viewSnapshot.getMutatedKeys(), viewSnapshot.isFromCache(), viewSnapshot.excludesMetadataChanges());
        this.raisedInitialEvent = true;
        this.listener.onEvent(viewSnapshotFromInitialDocuments, null);
    }

    private boolean shouldRaiseEvent(ViewSnapshot viewSnapshot) {
        if (!viewSnapshot.getChanges().isEmpty()) {
            return true;
        }
        ViewSnapshot viewSnapshot2 = this.snapshot;
        boolean z = (viewSnapshot2 == null || viewSnapshot2.hasPendingWrites() == viewSnapshot.hasPendingWrites()) ? false : true;
        if (viewSnapshot.didSyncStateChange() || z) {
            return this.options.includeQueryMetadataChanges;
        }
        return false;
    }

    private boolean shouldRaiseInitialEvent(ViewSnapshot viewSnapshot, OnlineState onlineState) {
        Assert.hardAssert(!this.raisedInitialEvent, "Determining whether to raise first event but already had first event.", new Object[0]);
        if (!viewSnapshot.isFromCache()) {
            return true;
        }
        boolean z = !onlineState.equals(OnlineState.OFFLINE);
        if (!this.options.waitForSyncWhenOnline || !z) {
            return !viewSnapshot.getDocuments().isEmpty() || onlineState.equals(OnlineState.OFFLINE);
        }
        Assert.hardAssert(viewSnapshot.isFromCache(), "Waiting for sync, but snapshot is not from cache", new Object[0]);
        return false;
    }

    public Query getQuery() {
        return this.query;
    }

    public void onError(FirebaseFirestoreException firebaseFirestoreException) {
        this.listener.onEvent(null, firebaseFirestoreException);
    }

    public boolean onOnlineStateChanged(OnlineState onlineState) {
        this.onlineState = onlineState;
        ViewSnapshot viewSnapshot = this.snapshot;
        if (viewSnapshot == null || this.raisedInitialEvent || !shouldRaiseInitialEvent(viewSnapshot, onlineState)) {
            return false;
        }
        raiseInitialEvent(this.snapshot);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0085  */
    public boolean onViewSnapshot(ViewSnapshot viewSnapshot) {
        boolean z = true;
        Assert.hardAssert(!viewSnapshot.getChanges().isEmpty() || viewSnapshot.didSyncStateChange(), "We got a new snapshot with no changes?", new Object[0]);
        if (!this.options.includeDocumentMetadataChanges) {
            ArrayList arrayList = new ArrayList();
            for (DocumentViewChange documentViewChange : viewSnapshot.getChanges()) {
                if (documentViewChange.getType() != DocumentViewChange.Type.METADATA) {
                    arrayList.add(documentViewChange);
                }
            }
            viewSnapshot = new ViewSnapshot(viewSnapshot.getQuery(), viewSnapshot.getDocuments(), viewSnapshot.getOldDocuments(), arrayList, viewSnapshot.isFromCache(), viewSnapshot.getMutatedKeys(), viewSnapshot.didSyncStateChange(), true);
        }
        if (this.raisedInitialEvent) {
            if (shouldRaiseEvent(viewSnapshot)) {
                this.listener.onEvent(viewSnapshot, null);
            } else {
                z = false;
            }
        } else if (shouldRaiseInitialEvent(viewSnapshot, this.onlineState)) {
            raiseInitialEvent(viewSnapshot);
        } else {
            z = false;
        }
        this.snapshot = viewSnapshot;
        return z;
    }
}
