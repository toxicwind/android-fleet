package io.ktor.utils.io;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Exceptions.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0016\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0013\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/utils/io/ClosedByteChannelException;", "Ljava/io/IOException;", "Lkotlinx/io/IOException;", "", "cause", "<init>", "(Ljava/lang/Throwable;)V", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class ClosedByteChannelException extends IOException {
    /* JADX WARN: Multi-variable type inference failed */
    public ClosedByteChannelException() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ClosedByteChannelException(Throwable th) {
        super(th != null ? th.getMessage() : null, th);
    }

    public /* synthetic */ ClosedByteChannelException(Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : th);
    }
}
