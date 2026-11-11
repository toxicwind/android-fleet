package io.ktor.utils.io;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: CloseToken.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
public final /* synthetic */ class CloseToken$Companion$wrapCause$1 extends FunctionReferenceImpl implements Function1<Throwable, ClosedByteChannelException> {
    public static final CloseToken$Companion$wrapCause$1 INSTANCE = new CloseToken$Companion$wrapCause$1();

    public CloseToken$Companion$wrapCause$1() {
        super(1, ClosedByteChannelException.class, "<init>", "<init>(Ljava/lang/Throwable;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final ClosedByteChannelException invoke(Throwable th) {
        return new ClosedByteChannelException(th);
    }
}
