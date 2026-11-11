package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: MustUseReturnValueStatus.kt */
/* JADX INFO: loaded from: classes7.dex */
public enum ReturnValueStatus {
    MustUse,
    ExplicitlyIgnorable,
    Unspecified;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    public static final Companion Companion = new Companion(null);

    /* JADX INFO: compiled from: MustUseReturnValueStatus.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
