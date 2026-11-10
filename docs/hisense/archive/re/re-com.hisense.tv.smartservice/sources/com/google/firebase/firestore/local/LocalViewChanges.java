package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.DocumentKey;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class LocalViewChanges {
    public final ImmutableSortedSet<DocumentKey> added;
    public final boolean fromCache;
    public final ImmutableSortedSet<DocumentKey> removed;
    public final int targetId;

    /* JADX INFO: renamed from: com.google.firebase.firestore.local.LocalViewChanges$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type = new int[DocumentViewChange.Type.values().length];

        static {
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.REMOVED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public LocalViewChanges(int i, boolean z, ImmutableSortedSet<DocumentKey> immutableSortedSet, ImmutableSortedSet<DocumentKey> immutableSortedSet2) {
        this.targetId = i;
        this.fromCache = z;
        this.added = immutableSortedSet;
        this.removed = immutableSortedSet2;
    }

    public static LocalViewChanges fromViewSnapshot(int i, ViewSnapshot viewSnapshot) {
        ImmutableSortedSet immutableSortedSet = new ImmutableSortedSet(new ArrayList(), DocumentKey.COMPARATOR);
        ImmutableSortedSet immutableSortedSet2 = new ImmutableSortedSet(new ArrayList(), DocumentKey.COMPARATOR);
        for (DocumentViewChange documentViewChange : viewSnapshot.getChanges()) {
            int iOrdinal = documentViewChange.getType().ordinal();
            if (iOrdinal == 0) {
                immutableSortedSet2 = immutableSortedSet2.insert(documentViewChange.getDocument().getKey());
            } else if (iOrdinal == 1) {
                immutableSortedSet = immutableSortedSet.insert(documentViewChange.getDocument().getKey());
            }
        }
        return new LocalViewChanges(i, viewSnapshot.isFromCache(), immutableSortedSet, immutableSortedSet2);
    }

    public ImmutableSortedSet<DocumentKey> getAdded() {
        return this.added;
    }

    public ImmutableSortedSet<DocumentKey> getRemoved() {
        return this.removed;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public boolean isFromCache() {
        return this.fromCache;
    }
}
