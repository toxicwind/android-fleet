package kotlinx.serialization.descriptors;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: SerialDescriptor.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u000fH¦\u0080\u0004J\u0012\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u0003H¦\u0080\u0004J\u0018\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0018\u001a\u00020\u000fH¦\u0080\u0004J\u0012\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u000fH¦\u0080\u0004J\u0012\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000fH¦\u0080\u0004R\u0013\u0010\u0002\u001a\u00020\u0003X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0007X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0015\u0010\n\u001a\u00020\u000b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0015\u0010\r\u001a\u00020\u000b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\u0013\u0010\u000e\u001a\u00020\u000fX¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001eÀ\u0006\u0003"}, d2 = {"Lkotlinx/serialization/descriptors/SerialDescriptor;", "", "serialName", "", "getSerialName", "()Ljava/lang/String;", "kind", "Lkotlinx/serialization/descriptors/SerialKind;", "getKind", "()Lkotlinx/serialization/descriptors/SerialKind;", "isNullable", "", "()Z", "isInline", "elementsCount", "", "getElementsCount", "()I", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "getElementName", "index", "getElementIndex", "name", "getElementAnnotations", "getElementDescriptor", "isElementOptional", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface SerialDescriptor {
    List<Annotation> getAnnotations();

    List<Annotation> getElementAnnotations(int index);

    SerialDescriptor getElementDescriptor(int index);

    int getElementIndex(String name);

    String getElementName(int index);

    int getElementsCount();

    SerialKind getKind();

    String getSerialName();

    boolean isElementOptional(int index);

    boolean isInline();

    boolean isNullable();

    /* JADX INFO: renamed from: kotlinx.serialization.descriptors.SerialDescriptor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SerialDescriptor.kt */
    public final /* synthetic */ class CC {
        public static boolean $default$isInline(SerialDescriptor _this) {
            return false;
        }

        public static boolean $default$isNullable(SerialDescriptor _this) {
            return false;
        }

        public static List $default$getAnnotations(SerialDescriptor _this) {
            return CollectionsKt.emptyList();
        }
    }

    /* JADX INFO: compiled from: SerialDescriptor.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean isNullable(SerialDescriptor serialDescriptor) {
            return CC.$default$isNullable(serialDescriptor);
        }

        @Deprecated
        public static boolean isInline(SerialDescriptor serialDescriptor) {
            return CC.$default$isInline(serialDescriptor);
        }

        @Deprecated
        public static List<Annotation> getAnnotations(SerialDescriptor serialDescriptor) {
            return CC.$default$getAnnotations(serialDescriptor);
        }
    }
}
