package kotlinx.datetime.internal;

import kotlin.Metadata;

/* JADX INFO: compiled from: math.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003H\u0086\u0002J\t\u0010\u000b\u001a\u00020\u0003H\u0086\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\f"}, d2 = {"Lkotlinx/datetime/internal/DivRemResult;", "", "q", "", "r", "<init>", "(JJ)V", "getQ", "()J", "getR", "component1", "component2", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DivRemResult {
    private final long q;
    private final long r;

    public DivRemResult(long j, long j2) {
        this.q = j;
        this.r = j2;
    }

    public final long getQ() {
        return this.q;
    }

    public final long getR() {
        return this.r;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getQ() {
        return this.q;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getR() {
        return this.r;
    }
}
