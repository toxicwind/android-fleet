package kotlin.reflect.jvm.internal.impl.km;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: Modifiers.kt */
/* JADX INFO: loaded from: classes7.dex */
public enum ReturnValueStatus {
    UNSPECIFIED,
    MUST_USE,
    EXPLICITLY_IGNORABLE;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<ReturnValueStatus> getEntries() {
        return $ENTRIES;
    }
}
