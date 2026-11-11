package io.ktor.utils.io;

import io.ktor.utils.io.core.BytePacketBuilderKt;
import java.io.IOException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByteWriteChannel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0007¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0011\u0010\u0005\u001a\u00020\u0001*\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0013\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\u0007¢\u0006\u0004\b\u0005\u0010\u0003\u001a\u0014\u0010\u0007\u001a\u00020\u0001*\u00020\u0000H\u0087@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lio/ktor/utils/io/ByteWriteChannel;", "", "close", "(Lio/ktor/utils/io/ByteWriteChannel;)V", "Lio/ktor/utils/io/ByteChannel;", "cancel", "(Lio/ktor/utils/io/ByteChannel;)V", "flushIfNeeded", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ByteWriteChannelKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteWriteChannelKt$close$1, reason: invalid class name */
    /* JADX INFO: compiled from: ByteWriteChannel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Continuation<? super Unit>, Object>, SuspendFunction {
        AnonymousClass1(Object obj) {
            super(1, obj, ByteWriteChannel.class, "flushAndClose", "flushAndClose(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((ByteWriteChannel) this.receiver).flushAndClose(continuation);
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Async close is deprecated. Please consider replacing it with flushAndClose or cancel ", replaceWith = @ReplaceWith(expression = "flushAndClose()", imports = {}))
    public static final void close(ByteWriteChannel byteWriteChannel) {
        Intrinsics.checkNotNullParameter(byteWriteChannel, "<this>");
        ByteWriteChannelOperationsKt.fireAndForget(new AnonymousClass1(byteWriteChannel));
    }

    public static final void cancel(ByteChannel byteChannel) {
        Intrinsics.checkNotNullParameter(byteChannel, "<this>");
        byteChannel.cancel(new IOException("Channel was cancelled"));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Cancel without reason is deprecated. Please provide a cause for cancellation.", replaceWith = @ReplaceWith(expression = "cancel(IOException())", imports = {"kotlinx.coroutines.cancel"}))
    public static final void cancel(ByteWriteChannel byteWriteChannel) {
        Intrinsics.checkNotNullParameter(byteWriteChannel, "<this>");
        byteWriteChannel.cancel(new IOException("Channel was cancelled"));
    }

    public static final Object flushIfNeeded(ByteWriteChannel byteWriteChannel, Continuation<? super Unit> continuation) {
        ByteReadChannelOperationsKt.rethrowCloseCauseIfNeeded(byteWriteChannel);
        if (byteWriteChannel.getAutoFlush() || BytePacketBuilderKt.getSize(byteWriteChannel.getWriteBuffer()) >= 1048576) {
            Object objFlush = byteWriteChannel.flush(continuation);
            return objFlush == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlush : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
