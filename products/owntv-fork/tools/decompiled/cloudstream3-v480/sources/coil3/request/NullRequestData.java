package coil3.request;

import kotlin.Metadata;

/* JADX INFO: compiled from: NullRequestData.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lcoil3/request/NullRequestData;", "", "<init>", "()V", "equals", "", "other", "hashCode", "", "toString", "", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class NullRequestData {
    public static final NullRequestData INSTANCE = new NullRequestData();

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NullRequestData)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 2113961193;
    }

    public String toString() {
        return "NullRequestData";
    }

    private NullRequestData() {
    }
}
