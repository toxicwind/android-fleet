package io.ktor.utils.io;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;

/* JADX INFO: compiled from: CloseToken.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\b\u0000\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0006¨\u0006\b"}, d2 = {"Lio/ktor/utils/io/CloseToken;", "", "", "origin", "<init>", "(Ljava/lang/Throwable;)V", "Ljava/lang/Throwable;", "Companion", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CloseToken {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Throwable origin;

    public CloseToken(Throwable th) {
        this.origin = th;
    }

    /* JADX INFO: compiled from: CloseToken.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\b\u001a\u0004\u0018\u00010\u0006*\u00020\u00042\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H\u0086\bø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ-\u0010\u000b\u001a\u0004\u0018\u00010\n*\u00020\u00042\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"}, d2 = {"Lio/ktor/utils/io/CloseToken$Companion;", "", "<init>", "()V", "Lio/ktor/utils/io/CloseToken;", "Lkotlin/Function1;", "", "wrap", "wrapCause", "(Lio/ktor/utils/io/CloseToken;Lkotlin/jvm/functions/Function1;)Ljava/lang/Throwable;", "", "throwOrNull", "(Lio/ktor/utils/io/CloseToken;Lkotlin/jvm/functions/Function1;)Lkotlin/Unit;", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ Throwable wrapCause$default(Companion companion, CloseToken closeToken, Function1 wrap, int i, Object obj) {
            if ((i & 1) != 0) {
                wrap = CloseToken$Companion$wrapCause$1.INSTANCE;
            }
            Intrinsics.checkNotNullParameter(closeToken, "<this>");
            Intrinsics.checkNotNullParameter(wrap, "wrap");
            Throwable th = closeToken.origin;
            if (th == null) {
                return null;
            }
            if (th instanceof CopyableThrowable) {
                return ((CopyableThrowable) closeToken.origin).createCopy();
            }
            return th instanceof CancellationException ? kotlinx.coroutines.ExceptionsKt.CancellationException(((CancellationException) closeToken.origin).getMessage(), closeToken.origin) : (Throwable) wrap.invoke(closeToken.origin);
        }

        public final Throwable wrapCause(CloseToken closeToken, Function1<? super Throwable, ? extends Throwable> wrap) {
            Intrinsics.checkNotNullParameter(closeToken, "<this>");
            Intrinsics.checkNotNullParameter(wrap, "wrap");
            Throwable th = closeToken.origin;
            if (th == null) {
                return null;
            }
            if (th instanceof CopyableThrowable) {
                return ((CopyableThrowable) closeToken.origin).createCopy();
            }
            return th instanceof CancellationException ? kotlinx.coroutines.ExceptionsKt.CancellationException(((CancellationException) closeToken.origin).getMessage(), closeToken.origin) : wrap.invoke(closeToken.origin);
        }

        public final Unit throwOrNull(CloseToken closeToken, Function1<? super Throwable, ? extends Throwable> wrap) throws Throwable {
            Throwable thCancellationException;
            Intrinsics.checkNotNullParameter(closeToken, "<this>");
            Intrinsics.checkNotNullParameter(wrap, "wrap");
            Throwable th = closeToken.origin;
            if (th == null) {
                thCancellationException = null;
            } else if (th instanceof CopyableThrowable) {
                thCancellationException = ((CopyableThrowable) closeToken.origin).createCopy();
            } else {
                thCancellationException = th instanceof CancellationException ? kotlinx.coroutines.ExceptionsKt.CancellationException(((CancellationException) closeToken.origin).getMessage(), closeToken.origin) : wrap.invoke(closeToken.origin);
            }
            if (thCancellationException == null) {
                return null;
            }
            throw thCancellationException;
        }
    }
}
