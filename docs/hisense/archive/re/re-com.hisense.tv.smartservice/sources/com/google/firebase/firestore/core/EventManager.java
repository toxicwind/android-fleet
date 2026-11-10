package com.google.firebase.firestore.core;

import c.a.za;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class EventManager implements SyncEngine.SyncEngineCallback {
    public final SyncEngine syncEngine;
    public final Set<EventListener<Void>> snapshotsInSyncListeners = new HashSet();
    public OnlineState onlineState = OnlineState.UNKNOWN;
    public final Map<Query, QueryListenersInfo> queries = new HashMap();

    public static class ListenOptions {
        public boolean includeDocumentMetadataChanges;
        public boolean includeQueryMetadataChanges;
        public boolean waitForSyncWhenOnline;
    }

    private static class QueryListenersInfo {
        public final List<QueryListener> listeners = new ArrayList();
        public int targetId;
        public ViewSnapshot viewSnapshot;
    }

    public EventManager(SyncEngine syncEngine) {
        this.syncEngine = syncEngine;
        syncEngine.setCallback(this);
    }

    private void raiseSnapshotsInSyncEvent() {
        Iterator<EventListener<Void>> it = this.snapshotsInSyncListeners.iterator();
        while (it.hasNext()) {
            it.next().onEvent(null, null);
        }
    }

    public int addQueryListener(QueryListener queryListener) {
        Query query = queryListener.getQuery();
        QueryListenersInfo queryListenersInfo = this.queries.get(query);
        boolean z = queryListenersInfo == null;
        if (z) {
            queryListenersInfo = new QueryListenersInfo();
            this.queries.put(query, queryListenersInfo);
        }
        queryListenersInfo.listeners.add(queryListener);
        Assert.hardAssert(true ^ queryListener.onOnlineStateChanged(this.onlineState), "onOnlineStateChanged() shouldn't raise an event for brand-new listeners.", new Object[0]);
        ViewSnapshot viewSnapshot = queryListenersInfo.viewSnapshot;
        if (viewSnapshot != null && queryListener.onViewSnapshot(viewSnapshot)) {
            raiseSnapshotsInSyncEvent();
        }
        if (z) {
            queryListenersInfo.targetId = this.syncEngine.listen(query);
        }
        return queryListenersInfo.targetId;
    }

    public void addSnapshotsInSyncListener(EventListener<Void> eventListener) {
        this.snapshotsInSyncListeners.add(eventListener);
        eventListener.onEvent(null, null);
    }

    @Override // com.google.firebase.firestore.core.SyncEngine.SyncEngineCallback
    public void handleOnlineStateChange(OnlineState onlineState) {
        this.onlineState = onlineState;
        Iterator<QueryListenersInfo> it = this.queries.values().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Iterator<QueryListener> it2 = it.next().listeners.iterator();
            while (it2.hasNext()) {
                if (it2.next().onOnlineStateChanged(onlineState)) {
                    z = true;
                }
            }
        }
        if (z) {
            raiseSnapshotsInSyncEvent();
        }
    }

    @Override // com.google.firebase.firestore.core.SyncEngine.SyncEngineCallback
    public void onError(Query query, za zaVar) {
        QueryListenersInfo queryListenersInfo = this.queries.get(query);
        if (queryListenersInfo != null) {
            Iterator<QueryListener> it = queryListenersInfo.listeners.iterator();
            while (it.hasNext()) {
                it.next().onError(Util.exceptionFromStatus(zaVar));
            }
        }
        this.queries.remove(query);
    }

    @Override // com.google.firebase.firestore.core.SyncEngine.SyncEngineCallback
    public void onViewSnapshots(List<ViewSnapshot> list) {
        boolean z = false;
        for (ViewSnapshot viewSnapshot : list) {
            QueryListenersInfo queryListenersInfo = this.queries.get(viewSnapshot.getQuery());
            if (queryListenersInfo != null) {
                Iterator<QueryListener> it = queryListenersInfo.listeners.iterator();
                while (it.hasNext()) {
                    if (it.next().onViewSnapshot(viewSnapshot)) {
                        z = true;
                    }
                }
                queryListenersInfo.viewSnapshot = viewSnapshot;
            }
        }
        if (z) {
            raiseSnapshotsInSyncEvent();
        }
    }

    public void removeQueryListener(QueryListener queryListener) {
        boolean zIsEmpty;
        Query query = queryListener.getQuery();
        QueryListenersInfo queryListenersInfo = this.queries.get(query);
        if (queryListenersInfo != null) {
            queryListenersInfo.listeners.remove(queryListener);
            zIsEmpty = queryListenersInfo.listeners.isEmpty();
        } else {
            zIsEmpty = false;
        }
        if (zIsEmpty) {
            this.queries.remove(query);
            this.syncEngine.stopListening(query);
        }
    }

    public void removeSnapshotsInSyncListener(EventListener<Void> eventListener) {
        this.snapshotsInSyncListeners.remove(eventListener);
    }
}
