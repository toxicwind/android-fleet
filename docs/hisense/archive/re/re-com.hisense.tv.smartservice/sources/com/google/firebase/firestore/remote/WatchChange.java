package com.google.firebase.firestore.remote;

import androidx.annotation.Nullable;
import b.a.a.a.a;
import c.a.za;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.protobuf.ByteString;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class WatchChange {

    public static final class DocumentChange extends WatchChange {
        public final DocumentKey documentKey;

        @Nullable
        public final MutableDocument newDocument;
        public final List<Integer> removedTargetIds;
        public final List<Integer> updatedTargetIds;

        public DocumentChange(List<Integer> list, List<Integer> list2, DocumentKey documentKey, @Nullable MutableDocument mutableDocument) {
            super(null);
            this.updatedTargetIds = list;
            this.removedTargetIds = list2;
            this.documentKey = documentKey;
            this.newDocument = mutableDocument;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || DocumentChange.class != obj.getClass()) {
                return false;
            }
            DocumentChange documentChange = (DocumentChange) obj;
            if (!this.updatedTargetIds.equals(documentChange.updatedTargetIds) || !this.removedTargetIds.equals(documentChange.removedTargetIds) || !this.documentKey.equals(documentChange.documentKey)) {
                return false;
            }
            MutableDocument mutableDocument = this.newDocument;
            if (mutableDocument != null) {
                return mutableDocument.equals(documentChange.newDocument);
            }
            return documentChange.newDocument == null;
        }

        public DocumentKey getDocumentKey() {
            return this.documentKey;
        }

        @Nullable
        public MutableDocument getNewDocument() {
            return this.newDocument;
        }

        public List<Integer> getRemovedTargetIds() {
            return this.removedTargetIds;
        }

        public List<Integer> getUpdatedTargetIds() {
            return this.updatedTargetIds;
        }

        public int hashCode() {
            int iHashCode = (this.documentKey.hashCode() + ((this.removedTargetIds.hashCode() + (this.updatedTargetIds.hashCode() * 31)) * 31)) * 31;
            MutableDocument mutableDocument = this.newDocument;
            return iHashCode + (mutableDocument != null ? mutableDocument.hashCode() : 0);
        }

        public String toString() {
            StringBuilder sbA = a.a("DocumentChange{updatedTargetIds=");
            sbA.append(this.updatedTargetIds);
            sbA.append(", removedTargetIds=");
            sbA.append(this.removedTargetIds);
            sbA.append(", key=");
            sbA.append(this.documentKey);
            sbA.append(", newDocument=");
            return a.a(sbA, (Object) this.newDocument, '}');
        }
    }

    public static final class ExistenceFilterWatchChange extends WatchChange {
        public final ExistenceFilter existenceFilter;
        public final int targetId;

        public ExistenceFilterWatchChange(int i, ExistenceFilter existenceFilter) {
            super(null);
            this.targetId = i;
            this.existenceFilter = existenceFilter;
        }

        public ExistenceFilter getExistenceFilter() {
            return this.existenceFilter;
        }

        public int getTargetId() {
            return this.targetId;
        }

        public String toString() {
            StringBuilder sbA = a.a("ExistenceFilterWatchChange{targetId=");
            sbA.append(this.targetId);
            sbA.append(", existenceFilter=");
            return a.a(sbA, (Object) this.existenceFilter, '}');
        }
    }

    public static final class WatchTargetChange extends WatchChange {

        @Nullable
        public final za cause;
        public final WatchTargetChangeType changeType;
        public final ByteString resumeToken;
        public final List<Integer> targetIds;

        public WatchTargetChange(WatchTargetChangeType watchTargetChangeType, List<Integer> list) {
            this(watchTargetChangeType, list, WatchStream.EMPTY_RESUME_TOKEN, null);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || WatchTargetChange.class != obj.getClass()) {
                return false;
            }
            WatchTargetChange watchTargetChange = (WatchTargetChange) obj;
            if (this.changeType != watchTargetChange.changeType || !this.targetIds.equals(watchTargetChange.targetIds) || !this.resumeToken.equals(watchTargetChange.resumeToken)) {
                return false;
            }
            za zaVar = this.cause;
            if (zaVar == null) {
                return watchTargetChange.cause == null;
            }
            za zaVar2 = watchTargetChange.cause;
            return zaVar2 != null && zaVar.p.equals(zaVar2.p);
        }

        @Nullable
        public za getCause() {
            return this.cause;
        }

        public WatchTargetChangeType getChangeType() {
            return this.changeType;
        }

        public ByteString getResumeToken() {
            return this.resumeToken;
        }

        public List<Integer> getTargetIds() {
            return this.targetIds;
        }

        public int hashCode() {
            int iHashCode = (this.resumeToken.hashCode() + ((this.targetIds.hashCode() + (this.changeType.hashCode() * 31)) * 31)) * 31;
            za zaVar = this.cause;
            return iHashCode + (zaVar != null ? zaVar.p.hashCode() : 0);
        }

        public String toString() {
            StringBuilder sbA = a.a("WatchTargetChange{changeType=");
            sbA.append(this.changeType);
            sbA.append(", targetIds=");
            return a.a(sbA, (Object) this.targetIds, '}');
        }

        public WatchTargetChange(WatchTargetChangeType watchTargetChangeType, List<Integer> list, ByteString byteString) {
            this(watchTargetChangeType, list, byteString, null);
        }

        public WatchTargetChange(WatchTargetChangeType watchTargetChangeType, List<Integer> list, ByteString byteString, @Nullable za zaVar) {
            super(null);
            Assert.hardAssert(zaVar == null || watchTargetChangeType == WatchTargetChangeType.Removed, "Got cause for a target change that was not a removal", new Object[0]);
            this.changeType = watchTargetChangeType;
            this.targetIds = list;
            this.resumeToken = byteString;
            if (zaVar != null && !zaVar.c()) {
                this.cause = zaVar;
            } else {
                this.cause = null;
            }
        }
    }

    public enum WatchTargetChangeType {
        NoChange,
        Added,
        Removed,
        Current,
        Reset
    }

    public WatchChange() {
    }

    public /* synthetic */ WatchChange(AnonymousClass1 anonymousClass1) {
    }
}
