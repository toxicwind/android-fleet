package io.ktor.utils.io;

import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.utils.io.core.BytePacketBuilderKt;
import io.ktor.utils.io.core.ByteReadPacketKt;
import io.ktor.utils.io.core.StringsKt;
import java.nio.charset.Charset;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.io.Buffer;
import kotlinx.io.CoreKt;
import kotlinx.io.RawSource;
import kotlinx.io.Segment;
import kotlinx.io.SegmentKt;
import kotlinx.io.Sink;
import kotlinx.io.SinksKt;
import kotlinx.io.Source;
import kotlinx.io.unsafe.UnsafeBufferOperations;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ByteWriteChannelOperations.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000Ý\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\b\u0004*\u0001`\u001a\u001c\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0007\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0006H\u0086@¢\u0006\u0004\b\u0007\u0010\b\u001a\u001c\u0010\n\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\tH\u0086@¢\u0006\u0004\b\n\u0010\u000b\u001a\u001c\u0010\r\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\fH\u0086@¢\u0006\u0004\b\r\u0010\u000e\u001a\u001c\u0010\u0010\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000fH\u0086@¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001c\u0010\u0013\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0012H\u0086@¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001c\u0010\u0017\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0015H\u0086@¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u001c\u0010\u001b\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0019H\u0086@¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001c\u0010\u001e\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u001dH\u0086@¢\u0006\u0004\b\u001e\u0010\u001f\u001a0\u0010\"\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00152\b\b\u0002\u0010 \u001a\u00020\t2\b\b\u0002\u0010!\u001a\u00020\tH\u0086@¢\u0006\u0004\b\"\u0010#\u001a\u001c\u0010%\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u001a\u001a\u00020$H\u0086@¢\u0006\u0004\b%\u0010&\u001a$\u0010%\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020$2\u0006\u0010'\u001a\u00020\u0012H\u0086@¢\u0006\u0004\b%\u0010(\u001a\u001c\u0010)\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u001dH\u0086@¢\u0006\u0004\b)\u0010\u001f\u001a\u001c\u0010,\u001a\u00020\u0003*\u00020\u00002\u0006\u0010+\u001a\u00020*H\u0086@¢\u0006\u0004\b,\u0010-\u001a\u001c\u0010,\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0019H\u0086@¢\u0006\u0004\b,\u0010\u001c\u001a\u001b\u00100\u001a\u00020\u0003*\u00020\u00002\b\u0010/\u001a\u0004\u0018\u00010.¢\u0006\u0004\b0\u00101\u001a\u0014\u00103\u001a\u00020\u0003*\u000202H\u0086@¢\u0006\u0004\b3\u00104\u001a\u0015\u00107\u001a\u000605j\u0002`6*\u000202¢\u0006\u0004\b7\u00108\u001a6\u0010>\u001a\u00020=*\u0002022#\u0010<\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010.¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u000309¢\u0006\u0004\b>\u0010?\u001a!\u0010>\u001a\u00020\u0003*\u0002022\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00030@H\u0007¢\u0006\u0004\b>\u0010A\u001a\u0011\u0010B\u001a\u00020\u0003*\u000202¢\u0006\u0004\bB\u0010C\u001aN\u0010O\u001a\u00020N*\u00020D2\b\b\u0002\u0010F\u001a\u00020E2\b\b\u0002\u0010H\u001a\u00020G2'\u0010<\u001a#\b\u0001\u0012\u0004\u0012\u00020J\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030K\u0012\u0006\u0012\u0004\u0018\u00010L0I¢\u0006\u0002\bM¢\u0006\u0004\bO\u0010P\u001aL\u0010O\u001a\u00020N*\u00020D2\b\b\u0002\u0010F\u001a\u00020E2\u0006\u0010R\u001a\u00020Q2'\u0010<\u001a#\b\u0001\u0012\u0004\u0012\u00020J\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030K\u0012\u0006\u0012\u0004\u0018\u00010L0I¢\u0006\u0002\bM¢\u0006\u0004\bO\u0010S\u001a>\u0010V\u001a\u00020\t*\u00020\u00002\b\b\u0002\u0010T\u001a\u00020\t2\u001e\u0010<\u001a\u001a\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0UH\u0086@¢\u0006\u0004\bV\u0010W\u001a\u0014\u0010X\u001a\u00020\u0003*\u00020\u0000H\u0086@¢\u0006\u0004\bX\u0010Y\u001a/\u0010[\u001a\u00020\u0003\"\u0004\b\u0000\u0010Z*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000K\u0012\u0006\u0012\u0004\u0018\u00010L09H\u0000¢\u0006\u0004\b[\u0010\\\"\u0015\u0010]\u001a\u00020G*\u0002028F¢\u0006\u0006\u001a\u0004\b]\u0010^\"\u0015\u0010_\u001a\u00020G*\u0002028F¢\u0006\u0006\u001a\u0004\b_\u0010^\"\u0014\u0010a\u001a\u00020`8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010b¨\u0006c"}, d2 = {"Lio/ktor/utils/io/ByteWriteChannel;", "", ES6Iterator.VALUE_PROPERTY, "", "writeByte", "(Lio/ktor/utils/io/ByteWriteChannel;BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "writeShort", "(Lio/ktor/utils/io/ByteWriteChannel;SLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "writeInt", "(Lio/ktor/utils/io/ByteWriteChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "writeFloat", "(Lio/ktor/utils/io/ByteWriteChannel;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "writeDouble", "(Lio/ktor/utils/io/ByteWriteChannel;DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "writeLong", "(Lio/ktor/utils/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "array", "writeByteArray", "(Lio/ktor/utils/io/ByteWriteChannel;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/Source;", "source", "writeSource", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlinx/io/Source;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "writeString", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startIndex", "endIndex", "writeFully", "(Lio/ktor/utils/io/ByteWriteChannel;[BIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/RawSource;", "writeBuffer", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlinx/io/RawSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "length", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlinx/io/RawSource;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeStringUtf8", "Lkotlinx/io/Buffer;", "copy", "writePacket", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlinx/io/Buffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "cause", "close", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/lang/Throwable;)V", "Lio/ktor/utils/io/ChannelJob;", "join", "(Lio/ktor/utils/io/ChannelJob;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Ljava/util/concurrent/CancellationException;", "Lio/ktor/utils/io/CancellationException;", "getCancellationException", "(Lio/ktor/utils/io/ChannelJob;)Ljava/util/concurrent/CancellationException;", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "block", "Lkotlinx/coroutines/DisposableHandle;", "invokeOnCompletion", "(Lio/ktor/utils/io/ChannelJob;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "Lkotlin/Function0;", "(Lio/ktor/utils/io/ChannelJob;Lkotlin/jvm/functions/Function0;)V", "cancel", "(Lio/ktor/utils/io/ChannelJob;)V", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "", "autoFlush", "Lkotlin/Function2;", "Lio/ktor/utils/io/WriterScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "Lio/ktor/utils/io/WriterJob;", "writer", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function2;)Lio/ktor/utils/io/WriterJob;", "Lio/ktor/utils/io/ByteChannel;", TvContractCompat.PARAM_CHANNEL, "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lio/ktor/utils/io/ByteChannel;Lkotlin/jvm/functions/Function2;)Lio/ktor/utils/io/WriterJob;", "desiredSpace", "Lkotlin/Function3;", "write", "(Lio/ktor/utils/io/ByteWriteChannel;ILkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitFreeSpace", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "R", "fireAndForget", "(Lkotlin/jvm/functions/Function1;)V", "isCompleted", "(Lio/ktor/utils/io/ChannelJob;)Z", "isCancelled", "io/ktor/utils/io/ByteWriteChannelOperationsKt$NO_CALLBACK$1", "NO_CALLBACK", "Lio/ktor/utils/io/ByteWriteChannelOperationsKt$NO_CALLBACK$1;", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ByteWriteChannelOperationsKt {
    private static final ByteWriteChannelOperationsKt$NO_CALLBACK$1 NO_CALLBACK = new Continuation<Object>() { // from class: io.ktor.utils.io.ByteWriteChannelOperationsKt$NO_CALLBACK$1
        private final CoroutineContext context = EmptyCoroutineContext.INSTANCE;

        @Override // kotlin.coroutines.Continuation
        public void resumeWith(Object result) {
        }

        @Override // kotlin.coroutines.Continuation
        public CoroutineContext getContext() {
            return this.context;
        }
    };

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteWriteChannelOperationsKt$write$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteWriteChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteWriteChannelOperationsKt", f = "ByteWriteChannelOperations.kt", i = {0, 0, 0, 0, 0, 0}, l = {230}, m = "write", n = {"$this$write", "block", "desiredSpace", TtmlNode.ANNOTATION_POSITION_BEFORE, TtmlNode.ANNOTATION_POSITION_AFTER, "written"}, s = {"L$0", "L$1", "I$0", "I$1", "I$2", "I$3"}, v = 1)
    static final class C10311 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C10311(Continuation<? super C10311> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteWriteChannelOperationsKt.write(null, 0, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteWriteChannelOperationsKt$writePacket$2, reason: invalid class name */
    /* JADX INFO: compiled from: ByteWriteChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteWriteChannelOperationsKt", f = "ByteWriteChannelOperations.kt", i = {0, 0}, l = {122}, m = "writePacket", n = {"$this$writePacket", "source"}, s = {"L$0", "L$1"}, v = 1)
    static final class AnonymousClass2 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteWriteChannelOperationsKt.writePacket((ByteWriteChannel) null, (Source) null, this);
        }
    }

    public static final Object writeByte(ByteWriteChannel byteWriteChannel, byte b, Continuation<? super Unit> continuation) {
        byteWriteChannel.getWriteBuffer().writeByte(b);
        Object objFlushIfNeeded = ByteWriteChannelKt.flushIfNeeded(byteWriteChannel, continuation);
        return objFlushIfNeeded == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlushIfNeeded : Unit.INSTANCE;
    }

    public static final Object writeShort(ByteWriteChannel byteWriteChannel, short s, Continuation<? super Unit> continuation) {
        byteWriteChannel.getWriteBuffer().writeShort(s);
        Object objFlushIfNeeded = ByteWriteChannelKt.flushIfNeeded(byteWriteChannel, continuation);
        return objFlushIfNeeded == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlushIfNeeded : Unit.INSTANCE;
    }

    public static final Object writeInt(ByteWriteChannel byteWriteChannel, int i, Continuation<? super Unit> continuation) {
        byteWriteChannel.getWriteBuffer().writeInt(i);
        Object objFlushIfNeeded = ByteWriteChannelKt.flushIfNeeded(byteWriteChannel, continuation);
        return objFlushIfNeeded == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlushIfNeeded : Unit.INSTANCE;
    }

    public static final Object writeFloat(ByteWriteChannel byteWriteChannel, float f, Continuation<? super Unit> continuation) {
        SinksKt.writeFloat(byteWriteChannel.getWriteBuffer(), f);
        Object objFlushIfNeeded = ByteWriteChannelKt.flushIfNeeded(byteWriteChannel, continuation);
        return objFlushIfNeeded == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlushIfNeeded : Unit.INSTANCE;
    }

    public static final Object writeDouble(ByteWriteChannel byteWriteChannel, double d, Continuation<? super Unit> continuation) {
        SinksKt.writeDouble(byteWriteChannel.getWriteBuffer(), d);
        Object objFlushIfNeeded = ByteWriteChannelKt.flushIfNeeded(byteWriteChannel, continuation);
        return objFlushIfNeeded == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlushIfNeeded : Unit.INSTANCE;
    }

    public static final Object writeLong(ByteWriteChannel byteWriteChannel, long j, Continuation<? super Unit> continuation) {
        byteWriteChannel.getWriteBuffer().writeLong(j);
        Object objFlushIfNeeded = ByteWriteChannelKt.flushIfNeeded(byteWriteChannel, continuation);
        return objFlushIfNeeded == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlushIfNeeded : Unit.INSTANCE;
    }

    public static final Object writeByteArray(ByteWriteChannel byteWriteChannel, byte[] bArr, Continuation<? super Unit> continuation) {
        Sink.CC.write$default(byteWriteChannel.getWriteBuffer(), bArr, 0, 0, 6, null);
        Object objFlushIfNeeded = ByteWriteChannelKt.flushIfNeeded(byteWriteChannel, continuation);
        return objFlushIfNeeded == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlushIfNeeded : Unit.INSTANCE;
    }

    public static final Object writeSource(ByteWriteChannel byteWriteChannel, Source source, Continuation<? super Unit> continuation) {
        Object objWritePacket = writePacket(byteWriteChannel, source, continuation);
        return objWritePacket == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWritePacket : Unit.INSTANCE;
    }

    public static final Object writeString(ByteWriteChannel byteWriteChannel, String str, Continuation<? super Unit> continuation) {
        StringsKt.writeText$default(byteWriteChannel.getWriteBuffer(), str, 0, 0, (Charset) null, 14, (Object) null);
        Object objFlushIfNeeded = ByteWriteChannelKt.flushIfNeeded(byteWriteChannel, continuation);
        return objFlushIfNeeded == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlushIfNeeded : Unit.INSTANCE;
    }

    public static /* synthetic */ Object writeFully$default(ByteWriteChannel byteWriteChannel, byte[] bArr, int i, int i2, Continuation continuation, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return writeFully(byteWriteChannel, bArr, i, i2, continuation);
    }

    public static final Object writeFully(ByteWriteChannel byteWriteChannel, byte[] bArr, int i, int i2, Continuation<? super Unit> continuation) {
        byteWriteChannel.getWriteBuffer().write(bArr, i, i2);
        Object objFlushIfNeeded = ByteWriteChannelKt.flushIfNeeded(byteWriteChannel, continuation);
        return objFlushIfNeeded == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlushIfNeeded : Unit.INSTANCE;
    }

    public static final Object writeBuffer(ByteWriteChannel byteWriteChannel, RawSource rawSource, Continuation<? super Unit> continuation) {
        Object objWritePacket = writePacket(byteWriteChannel, CoreKt.buffered(rawSource), continuation);
        return objWritePacket == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWritePacket : Unit.INSTANCE;
    }

    public static final Object writeBuffer(ByteWriteChannel byteWriteChannel, RawSource rawSource, long j, Continuation<? super Unit> continuation) {
        byteWriteChannel.getWriteBuffer().write(rawSource, j);
        Object objFlushIfNeeded = ByteWriteChannelKt.flushIfNeeded(byteWriteChannel, continuation);
        return objFlushIfNeeded == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlushIfNeeded : Unit.INSTANCE;
    }

    public static final Object writeStringUtf8(ByteWriteChannel byteWriteChannel, String str, Continuation<? super Unit> continuation) {
        StringsKt.writeText$default(byteWriteChannel.getWriteBuffer(), str, 0, 0, (Charset) null, 14, (Object) null);
        Object objFlushIfNeeded = ByteWriteChannelKt.flushIfNeeded(byteWriteChannel, continuation);
        return objFlushIfNeeded == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlushIfNeeded : Unit.INSTANCE;
    }

    public static final Object writePacket(ByteWriteChannel byteWriteChannel, Buffer buffer, Continuation<? super Unit> continuation) {
        byteWriteChannel.getWriteBuffer().transferFrom(buffer);
        Object objFlushIfNeeded = ByteWriteChannelKt.flushIfNeeded(byteWriteChannel, continuation);
        return objFlushIfNeeded == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlushIfNeeded : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object writePacket(ByteWriteChannel byteWriteChannel, Source source, Continuation<? super Unit> continuation) {
        AnonymousClass2 anonymousClass2;
        if (continuation instanceof AnonymousClass2) {
            anonymousClass2 = (AnonymousClass2) continuation;
            if ((anonymousClass2.label & Integer.MIN_VALUE) != 0) {
                anonymousClass2.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass2 = new AnonymousClass2(continuation);
            }
        } else {
            anonymousClass2 = new AnonymousClass2(continuation);
        }
        Object obj = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass2.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Source source2 = (Source) anonymousClass2.L$1;
            ByteWriteChannel byteWriteChannel2 = (ByteWriteChannel) anonymousClass2.L$0;
            ResultKt.throwOnFailure(obj);
            source = source2;
            byteWriteChannel = byteWriteChannel2;
        }
        while (!source.exhausted()) {
            byteWriteChannel.getWriteBuffer().write(source, ByteReadPacketKt.getRemaining(source));
            anonymousClass2.L$0 = byteWriteChannel;
            anonymousClass2.L$1 = source;
            anonymousClass2.label = 1;
            if (ByteWriteChannelKt.flushIfNeeded(byteWriteChannel, anonymousClass2) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteWriteChannelOperationsKt$close$1, reason: invalid class name */
    /* JADX INFO: compiled from: ByteWriteChannelOperations.kt */
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

    public static final void close(ByteWriteChannel byteWriteChannel, Throwable th) {
        Intrinsics.checkNotNullParameter(byteWriteChannel, "<this>");
        if (th == null) {
            fireAndForget(new AnonymousClass1(byteWriteChannel));
        } else {
            byteWriteChannel.cancel(th);
        }
    }

    public static final Object join(ChannelJob channelJob, Continuation<? super Unit> continuation) {
        Object objJoin = channelJob.getJob().join(continuation);
        return objJoin == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objJoin : Unit.INSTANCE;
    }

    public static final boolean isCompleted(ChannelJob channelJob) {
        Intrinsics.checkNotNullParameter(channelJob, "<this>");
        return channelJob.getJob().isCompleted();
    }

    public static final boolean isCancelled(ChannelJob channelJob) {
        Intrinsics.checkNotNullParameter(channelJob, "<this>");
        return channelJob.getJob().isCancelled();
    }

    public static final CancellationException getCancellationException(ChannelJob channelJob) {
        Intrinsics.checkNotNullParameter(channelJob, "<this>");
        return channelJob.getJob().getCancellationException();
    }

    public static final DisposableHandle invokeOnCompletion(ChannelJob channelJob, Function1<? super Throwable, Unit> block) {
        Intrinsics.checkNotNullParameter(channelJob, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        return channelJob.getJob().invokeOnCompletion(block);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public static final /* synthetic */ void invokeOnCompletion(ChannelJob channelJob, final Function0 block) {
        Intrinsics.checkNotNullParameter(channelJob, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        channelJob.getJob().invokeOnCompletion(new Function1() { // from class: io.ktor.utils.io.ByteWriteChannelOperationsKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ByteWriteChannelOperationsKt.invokeOnCompletion$lambda$0(block, (Throwable) obj);
            }
        });
    }

    static final Unit invokeOnCompletion$lambda$0(Function0 function0, Throwable th) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    public static final void cancel(ChannelJob channelJob) {
        Intrinsics.checkNotNullParameter(channelJob, "<this>");
        Job.CC.cancel$default(channelJob.getJob(), (CancellationException) null, 1, (Object) null);
    }

    public static /* synthetic */ WriterJob writer$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return writer(coroutineScope, coroutineContext, z, (Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object>) function2);
    }

    public static final WriterJob writer(CoroutineScope coroutineScope, CoroutineContext coroutineContext, boolean z, Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        Intrinsics.checkNotNullParameter(block, "block");
        return writer(coroutineScope, coroutineContext, new ByteChannel(false, 1, null), block);
    }

    public static /* synthetic */ WriterJob writer$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, ByteChannel byteChannel, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return writer(coroutineScope, coroutineContext, byteChannel, (Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object>) function2);
    }

    public static final WriterJob writer(CoroutineScope coroutineScope, CoroutineContext coroutineContext, final ByteChannel channel, Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        Intrinsics.checkNotNullParameter(channel, "channel");
        Intrinsics.checkNotNullParameter(block, "block");
        Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, coroutineContext, null, new ByteWriteChannelOperationsKt$writer$job$1(block, channel, null), 2, null);
        jobLaunch$default.invokeOnCompletion(new Function1() { // from class: io.ktor.utils.io.ByteWriteChannelOperationsKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ByteWriteChannelOperationsKt.writer$lambda$0$0(channel, (Throwable) obj);
            }
        });
        return new WriterJob(channel, jobLaunch$default);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit writer$lambda$0$0(ByteChannel byteChannel, Throwable th) {
        if (th != null && !byteChannel.isClosedForWrite()) {
            byteChannel.cancel(th);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object write(ByteWriteChannel byteWriteChannel, int i, Function3<? super byte[], ? super Integer, ? super Integer, Integer> function3, Continuation<? super Integer> continuation) {
        C10311 c10311;
        int i2;
        if (continuation instanceof C10311) {
            c10311 = (C10311) continuation;
            if ((c10311.label & Integer.MIN_VALUE) != 0) {
                c10311.label -= Integer.MIN_VALUE;
            } else {
                c10311 = new C10311(continuation);
            }
        } else {
            c10311 = new C10311(continuation);
        }
        Object obj = c10311.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c10311.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            int size = BytePacketBuilderKt.getSize(byteWriteChannel.getWriteBuffer());
            UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
            Buffer bufferField = byteWriteChannel.getWriteBuffer().getBufferField();
            Segment segmentWritableSegment = bufferField.writableSegment(i);
            byte[] bArrDataAsByteArray = segmentWritableSegment.dataAsByteArray(false);
            int iIntValue = function3.invoke(bArrDataAsByteArray, Boxing.boxInt(segmentWritableSegment.getLimit()), Boxing.boxInt(bArrDataAsByteArray.length)).intValue();
            if (iIntValue == i) {
                segmentWritableSegment.writeBackData(bArrDataAsByteArray, iIntValue);
                segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iIntValue);
                bufferField.setSizeMut(bufferField.getSizeMut() + ((long) iIntValue));
            } else {
                if (iIntValue < 0 || iIntValue > segmentWritableSegment.getRemainingCapacity()) {
                    throw new IllegalStateException(("Invalid number of bytes written: " + iIntValue + ". Should be in 0.." + segmentWritableSegment.getRemainingCapacity()).toString());
                }
                if (iIntValue != 0) {
                    segmentWritableSegment.writeBackData(bArrDataAsByteArray, iIntValue);
                    segmentWritableSegment.setLimit(segmentWritableSegment.getLimit() + iIntValue);
                    bufferField.setSizeMut(bufferField.getSizeMut() + ((long) iIntValue));
                } else if (SegmentKt.isEmpty(segmentWritableSegment)) {
                    bufferField.recycleTail();
                }
            }
            int size2 = BytePacketBuilderKt.getSize(byteWriteChannel.getWriteBuffer());
            int i4 = size2 - size;
            c10311.L$0 = SpillingKt.nullOutSpilledVariable(byteWriteChannel);
            c10311.L$1 = SpillingKt.nullOutSpilledVariable(function3);
            c10311.I$0 = i;
            c10311.I$1 = size;
            c10311.I$2 = size2;
            c10311.I$3 = i4;
            c10311.label = 1;
            if (ByteWriteChannelKt.flushIfNeeded(byteWriteChannel, c10311) == coroutine_suspended) {
                return coroutine_suspended;
            }
            i2 = i4;
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i2 = c10311.I$3;
            int i5 = c10311.I$2;
            int i6 = c10311.I$1;
            int i7 = c10311.I$0;
            ResultKt.throwOnFailure(obj);
        }
        return Boxing.boxInt(i2);
    }

    public static /* synthetic */ Object write$default(ByteWriteChannel byteWriteChannel, int i, Function3 function3, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        return write(byteWriteChannel, i, function3, continuation);
    }

    public static final Object awaitFreeSpace(ByteWriteChannel byteWriteChannel, Continuation<? super Unit> continuation) {
        Object objFlush = byteWriteChannel.flush(continuation);
        return objFlush == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlush : Unit.INSTANCE;
    }

    public static final <R> void fireAndForget(Function1<? super Continuation<? super R>, ? extends Object> function1) {
        Intrinsics.checkNotNullParameter(function1, "<this>");
        CancellableKt.startCoroutineCancellable(function1, NO_CALLBACK);
    }
}
