package io.ktor.util.collections;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.util.collections.TreeLike;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: TreeLike.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u001c\n\u0002\b\u0004\bf\u0018\u0000*\u0010\b\u0000\u0010\u0001 \u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u00020\u0002J\u0015\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016¢\u0006\u0004\b\u0006\u0010\u0005J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\tR\u0016\u0010\r\u001a\u0004\u0018\u00018\u00008&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012À\u0006\u0003"}, d2 = {"Lio/ktor/util/collections/TreeLike;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlin/sequences/Sequence;", "lineage", "()Lkotlin/sequences/Sequence;", "descendants", "", "isRoot", "()Z", "isLeaf", "getParent", "()Lio/ktor/util/collections/TreeLike;", "parent", "", "getChildren", "()Ljava/lang/Iterable;", "children", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface TreeLike<T extends TreeLike<? extends T>> {
    Sequence<T> descendants();

    Iterable<T> getChildren();

    T getParent();

    boolean isLeaf();

    boolean isRoot();

    Sequence<T> lineage();

    /* JADX INFO: renamed from: io.ktor.util.collections.TreeLike$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: TreeLike.kt */
    public final /* synthetic */ class CC {
        public static Sequence $default$lineage(TreeLike _this) {
            if (!(_this instanceof TreeLike)) {
                _this = null;
            }
            return SequencesKt.generateSequence(_this, (Function1<? super TreeLike, ? extends TreeLike>) new Function1() { // from class: io.ktor.util.collections.TreeLike$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TreeLike.CC.lineage$lambda$0((TreeLike) obj);
                }
            });
        }

        public static TreeLike lineage$lambda$0(TreeLike it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it.getParent();
        }

        public static Sequence $default$descendants(TreeLike _this) {
            return SequencesKt.flatMap(CollectionsKt.asSequence(_this.getChildren()), new Function1() { // from class: io.ktor.util.collections.TreeLike$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TreeLike.CC.descendants$lambda$0((TreeLike) obj);
                }
            });
        }

        public static Sequence descendants$lambda$0(TreeLike it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return SequencesKt.plus(SequencesKt.sequenceOf(it), (Sequence) it.descendants());
        }

        public static boolean $default$isRoot(TreeLike _this) {
            return _this.getParent() == null;
        }

        public static boolean $default$isLeaf(TreeLike _this) {
            return !_this.getChildren().iterator().hasNext();
        }
    }

    /* JADX INFO: compiled from: TreeLike.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <T extends TreeLike<? extends T>> Sequence<T> lineage(TreeLike<? extends T> treeLike) {
            return CC.$default$lineage(treeLike);
        }

        @Deprecated
        public static <T extends TreeLike<? extends T>> Sequence<T> descendants(TreeLike<? extends T> treeLike) {
            return CC.$default$descendants(treeLike);
        }

        @Deprecated
        public static <T extends TreeLike<? extends T>> boolean isRoot(TreeLike<? extends T> treeLike) {
            return CC.$default$isRoot(treeLike);
        }

        @Deprecated
        public static <T extends TreeLike<? extends T>> boolean isLeaf(TreeLike<? extends T> treeLike) {
            return CC.$default$isLeaf(treeLike);
        }
    }
}
