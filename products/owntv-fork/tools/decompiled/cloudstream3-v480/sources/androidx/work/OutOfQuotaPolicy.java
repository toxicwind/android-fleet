package androidx.work;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: OutOfQuotaPolicy.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/work/OutOfQuotaPolicy;", "", "<init>", "(Ljava/lang/String;I)V", "RUN_AS_NON_EXPEDITED_WORK_REQUEST", "DROP_WORK_REQUEST", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public enum OutOfQuotaPolicy {
    RUN_AS_NON_EXPEDITED_WORK_REQUEST,
    DROP_WORK_REQUEST;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<OutOfQuotaPolicy> getEntries() {
        return $ENTRIES;
    }
}
