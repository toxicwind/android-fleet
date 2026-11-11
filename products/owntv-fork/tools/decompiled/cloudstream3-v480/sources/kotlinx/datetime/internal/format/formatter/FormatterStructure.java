package kotlinx.datetime.internal.format.formatter;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* JADX INFO: compiled from: Formatter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b`\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002J+\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u00002\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b2\b\b\u0002\u0010\t\u001a\u00020\nH&¢\u0006\u0002\u0010\u000b¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", ExifInterface.GPS_DIRECTION_TRUE, "", "format", "", "obj", "builder", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "minusNotRequired", "", "(Ljava/lang/Object;Ljava/lang/Appendable;Z)V", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface FormatterStructure<T> {
    void format(T obj, Appendable builder, boolean minusNotRequired);

    /* JADX INFO: renamed from: kotlinx.datetime.internal.format.formatter.FormatterStructure$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Formatter.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ void format$default(FormatterStructure formatterStructure, Object obj, Appendable appendable, boolean z, int i, Object obj2) {
            if (obj2 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: format");
            }
            if ((i & 4) != 0) {
                z = false;
            }
            formatterStructure.format(obj, appendable, z);
        }
    }

    /* JADX INFO: compiled from: Formatter.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
    }
}
