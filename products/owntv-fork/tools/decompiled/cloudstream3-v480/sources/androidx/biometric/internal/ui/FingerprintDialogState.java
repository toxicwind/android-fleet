package androidx.biometric.internal.ui;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: FingerprintDialogActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/biometric/internal/ui/FingerprintDialogState;", "", "<init>", "(Ljava/lang/String;I)V", "NONE", "FINGERPRINT", "FINGERPRINT_ERROR", "FINGERPRINT_AUTHENTICATED", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public enum FingerprintDialogState {
    NONE,
    FINGERPRINT,
    FINGERPRINT_ERROR,
    FINGERPRINT_AUTHENTICATED;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<FingerprintDialogState> getEntries() {
        return $ENTRIES;
    }
}
