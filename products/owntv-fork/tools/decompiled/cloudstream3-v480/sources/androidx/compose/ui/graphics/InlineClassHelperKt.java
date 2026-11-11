package androidx.compose.ui.graphics;

import io.ktor.http.ContentType;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: InlineClassHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a*\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0080\b\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001¨\u0006\t"}, d2 = {"throwIllegalArgumentException", "", ContentType.Message.TYPE, "", "requirePrecondition", ES6Iterator.VALUE_PROPERTY, "", "lazyMessage", "Lkotlin/Function0;", "ui-graphics_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class InlineClassHelperKt {
    public static final void throwIllegalArgumentException(String str) {
        throw new IllegalArgumentException(str);
    }

    public static final void requirePrecondition(boolean z, Function0<String> function0) {
        if (z) {
            return;
        }
        throwIllegalArgumentException(function0.invoke());
    }
}
