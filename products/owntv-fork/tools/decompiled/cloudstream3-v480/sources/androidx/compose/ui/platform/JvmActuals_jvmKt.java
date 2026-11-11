package androidx.compose.ui.platform;

import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: JvmActuals.jvm.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0000\u001a\f\u0010\u0007\u001a\u00020\u0005*\u00020\u0005H\u0000\u001a0\u0010\b\u001a\u0002H\t\"\u0004\b\u0000\u0010\t2\n\u0010\n\u001a\u00060\u0005j\u0002`\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\t0\rH\u0081\b¢\u0006\u0004\b\u000e\u0010\u000f*\f\b\u0000\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\u0010"}, d2 = {"AtomicInt", "Ljava/util/concurrent/atomic/AtomicInteger;", "simpleIdentityToString", "", "obj", "", "name", "nativeClass", "oldSynchronized", "R", "lock", "Landroidx/compose/ui/platform/SynchronizedObject;", "block", "Lkotlin/Function0;", "synchronized", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class JvmActuals_jvmKt {
    public static final String simpleIdentityToString(Object obj, String str) {
        if (str == null) {
            if (obj.getClass().isAnonymousClass()) {
                str = obj.getClass().getName();
            } else {
                str = obj.getClass().getSimpleName();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append('@');
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str2 = String.format("%07x", Arrays.copyOf(new Object[]{Integer.valueOf(System.identityHashCode(obj))}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
        sb.append(str2);
        return sb.toString();
    }

    public static final Object nativeClass(Object obj) {
        return obj.getClass();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "not expected to be referenced directly as the old version had to be inlined")
    /* JADX INFO: renamed from: synchronized, reason: not valid java name */
    public static final /* synthetic */ <R> R m6055synchronized(Object obj, Function0<? extends R> function0) {
        R rInvoke;
        synchronized (obj) {
            rInvoke = function0.invoke();
        }
        return rInvoke;
    }
}
