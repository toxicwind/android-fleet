package androidx.compose.ui.text.internal;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentType;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: InlineClassHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a4\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\fH\u0080\b\u0082\u0002\u0012\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0000\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001aG\u0010\r\u001a\u0002H\u000e\"\b\b\u0000\u0010\u000e*\u00020\u000f2\b\u0010\t\u001a\u0004\u0018\u0001H\u000e2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\fH\u0080\b\u0082\u0002\u0014\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0000\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\u0010\u001a4\u0010\u0011\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\fH\u0080\b\u0082\u0002\u0012\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0000\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001aG\u0010\u0012\u001a\u0002H\u000e\"\b\b\u0000\u0010\u000e*\u00020\u000f2\b\u0010\t\u001a\u0004\u0018\u0001H\u000e2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\fH\u0080\b\u0082\u0002\u0014\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0000\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\u0010¨\u0006\u0013"}, d2 = {"throwIllegalStateException", "", ContentType.Message.TYPE, "", "throwIllegalStateExceptionForNullCheck", "", "throwIllegalArgumentException", "throwIllegalArgumentExceptionForNullCheck", "checkPrecondition", ES6Iterator.VALUE_PROPERTY, "", "lazyMessage", "Lkotlin/Function0;", "checkPreconditionNotNull", ExifInterface.GPS_DIRECTION_TRUE, "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "requirePrecondition", "requirePreconditionNotNull", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class InlineClassHelperKt {
    public static final void throwIllegalStateException(String str) {
        throw new IllegalStateException(str);
    }

    public static final Void throwIllegalStateExceptionForNullCheck(String str) {
        throw new IllegalStateException(str);
    }

    public static final void throwIllegalArgumentException(String str) {
        throw new IllegalArgumentException(str);
    }

    public static final Void throwIllegalArgumentExceptionForNullCheck(String str) {
        throw new IllegalArgumentException(str);
    }

    public static final void checkPrecondition(boolean z, Function0<String> function0) {
        if (z) {
            return;
        }
        throwIllegalStateException(function0.invoke());
    }

    public static final <T> T checkPreconditionNotNull(T t, Function0<String> function0) {
        if (t != null) {
            return t;
        }
        throwIllegalStateExceptionForNullCheck(function0.invoke());
        throw new KotlinNothingValueException();
    }

    public static final void requirePrecondition(boolean z, Function0<String> function0) {
        if (z) {
            return;
        }
        throwIllegalArgumentException(function0.invoke());
    }

    public static final <T> T requirePreconditionNotNull(T t, Function0<String> function0) {
        if (t != null) {
            return t;
        }
        throwIllegalArgumentExceptionForNullCheck(function0.invoke());
        throw new KotlinNothingValueException();
    }
}
