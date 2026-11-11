package androidx.compose.ui.focus;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: FocusTransactions.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/focus/CustomDestinationResult;", "", "<init>", "(Ljava/lang/String;I)V", "None", "Cancelled", "Redirected", "RedirectCancelled", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public enum CustomDestinationResult {
    None,
    Cancelled,
    Redirected,
    RedirectCancelled;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<CustomDestinationResult> getEntries() {
        return $ENTRIES;
    }
}
