package kotlinx.datetime.internal.format;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: Predicate.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b`\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lkotlinx/datetime/internal/format/Predicate;", ExifInterface.GPS_DIRECTION_TRUE, "", "test", "", ES6Iterator.VALUE_PROPERTY, Signatures.MATH_TO_BOOLEAN, "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface Predicate<T> {
    boolean test(T value);
}
