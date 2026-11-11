package io.ktor.utils.io;

import androidx.compose.runtime.ComposerKt;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.utils.io.core.BytePacketBuilderExtensions_jvmKt;
import io.ktor.utils.io.core.ByteReadPacketExtensions_jvmKt;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.io.Buffer;
import kotlinx.io.Segment;
import kotlinx.io.SourcesJvmKt;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.unsafe.UnsafeBufferOperations;

/* JADX INFO: compiled from: ByteReadChannelOperations.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001c\u0010\u0007\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0000H\u0086@¢\u0006\u0004\b\u0007\u0010\b\u001a\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a&\u0010\u0010\u001a\u00020\u000e*\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0086@¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001c\u0010\u0014\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0000H\u0086@¢\u0006\u0004\b\u0014\u0010\b\u001a\u001c\u0010\u0014\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\tH\u0086@¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u001c\u0010\u0016\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0000H\u0086@¢\u0006\u0004\b\u0016\u0010\b\u001a%\u0010\u0007\u001a\u00020\u0006*\u00020\u00022\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00060\u0017¢\u0006\u0004\b\u0007\u0010\u0019\u001a4\u0010\u001c\u001a\u00020\u0013*\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00062\u0014\b\b\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00130\u0017H\u0086H¢\u0006\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Ljava/nio/ByteBuffer;", "content", "Lio/ktor/utils/io/ByteReadChannel;", "ByteReadChannel", "(Ljava/nio/ByteBuffer;)Lio/ktor/utils/io/ByteReadChannel;", "buffer", "", "readAvailable", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/bytestring/ByteString;", "ByteString", "(Ljava/nio/ByteBuffer;)Lkotlinx/io/bytestring/ByteString;", "Ljava/nio/channels/WritableByteChannel;", TvContractCompat.PARAM_CHANNEL, "", "limit", "copyTo", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/channels/WritableByteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", TtmlNode.RUBY_DELIMITER, "", "skipDelimiter", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readFully", "Lkotlin/Function1;", "block", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlin/jvm/functions/Function1;)I", "min", "consumer", "read", "(Lio/ktor/utils/io/ByteReadChannel;ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ByteReadChannelOperations_jvmKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperations_jvmKt$copyTo$1, reason: invalid class name */
    /* JADX INFO: compiled from: ByteReadChannelOperations.jvm.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperations_jvmKt", f = "ByteReadChannelOperations.jvm.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {217, 221}, m = "copyTo", n = {"$this$copyTo", TvContractCompat.PARAM_CHANNEL, "copied", "copy", "$this$read$iv", "consumer$iv", "limit", "min$iv", "$i$f$read", "$this$copyTo", TvContractCompat.PARAM_CHANNEL, "copied", "copy", "$this$read$iv", "consumer$iv", "limit", "min$iv", "$i$f$read"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0", "I$0", "I$1"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperations_jvmKt.copyTo(null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperations_jvmKt$read$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.jvm.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperations_jvmKt", f = "ByteReadChannelOperations.jvm.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {ComposerKt.providerMapsKey, 208}, m = "read", n = {"$this$read", "consumer", "min", "$i$f$read", "$this$read", "consumer", "min", "$i$f$read"}, s = {"L$0", "L$1", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1"}, v = 1)
    static final class C10281 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C10281(Continuation<? super C10281> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperations_jvmKt.read(null, 0, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperations_jvmKt$readAvailable$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.jvm.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperations_jvmKt", f = "ByteReadChannelOperations.jvm.kt", i = {0, 0}, l = {44}, m = "readAvailable", n = {"$this$readAvailable", "buffer"}, s = {"L$0", "L$1"}, v = 1)
    static final class C10291 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C10291(Continuation<? super C10291> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperations_jvmKt.readAvailable(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperations_jvmKt$readFully$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.jvm.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperations_jvmKt", f = "ByteReadChannelOperations.jvm.kt", i = {0, 0}, l = {136}, m = "readFully", n = {"$this$readFully", "buffer"}, s = {"L$0", "L$1"}, v = 1)
    static final class C10301 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C10301(Continuation<? super C10301> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperations_jvmKt.readFully(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperations_jvmKt$skipDelimiter$2, reason: invalid class name */
    /* JADX INFO: compiled from: ByteReadChannelOperations.jvm.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperations_jvmKt", f = "ByteReadChannelOperations.jvm.kt", i = {0, 0, 0}, l = {118}, m = "skipDelimiter", n = {"$this$skipDelimiter", TtmlNode.RUBY_DELIMITER, CmcdData.OBJECT_TYPE_INIT_SEGMENT}, s = {"L$0", "L$1", "I$0"}, v = 1)
    static final class AnonymousClass2 extends ContinuationImpl {
        int I$0;
        int I$1;
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
            return ByteReadChannelOperations_jvmKt.skipDelimiter((ByteReadChannel) null, (ByteString) null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object readAvailable(ByteReadChannel byteReadChannel, ByteBuffer byteBuffer, Continuation<? super Integer> continuation) {
        C10291 c10291;
        if (continuation instanceof C10291) {
            c10291 = (C10291) continuation;
            if ((c10291.label & Integer.MIN_VALUE) != 0) {
                c10291.label -= Integer.MIN_VALUE;
            } else {
                c10291 = new C10291(continuation);
            }
        } else {
            c10291 = new C10291(continuation);
        }
        Object obj = c10291.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10291.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (byteReadChannel.isClosedForRead()) {
                return Boxing.boxInt(-1);
            }
            if (byteReadChannel.getReadBuffer().exhausted()) {
                c10291.L$0 = byteReadChannel;
                c10291.L$1 = byteBuffer;
                c10291.label = 1;
                if (ByteReadChannel.CC.awaitContent$default(byteReadChannel, 0, c10291, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ByteBuffer byteBuffer2 = (ByteBuffer) c10291.L$1;
            ByteReadChannel byteReadChannel2 = (ByteReadChannel) c10291.L$0;
            ResultKt.throwOnFailure(obj);
            byteBuffer = byteBuffer2;
            byteReadChannel = byteReadChannel2;
        }
        return byteReadChannel.isClosedForRead() ? Boxing.boxInt(-1) : Boxing.boxInt(SourcesJvmKt.readAtMostTo(byteReadChannel.getReadBuffer(), byteBuffer));
    }

    public static final ByteString ByteString(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        byte[] bArr = new byte[buffer.remaining()];
        buffer.mark();
        buffer.get(bArr);
        buffer.reset();
        return new ByteString(bArr, 0, 0, 6, null);
    }

    /* JADX WARN: Code duplicated, block: B:47:0x0125  */
    /* JADX WARN: Code duplicated, block: B:51:0x0136  */
    /* JADX WARN: Code duplicated, block: B:53:0x013d  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x0118 -> B:45:0x011d). Please report as a decompilation issue!!! */
    public static final Object copyTo(ByteReadChannel byteReadChannel, final WritableByteChannel writableByteChannel, final long j, Continuation<? super Long> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        Function1 function1;
        Ref.LongRef longRef;
        Object obj;
        Throwable closedCause;
        ByteReadChannel byteReadChannel2;
        Object obj2;
        long j2;
        Function1 function2;
        ByteReadChannel byteReadChannel3;
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
        Object objAwaitContent$default = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i != 0) {
            if (i == 1) {
                int i2 = anonymousClass1.I$1;
                int i3 = anonymousClass1.I$0;
                long j3 = anonymousClass1.J$0;
                Function1 function3 = (Function1) anonymousClass1.L$5;
                ByteReadChannel byteReadChannel4 = (ByteReadChannel) anonymousClass1.L$4;
                Function1 function4 = (Function1) anonymousClass1.L$3;
                Ref.LongRef longRef2 = (Ref.LongRef) anonymousClass1.L$2;
                Object obj3 = (WritableByteChannel) anonymousClass1.L$1;
                ByteReadChannel byteReadChannel5 = (ByteReadChannel) anonymousClass1.L$0;
                ResultKt.throwOnFailure(objAwaitContent$default);
                if (!((Boolean) objAwaitContent$default).booleanValue()) {
                    throw new EOFException("Not enough bytes available: required " + i3 + " but " + ByteReadChannelOperationsKt.getAvailableForRead(byteReadChannel4) + " available");
                }
                ByteReadPacketExtensions_jvmKt.read(byteReadChannel4.getReadBuffer(), function3);
                j = j3;
                function1 = function4;
                longRef = longRef2;
                obj = obj3;
                byteReadChannel = byteReadChannel5;
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i4 = anonymousClass1.I$1;
                int i5 = anonymousClass1.I$0;
                j2 = anonymousClass1.J$0;
                function2 = (Function1) anonymousClass1.L$5;
                byteReadChannel3 = (ByteReadChannel) anonymousClass1.L$4;
                function1 = (Function1) anonymousClass1.L$3;
                longRef = (Ref.LongRef) anonymousClass1.L$2;
                Object obj4 = (WritableByteChannel) anonymousClass1.L$1;
                byteReadChannel2 = (ByteReadChannel) anonymousClass1.L$0;
                ResultKt.throwOnFailure(objAwaitContent$default);
                obj2 = obj4;
            }
            if (((Boolean) objAwaitContent$default).booleanValue()) {
                ByteReadPacketExtensions_jvmKt.read(byteReadChannel3.getReadBuffer(), function2);
            }
            j = j2;
            obj = obj2;
            byteReadChannel = byteReadChannel2;
        } else {
            ResultKt.throwOnFailure(objAwaitContent$default);
            if (j < 0) {
                throw new IllegalArgumentException(("Limit shouldn't be negative: " + j).toString());
            }
            if ((writableByteChannel instanceof SelectableChannel) && !((SelectableChannel) writableByteChannel).isBlocking()) {
                throw new IllegalArgumentException("Non-blocking channels are not supported");
            }
            if (byteReadChannel.isClosedForRead()) {
                Throwable closedCause2 = byteReadChannel.getClosedCause();
                if (closedCause2 != null) {
                    throw closedCause2;
                }
                return Boxing.boxLong(0L);
            }
            final Ref.LongRef longRef3 = new Ref.LongRef();
            function1 = new Function1() { // from class: io.ktor.utils.io.ByteReadChannelOperations_jvmKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj5) {
                    return ByteReadChannelOperations_jvmKt.copyTo$lambda$2(j, longRef3, writableByteChannel, (ByteBuffer) obj5);
                }
            };
            longRef = longRef3;
            obj = writableByteChannel;
        }
        if (longRef.element < j || byteReadChannel.isClosedForRead()) {
            closedCause = byteReadChannel.getClosedCause();
            if (closedCause == null) {
                throw closedCause;
            }
            return Boxing.boxLong(longRef.element);
        }
        anonymousClass1.L$0 = byteReadChannel;
        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(obj);
        anonymousClass1.L$2 = longRef;
        anonymousClass1.L$3 = function1;
        anonymousClass1.L$4 = byteReadChannel;
        anonymousClass1.L$5 = function1;
        anonymousClass1.J$0 = j;
        anonymousClass1.I$0 = 0;
        anonymousClass1.I$1 = 0;
        anonymousClass1.label = 2;
        objAwaitContent$default = ByteReadChannel.CC.awaitContent$default(byteReadChannel, 0, anonymousClass1, 1, null);
        if (objAwaitContent$default == coroutine_suspended) {
            return coroutine_suspended;
        }
        byteReadChannel2 = byteReadChannel;
        obj2 = obj;
        j2 = j;
        function2 = function1;
        byteReadChannel3 = byteReadChannel2;
        if (((Boolean) objAwaitContent$default).booleanValue()) {
            ByteReadPacketExtensions_jvmKt.read(byteReadChannel3.getReadBuffer(), function2);
        }
        j = j2;
        obj = obj2;
        byteReadChannel = byteReadChannel2;
        if (longRef.element < j) {
        }
        closedCause = byteReadChannel.getClosedCause();
        if (closedCause == null) {
            throw closedCause;
        }
        return Boxing.boxLong(longRef.element);
    }

    public static /* synthetic */ Object copyTo$default(ByteReadChannel byteReadChannel, WritableByteChannel writableByteChannel, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyTo(byteReadChannel, writableByteChannel, j, continuation);
    }

    static final Unit copyTo$lambda$2(long j, Ref.LongRef longRef, WritableByteChannel writableByteChannel, ByteBuffer bb) throws IOException {
        Intrinsics.checkNotNullParameter(bb, "bb");
        long j2 = j - longRef.element;
        if (j2 < bb.remaining()) {
            int iLimit = bb.limit();
            bb.limit(bb.position() + ((int) j2));
            while (bb.hasRemaining()) {
                writableByteChannel.write(bb);
            }
            bb.limit(iLimit);
            longRef.element += j2;
        } else {
            long jWrite = 0;
            while (bb.hasRemaining()) {
                jWrite += (long) writableByteChannel.write(bb);
            }
            longRef.element += jWrite;
        }
        return Unit.INSTANCE;
    }

    public static final Object skipDelimiter(ByteReadChannel byteReadChannel, ByteBuffer byteBuffer, Continuation<? super Unit> continuation) throws EOFException {
        Object objSkipDelimiter = skipDelimiter(byteReadChannel, ByteString(byteBuffer), continuation);
        return objSkipDelimiter == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSkipDelimiter : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x004c  */
    /* JADX WARN: Code duplicated, block: B:18:0x005c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:19:0x005d  */
    /* JADX WARN: Code duplicated, block: B:22:0x006e  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x005d -> B:20:0x0062). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object skipDelimiter(io.ktor.utils.io.ByteReadChannel r7, kotlinx.io.bytestring.ByteString r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) throws java.io.EOFException {
        /*
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteReadChannelOperations_jvmKt.AnonymousClass2
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.ByteReadChannelOperations_jvmKt$skipDelimiter$2 r0 = (io.ktor.utils.io.ByteReadChannelOperations_jvmKt.AnonymousClass2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.ByteReadChannelOperations_jvmKt$skipDelimiter$2 r0 = new io.ktor.utils.io.ByteReadChannelOperations_jvmKt$skipDelimiter$2
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3e
            if (r2 != r3) goto L36
            int r7 = r0.I$1
            int r8 = r0.I$0
            java.lang.Object r2 = r0.L$1
            kotlinx.io.bytestring.ByteString r2 = (kotlinx.io.bytestring.ByteString) r2
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r4 = (io.ktor.utils.io.ByteReadChannel) r4
            kotlin.ResultKt.throwOnFailure(r9)
            goto L62
        L36:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3e:
            kotlin.ResultKt.throwOnFailure(r9)
            int r9 = r8.getSize()
            r2 = 0
            r6 = r8
            r8 = r7
            r7 = r9
            r9 = r6
        L4a:
            if (r2 >= r7) goto L7c
            r0.L$0 = r8
            r0.L$1 = r9
            r0.I$0 = r2
            r0.I$1 = r7
            r0.label = r3
            java.lang.Object r4 = io.ktor.utils.io.ByteReadChannelOperationsKt.readByte(r8, r0)
            if (r4 != r1) goto L5d
            return r1
        L5d:
            r6 = r4
            r4 = r8
            r8 = r2
            r2 = r9
            r9 = r6
        L62:
            java.lang.Number r9 = (java.lang.Number) r9
            byte r9 = r9.byteValue()
            byte r5 = r2.get(r8)
            if (r9 != r5) goto L74
            int r8 = r8 + 1
            r9 = r2
            r2 = r8
            r8 = r4
            goto L4a
        L74:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "Delimiter is not found"
            r7.<init>(r8)
            throw r7
        L7c:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelOperations_jvmKt.skipDelimiter(io.ktor.utils.io.ByteReadChannel, kotlinx.io.bytestring.ByteString, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0046  */
    /* JADX WARN: Code duplicated, block: B:19:0x0054 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:22:0x005d  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0052 -> B:20:0x0055). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object readFully(io.ktor.utils.io.ByteReadChannel r5, java.nio.ByteBuffer r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteReadChannelOperations_jvmKt.C10301
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.utils.io.ByteReadChannelOperations_jvmKt$readFully$1 r0 = (io.ktor.utils.io.ByteReadChannelOperations_jvmKt.C10301) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.utils.io.ByteReadChannelOperations_jvmKt$readFully$1 r0 = new io.ktor.utils.io.ByteReadChannelOperations_jvmKt$readFully$1
            r0.<init>(r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r5 = r0.L$1
            java.nio.ByteBuffer r5 = (java.nio.ByteBuffer) r5
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r6 = (io.ktor.utils.io.ByteReadChannel) r6
            kotlin.ResultKt.throwOnFailure(r7)
            r4 = r6
            r6 = r5
            r5 = r4
            goto L55
        L35:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3d:
            kotlin.ResultKt.throwOnFailure(r7)
        L40:
            boolean r7 = r6.hasRemaining()
            if (r7 == 0) goto L82
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            r7 = 0
            r2 = 0
            java.lang.Object r7 = io.ktor.utils.io.ByteReadChannel.CC.awaitContent$default(r5, r7, r0, r3, r2)
            if (r7 != r1) goto L55
            return r1
        L55:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L65
            kotlinx.io.Source r7 = r5.getReadBuffer()
            kotlinx.io.SourcesJvmKt.readAtMostTo(r7, r6)
            goto L40
        L65:
            java.io.EOFException r5 = new java.io.EOFException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "Not enough bytes available: expected "
            r7.<init>(r0)
            int r6 = r6.remaining()
            r7.append(r6)
            java.lang.String r6 = " more bytes"
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            r5.<init>(r6)
            throw r5
        L82:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelOperations_jvmKt.readFully(io.ktor.utils.io.ByteReadChannel, java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final int readAvailable(ByteReadChannel byteReadChannel, Function1<? super ByteBuffer, Integer> block) throws EOFException {
        Intrinsics.checkNotNullParameter(byteReadChannel, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        if (byteReadChannel.isClosedForRead() || byteReadChannel.getReadBuffer().exhausted()) {
            return -1;
        }
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        Buffer bufferField = byteReadChannel.getReadBuffer().getBufferField();
        if (bufferField.exhausted()) {
            throw new IllegalArgumentException("Buffer is empty".toString());
        }
        Segment head = bufferField.getHead();
        Intrinsics.checkNotNull(head);
        byte[] bArrDataAsByteArray = head.dataAsByteArray(true);
        int pos = head.getPos();
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArrDataAsByteArray, pos, head.getLimit() - pos);
        Intrinsics.checkNotNull(byteBufferWrap);
        int iIntValue = block.invoke(byteBufferWrap).intValue();
        if (iIntValue == 0) {
            return iIntValue;
        }
        if (iIntValue < 0) {
            throw new IllegalStateException("Returned negative read bytes count");
        }
        if (iIntValue > head.getSize()) {
            throw new IllegalStateException("Returned too many bytes");
        }
        bufferField.skip(iIntValue);
        return iIntValue;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x006a, code lost:
    
        if (r8 == r1) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b1, code lost:
    
        if (r8 == r1) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object read(io.ktor.utils.io.ByteReadChannel r5, int r6, kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelOperations_jvmKt.read(io.ktor.utils.io.ByteReadChannel, int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object read$$forInline(ByteReadChannel byteReadChannel, int i, Function1<? super ByteBuffer, Unit> function1, Continuation<? super Unit> continuation) throws EOFException {
        if (i < 0) {
            throw new IllegalArgumentException("min should be positive or zero".toString());
        }
        if (i > 0) {
            if (!((Boolean) byteReadChannel.awaitContent(i, continuation)).booleanValue()) {
                throw new EOFException("Not enough bytes available: required " + i + " but " + ByteReadChannelOperationsKt.getAvailableForRead(byteReadChannel) + " available");
            }
            ByteReadPacketExtensions_jvmKt.read(byteReadChannel.getReadBuffer(), function1);
        } else if (((Boolean) ByteReadChannel.CC.awaitContent$default(byteReadChannel, 0, continuation, 1, null)).booleanValue()) {
            ByteReadPacketExtensions_jvmKt.read(byteReadChannel.getReadBuffer(), function1);
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object read$default(ByteReadChannel byteReadChannel, int i, Function1 function1, Continuation continuation, int i2, Object obj) throws EOFException {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        if (i < 0) {
            throw new IllegalArgumentException("min should be positive or zero".toString());
        }
        if (i > 0) {
            if (!((Boolean) byteReadChannel.awaitContent(i, continuation)).booleanValue()) {
                throw new EOFException("Not enough bytes available: required " + i + " but " + ByteReadChannelOperationsKt.getAvailableForRead(byteReadChannel) + " available");
            }
            ByteReadPacketExtensions_jvmKt.read(byteReadChannel.getReadBuffer(), function1);
        } else if (((Boolean) ByteReadChannel.CC.awaitContent$default(byteReadChannel, 0, continuation, 1, null)).booleanValue()) {
            ByteReadPacketExtensions_jvmKt.read(byteReadChannel.getReadBuffer(), function1);
        }
        return Unit.INSTANCE;
    }

    public static final ByteReadChannel ByteReadChannel(ByteBuffer content) {
        Intrinsics.checkNotNullParameter(content, "content");
        Buffer buffer = new Buffer();
        BytePacketBuilderExtensions_jvmKt.writeFully(buffer, content);
        return ByteChannelCtorKt.ByteReadChannel(buffer);
    }
}
