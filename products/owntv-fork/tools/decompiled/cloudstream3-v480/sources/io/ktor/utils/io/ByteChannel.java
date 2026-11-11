package io.ktor.utils.io;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CopyableThrowable;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.io.Buffer;
import kotlinx.io.Sink;
import kotlinx.io.Source;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: ByteChannel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u00012\u00020\u0002:\u0001QB\u0011\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0007H\u0096@¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000bH\u0096@¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u000bH\u0017¢\u0006\u0004\b\u0010\u0010\rJ\u000f\u0010\u0011\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0011\u0010\rJ\u0010\u0010\u0012\u001a\u00020\u000bH\u0096@¢\u0006\u0004\b\u0012\u0010\u000fJ\u0019\u0010\u0015\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J4\u0010\u001e\u001a\u00020\u001b2#\u0010\u001a\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000b0\u0017H\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010 \u001a\u00020\u001fH\u0096\u0080\u0004¢\u0006\u0004\b \u0010!JH\u0010(\u001a\u00020\u000b\"\n\b\u0000\u0010#\u0018\u0001*\u00020\"2\u001a\b\u0004\u0010%\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0$\u0012\u0004\u0012\u00028\u00000\u00172\u000e\b\u0004\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030&H\u0082H¢\u0006\u0004\b(\u0010)J\u001c\u0010+\u001a\u00020\u000b\"\n\b\u0000\u0010*\u0018\u0001*\u00020\"H\u0082\b¢\u0006\u0004\b+\u0010\rJ\u0019\u0010,\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0004\b,\u0010\u0016J4\u0010.\u001a\u00020\u000b\"\n\b\u0000\u0010#\u0018\u0001*\u00020\"2\u0006\u0010-\u001a\u00028\u00002\u000e\b\u0004\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030&H\u0082\b¢\u0006\u0004\b.\u0010/R\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u00100\u001a\u0004\b1\u00102R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u0016\u00106\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u001a\u0010:\u001a\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b9\u0010\r\u001a\u0004\b8\u00102R\u001e\u0010=\u001a\u00060;j\u0002`<8\u0002X\u0082\u0004¢\u0006\f\n\u0004\b=\u0010>\u0012\u0004\b?\u0010\rR\u0014\u0010@\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u00105R\u0014\u0010A\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u00105R\u001a\u0010F\u001a\u00020B8VX\u0097\u0004¢\u0006\f\u0012\u0004\bE\u0010\r\u001a\u0004\bC\u0010DR\u001a\u0010K\u001a\u00020G8VX\u0097\u0004¢\u0006\f\u0012\u0004\bJ\u0010\r\u001a\u0004\bH\u0010IR\u0016\u0010N\u001a\u0004\u0018\u00010\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bL\u0010MR\u0014\u0010O\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bO\u00102R\u0014\u0010P\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bP\u00102¨\u0006R"}, d2 = {"Lio/ktor/utils/io/ByteChannel;", "Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/utils/io/BufferedByteWriteChannel;", "", "autoFlush", "<init>", "(Z)V", "", "min", "awaitContent", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "moveFlushToReadBuffer", "()V", "flush", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "flushWriteBuffer", "close", "flushAndClose", "", "cause", "cancel", "(Ljava/lang/Throwable;)V", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "handler", "Lkotlinx/coroutines/DisposableHandle;", "invokeOnClose$ktor_io", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "invokeOnClose", "", "toString", "()Ljava/lang/String;", "Lio/ktor/utils/io/ByteChannel$Slot$Task;", "TaskType", "Lkotlin/coroutines/Continuation;", "createTask", "Lkotlin/Function0;", "shouldSleep", "sleepWhile", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Expected", "resumeSlot", "closeSlot", "slot", "trySuspend", "(Lio/ktor/utils/io/ByteChannel$Slot$Task;Lkotlin/jvm/functions/Function0;)V", "Z", "getAutoFlush", "()Z", "Lkotlinx/io/Buffer;", "flushBuffer", "Lkotlinx/io/Buffer;", "flushBufferSize", "I", "getHasFreeSpace", "getHasFreeSpace$annotations", "hasFreeSpace", "", "Lio/ktor/utils/io/locks/SynchronizedObject;", "flushBufferMutex", "Ljava/lang/Object;", "getFlushBufferMutex$annotations", "_readBuffer", "_writeBuffer", "Lkotlinx/io/Source;", "getReadBuffer", "()Lkotlinx/io/Source;", "getReadBuffer$annotations", "readBuffer", "Lkotlinx/io/Sink;", "getWriteBuffer", "()Lkotlinx/io/Sink;", "getWriteBuffer$annotations", "writeBuffer", "getClosedCause", "()Ljava/lang/Throwable;", "closedCause", "isClosedForWrite", "isClosedForRead", "Slot", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ByteChannel implements ByteReadChannel, BufferedByteWriteChannel {
    volatile /* synthetic */ Object _closedCause;
    private final Buffer _readBuffer;
    private final Buffer _writeBuffer;
    private final boolean autoFlush;
    private volatile /* synthetic */ Object closeHandler;
    private final Buffer flushBuffer;
    private final Object flushBufferMutex;
    private volatile int flushBufferSize;
    volatile /* synthetic */ Object suspensionSlot;
    static final /* synthetic */ AtomicReferenceFieldUpdater suspensionSlot$FU = AtomicReferenceFieldUpdater.newUpdater(ByteChannel.class, Object.class, "suspensionSlot");
    static final /* synthetic */ AtomicReferenceFieldUpdater _closedCause$FU = AtomicReferenceFieldUpdater.newUpdater(ByteChannel.class, Object.class, "_closedCause");
    private static final /* synthetic */ AtomicReferenceFieldUpdater closeHandler$FU = AtomicReferenceFieldUpdater.newUpdater(ByteChannel.class, Object.class, "closeHandler");

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannel$awaitContent$1, reason: invalid class name */
    /* JADX INFO: compiled from: ByteChannel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteChannel", f = "ByteChannel.kt", i = {0, 0, 0, 0}, l = {325}, m = "awaitContent", n = {"this_$iv", "min", "$i$f$sleepWhile", "$i$f$suspendCancellableCoroutine"}, s = {"L$0", "I$0", "I$1", "I$2"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannel.this.awaitContent(0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannel$flush$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteChannel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteChannel", f = "ByteChannel.kt", i = {0, 0, 0}, l = {325}, m = "flush", n = {"this_$iv", "$i$f$sleepWhile", "$i$f$suspendCancellableCoroutine"}, s = {"L$0", "I$0", "I$1"}, v = 1)
    static final class C09961 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C09961(Continuation<? super C09961> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannel.this.flush(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannel$flushAndClose$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteChannel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteChannel", f = "ByteChannel.kt", i = {0, 0}, l = {150}, m = "flushAndClose", n = {"$this$flushAndClose_u24lambda_u240", "$i$a$-runCatching-ByteChannel$flushAndClose$2"}, s = {"L$0", "I$0"}, v = 1)
    static final class C09971 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C09971(Continuation<? super C09971> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannel.this.flushAndClose(this);
        }
    }

    public ByteChannel() {
        this(false, 1, null);
    }

    private static /* synthetic */ void getFlushBufferMutex$annotations() {
    }

    public static /* synthetic */ void getHasFreeSpace$annotations() {
    }

    public static /* synthetic */ void getReadBuffer$annotations() {
    }

    public static /* synthetic */ void getWriteBuffer$annotations() {
    }

    static final void invokeOnClose$lambda$0() {
    }

    static final void invokeOnClose$lambda$1() {
    }

    public ByteChannel(boolean z) {
        this.autoFlush = z;
        this.flushBuffer = new Buffer();
        this.flushBufferMutex = new Object();
        this.suspensionSlot = Slot.Empty.INSTANCE;
        this._readBuffer = new Buffer();
        this._writeBuffer = new Buffer();
        this._closedCause = null;
        this.closeHandler = null;
    }

    public /* synthetic */ ByteChannel(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public boolean getAutoFlush() {
        return this.autoFlush;
    }

    public final boolean getHasFreeSpace() {
        return this.flushBufferSize < 1048576;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Source getReadBuffer() throws Throwable {
        ClosedReadChannelException closedReadChannelExceptionCancellationException;
        CloseToken closeToken = (CloseToken) this._closedCause;
        if (closeToken != null) {
            CloseToken.Companion companion = CloseToken.INSTANCE;
            Throwable th = closeToken.origin;
            if (th == null) {
                closedReadChannelExceptionCancellationException = null;
            } else if (th instanceof CopyableThrowable) {
                closedReadChannelExceptionCancellationException = ((CopyableThrowable) closeToken.origin).createCopy();
            } else {
                closedReadChannelExceptionCancellationException = th instanceof CancellationException ? kotlinx.coroutines.ExceptionsKt.CancellationException(((CancellationException) closeToken.origin).getMessage(), closeToken.origin) : new ClosedReadChannelException(closeToken.origin);
            }
            if (closedReadChannelExceptionCancellationException != null) {
                throw closedReadChannelExceptionCancellationException;
            }
        }
        if (this._readBuffer.exhausted()) {
            moveFlushToReadBuffer();
        }
        return this._readBuffer;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Sink getWriteBuffer() throws Throwable {
        ClosedWriteChannelException closedWriteChannelExceptionCancellationException;
        if (isClosedForWrite()) {
            CloseToken closeToken = (CloseToken) this._closedCause;
            if (closeToken != null) {
                CloseToken.Companion companion = CloseToken.INSTANCE;
                Throwable th = closeToken.origin;
                if (th == null) {
                    closedWriteChannelExceptionCancellationException = null;
                } else if (th instanceof CopyableThrowable) {
                    closedWriteChannelExceptionCancellationException = ((CopyableThrowable) closeToken.origin).createCopy();
                } else {
                    closedWriteChannelExceptionCancellationException = th instanceof CancellationException ? kotlinx.coroutines.ExceptionsKt.CancellationException(((CancellationException) closeToken.origin).getMessage(), closeToken.origin) : new ClosedWriteChannelException(closeToken.origin);
                }
                if (closedWriteChannelExceptionCancellationException != null) {
                    throw closedWriteChannelExceptionCancellationException;
                }
            }
            throw new ClosedWriteChannelException(null, 1, null);
        }
        return this._writeBuffer;
    }

    @Override // io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel
    public Throwable getClosedCause() {
        CloseToken closeToken = (CloseToken) this._closedCause;
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
    public boolean isClosedForWrite() {
        return this._closedCause != null;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public boolean isClosedForRead() {
        if (getClosedCause() == null) {
            return isClosedForWrite() && this.flushBufferSize == 0 && this._readBuffer.exhausted();
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // io.ktor.utils.io.ByteReadChannel
    public Object awaitContent(int i, Continuation<? super Boolean> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        int i2;
        ByteChannel byteChannel;
        int i3;
        Object result;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = anonymousClass1.label;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            ByteReadChannelOperationsKt.rethrowCloseCauseIfNeeded(this);
            i2 = i;
            if (this._readBuffer.getSizeMut() >= i2) {
                return Boxing.boxBoolean(true);
            }
            byteChannel = this;
            i3 = 0;
        } else {
            if (i4 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i5 = anonymousClass1.I$2;
            i3 = anonymousClass1.I$1;
            int i6 = anonymousClass1.I$0;
            byteChannel = (ByteChannel) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            i2 = i6;
        }
        do {
            long j = i2;
            if (((long) this.flushBufferSize) + this._readBuffer.getSizeMut() >= j || this._closedCause != null) {
                if (this._readBuffer.getSizeMut() < 1048576) {
                    moveFlushToReadBuffer();
                }
                return Boxing.boxBoolean(this._readBuffer.getSizeMut() >= j);
            }
            anonymousClass1.L$0 = byteChannel;
            anonymousClass1.I$0 = i2;
            anonymousClass1.I$1 = i3;
            anonymousClass1.I$2 = 0;
            anonymousClass1.label = 1;
            AnonymousClass1 anonymousClass2 = anonymousClass1;
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(anonymousClass2), 1);
            cancellableContinuationImpl.initCancellability();
            Slot.Read read = new Slot.Read(cancellableContinuationImpl);
            Slot slot = (Slot) byteChannel.suspensionSlot;
            boolean z = slot instanceof Slot.Closed;
            if (!z && !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(suspensionSlot$FU, byteChannel, slot, read)) {
                read.resume();
            } else {
                if (slot instanceof Slot.Read) {
                    Slot.Task task = (Slot.Task) slot;
                    task.resume(new ConcurrentIOException(read.taskName(), task.getCreated()));
                } else if (slot instanceof Slot.Task) {
                    ((Slot.Task) slot).resume();
                } else if (z) {
                    read.resume(((Slot.Closed) slot).getCause());
                } else if (!Intrinsics.areEqual(slot, Slot.Empty.INSTANCE)) {
                    throw new NoWhenBranchMatchedException();
                }
                if (((long) this.flushBufferSize) + this._readBuffer.getSizeMut() >= j || this._closedCause != null) {
                    Slot slot2 = (Slot) byteChannel.suspensionSlot;
                    if ((slot2 instanceof Slot.Read) && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(suspensionSlot$FU, byteChannel, slot2, Slot.Empty.INSTANCE)) {
                        ((Slot.Task) slot2).resume();
                    }
                }
            }
            result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(anonymousClass2);
            }
        } while (result != coroutine_suspended);
        return coroutine_suspended;
    }

    private final void moveFlushToReadBuffer() {
        synchronized (this.flushBufferMutex) {
            this.flushBuffer.transferTo(this._readBuffer);
            this.flushBufferSize = 0;
            Unit unit = Unit.INSTANCE;
        }
        Slot slot = (Slot) this.suspensionSlot;
        if ((slot instanceof Slot.Write) && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(suspensionSlot$FU, this, slot, Slot.Empty.INSTANCE)) {
            ((Slot.Task) slot).resume();
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object flush(Continuation<? super Unit> continuation) throws Throwable {
        C09961 c09961;
        ByteChannel byteChannel;
        int i;
        if (continuation instanceof C09961) {
            c09961 = (C09961) continuation;
            if ((c09961.label & Integer.MIN_VALUE) != 0) {
                c09961.label -= Integer.MIN_VALUE;
            } else {
                c09961 = new C09961(continuation);
            }
        } else {
            c09961 = new C09961(continuation);
        }
        Object obj = c09961.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c09961.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            ByteReadChannelOperationsKt.rethrowCloseCauseIfNeeded(this);
            flushWriteBuffer();
            if (getHasFreeSpace()) {
                return Unit.INSTANCE;
            }
            byteChannel = this;
            i = 0;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c09961.I$1;
            i = c09961.I$0;
            byteChannel = (ByteChannel) c09961.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (!getHasFreeSpace() && this._closedCause == null) {
            c09961.L$0 = byteChannel;
            c09961.I$0 = i;
            c09961.I$1 = 0;
            c09961.label = 1;
            C09961 c09962 = c09961;
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(c09962), 1);
            cancellableContinuationImpl.initCancellability();
            Slot.Write write = new Slot.Write(cancellableContinuationImpl);
            Slot slot = (Slot) byteChannel.suspensionSlot;
            boolean z = slot instanceof Slot.Closed;
            if (!z && !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(suspensionSlot$FU, byteChannel, slot, write)) {
                write.resume();
            } else {
                if (slot instanceof Slot.Write) {
                    Slot.Task task = (Slot.Task) slot;
                    task.resume(new ConcurrentIOException(write.taskName(), task.getCreated()));
                } else if (slot instanceof Slot.Task) {
                    ((Slot.Task) slot).resume();
                } else if (z) {
                    write.resume(((Slot.Closed) slot).getCause());
                } else if (!Intrinsics.areEqual(slot, Slot.Empty.INSTANCE)) {
                    throw new NoWhenBranchMatchedException();
                }
                if (getHasFreeSpace() || this._closedCause != null) {
                    Slot slot2 = (Slot) byteChannel.suspensionSlot;
                    if ((slot2 instanceof Slot.Write) && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(suspensionSlot$FU, byteChannel, slot2, Slot.Empty.INSTANCE)) {
                        ((Slot.Task) slot2).resume();
                    }
                }
            }
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(c09962);
            }
            if (result == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }

    @Override // io.ktor.utils.io.BufferedByteWriteChannel
    public void flushWriteBuffer() {
        if (this._writeBuffer.exhausted()) {
            return;
        }
        synchronized (this.flushBufferMutex) {
            int sizeMut = (int) this._writeBuffer.getSizeMut();
            this.flushBuffer.transferFrom(this._writeBuffer);
            this.flushBufferSize += sizeMut;
            Unit unit = Unit.INSTANCE;
        }
        Slot slot = (Slot) this.suspensionSlot;
        if ((slot instanceof Slot.Read) && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(suspensionSlot$FU, this, slot, Slot.Empty.INSTANCE)) {
            ((Slot.Task) slot).resume();
        }
    }

    @Override // io.ktor.utils.io.BufferedByteWriteChannel
    public void close() {
        flushWriteBuffer();
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_closedCause$FU, this, null, CloseTokenKt.getCLOSED())) {
            closeSlot(null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object flushAndClose(Continuation<? super Unit> continuation) {
        C09971 c09971;
        if (continuation instanceof C09971) {
            c09971 = (C09971) continuation;
            if ((c09971.label & Integer.MIN_VALUE) != 0) {
                c09971.label -= Integer.MIN_VALUE;
            } else {
                c09971 = new C09971(continuation);
            }
        } else {
            c09971 = new C09971(continuation);
        }
        Object obj = c09971.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09971.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Result.Companion companion = Result.INSTANCE;
                ByteChannel byteChannel = this;
                c09971.L$0 = SpillingKt.nullOutSpilledVariable(this);
                c09971.I$0 = 0;
                c09971.label = 1;
                if (flush(c09971) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i2 = c09971.I$0;
                ResultKt.throwOnFailure(obj);
            }
            Result.m8936constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_closedCause$FU, this, null, CloseTokenKt.getCLOSED())) {
            return Unit.INSTANCE;
        }
        closeSlot(null);
        return Unit.INSTANCE;
    }

    @Override // io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel
    public void cancel(Throwable cause) {
        if (this._closedCause != null) {
            return;
        }
        CloseToken closeToken = new CloseToken(cause);
        ClosedByteChannelException closedByteChannelExceptionCancellationException = null;
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_closedCause$FU, this, null, closeToken);
        CloseToken.Companion companion = CloseToken.INSTANCE;
        Throwable th = closeToken.origin;
        if (th != null) {
            if (th instanceof CopyableThrowable) {
                closedByteChannelExceptionCancellationException = ((CopyableThrowable) closeToken.origin).createCopy();
            } else {
                closedByteChannelExceptionCancellationException = th instanceof CancellationException ? kotlinx.coroutines.ExceptionsKt.CancellationException(((CancellationException) closeToken.origin).getMessage(), closeToken.origin) : new ClosedByteChannelException(closeToken.origin);
            }
        }
        closeSlot(closedByteChannelExceptionCancellationException);
    }

    public final DisposableHandle invokeOnClose$ktor_io(final Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        CloseToken closeToken = (CloseToken) this._closedCause;
        ClosedByteChannelException closedByteChannelExceptionCancellationException = null;
        if (closeToken != null) {
            CloseToken.Companion companion = CloseToken.INSTANCE;
            Throwable th = closeToken.origin;
            if (th != null) {
                if (th instanceof CopyableThrowable) {
                    closedByteChannelExceptionCancellationException = ((CopyableThrowable) closeToken.origin).createCopy();
                } else {
                    closedByteChannelExceptionCancellationException = th instanceof CancellationException ? kotlinx.coroutines.ExceptionsKt.CancellationException(((CancellationException) closeToken.origin).getMessage(), closeToken.origin) : new ClosedByteChannelException(closeToken.origin);
                }
            }
            handler.invoke(closedByteChannelExceptionCancellationException);
            return new DisposableHandle() { // from class: io.ktor.utils.io.ByteChannel$$ExternalSyntheticLambda0
                @Override // kotlinx.coroutines.DisposableHandle
                public final void dispose() {
                    ByteChannel.invokeOnClose$lambda$0();
                }
            };
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = closeHandler$FU;
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, null, handler)) {
            throw new IllegalStateException("Only one invokeOnClose handler is supported per channel".toString());
        }
        CloseToken closeToken2 = (CloseToken) this._closedCause;
        if (closeToken2 != null && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, handler, null)) {
            CloseToken.Companion companion2 = CloseToken.INSTANCE;
            Throwable th2 = closeToken2.origin;
            if (th2 != null) {
                if (th2 instanceof CopyableThrowable) {
                    closedByteChannelExceptionCancellationException = ((CopyableThrowable) closeToken2.origin).createCopy();
                } else {
                    closedByteChannelExceptionCancellationException = th2 instanceof CancellationException ? kotlinx.coroutines.ExceptionsKt.CancellationException(((CancellationException) closeToken2.origin).getMessage(), closeToken2.origin) : new ClosedByteChannelException(closeToken2.origin);
                }
            }
            handler.invoke(closedByteChannelExceptionCancellationException);
            return new DisposableHandle() { // from class: io.ktor.utils.io.ByteChannel$$ExternalSyntheticLambda1
                @Override // kotlinx.coroutines.DisposableHandle
                public final void dispose() {
                    ByteChannel.invokeOnClose$lambda$1();
                }
            };
        }
        return new DisposableHandle() { // from class: io.ktor.utils.io.ByteChannel$$ExternalSyntheticLambda2
            @Override // kotlinx.coroutines.DisposableHandle
            public final void dispose() {
                AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(ByteChannel.closeHandler$FU, this.f$0, handler, null);
            }
        };
    }

    public String toString() {
        return "ByteChannel[" + hashCode() + AbstractJsonLexerKt.END_LIST;
    }

    private final /* synthetic */ <TaskType extends Slot.Task> Object sleepWhile(Function1<? super Continuation<? super Unit>, ? extends TaskType> function1, Function0<Boolean> function0, Continuation<? super Unit> continuation) {
        while (function0.invoke().booleanValue()) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            TaskType tasktypeInvoke = function1.invoke(cancellableContinuationImpl);
            Slot slot = (Slot) this.suspensionSlot;
            boolean z = slot instanceof Slot.Closed;
            if (!z && !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(suspensionSlot$FU, this, slot, tasktypeInvoke)) {
                tasktypeInvoke.resume();
                Unit unit = Unit.INSTANCE;
            } else {
                Intrinsics.reifiedOperationMarker(3, "TaskType");
                boolean z2 = slot instanceof Slot.Task;
                if (z2) {
                    Slot.Task task = (Slot.Task) slot;
                    task.resume(new ConcurrentIOException(tasktypeInvoke.taskName(), task.getCreated()));
                } else if (z2) {
                    ((Slot.Task) slot).resume();
                } else if (z) {
                    tasktypeInvoke.resume(((Slot.Closed) slot).getCause());
                    Unit unit2 = Unit.INSTANCE;
                } else if (!Intrinsics.areEqual(slot, Slot.Empty.INSTANCE)) {
                    throw new NoWhenBranchMatchedException();
                }
                if (!function0.invoke().booleanValue()) {
                    Slot slot2 = (Slot) this.suspensionSlot;
                    Intrinsics.reifiedOperationMarker(3, "TaskType");
                    if ((slot2 instanceof Slot.Task) && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(suspensionSlot$FU, this, slot2, Slot.Empty.INSTANCE)) {
                        ((Slot.Task) slot2).resume();
                    }
                }
            }
            Unit unit3 = Unit.INSTANCE;
            if (cancellableContinuationImpl.getResult() == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
        }
        return Unit.INSTANCE;
    }

    private final /* synthetic */ <Expected extends Slot.Task> void resumeSlot() {
        Slot slot = (Slot) this.suspensionSlot;
        Intrinsics.reifiedOperationMarker(3, "Expected");
        if ((slot instanceof Slot.Task) && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(suspensionSlot$FU, this, slot, Slot.Empty.INSTANCE)) {
            ((Slot.Task) slot).resume();
        }
    }

    private final void closeSlot(Throwable cause) {
        Slot slot = (Slot) suspensionSlot$FU.getAndSet(this, cause != null ? new Slot.Closed(cause) : Slot.INSTANCE.getCLOSED());
        if (slot instanceof Slot.Task) {
            ((Slot.Task) slot).resume(cause);
        }
        Function1 function1 = (Function1) closeHandler$FU.getAndSet(this, null);
        if (function1 != null) {
            function1.invoke(cause);
        }
    }

    private final /* synthetic */ <TaskType extends Slot.Task> void trySuspend(TaskType slot, Function0<Boolean> shouldSleep) {
        Slot slot2 = (Slot) this.suspensionSlot;
        boolean z = slot2 instanceof Slot.Closed;
        if (!z && !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(suspensionSlot$FU, this, slot2, slot)) {
            slot.resume();
            return;
        }
        Intrinsics.reifiedOperationMarker(3, "TaskType");
        boolean z2 = slot2 instanceof Slot.Task;
        if (z2) {
            Slot.Task task = (Slot.Task) slot2;
            task.resume(new ConcurrentIOException(slot.taskName(), task.getCreated()));
        } else if (z2) {
            ((Slot.Task) slot2).resume();
        } else if (z) {
            slot.resume(((Slot.Closed) slot2).getCause());
            return;
        } else if (!Intrinsics.areEqual(slot2, Slot.Empty.INSTANCE)) {
            throw new NoWhenBranchMatchedException();
        }
        if (shouldSleep.invoke().booleanValue()) {
            return;
        }
        Slot slot3 = (Slot) this.suspensionSlot;
        Intrinsics.reifiedOperationMarker(3, "TaskType");
        if ((slot3 instanceof Slot.Task) && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(suspensionSlot$FU, this, slot3, Slot.Empty.INSTANCE)) {
            ((Slot.Task) slot3).resume();
        }
    }

    /* JADX INFO: compiled from: ByteChannel.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\br\u0018\u0000 \u00022\u00020\u0001:\u0006\u0002\u0003\u0004\u0005\u0006\u0007\u0082\u0001\u0003\b\t\n¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lio/ktor/utils/io/ByteChannel$Slot;", "", "Companion", "Empty", "Closed", "Task", "Read", "Write", "Lio/ktor/utils/io/ByteChannel$Slot$Closed;", "Lio/ktor/utils/io/ByteChannel$Slot$Empty;", "Lio/ktor/utils/io/ByteChannel$Slot$Task;", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private interface Slot {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannel$Slot$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: ByteChannel.kt */
        public final /* synthetic */ class CC {
            static {
                Companion companion = Slot.INSTANCE;
            }

            public static Closed getCLOSED() {
                return Slot.INSTANCE.getCLOSED();
            }

            /* JADX INFO: renamed from: getRESUME-d1pmJ48, reason: not valid java name */
            public static Object m8861getRESUMEd1pmJ48() {
                return Slot.INSTANCE.m8863getRESUMEd1pmJ48();
            }
        }

        /* JADX INFO: compiled from: ByteChannel.kt */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u0012\u0004\b\t\u0010\u0003\u001a\u0004\b\u0007\u0010\bR&\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\f\u0010\r\u0012\u0004\b\u0010\u0010\u0003\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lio/ktor/utils/io/ByteChannel$Slot$Companion;", "", "<init>", "()V", "Lio/ktor/utils/io/ByteChannel$Slot$Closed;", "CLOSED", "Lio/ktor/utils/io/ByteChannel$Slot$Closed;", "getCLOSED", "()Lio/ktor/utils/io/ByteChannel$Slot$Closed;", "getCLOSED$annotations", "Lkotlin/Result;", "", "RESUME", "Ljava/lang/Object;", "getRESUME-d1pmJ48", "()Ljava/lang/Object;", "getRESUME-d1pmJ48$annotations", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            private static final Closed CLOSED = new Closed(null);
            private static final Object RESUME;

            @JvmStatic
            public static /* synthetic */ void getCLOSED$annotations() {
            }

            @JvmStatic
            /* JADX INFO: renamed from: getRESUME-d1pmJ48$annotations, reason: not valid java name */
            public static /* synthetic */ void m8862getRESUMEd1pmJ48$annotations() {
            }

            private Companion() {
            }

            static {
                Result.Companion companion = Result.INSTANCE;
                RESUME = Result.m8936constructorimpl(Unit.INSTANCE);
            }

            public final Closed getCLOSED() {
                return CLOSED;
            }

            /* JADX INFO: renamed from: getRESUME-d1pmJ48, reason: not valid java name */
            public final Object m8863getRESUMEd1pmJ48() {
                return RESUME;
            }
        }

        /* JADX INFO: compiled from: ByteChannel.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÖ\u0083\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\n\u001a\u00020\tHÖ\u0081\u0004¢\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\r\u001a\u00020\fHÖ\u0081\u0004¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/utils/io/ByteChannel$Slot$Empty;", "Lio/ktor/utils/io/ByteChannel$Slot;", "<init>", "()V", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class Empty implements Slot {
            public static final Empty INSTANCE = new Empty();

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Empty)) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return -231472095;
            }

            public String toString() {
                return "Empty";
            }

            private Empty() {
            }
        }

        /* JADX INFO: compiled from: ByteChannel.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nHÖ\u0083\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u0010\u001a\u00020\u000fHÖ\u0081\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012HÖ\u0081\u0004¢\u0006\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0007¨\u0006\u0017"}, d2 = {"Lio/ktor/utils/io/ByteChannel$Slot$Closed;", "Lio/ktor/utils/io/ByteChannel$Slot;", "", "cause", "<init>", "(Ljava/lang/Throwable;)V", "component1", "()Ljava/lang/Throwable;", "copy", "(Ljava/lang/Throwable;)Lio/ktor/utils/io/ByteChannel$Slot$Closed;", "", "other", "", "equals", Signatures.MATH_TO_BOOLEAN, "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Ljava/lang/Throwable;", "getCause", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class Closed implements Slot {
            private final Throwable cause;

            public static /* synthetic */ Closed copy$default(Closed closed, Throwable th, int i, Object obj) {
                if ((i & 1) != 0) {
                    th = closed.cause;
                }
                return closed.copy(th);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Throwable getCause() {
                return this.cause;
            }

            public final Closed copy(Throwable cause) {
                return new Closed(cause);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Closed) && Intrinsics.areEqual(this.cause, ((Closed) other).cause);
            }

            public int hashCode() {
                Throwable th = this.cause;
                if (th == null) {
                    return 0;
                }
                return th.hashCode();
            }

            public String toString() {
                return "Closed(cause=" + this.cause + ')';
            }

            public Closed(Throwable th) {
                this.cause = th;
            }

            public final Throwable getCause() {
                return this.cause;
            }
        }

        /* JADX INFO: compiled from: ByteChannel.kt */
        @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\b\u0006\u0010\nR\u0016\u0010\r\u001a\u0004\u0018\u00010\b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u0082\u0001\u0002\u0012\u0013¨\u0006\u0014À\u0006\u0003"}, d2 = {"Lio/ktor/utils/io/ByteChannel$Slot$Task;", "Lio/ktor/utils/io/ByteChannel$Slot;", "", "taskName", "()Ljava/lang/String;", "", "resume", "()V", "", "throwable", "(Ljava/lang/Throwable;)V", "getCreated", "()Ljava/lang/Throwable;", "created", "Lkotlin/coroutines/Continuation;", "getContinuation", "()Lkotlin/coroutines/Continuation;", "continuation", "Lio/ktor/utils/io/ByteChannel$Slot$Read;", "Lio/ktor/utils/io/ByteChannel$Slot$Write;", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public interface Task extends Slot {
            Continuation<Unit> getContinuation();

            Throwable getCreated();

            void resume();

            void resume(Throwable throwable);

            String taskName();

            /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannel$Slot$Task$-CC, reason: invalid class name */
            /* JADX INFO: compiled from: ByteChannel.kt */
            public final /* synthetic */ class CC {
                public static void $default$resume(Task _this) {
                    _this.getContinuation().resumeWith(Slot.INSTANCE.m8863getRESUMEd1pmJ48());
                }

                public static /* synthetic */ void resume$default(Task task, Throwable th, int i, Object obj) {
                    if (obj != null) {
                        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resume");
                    }
                    if ((i & 1) != 0) {
                        th = null;
                    }
                    task.resume(th);
                }

                public static void $default$resume(Task _this, Throwable th) {
                    Object objM8863getRESUMEd1pmJ48;
                    Continuation<Unit> continuation = _this.getContinuation();
                    if (th != null) {
                        Result.Companion companion = Result.INSTANCE;
                        objM8863getRESUMEd1pmJ48 = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    } else {
                        objM8863getRESUMEd1pmJ48 = Slot.INSTANCE.m8863getRESUMEd1pmJ48();
                    }
                    continuation.resumeWith(objM8863getRESUMEd1pmJ48);
                }
            }

            /* JADX INFO: compiled from: ByteChannel.kt */
            @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
            public static final class DefaultImpls {
                @Deprecated
                public static void resume(Task task) {
                    CC.$default$resume(task);
                }

                @Deprecated
                public static void resume(Task task, Throwable th) {
                    CC.$default$resume(task, th);
                }
            }
        }

        /* JADX INFO: compiled from: ByteChannel.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\n\u001a\u0004\b\u000b\u0010\fR$\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lio/ktor/utils/io/ByteChannel$Slot$Read;", "Lio/ktor/utils/io/ByteChannel$Slot$Task;", "Lkotlin/coroutines/Continuation;", "", "continuation", "<init>", "(Lkotlin/coroutines/Continuation;)V", "", "taskName", "()Ljava/lang/String;", "Lkotlin/coroutines/Continuation;", "getContinuation", "()Lkotlin/coroutines/Continuation;", "", "created", "Ljava/lang/Throwable;", "getCreated", "()Ljava/lang/Throwable;", "setCreated", "(Ljava/lang/Throwable;)V", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Read implements Task {
            private final Continuation<Unit> continuation;
            private Throwable created;

            /* JADX WARN: Multi-variable type inference failed */
            public Read(Continuation<? super Unit> continuation) {
                Intrinsics.checkNotNullParameter(continuation, "continuation");
                this.continuation = continuation;
                if (ByteChannel_jvmKt.getDEVELOPMENT_MODE()) {
                    StringBuilder sb = new StringBuilder("ReadTask 0x");
                    String string = Integer.toString(getContinuation().hashCode(), CharsKt.checkRadix(16));
                    Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                    sb.append(string);
                    Throwable th = new Throwable(sb.toString());
                    kotlin.ExceptionsKt.stackTraceToString(th);
                    setCreated(th);
                }
            }

            @Override // io.ktor.utils.io.ByteChannel.Slot.Task
            public Continuation<Unit> getContinuation() {
                return this.continuation;
            }

            @Override // io.ktor.utils.io.ByteChannel.Slot.Task
            public /* bridge */ void resume() {
                Task.CC.$default$resume(this);
            }

            @Override // io.ktor.utils.io.ByteChannel.Slot.Task
            public /* bridge */ void resume(Throwable th) {
                Task.CC.$default$resume(this, th);
            }

            @Override // io.ktor.utils.io.ByteChannel.Slot.Task
            public Throwable getCreated() {
                return this.created;
            }

            public void setCreated(Throwable th) {
                this.created = th;
            }

            @Override // io.ktor.utils.io.ByteChannel.Slot.Task
            public String taskName() {
                return "read";
            }
        }

        /* JADX INFO: compiled from: ByteChannel.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\n\u001a\u0004\b\u000b\u0010\fR$\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lio/ktor/utils/io/ByteChannel$Slot$Write;", "Lio/ktor/utils/io/ByteChannel$Slot$Task;", "Lkotlin/coroutines/Continuation;", "", "continuation", "<init>", "(Lkotlin/coroutines/Continuation;)V", "", "taskName", "()Ljava/lang/String;", "Lkotlin/coroutines/Continuation;", "getContinuation", "()Lkotlin/coroutines/Continuation;", "", "created", "Ljava/lang/Throwable;", "getCreated", "()Ljava/lang/Throwable;", "setCreated", "(Ljava/lang/Throwable;)V", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Write implements Task {
            private final Continuation<Unit> continuation;
            private Throwable created;

            /* JADX WARN: Multi-variable type inference failed */
            public Write(Continuation<? super Unit> continuation) {
                Intrinsics.checkNotNullParameter(continuation, "continuation");
                this.continuation = continuation;
                if (ByteChannel_jvmKt.getDEVELOPMENT_MODE()) {
                    StringBuilder sb = new StringBuilder("WriteTask 0x");
                    String string = Integer.toString(getContinuation().hashCode(), CharsKt.checkRadix(16));
                    Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                    sb.append(string);
                    Throwable th = new Throwable(sb.toString());
                    kotlin.ExceptionsKt.stackTraceToString(th);
                    setCreated(th);
                }
            }

            @Override // io.ktor.utils.io.ByteChannel.Slot.Task
            public Continuation<Unit> getContinuation() {
                return this.continuation;
            }

            @Override // io.ktor.utils.io.ByteChannel.Slot.Task
            public /* bridge */ void resume() {
                Task.CC.$default$resume(this);
            }

            @Override // io.ktor.utils.io.ByteChannel.Slot.Task
            public /* bridge */ void resume(Throwable th) {
                Task.CC.$default$resume(this, th);
            }

            @Override // io.ktor.utils.io.ByteChannel.Slot.Task
            public Throwable getCreated() {
                return this.created;
            }

            public void setCreated(Throwable th) {
                this.created = th;
            }

            @Override // io.ktor.utils.io.ByteChannel.Slot.Task
            public String taskName() {
                return "write";
            }
        }
    }
}
