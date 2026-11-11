package kotlinx.io;

import kotlin.Metadata;

/* JADX INFO: compiled from: Segment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b \u0018\u00002\u00020\u0001B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\b\u001a\u00020\tH¦\u0080\u0004J\n\u0010\n\u001a\u00020\u0005H¦\u0080\u0004R\u0013\u0010\u0004\u001a\u00020\u0005X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lkotlinx/io/SegmentCopyTracker;", "", "<init>", "()V", "shared", "", "getShared", "()Z", "addCopy", "", "removeCopy", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class SegmentCopyTracker {
    public abstract void addCopy();

    public abstract boolean getShared();

    public abstract boolean removeCopy();
}
