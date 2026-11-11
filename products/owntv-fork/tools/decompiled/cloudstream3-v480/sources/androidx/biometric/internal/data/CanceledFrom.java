package androidx.biometric.internal.data;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: CanceledFrom.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\n\u001a\u00020\u000bj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\f"}, d2 = {"Landroidx/biometric/internal/data/CanceledFrom;", "", "<init>", "(Ljava/lang/String;I)V", "INTERNAL", "USER", "CLIENT", "NEGATIVE_BUTTON", "FALLBACK_OPTION", "MORE_OPTIONS_BUTTON", "isNotUserInitiated", "", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public enum CanceledFrom {
    INTERNAL,
    USER,
    CLIENT,
    NEGATIVE_BUTTON,
    FALLBACK_OPTION,
    MORE_OPTIONS_BUTTON;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<CanceledFrom> getEntries() {
        return $ENTRIES;
    }

    public final boolean isNotUserInitiated() {
        return this == INTERNAL || this == CLIENT;
    }
}
