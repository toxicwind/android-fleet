package io.ktor.utils.io;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;
import kotlinx.io.CoreKt;
import kotlinx.io.RawSink;
import kotlinx.io.Sink;

/* JADX INFO: compiled from: SinkByteWriteChannel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\t\u0010\bJ\u0019\u0010\f\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u001b\u001a\u00020\u000e8VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001c"}, d2 = {"Lio/ktor/utils/io/SinkByteWriteChannel;", "Lio/ktor/utils/io/ByteWriteChannel;", "Lkotlinx/io/RawSink;", "origin", "<init>", "(Lkotlinx/io/RawSink;)V", "", "flush", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "flushAndClose", "", "cause", "cancel", "(Ljava/lang/Throwable;)V", "Lkotlinx/io/Sink;", "buffer", "Lkotlinx/io/Sink;", "", "isClosedForWrite", "()Z", "getClosedCause", "()Ljava/lang/Throwable;", "closedCause", "getWriteBuffer", "()Lkotlinx/io/Sink;", "getWriteBuffer$annotations", "()V", "writeBuffer", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SinkByteWriteChannel implements ByteWriteChannel {
    static final /* synthetic */ AtomicReferenceFieldUpdater closed$FU = AtomicReferenceFieldUpdater.newUpdater(SinkByteWriteChannel.class, Object.class, "closed");
    private final Sink buffer;
    volatile /* synthetic */ Object closed;

    public static /* synthetic */ void getWriteBuffer$annotations() {
    }

    public SinkByteWriteChannel(RawSink origin) {
        Intrinsics.checkNotNullParameter(origin, "origin");
        this.closed = null;
        this.buffer = CoreKt.buffered(origin);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public /* bridge */ boolean getAutoFlush() {
        return ByteWriteChannel.CC.$default$getAutoFlush(this);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public boolean isClosedForWrite() {
        return this.closed != null;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Throwable getClosedCause() {
        CloseToken closeToken = (CloseToken) this.closed;
        if (closeToken == null) {
            return null;
        }
        CloseToken.Companion companion = CloseToken.INSTANCE;
        Throwable th = closeToken.origin;
        if (th == null) {
            return null;
        }
        if (th instanceof CopyableThrowable) {
            return ((CopyableThrowable) closeToken.origin).createCopy();
        }
        if (th instanceof CancellationException) {
            return kotlinx.coroutines.ExceptionsKt.CancellationException(((CancellationException) closeToken.origin).getMessage(), closeToken.origin);
        }
        return new ClosedByteChannelException(closeToken.origin);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Sink getWriteBuffer() throws Throwable {
        if (!isClosedForWrite()) {
            return this.buffer;
        }
        Throwable closedCause = getClosedCause();
        if (closedCause == null) {
            throw new IOException("Channel is closed for write");
        }
        throw closedCause;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object flush(Continuation<? super Unit> continuation) {
        getWriteBuffer().flush();
        return Unit.INSTANCE;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object flushAndClose(Continuation<? super Unit> continuation) {
        getWriteBuffer().flush();
        return !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(closed$FU, this, null, CloseTokenKt.getCLOSED()) ? Unit.INSTANCE : Unit.INSTANCE;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public void cancel(Throwable cause) {
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(closed$FU, this, null, cause == null ? CloseTokenKt.getCLOSED() : new CloseToken(cause));
    }
}
