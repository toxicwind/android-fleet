package kotlinx.datetime;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: OverloadMarker.kt */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.ERROR, message = "It is meaningless to try to pass an OverloadMarker to a function directly. All functions accepting it have its instance as a default value.")
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lkotlinx/datetime/OverloadMarker;", "", "<init>", "()V", "Companion", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class OverloadMarker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final OverloadMarker INSTANCE = new OverloadMarker();

    /* JADX INFO: compiled from: OverloadMarker.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/datetime/OverloadMarker$Companion;", "", "<init>", "()V", "INSTANCE", "Lkotlinx/datetime/OverloadMarker;", "getINSTANCE$kotlinx_datetime$annotations", "getINSTANCE$kotlinx_datetime", "()Lkotlinx/datetime/OverloadMarker;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getINSTANCE$kotlinx_datetime$annotations() {
        }

        private Companion() {
        }

        public final OverloadMarker getINSTANCE$kotlinx_datetime() {
            return OverloadMarker.INSTANCE;
        }
    }

    private OverloadMarker() {
    }
}
