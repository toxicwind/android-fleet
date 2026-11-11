package app.cash.zipline.internal.bridge;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: throwablesJni.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a$\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u0006H\u0000¨\u0006\u0007"}, d2 = {"stacktraceString", "", "throwable", "", "toInboundThrowable", "constructor", "Lkotlin/Function1;", "zipline_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ThrowablesJniKt {
    public static final String stacktraceString(Throwable throwable) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "getStackTrace(...)");
        int length = stackTrace.length;
        for (int i = 0; i < length; i++) {
            String className = stackTrace[i].getClassName();
            Intrinsics.checkNotNullExpressionValue(className, "getClassName(...)");
            String qualifiedName = Reflection.getOrCreateKotlinClass(Endpoint.class).getQualifiedName();
            Intrinsics.checkNotNull(qualifiedName);
            if (StringsKt.startsWith$default(className, qualifiedName, false, 2, (Object) null)) {
                StackTraceElement[] stackTrace2 = throwable.getStackTrace();
                Intrinsics.checkNotNullExpressionValue(stackTrace2, "getStackTrace(...)");
                throwable.setStackTrace((StackTraceElement[]) ArraysKt.sliceArray(stackTrace2, RangesKt.until(0, i)));
                break;
            }
        }
        return StringsKt.trim((CharSequence) ExceptionsKt.stackTraceToString(throwable)).toString();
    }

    public static final Throwable toInboundThrowable(String stacktraceString, Function1<? super String, ? extends Throwable> constructor) {
        Intrinsics.checkNotNullParameter(stacktraceString, "stacktraceString");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        Throwable thInvoke = constructor.invoke(StringsKt.trim((CharSequence) new Regex("\n+").replace(new Regex("\n[ ]+at ").replace(stacktraceString, "\n\tat "), "\n")).toString());
        StackTraceElement[] stackTrace = thInvoke.getStackTrace();
        int length = stackTrace.length;
        do {
            length--;
            if (-1 >= length) {
                return thInvoke;
            }
        } while (!Intrinsics.areEqual(stackTrace[length].getClassName(), Reflection.getOrCreateKotlinClass(OutboundCallHandler.class).getQualifiedName()));
        Intrinsics.checkNotNull(stackTrace);
        thInvoke.setStackTrace((StackTraceElement[]) ArraysKt.sliceArray(stackTrace, RangesKt.until(length + 1, stackTrace.length)));
        return thInvoke;
    }
}
