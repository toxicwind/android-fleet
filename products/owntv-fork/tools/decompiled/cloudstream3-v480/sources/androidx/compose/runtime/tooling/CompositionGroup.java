package androidx.compose.runtime.tooling;

import kotlin.Metadata;

/* JADX INFO: compiled from: CompositionData.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005R\u001a\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0018À\u0006\u0003"}, d2 = {"Landroidx/compose/runtime/tooling/CompositionGroup;", "Landroidx/compose/runtime/tooling/CompositionData;", "key", "", "getKey", "()Ljava/lang/Object;", "sourceInfo", "", "getSourceInfo", "()Ljava/lang/String;", "node", "getNode", "data", "", "getData", "()Ljava/lang/Iterable;", "identity", "getIdentity", "groupSize", "", "getGroupSize", "()I", "slotsSize", "getSlotsSize", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface CompositionGroup extends CompositionData {
    Iterable<Object> getData();

    int getGroupSize();

    Object getIdentity();

    Object getKey();

    Object getNode();

    int getSlotsSize();

    String getSourceInfo();

    /* JADX INFO: renamed from: androidx.compose.runtime.tooling.CompositionGroup$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: CompositionData.kt */
    public final /* synthetic */ class CC {
        public static int $default$getGroupSize(CompositionGroup _this) {
            return 0;
        }

        public static Object $default$getIdentity(CompositionGroup _this) {
            return null;
        }

        public static int $default$getSlotsSize(CompositionGroup _this) {
            return 0;
        }
    }

    /* JADX INFO: compiled from: CompositionData.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static CompositionGroup find(CompositionGroup compositionGroup, Object obj) {
            return CompositionData.CC.$default$find(compositionGroup, obj);
        }

        @Deprecated
        public static Object getIdentity(CompositionGroup compositionGroup) {
            return CC.$default$getIdentity(compositionGroup);
        }

        @Deprecated
        public static int getGroupSize(CompositionGroup compositionGroup) {
            return CC.$default$getGroupSize(compositionGroup);
        }

        @Deprecated
        public static int getSlotsSize(CompositionGroup compositionGroup) {
            return CC.$default$getSlotsSize(compositionGroup);
        }
    }
}
