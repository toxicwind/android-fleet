package androidx.compose.runtime;

import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SnapshotLongState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0007\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001R\u0014\u0010\u0003\u001a\u00020\u00028WX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0003"}, d2 = {"Landroidx/compose/runtime/LongState;", "Landroidx/compose/runtime/State;", "", ES6Iterator.VALUE_PROPERTY, "getValue", "()Ljava/lang/Long;", "longValue", "getLongValue", "()J", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface LongState extends State<Long> {
    long getLongValue();

    @Override // androidx.compose.runtime.State
    Long getValue();

    /* JADX INFO: renamed from: androidx.compose.runtime.LongState$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SnapshotLongState.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ long access$getValue$jd(LongState longState) {
            return $default$getValue(longState).longValue();
        }

        public static Long $default$getValue(LongState _this) {
            return Long.valueOf(_this.getLongValue());
        }
    }

    /* JADX INFO: compiled from: SnapshotLongState.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static Long getValue(LongState longState) {
            return Long.valueOf(CC.access$getValue$jd(longState));
        }
    }
}
