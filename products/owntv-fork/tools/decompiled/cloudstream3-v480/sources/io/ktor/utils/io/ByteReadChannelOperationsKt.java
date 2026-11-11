package io.ktor.utils.io;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.utils.io.charsets.TooLongLineException;
import io.ktor.utils.io.core.BuffersKt;
import io.ktor.utils.io.core.BytePacketBuilderKt;
import io.ktor.utils.io.core.ByteReadPacketKt;
import io.ktor.utils.io.core.InputKt;
import java.io.EOFException;
import java.io.IOException;
import kotlin.Deprecated;
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
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.io.Buffer;
import kotlinx.io.ByteStringsKt;
import kotlinx.io.RawSink;
import kotlinx.io.Segment;
import kotlinx.io.Sink;
import kotlinx.io.Source;
import kotlinx.io.SourcesKt;
import kotlinx.io.Utf8Kt;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.unsafe.UnsafeBufferOperations;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.universalchardet.prober.HebrewProber;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: ByteReadChannelOperations.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0015\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0086@¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0014\u0010\u0005\u001a\u00020\u0004*\u00020\u0000H\u0086@¢\u0006\u0004\b\u0005\u0010\u0003\u001a\u0014\u0010\u0007\u001a\u00020\u0006*\u00020\u0000H\u0086@¢\u0006\u0004\b\u0007\u0010\u0003\u001a\u0014\u0010\t\u001a\u00020\b*\u00020\u0000H\u0086@¢\u0006\u0004\b\t\u0010\u0003\u001a\u0014\u0010\u000b\u001a\u00020\n*\u00020\u0000H\u0086@¢\u0006\u0004\b\u000b\u0010\u0003\u001a\u0014\u0010\r\u001a\u00020\f*\u00020\u0000H\u0086@¢\u0006\u0004\b\r\u0010\u0003\u001a\u0014\u0010\u000f\u001a\u00020\u000e*\u00020\u0000H\u0086@¢\u0006\u0004\b\u000f\u0010\u0003\u001a\u0014\u0010\u0011\u001a\u00020\u0010*\u00020\u0000H\u0086@¢\u0006\u0004\b\u0011\u0010\u0003\u001a\u001c\u0010\u0014\u001a\u00020\u0013*\u00020\u00002\u0006\u0010\u0012\u001a\u00020\nH\u0082@¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0014\u0010\u0017\u001a\u00020\u0016*\u00020\u0000H\u0086@¢\u0006\u0004\b\u0017\u0010\u0003\u001a\u001c\u0010\u0017\u001a\u00020\u0016*\u00020\u00002\u0006\u0010\u0018\u001a\u00020\nH\u0086@¢\u0006\u0004\b\u0017\u0010\u0015\u001a\u001c\u0010\u001b\u001a\u00020\u000e*\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0019H\u0086@¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001c\u0010\u001d\u001a\u00020\u000e*\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0019H\u0086@¢\u0006\u0004\b\u001d\u0010\u001c\u001a&\u0010!\u001a\u00020\u000e*\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010 \u001a\u00020\u000eH\u0086@¢\u0006\u0004\b!\u0010\"\u001a$\u0010\u001d\u001a\u00020\u000e*\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u000eH\u0086@¢\u0006\u0004\b\u001d\u0010#\u001a\u001c\u0010%\u001a\u00020\u0004*\u00020\u00002\u0006\u0010$\u001a\u00020\nH\u0086@¢\u0006\u0004\b%\u0010\u0015\u001a\u0014\u0010'\u001a\u00020&*\u00020\u0000H\u0086@¢\u0006\u0004\b'\u0010\u0003\u001a\u001c\u0010'\u001a\u00020&*\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u000eH\u0086@¢\u0006\u0004\b'\u0010(\u001a0\u0010,\u001a\u00020\n*\u00020\u00002\u0006\u0010)\u001a\u00020\u00042\b\b\u0002\u0010*\u001a\u00020\n2\b\b\u0002\u0010+\u001a\u00020\nH\u0086@¢\u0006\u0004\b,\u0010-\u001a-\u0010,\u001a\u00020\n*\u00020\u00002\u0006\u0010.\u001a\u00020\n2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\n0/¢\u0006\u0004\b,\u00101\u001aN\u0010<\u001a\u00020;*\u0002022\b\b\u0002\u00104\u001a\u0002032\b\b\u0002\u00105\u001a\u00020\u00012'\u00100\u001a#\b\u0001\u0012\u0004\u0012\u000207\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001308\u0012\u0006\u0012\u0004\u0018\u00010906¢\u0006\u0002\b:¢\u0006\u0004\b<\u0010=\u001aJ\u0010<\u001a\u00020;*\u0002022\u0006\u00104\u001a\u0002032\u0006\u0010\u001a\u001a\u00020>2'\u00100\u001a#\b\u0001\u0012\u0004\u0012\u000207\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001308\u0012\u0006\u0012\u0004\u0018\u00010906¢\u0006\u0002\b:¢\u0006\u0004\b<\u0010?\u001a\u001c\u0010A\u001a\u00020&*\u00020\u00002\u0006\u0010@\u001a\u00020\nH\u0086@¢\u0006\u0004\bA\u0010\u0015\u001a\u001c\u0010C\u001a\u00020\u0013*\u00020\u00002\u0006\u0010B\u001a\u00020\u000eH\u0086@¢\u0006\u0004\bC\u0010(\u001a\u001e\u0010D\u001a\u00020\u000e*\u00020\u00002\b\b\u0002\u0010\u0018\u001a\u00020\u000eH\u0086@¢\u0006\u0004\bD\u0010(\u001a \u0010F\u001a\u0004\u0018\u00010E*\u00020\u00002\b\b\u0002\u0010\u0018\u001a\u00020\nH\u0087@¢\u0006\u0004\bF\u0010\u0015\u001a*\u0010J\u001a\u00020\u0001*\u00020\u00002\n\u0010I\u001a\u00060Gj\u0002`H2\b\b\u0002\u0010\u0018\u001a\u00020\nH\u0087@¢\u0006\u0004\bJ\u0010K\u001a4\u0010J\u001a\u00020\u0001*\u00020\u00002\n\u0010I\u001a\u00060Gj\u0002`H2\b\b\u0002\u0010\u0018\u001a\u00020\n2\b\b\u0002\u0010M\u001a\u00020LH\u0087@¢\u0006\u0004\bN\u0010O\u001a \u0010Q\u001a\u0004\u0018\u00010E*\u00020\u00002\b\b\u0002\u0010M\u001a\u00020PH\u0086@¢\u0006\u0004\bQ\u0010R\u001a*\u0010S\u001a\u00020\u000e*\u00020\u00002\n\u0010I\u001a\u00060Gj\u0002`H2\b\b\u0002\u0010M\u001a\u00020PH\u0086@¢\u0006\u0004\bS\u0010T\u001a*\u0010U\u001a\u0004\u0018\u00010E*\u00020\u00002\b\b\u0002\u0010 \u001a\u00020\u000e2\b\b\u0002\u0010M\u001a\u00020PH\u0086@¢\u0006\u0004\bU\u0010V\u001a4\u0010W\u001a\u00020\u000e*\u00020\u00002\n\u0010I\u001a\u00060Gj\u0002`H2\b\b\u0002\u0010 \u001a\u00020\u000e2\b\b\u0002\u0010M\u001a\u00020PH\u0086@¢\u0006\u0004\bW\u0010X\u001a8\u0010[\u001a\u00020\u000e*\u00020\u00002\n\u0010I\u001a\u00060Gj\u0002`H2\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010Y\u001a\u00020\u00012\u0006\u0010Z\u001a\u00020\u0001H\u0082@¢\u0006\u0004\b[\u0010\\\u001a\u0017\u0010^\u001a\u00020]2\u0006\u0010 \u001a\u00020\u000eH\u0002¢\u0006\u0004\b^\u0010_\u001a\u0017\u0010a\u001a\u00020]2\u0006\u0010`\u001a\u00020\u000eH\u0002¢\u0006\u0004\ba\u0010_\u001aF\u0010c\u001a\u00020\n*\u00020\u000020\b\u0004\u00100\u001a*\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n08\u0012\u0006\u0012\u0004\u0018\u0001090bH\u0086H¢\u0006\u0004\bc\u0010d\u001a0\u0010g\u001a\u00020\u0013*\u00020\u00002\u0006\u0010I\u001a\u00020\u00042\b\b\u0002\u0010e\u001a\u00020\n2\b\b\u0002\u0010f\u001a\u00020\nH\u0086@¢\u0006\u0004\bg\u0010-\u001a\u0013\u0010h\u001a\u00020\u0013*\u00020\u0000H\u0007¢\u0006\u0004\bh\u0010i\u001a\u0013\u0010h\u001a\u00020\u0013*\u00020\u0019H\u0007¢\u0006\u0004\bh\u0010j\u001a\u0013\u0010h\u001a\u00020\u0013*\u00020>H\u0007¢\u0006\u0004\bh\u0010k\u001a8\u0010p\u001a\u00020\u000e*\u00020\u00002\u0006\u0010m\u001a\u00020l2\u0006\u0010n\u001a\u00020\u00192\b\b\u0002\u0010 \u001a\u00020\u000e2\b\b\u0002\u0010o\u001a\u00020\u0001H\u0086@¢\u0006\u0004\bp\u0010q\u001a\u001c\u0010s\u001a\u00020\u0001*\u00020\u00002\u0006\u0010r\u001a\u00020lH\u0086@¢\u0006\u0004\bs\u0010t\u001a\u001e\u0010u\u001a\u0004\u0018\u00010l*\u00020\u00002\u0006\u0010$\u001a\u00020\nH\u0086@¢\u0006\u0004\bu\u0010\u0015\"\u001b\u0010y\u001a\u00020\n*\u00020\u00198F¢\u0006\f\u0012\u0004\bx\u0010j\u001a\u0004\bv\u0010w\"\u0014\u0010z\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\bz\u0010{\"\u0014\u0010|\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b|\u0010{\"\u001c\u0010\u0080\u0001\u001a\u00020\n*\u00020\u00008F¢\u0006\f\u0012\u0004\b\u007f\u0010i\u001a\u0004\b}\u0010~¨\u0006\u0081\u0001"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "", "exhausted", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "toByteArray", "", "readByte", "", "readShort", "", "readInt", "", "readFloat", "", "readLong", "", "readDouble", "numberOfBytes", "", "awaitUntilReadable", "(Lio/ktor/utils/io/ByteReadChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/io/Buffer;", "readBuffer", "max", "Lio/ktor/utils/io/ByteWriteChannel;", TvContractCompat.PARAM_CHANNEL, "copyAndClose", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyTo", "Lkotlinx/io/RawSink;", "sink", "limit", "readTo", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlinx/io/RawSink;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", PeertubeParsingHelper.COUNT_KEY, "readByteArray", "Lkotlinx/io/Source;", "readRemaining", "(Lio/ktor/utils/io/ByteReadChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buffer", TypedValues.CycleType.S_WAVE_OFFSET, "length", "readAvailable", "(Lio/ktor/utils/io/ByteReadChannel;[BIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "min", "Lkotlin/Function1;", "block", "(Lio/ktor/utils/io/ByteReadChannel;ILkotlin/jvm/functions/Function1;)I", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "autoFlush", "Lkotlin/Function2;", "Lio/ktor/utils/io/ReaderScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "Lio/ktor/utils/io/ReaderJob;", "reader", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ZLkotlin/jvm/functions/Function2;)Lio/ktor/utils/io/ReaderJob;", "Lio/ktor/utils/io/ByteChannel;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lio/ktor/utils/io/ByteChannel;Lkotlin/jvm/functions/Function2;)Lio/ktor/utils/io/ReaderJob;", "packet", "readPacket", ES6Iterator.VALUE_PROPERTY, "discardExact", "discard", "", "readUTF8Line", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "out", "readUTF8LineTo", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/Appendable;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/ktor/utils/io/LineEndingMode;", "lineEnding", "readUTF8LineTo-RRvyBJ8", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/Appendable;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/ktor/utils/io/LineEnding;", "readLine", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/LineEnding;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readLineTo", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/Appendable;Lio/ktor/utils/io/LineEnding;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readLineStrict", "(Lio/ktor/utils/io/ByteReadChannel;JLio/ktor/utils/io/LineEnding;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readLineStrictTo", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/Appendable;JLio/ktor/utils/io/LineEnding;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lenientLineEnding", "throwOnIncompleteLine", "internalReadLineTo", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/Appendable;JZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "throwTooLongLineException", "(J)Ljava/lang/Void;", "consumed", "throwEndOfStreamException", "Lkotlin/Function4;", "read", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlin/jvm/functions/Function4;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", TtmlNode.END, "readFully", "rethrowCloseCauseIfNeeded", "(Lio/ktor/utils/io/ByteReadChannel;)V", "(Lio/ktor/utils/io/ByteWriteChannel;)V", "(Lio/ktor/utils/io/ByteChannel;)V", "Lkotlinx/io/bytestring/ByteString;", "matchString", "writeChannel", "ignoreMissing", "readUntil", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlinx/io/bytestring/ByteString;Lio/ktor/utils/io/ByteWriteChannel;JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "byteString", "skipIfFound", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlinx/io/bytestring/ByteString;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "peek", "getAvailableForWrite", "(Lio/ktor/utils/io/ByteWriteChannel;)I", "getAvailableForWrite$annotations", "availableForWrite", "CR", "B", "LF", "getAvailableForRead", "(Lio/ktor/utils/io/ByteReadChannel;)I", "getAvailableForRead$annotations", "availableForRead", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ByteReadChannelOperationsKt {
    private static final byte CR = 13;
    private static final byte LF = 10;

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$awaitUntilReadable$1, reason: invalid class name */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0}, l = {92}, m = "awaitUntilReadable", n = {"$this$awaitUntilReadable", "numberOfBytes"}, s = {"L$0", "I$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
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
            return ByteReadChannelOperationsKt.awaitUntilReadable(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$copyAndClose$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}, l = {132, 133, 142, 142}, m = "copyAndClose", n = {"$this$copyAndClose", TvContractCompat.PARAM_CHANNEL, "result", "$this$copyAndClose", TvContractCompat.PARAM_CHANNEL, "result", "$this$copyAndClose", TvContractCompat.PARAM_CHANNEL, "result", "$this$copyAndClose", TvContractCompat.PARAM_CHANNEL, "result"}, s = {"L$0", "L$1", "J$0", "L$0", "L$1", "J$0", "L$0", "L$1", "J$0", "L$0", "L$1", "J$0"}, v = 1)
    static final class C09991 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C09991(Continuation<? super C09991> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.copyAndClose(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$copyTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}, l = {154, 155, 162, 162}, m = "copyTo", n = {"$this$copyTo", TvContractCompat.PARAM_CHANNEL, "result", "$this$copyTo", TvContractCompat.PARAM_CHANNEL, "result", "$this$copyTo", TvContractCompat.PARAM_CHANNEL, "result", "$this$copyTo", TvContractCompat.PARAM_CHANNEL, "result"}, s = {"L$0", "L$1", "J$0", "L$0", "L$1", "J$0", "L$0", "L$1", "J$0", "L$0", "L$1", "J$0"}, v = 1)
    static final class C10001 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C10001(Continuation<? super C10001> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.copyTo(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$copyTo$2, reason: invalid class name */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3}, l = {205, 209, 216, 216}, m = "copyTo", n = {"$this$copyTo", TvContractCompat.PARAM_CHANNEL, "limit", "remaining", "$this$copyTo", TvContractCompat.PARAM_CHANNEL, "limit", "remaining", PeertubeParsingHelper.COUNT_KEY, "$this$copyTo", TvContractCompat.PARAM_CHANNEL, "limit", "remaining", "$this$copyTo", TvContractCompat.PARAM_CHANNEL, "limit", "remaining"}, s = {"L$0", "L$1", "J$0", "J$1", "L$0", "L$1", "J$0", "J$1", "J$2", "L$0", "L$1", "J$0", "J$1", "L$0", "L$1", "J$0", "J$1"}, v = 1)
    static final class AnonymousClass2 extends ContinuationImpl {
        long J$0;
        long J$1;
        long J$2;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.copyTo(null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$discard$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0}, l = {408}, m = "discard", n = {"$this$discard", "max", "remaining"}, s = {"L$0", "J$0", "J$1"}, v = 1)
    static final class C10011 extends ContinuationImpl {
        long J$0;
        long J$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C10011(Continuation<? super C10011> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.discard(null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$discardExact$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0}, l = {400}, m = "discardExact", n = {"$this$discardExact", ES6Iterator.VALUE_PROPERTY}, s = {"L$0", "J$0"}, v = 1)
    static final class C10021 extends ContinuationImpl {
        long J$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C10021(Continuation<? super C10021> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.discardExact(null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$exhausted$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0}, l = {34}, m = "exhausted", n = {"$this$exhausted"}, s = {"L$0"}, v = 1)
    static final class C10031 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C10031(Continuation<? super C10031> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.exhausted(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$internalReadLineTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4}, l = {686, 757, 764, 776, 785}, m = "internalReadLineTo", n = {"$this$internalReadLineTo", "out", "readBuffer", "limit", "lenientLineEnding", "throwOnIncompleteLine", "$this$internalReadLineTo", "out", "readBuffer", "consumed", "limit", "lenientLineEnding", "throwOnIncompleteLine", "limitLeft", "lfIndex", "crIndex", PeertubeParsingHelper.COUNT_KEY, "$this$internalReadLineTo", "out", "readBuffer", "consumed", "limit", "lenientLineEnding", "throwOnIncompleteLine", "limitLeft", "lfIndex", "crIndex", PeertubeParsingHelper.COUNT_KEY, "$this$internalReadLineTo", "out", "readBuffer", "consumed", "limit", "lenientLineEnding", "throwOnIncompleteLine", "$this$internalReadLineTo", "out", "readBuffer", "consumed", "limit", "lenientLineEnding", "throwOnIncompleteLine"}, s = {"L$0", "L$1", "L$2", "J$0", "Z$0", "Z$1", "L$0", "L$1", "L$2", "L$3", "J$0", "Z$0", "Z$1", "J$1", "J$2", "J$3", "J$4", "L$0", "L$1", "L$2", "L$3", "J$0", "Z$0", "Z$1", "J$1", "J$2", "J$3", "J$4", "L$0", "L$1", "L$2", "L$3", "J$0", "Z$0", "Z$1", "L$0", "L$1", "L$2", "L$3", "J$0", "Z$0", "Z$1"}, v = 1)
    static final class C10041 extends ContinuationImpl {
        long J$0;
        long J$1;
        long J$2;
        long J$3;
        long J$4;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        boolean Z$1;
        int label;
        /* synthetic */ Object result;

        C10041(Continuation<? super C10041> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.internalReadLineTo(null, null, 0L, false, false, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$peek$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0}, l = {925}, m = "peek", n = {"$this$peek", PeertubeParsingHelper.COUNT_KEY}, s = {"L$0", "I$0"}, v = 1)
    static final class C10051 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C10051(Continuation<? super C10051> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.peek(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$read$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {806, 811}, m = "read", n = {"$this$read", "block", "$i$f$read", "$this$read", "block", "result", "this_$iv", "buffer$iv", "head$iv", "array", "$i$f$read", "$i$f$readFromHead", "endExclusive", "start", "$i$a$-readFromHead-ByteReadChannelOperationsKt$read$2"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1", "I$2", "I$3", "I$4"}, v = 1)
    static final class C10061 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        C10061(Continuation<? super C10061> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.read(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readAvailable$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0, 0}, l = {274}, m = "readAvailable", n = {"$this$readAvailable", "buffer", TypedValues.CycleType.S_WAVE_OFFSET, "length"}, s = {"L$0", "L$1", "I$0", "I$1"}, v = 1)
    static final class C10071 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C10071(Continuation<? super C10071> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readAvailable(null, null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readBuffer$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0}, l = {102}, m = "readBuffer", n = {"$this$readBuffer", "result"}, s = {"L$0", "L$1"}, v = 1)
    static final class C10081 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C10081(Continuation<? super C10081> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readBuffer(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readBuffer$3, reason: invalid class name */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0, 0}, l = {116}, m = "readBuffer", n = {"$this$readBuffer", "result", "max", "remaining"}, s = {"L$0", "L$1", "I$0", "I$1"}, v = 1)
    static final class AnonymousClass3 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readBuffer(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readByte$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0}, l = {45}, m = "readByte", n = {"$this$readByte", "currentBuffer"}, s = {"L$0", "L$1"}, v = 1)
    static final class C10091 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C10091(Continuation<? super C10091> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readByte(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readByteArray$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0, 0, 0, 0}, l = {224}, m = "readByteArray", n = {"$this$readByteArray", "builder$iv", "$this$readByteArray_u24lambda_u240", PeertubeParsingHelper.COUNT_KEY, "$i$f$buildPacket", "$i$a$-buildPacket-ByteReadChannelOperationsKt$readByteArray$2"}, s = {"L$0", "L$1", "L$2", "I$0", "I$1", "I$2"}, v = 1)
    static final class C10101 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C10101(Continuation<? super C10101> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readByteArray(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readDouble$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0}, l = {87}, m = "readDouble", n = {"$this$readDouble"}, s = {"L$0"}, v = 1)
    static final class C10111 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C10111(Continuation<? super C10111> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readDouble(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readFloat$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0}, l = {70}, m = "readFloat", n = {"$this$readFloat"}, s = {"L$0"}, v = 1)
    static final class C10121 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C10121(Continuation<? super C10121> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readFloat(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readFully$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0, 0, 0}, l = {840}, m = "readFully", n = {"$this$readFully", "out", "start", TtmlNode.END, TypedValues.CycleType.S_WAVE_OFFSET}, s = {"L$0", "L$1", "I$0", "I$1", "I$2"}, v = 1)
    static final class C10131 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C10131(Continuation<? super C10131> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readFully(null, null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readInt$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0}, l = {59}, m = "readInt", n = {"$this$readInt"}, s = {"L$0"}, v = 1)
    static final class C10141 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C10141(Continuation<? super C10141> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readInt(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readLine$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0}, l = {586}, m = "readLine", n = {"$this$readLine", "lineEnding", "result"}, s = {"L$0", "L$1", "L$2"}, v = 1)
    static final class C10151 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C10151(Continuation<? super C10151> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readLine(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readLineStrict$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0, 0}, l = {638}, m = "readLineStrict", n = {"$this$readLineStrict", "lineEnding", "result", "limit"}, s = {"L$0", "L$1", "L$2", "J$0"}, v = 1)
    static final class C10161 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C10161(Continuation<? super C10161> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readLineStrict(null, 0L, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readLong$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0}, l = {76}, m = "readLong", n = {"$this$readLong"}, s = {"L$0"}, v = 1)
    static final class C10171 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C10171(Continuation<? super C10171> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readLong(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readPacket$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0}, l = {383}, m = "readPacket", n = {"$this$readPacket", "result", "packet"}, s = {"L$0", "L$1", "I$0"}, v = 1)
    static final class C10181 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C10181(Continuation<? super C10181> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readPacket(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readRemaining$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0}, l = {HebrewProber.FINAL_KAF}, m = "readRemaining", n = {"$this$readRemaining", "result"}, s = {"L$0", "L$1"}, v = 1)
    static final class C10191 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C10191(Continuation<? super C10191> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readRemaining(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readRemaining$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0, 0}, l = {254}, m = "readRemaining", n = {"$this$readRemaining", "result", "max", "remaining"}, s = {"L$0", "L$1", "J$0", "J$1"}, v = 1)
    static final class C10202 extends ContinuationImpl {
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C10202(Continuation<? super C10202> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readRemaining(null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readShort$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0}, l = {53}, m = "readShort", n = {"$this$readShort"}, s = {"L$0"}, v = 1)
    static final class C10211 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C10211(Continuation<? super C10211> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readShort(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0, 0}, l = {184}, m = "readTo", n = {"$this$readTo", "sink", "limit", "remaining"}, s = {"L$0", "L$1", "J$0", "J$1"}, v = 1)
    static final class C10221 extends ContinuationImpl {
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C10221(Continuation<? super C10221> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readTo(null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readUTF8Line$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0}, l = {474}, m = "readUTF8Line", n = {"$this$readUTF8Line", "result", "max"}, s = {"L$0", "L$1", "I$0"}, v = 1)
    static final class C10231 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C10231(Continuation<? super C10231> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.readUTF8Line(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$readUTF8LineTo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 0, 0}, l = {559}, m = "readUTF8LineTo-RRvyBJ8", n = {"$this$readUTF8LineTo_u2dRRvyBJ8", "out", "max", "lineEnding"}, s = {"L$0", "L$1", "I$0", "I$1"}, v = 1)
    static final class C10242 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C10242(Continuation<? super C10242> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.m8865readUTF8LineToRRvyBJ8(null, null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$skipIfFound$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0, 0, 1, 1}, l = {905, 906}, m = "skipIfFound", n = {"$this$skipIfFound", "byteString", "$this$skipIfFound", "byteString"}, s = {"L$0", "L$1", "L$0", "L$1"}, v = 1)
    static final class C10261 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C10261(Continuation<? super C10261> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.skipIfFound(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$toByteArray$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt", f = "ByteReadChannelOperations.kt", i = {0}, l = {39}, m = "toByteArray", n = {"$this$toByteArray"}, s = {"L$0"}, v = 1)
    static final class C10271 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C10271(Continuation<? super C10271> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelOperationsKt.toByteArray(null, this);
        }
    }

    public static /* synthetic */ void getAvailableForRead$annotations(ByteReadChannel byteReadChannel) {
    }

    public static /* synthetic */ void getAvailableForWrite$annotations(ByteWriteChannel byteWriteChannel) {
    }

    public static final int getAvailableForWrite(ByteWriteChannel byteWriteChannel) {
        Intrinsics.checkNotNullParameter(byteWriteChannel, "<this>");
        return 1048576 - BytePacketBuilderKt.getSize(byteWriteChannel.getWriteBuffer());
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object exhausted(ByteReadChannel byteReadChannel, Continuation<? super Boolean> continuation) {
        C10031 c10031;
        if (continuation instanceof C10031) {
            c10031 = (C10031) continuation;
            if ((c10031.label & Integer.MIN_VALUE) != 0) {
                c10031.label -= Integer.MIN_VALUE;
            } else {
                c10031 = new C10031(continuation);
            }
        } else {
            c10031 = new C10031(continuation);
        }
        Object obj = c10031.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10031.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (byteReadChannel.getReadBuffer().exhausted()) {
                c10031.L$0 = byteReadChannel;
                c10031.label = 1;
                if (ByteReadChannel.CC.awaitContent$default(byteReadChannel, 0, c10031, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            byteReadChannel = (ByteReadChannel) c10031.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Boxing.boxBoolean(byteReadChannel.getReadBuffer().exhausted());
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object toByteArray(ByteReadChannel byteReadChannel, Continuation<? super byte[]> continuation) throws Throwable {
        C10271 c10271;
        if (continuation instanceof C10271) {
            c10271 = (C10271) continuation;
            if ((c10271.label & Integer.MIN_VALUE) != 0) {
                c10271.label -= Integer.MIN_VALUE;
            } else {
                c10271 = new C10271(continuation);
            }
        } else {
            c10271 = new C10271(continuation);
        }
        Object buffer = c10271.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10271.label;
        if (i == 0) {
            ResultKt.throwOnFailure(buffer);
            c10271.L$0 = SpillingKt.nullOutSpilledVariable(byteReadChannel);
            c10271.label = 1;
            buffer = readBuffer(byteReadChannel, c10271);
            if (buffer == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(buffer);
        }
        return BuffersKt.readBytes$default((Buffer) buffer, 0, 1, null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object readByte(ByteReadChannel byteReadChannel, Continuation<? super Byte> continuation) throws EOFException {
        C10091 c10091;
        Source readBuffer;
        Source source;
        if (continuation instanceof C10091) {
            c10091 = (C10091) continuation;
            if ((c10091.label & Integer.MIN_VALUE) != 0) {
                c10091.label -= Integer.MIN_VALUE;
            } else {
                c10091 = new C10091(continuation);
            }
        } else {
            c10091 = new C10091(continuation);
        }
        Object obj = c10091.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10091.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            readBuffer = byteReadChannel.getReadBuffer();
            if (readBuffer.exhausted()) {
                c10091.L$0 = SpillingKt.nullOutSpilledVariable(byteReadChannel);
                c10091.L$1 = readBuffer;
                c10091.label = 1;
                Object objAwaitContent$default = ByteReadChannel.CC.awaitContent$default(byteReadChannel, 0, c10091, 1, null);
                if (objAwaitContent$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = objAwaitContent$default;
                source = readBuffer;
            }
            return Boxing.boxByte(readBuffer.readByte());
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        source = (Source) c10091.L$1;
        ResultKt.throwOnFailure(obj);
        if (!((Boolean) obj).booleanValue()) {
            throw new EOFException("Not enough data available");
        }
        readBuffer = source;
        return Boxing.boxByte(readBuffer.readByte());
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object readShort(ByteReadChannel byteReadChannel, Continuation<? super Short> continuation) {
        C10211 c10211;
        if (continuation instanceof C10211) {
            c10211 = (C10211) continuation;
            if ((c10211.label & Integer.MIN_VALUE) != 0) {
                c10211.label -= Integer.MIN_VALUE;
            } else {
                c10211 = new C10211(continuation);
            }
        } else {
            c10211 = new C10211(continuation);
        }
        Object obj = c10211.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10211.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            c10211.L$0 = byteReadChannel;
            c10211.label = 1;
            if (awaitUntilReadable(byteReadChannel, 2, c10211) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            byteReadChannel = (ByteReadChannel) c10211.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Boxing.boxShort(byteReadChannel.getReadBuffer().readShort());
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object readInt(ByteReadChannel byteReadChannel, Continuation<? super Integer> continuation) {
        C10141 c10141;
        if (continuation instanceof C10141) {
            c10141 = (C10141) continuation;
            if ((c10141.label & Integer.MIN_VALUE) != 0) {
                c10141.label -= Integer.MIN_VALUE;
            } else {
                c10141 = new C10141(continuation);
            }
        } else {
            c10141 = new C10141(continuation);
        }
        Object obj = c10141.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10141.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            c10141.L$0 = byteReadChannel;
            c10141.label = 1;
            if (awaitUntilReadable(byteReadChannel, 4, c10141) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            byteReadChannel = (ByteReadChannel) c10141.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Boxing.boxInt(byteReadChannel.getReadBuffer().readInt());
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object readFloat(ByteReadChannel byteReadChannel, Continuation<? super Float> continuation) {
        C10121 c10121;
        if (continuation instanceof C10121) {
            c10121 = (C10121) continuation;
            if ((c10121.label & Integer.MIN_VALUE) != 0) {
                c10121.label -= Integer.MIN_VALUE;
            } else {
                c10121 = new C10121(continuation);
            }
        } else {
            c10121 = new C10121(continuation);
        }
        Object obj = c10121.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10121.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            c10121.L$0 = byteReadChannel;
            c10121.label = 1;
            if (awaitUntilReadable(byteReadChannel, 4, c10121) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            byteReadChannel = (ByteReadChannel) c10121.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Boxing.boxFloat(SourcesKt.readFloat(byteReadChannel.getReadBuffer()));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object readLong(ByteReadChannel byteReadChannel, Continuation<? super Long> continuation) {
        C10171 c10171;
        if (continuation instanceof C10171) {
            c10171 = (C10171) continuation;
            if ((c10171.label & Integer.MIN_VALUE) != 0) {
                c10171.label -= Integer.MIN_VALUE;
            } else {
                c10171 = new C10171(continuation);
            }
        } else {
            c10171 = new C10171(continuation);
        }
        Object obj = c10171.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10171.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            c10171.L$0 = byteReadChannel;
            c10171.label = 1;
            if (awaitUntilReadable(byteReadChannel, 8, c10171) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            byteReadChannel = (ByteReadChannel) c10171.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Boxing.boxLong(byteReadChannel.getReadBuffer().readLong());
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object readDouble(ByteReadChannel byteReadChannel, Continuation<? super Double> continuation) {
        C10111 c10111;
        if (continuation instanceof C10111) {
            c10111 = (C10111) continuation;
            if ((c10111.label & Integer.MIN_VALUE) != 0) {
                c10111.label -= Integer.MIN_VALUE;
            } else {
                c10111 = new C10111(continuation);
            }
        } else {
            c10111 = new C10111(continuation);
        }
        Object obj = c10111.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10111.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            c10111.L$0 = byteReadChannel;
            c10111.label = 1;
            if (awaitUntilReadable(byteReadChannel, 8, c10111) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            byteReadChannel = (ByteReadChannel) c10111.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Boxing.boxDouble(SourcesKt.readDouble(byteReadChannel.getReadBuffer()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object awaitUntilReadable(ByteReadChannel byteReadChannel, int i, Continuation<? super Unit> continuation) throws EOFException {
        AnonymousClass1 anonymousClass1;
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
        Object objAwaitContent = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objAwaitContent);
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(byteReadChannel);
            anonymousClass1.I$0 = i;
            anonymousClass1.label = 1;
            objAwaitContent = byteReadChannel.awaitContent(i, anonymousClass1);
            if (objAwaitContent == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = anonymousClass1.I$0;
            ResultKt.throwOnFailure(objAwaitContent);
        }
        if (!((Boolean) objAwaitContent).booleanValue()) {
            throw new EOFException("Not enough data available");
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object readBuffer(ByteReadChannel byteReadChannel, Continuation<? super Buffer> continuation) throws Throwable {
        C10081 c10081;
        Buffer buffer;
        if (continuation instanceof C10081) {
            c10081 = (C10081) continuation;
            if ((c10081.label & Integer.MIN_VALUE) != 0) {
                c10081.label -= Integer.MIN_VALUE;
            } else {
                c10081 = new C10081(continuation);
            }
        } else {
            c10081 = new C10081(continuation);
        }
        Object obj = c10081.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10081.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            buffer = new Buffer();
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Buffer buffer2 = (Buffer) c10081.L$1;
            ByteReadChannel byteReadChannel2 = (ByteReadChannel) c10081.L$0;
            ResultKt.throwOnFailure(obj);
            buffer = buffer2;
            byteReadChannel = byteReadChannel2;
        }
        while (!byteReadChannel.isClosedForRead()) {
            buffer.transferFrom(byteReadChannel.getReadBuffer());
            c10081.L$0 = byteReadChannel;
            c10081.L$1 = buffer;
            c10081.label = 1;
            if (ByteReadChannel.CC.awaitContent$default(byteReadChannel, 0, c10081, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        Throwable closedCause = byteReadChannel.getClosedCause();
        if (closedCause == null) {
            return buffer;
        }
        throw closedCause;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x004a  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0058 -> B:25:0x0073). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x006d -> B:24:0x0070). Please report as a decompilation issue!!! */
    public static final Object readBuffer(ByteReadChannel byteReadChannel, int i, Continuation<? super Buffer> continuation) {
        AnonymousClass3 anonymousClass3;
        Buffer buffer;
        int i2;
        ByteReadChannel byteReadChannel2;
        int i3;
        int i4;
        if (continuation instanceof AnonymousClass3) {
            anonymousClass3 = (AnonymousClass3) continuation;
            if ((anonymousClass3.label & Integer.MIN_VALUE) != 0) {
                anonymousClass3.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass3 = new AnonymousClass3(continuation);
            }
        } else {
            anonymousClass3 = new AnonymousClass3(continuation);
        }
        Object obj = anonymousClass3.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i5 = anonymousClass3.label;
        if (i5 == 0) {
            ResultKt.throwOnFailure(obj);
            buffer = new Buffer();
            i2 = i;
            if (i > 0 || byteReadChannel.isClosedForRead()) {
                return buffer;
            }
            if (byteReadChannel.getReadBuffer().exhausted()) {
                anonymousClass3.L$0 = byteReadChannel;
                anonymousClass3.L$1 = buffer;
                anonymousClass3.I$0 = i2;
                anonymousClass3.I$1 = i;
                anonymousClass3.label = 1;
                if (ByteReadChannel.CC.awaitContent$default(byteReadChannel, 0, anonymousClass3, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                byteReadChannel2 = byteReadChannel;
                i3 = i;
                i4 = i2;
            }
            long jMin = Math.min(i, ByteReadPacketKt.getRemaining(byteReadChannel.getReadBuffer()));
            byteReadChannel.getReadBuffer().readTo(buffer, jMin);
            i -= (int) jMin;
            if (i > 0) {
            }
            return buffer;
        }
        if (i5 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        i3 = anonymousClass3.I$1;
        i4 = anonymousClass3.I$0;
        buffer = (Buffer) anonymousClass3.L$1;
        byteReadChannel2 = (ByteReadChannel) anonymousClass3.L$0;
        ResultKt.throwOnFailure(obj);
        i2 = i4;
        i = i3;
        byteReadChannel = byteReadChannel2;
        long jMin2 = Math.min(i, ByteReadPacketKt.getRemaining(byteReadChannel.getReadBuffer()));
        byteReadChannel.getReadBuffer().readTo(buffer, jMin2);
        i -= (int) jMin2;
        if (i > 0) {
        }
        return buffer;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0087 A[Catch: all -> 0x00de, TRY_LEAVE, TryCatch #2 {all -> 0x00de, blocks: (B:27:0x0081, B:29:0x0087, B:36:0x00ba, B:44:0x00dd), top: B:59:0x0081 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:36:0x00ba A[Catch: all -> 0x00de, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x00de, blocks: (B:27:0x0081, B:29:0x0087, B:36:0x00ba, B:44:0x00dd), top: B:59:0x0081 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:41:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:44:0x00dd A[Catch: all -> 0x00de, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x00de, blocks: (B:27:0x0081, B:29:0x0087, B:36:0x00ba, B:44:0x00dd), top: B:59:0x0081 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b7, code lost:
    
        if (r15 == r1) goto L52;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r13v0, types: [io.ktor.utils.io.ByteReadChannel] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v15, types: [long] */
    /* JADX WARN: Type inference failed for: r13v2, types: [long] */
    /* JADX WARN: Type inference failed for: r13v24 */
    /* JADX WARN: Type inference failed for: r13v25 */
    /* JADX WARN: Type inference failed for: r13v26 */
    /* JADX WARN: Type inference failed for: r13v27 */
    /* JADX WARN: Type inference failed for: r13v28 */
    /* JADX WARN: Type inference failed for: r13v5, types: [long] */
    /* JADX WARN: Type inference failed for: r13v6, types: [io.ktor.utils.io.ByteReadChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r14v1, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v2, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r7v1, types: [io.ktor.utils.io.ByteReadChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [io.ktor.utils.io.ByteReadChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v4, types: [long] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00b7 -> B:20:0x0066). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyAndClose(io.ktor.utils.io.ByteReadChannel r13, io.ktor.utils.io.ByteWriteChannel r14, kotlin.coroutines.Continuation<? super java.lang.Long> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelOperationsKt.copyAndClose(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0086 A[Catch: all -> 0x00d6, TRY_LEAVE, TryCatch #1 {all -> 0x00d6, blocks: (B:27:0x0080, B:29:0x0086), top: B:54:0x0080 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:36:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:39:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b6, code lost:
    
        if (r15 == r1) goto L49;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r13v0, types: [io.ktor.utils.io.ByteReadChannel] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v15, types: [long] */
    /* JADX WARN: Type inference failed for: r13v2, types: [long] */
    /* JADX WARN: Type inference failed for: r13v24 */
    /* JADX WARN: Type inference failed for: r13v25 */
    /* JADX WARN: Type inference failed for: r13v26 */
    /* JADX WARN: Type inference failed for: r13v27 */
    /* JADX WARN: Type inference failed for: r13v28 */
    /* JADX WARN: Type inference failed for: r13v5, types: [long] */
    /* JADX WARN: Type inference failed for: r13v6, types: [io.ktor.utils.io.ByteReadChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r14v1, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v2, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r7v1, types: [io.ktor.utils.io.ByteReadChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [io.ktor.utils.io.ByteReadChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v4, types: [long] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7, types: [long] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00b6 -> B:20:0x0066). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyTo(io.ktor.utils.io.ByteReadChannel r13, io.ktor.utils.io.ByteWriteChannel r14, kotlin.coroutines.Continuation<? super java.lang.Long> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelOperationsKt.copyTo(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:21:0x004b  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0059 -> B:40:0x0078). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x006e -> B:29:0x0073). Please report as a decompilation issue!!! */
    public static final Object readTo(ByteReadChannel byteReadChannel, RawSink rawSink, long j, Continuation<? super Long> continuation) throws Throwable {
        C10221 c10221;
        long j2;
        Throwable th;
        if (continuation instanceof C10221) {
            c10221 = (C10221) continuation;
            if ((c10221.label & Integer.MIN_VALUE) != 0) {
                c10221.label -= Integer.MIN_VALUE;
            } else {
                c10221 = new C10221(continuation);
            }
        } else {
            c10221 = new C10221(continuation);
        }
        Object obj = c10221.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10221.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            j2 = j;
            if (byteReadChannel.isClosedForRead()) {
            }
            rethrowCloseCauseIfNeeded(byteReadChannel);
            return Boxing.boxLong(j2 - j);
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        long j3 = c10221.J$1;
        long j4 = c10221.J$0;
        RawSink rawSink2 = (RawSink) c10221.L$1;
        ByteReadChannel byteReadChannel2 = (ByteReadChannel) c10221.L$0;
        try {
            ResultKt.throwOnFailure(obj);
            try {
                long j5 = j3;
                byteReadChannel = byteReadChannel2;
                j2 = j4;
                j = j5;
                rawSink = rawSink2;
                long jMin = Math.min(j, ByteReadPacketKt.getRemaining(byteReadChannel.getReadBuffer()));
                byteReadChannel.getReadBuffer().readTo(rawSink, jMin);
                j -= jMin;
                rawSink.flush();
                if (byteReadChannel.isClosedForRead() && j > 0) {
                    if (byteReadChannel.getReadBuffer().exhausted()) {
                        c10221.L$0 = byteReadChannel;
                        c10221.L$1 = rawSink;
                        c10221.J$0 = j2;
                        c10221.J$1 = j;
                        c10221.label = 1;
                        if (ByteReadChannel.CC.awaitContent$default(byteReadChannel, 0, c10221, 1, null) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        rawSink2 = rawSink;
                        long j6 = j2;
                        byteReadChannel2 = byteReadChannel;
                        j3 = j;
                        j4 = j6;
                        long j7 = j3;
                        byteReadChannel = byteReadChannel2;
                        j2 = j4;
                        j = j7;
                        rawSink = rawSink2;
                    }
                    long jMin2 = Math.min(j, ByteReadPacketKt.getRemaining(byteReadChannel.getReadBuffer()));
                    byteReadChannel.getReadBuffer().readTo(rawSink, jMin2);
                    j -= jMin2;
                    rawSink.flush();
                    if (byteReadChannel.isClosedForRead()) {
                    }
                    rethrowCloseCauseIfNeeded(byteReadChannel);
                    return Boxing.boxLong(j2 - j);
                }
                rethrowCloseCauseIfNeeded(byteReadChannel);
                return Boxing.boxLong(j2 - j);
            } catch (Throwable th2) {
                byteReadChannel2 = byteReadChannel;
                rawSink2 = rawSink;
                th = th2;
                byteReadChannel2.cancel(th);
                rawSink2.close();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            byteReadChannel2.cancel(th);
            rawSink2.close();
            throw th;
        }
    }

    public static /* synthetic */ Object readTo$default(ByteReadChannel byteReadChannel, RawSink rawSink, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return readTo(byteReadChannel, rawSink, j, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:43:0x0109  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ba, code lost:
    
        if (io.ktor.utils.io.ByteReadChannel.CC.awaitContent$default(r1, 0, r12, 1, null) == r2) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ed, code lost:
    
        if (r0 == r2) goto L53;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v2, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v5, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x00ed -> B:20:0x0070). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyTo(io.ktor.utils.io.ByteReadChannel r17, io.ktor.utils.io.ByteWriteChannel r18, long r19, kotlin.coroutines.Continuation<? super java.lang.Long> r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelOperationsKt.copyTo(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0060  */
    /* JADX WARN: Code duplicated, block: B:20:0x007a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:21:0x007b  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x007b -> B:12:0x0042). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object readByteArray(io.ktor.utils.io.ByteReadChannel r8, int r9, kotlin.coroutines.Continuation<? super byte[]> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteReadChannelOperationsKt.C10101
            if (r0 == 0) goto L14
            r0 = r10
            io.ktor.utils.io.ByteReadChannelOperationsKt$readByteArray$1 r0 = (io.ktor.utils.io.ByteReadChannelOperationsKt.C10101) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            io.ktor.utils.io.ByteReadChannelOperationsKt$readByteArray$1 r0 = new io.ktor.utils.io.ByteReadChannelOperationsKt$readByteArray$1
            r0.<init>(r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L4c
            if (r2 != r3) goto L44
            int r8 = r0.I$2
            int r9 = r0.I$1
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$2
            kotlinx.io.Sink r4 = (kotlinx.io.Sink) r4
            java.lang.Object r5 = r0.L$1
            kotlinx.io.Buffer r5 = (kotlinx.io.Buffer) r5
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r6 = (io.ktor.utils.io.ByteReadChannel) r6
            kotlin.ResultKt.throwOnFailure(r10)
            r7 = r5
            r5 = r8
            r8 = r6
            r6 = r7
            r7 = r0
            r0 = r9
            r9 = r2
        L42:
            r2 = r7
            goto L82
        L44:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L4c:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.io.Buffer r10 = new kotlinx.io.Buffer
            r10.<init>()
            r2 = r10
            kotlinx.io.Sink r2 = (kotlinx.io.Sink) r2
            r4 = 0
            r5 = r10
            r10 = r4
        L5a:
            int r6 = io.ktor.utils.io.core.BytePacketBuilderKt.getSize(r2)
            if (r6 >= r9) goto L8d
            int r6 = io.ktor.utils.io.core.BytePacketBuilderKt.getSize(r2)
            int r6 = r9 - r6
            r0.L$0 = r8
            r0.L$1 = r5
            r0.L$2 = r2
            r0.I$0 = r9
            r0.I$1 = r10
            r0.I$2 = r4
            r0.label = r3
            java.lang.Object r6 = readPacket(r8, r6, r0)
            if (r6 != r1) goto L7b
            return r1
        L7b:
            r7 = r0
            r0 = r10
            r10 = r6
            r6 = r5
            r5 = r4
            r4 = r2
            goto L42
        L82:
            kotlinx.io.Source r10 = (kotlinx.io.Source) r10
            io.ktor.utils.io.core.BytePacketBuilderKt.writePacket(r4, r10)
            r10 = r0
            r0 = r2
            r2 = r4
            r4 = r5
            r5 = r6
            goto L5a
        L8d:
            kotlinx.io.Source r5 = (kotlinx.io.Source) r5
            byte[] r8 = kotlinx.io.SourcesKt.readByteArray(r5)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelOperationsKt.readByteArray(io.ktor.utils.io.ByteReadChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object readRemaining(ByteReadChannel byteReadChannel, Continuation<? super Source> continuation) throws Throwable {
        C10191 c10191;
        Sink sinkBytePacketBuilder;
        if (continuation instanceof C10191) {
            c10191 = (C10191) continuation;
            if ((c10191.label & Integer.MIN_VALUE) != 0) {
                c10191.label -= Integer.MIN_VALUE;
            } else {
                c10191 = new C10191(continuation);
            }
        } else {
            c10191 = new C10191(continuation);
        }
        Object obj = c10191.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10191.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            sinkBytePacketBuilder = BytePacketBuilderKt.BytePacketBuilder();
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Sink sink = (Sink) c10191.L$1;
            ByteReadChannel byteReadChannel2 = (ByteReadChannel) c10191.L$0;
            ResultKt.throwOnFailure(obj);
            sinkBytePacketBuilder = sink;
            byteReadChannel = byteReadChannel2;
        }
        while (!byteReadChannel.isClosedForRead()) {
            sinkBytePacketBuilder.transferFrom(byteReadChannel.getReadBuffer());
            c10191.L$0 = byteReadChannel;
            c10191.L$1 = sinkBytePacketBuilder;
            c10191.label = 1;
            if (ByteReadChannel.CC.awaitContent$default(byteReadChannel, 0, c10191, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        rethrowCloseCauseIfNeeded(byteReadChannel);
        return sinkBytePacketBuilder.getBufferField();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object readRemaining(ByteReadChannel byteReadChannel, long j, Continuation<? super Source> continuation) {
        C10202 c10202;
        Sink sinkBytePacketBuilder;
        long j2;
        if (continuation instanceof C10202) {
            c10202 = (C10202) continuation;
            if ((c10202.label & Integer.MIN_VALUE) != 0) {
                c10202.label -= Integer.MIN_VALUE;
            } else {
                c10202 = new C10202(continuation);
            }
        } else {
            c10202 = new C10202(continuation);
        }
        Object obj = c10202.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10202.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            sinkBytePacketBuilder = BytePacketBuilderKt.BytePacketBuilder();
            j2 = j;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            long j3 = c10202.J$1;
            j2 = c10202.J$0;
            Sink sink = (Sink) c10202.L$1;
            ByteReadChannel byteReadChannel2 = (ByteReadChannel) c10202.L$0;
            ResultKt.throwOnFailure(obj);
            sinkBytePacketBuilder = sink;
            j = j3;
            byteReadChannel = byteReadChannel2;
        }
        while (!byteReadChannel.isClosedForRead()) {
            long remaining = 0;
            if (j <= 0) {
                break;
            }
            if (j >= ByteReadPacketKt.getRemaining(byteReadChannel.getReadBuffer())) {
                remaining = j - ByteReadPacketKt.getRemaining(byteReadChannel.getReadBuffer());
                Boxing.boxLong(byteReadChannel.getReadBuffer().transferTo(sinkBytePacketBuilder));
            } else {
                byteReadChannel.getReadBuffer().readTo(sinkBytePacketBuilder, j);
            }
            c10202.L$0 = byteReadChannel;
            c10202.L$1 = sinkBytePacketBuilder;
            c10202.J$0 = j2;
            c10202.J$1 = remaining;
            c10202.label = 1;
            if (ByteReadChannel.CC.awaitContent$default(byteReadChannel, 0, c10202, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
            j = remaining;
        }
        return sinkBytePacketBuilder.getBufferField();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object readAvailable(ByteReadChannel byteReadChannel, byte[] bArr, int i, int i2, Continuation<? super Integer> continuation) {
        C10071 c10071;
        if (continuation instanceof C10071) {
            c10071 = (C10071) continuation;
            if ((c10071.label & Integer.MIN_VALUE) != 0) {
                c10071.label -= Integer.MIN_VALUE;
            } else {
                c10071 = new C10071(continuation);
            }
        } else {
            c10071 = new C10071(continuation);
        }
        Object obj = c10071.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c10071.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            if (byteReadChannel.isClosedForRead()) {
                return Boxing.boxInt(-1);
            }
            if (byteReadChannel.getReadBuffer().exhausted()) {
                c10071.L$0 = byteReadChannel;
                c10071.L$1 = bArr;
                c10071.I$0 = i;
                c10071.I$1 = i2;
                c10071.label = 1;
                if (ByteReadChannel.CC.awaitContent$default(byteReadChannel, 0, c10071, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i4 = c10071.I$1;
            int i5 = c10071.I$0;
            byte[] bArr2 = (byte[]) c10071.L$1;
            ByteReadChannel byteReadChannel2 = (ByteReadChannel) c10071.L$0;
            ResultKt.throwOnFailure(obj);
            i2 = i4;
            byteReadChannel = byteReadChannel2;
            i = i5;
            bArr = bArr2;
        }
        return byteReadChannel.isClosedForRead() ? Boxing.boxInt(-1) : Boxing.boxInt(InputKt.readAvailable(byteReadChannel.getReadBuffer(), bArr, i, i2));
    }

    public static /* synthetic */ Object readAvailable$default(ByteReadChannel byteReadChannel, byte[] bArr, int i, int i2, Continuation continuation, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        return readAvailable(byteReadChannel, bArr, i, i2, continuation);
    }

    public static final int readAvailable(ByteReadChannel byteReadChannel, int i, Function1<? super Buffer, Integer> block) {
        Intrinsics.checkNotNullParameter(byteReadChannel, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        if (i <= 0) {
            throw new IllegalArgumentException("min should be positive".toString());
        }
        if (i > 1048576) {
            throw new IllegalArgumentException(("Min(" + i + ") shouldn't be greater than 1048576").toString());
        }
        if (getAvailableForRead(byteReadChannel) < i) {
            return -1;
        }
        return block.invoke(byteReadChannel.getReadBuffer().getBufferField()).intValue();
    }

    public static /* synthetic */ ReaderJob reader$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return reader(coroutineScope, coroutineContext, z, (Function2<? super ReaderScope, ? super Continuation<? super Unit>, ? extends Object>) function2);
    }

    public static final ReaderJob reader(CoroutineScope coroutineScope, CoroutineContext coroutineContext, boolean z, Function2<? super ReaderScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        Intrinsics.checkNotNullParameter(block, "block");
        return reader(coroutineScope, coroutineContext, new ByteChannel(false, 1, null), block);
    }

    public static final ReaderJob reader(CoroutineScope coroutineScope, CoroutineContext coroutineContext, final ByteChannel channel, Function2<? super ReaderScope, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        Intrinsics.checkNotNullParameter(channel, "channel");
        Intrinsics.checkNotNullParameter(block, "block");
        Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, coroutineContext, null, new ByteReadChannelOperationsKt$reader$job$1(block, channel, null), 2, null);
        jobLaunch$default.invokeOnCompletion(new Function1() { // from class: io.ktor.utils.io.ByteReadChannelOperationsKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ByteReadChannelOperationsKt.reader$lambda$0$0(channel, (Throwable) obj);
            }
        });
        return new ReaderJob(CloseHookByteWriteChannelKt.onClose(channel, new C10251(jobLaunch$default, null)), jobLaunch$default);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit reader$lambda$0$0(ByteChannel byteChannel, Throwable th) {
        if (th != null && !byteChannel.isClosedForRead()) {
            byteChannel.cancel(th);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelOperationsKt$reader$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteReadChannelOperations.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteReadChannelOperationsKt$reader$1", f = "ByteReadChannelOperations.kt", i = {}, l = {368}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C10251 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Job $job;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10251(Job job, Continuation<? super C10251> continuation) {
            super(1, continuation);
            this.$job = job;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C10251(this.$job, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((C10251) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (this.$job.join(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0050  */
    /* JADX WARN: Code duplicated, block: B:19:0x005a  */
    /* JADX WARN: Code duplicated, block: B:21:0x006a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:22:0x006b  */
    /* JADX WARN: Code duplicated, block: B:24:0x006f A[PHI: r11 r12 r13
      0x006f: PHI (r11v5 io.ktor.utils.io.ByteReadChannel) = (r11v3 io.ktor.utils.io.ByteReadChannel), (r11v7 io.ktor.utils.io.ByteReadChannel) binds: [B:18:0x0058, B:23:0x006d] A[DONT_GENERATE, DONT_INLINE]
      0x006f: PHI (r12v7 kotlinx.io.Buffer) = (r12v6 kotlinx.io.Buffer), (r12v8 kotlinx.io.Buffer) binds: [B:18:0x0058, B:23:0x006d] A[DONT_GENERATE, DONT_INLINE]
      0x006f: PHI (r13v7 int) = (r13v6 int), (r13v8 int) binds: [B:18:0x0058, B:23:0x006d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:26:0x0075  */
    /* JADX WARN: Code duplicated, block: B:28:0x0088  */
    /* JADX WARN: Code duplicated, block: B:29:0x0098  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0058 -> B:24:0x006f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x006b -> B:23:0x006d). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object readPacket(io.ktor.utils.io.ByteReadChannel r11, int r12, kotlin.coroutines.Continuation<? super kotlinx.io.Source> r13) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelOperationsKt.readPacket(io.ktor.utils.io.ByteReadChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object discardExact(ByteReadChannel byteReadChannel, long j, Continuation<? super Unit> continuation) throws EOFException {
        C10021 c10021;
        if (continuation instanceof C10021) {
            c10021 = (C10021) continuation;
            if ((c10021.label & Integer.MIN_VALUE) != 0) {
                c10021.label -= Integer.MIN_VALUE;
            } else {
                c10021 = new C10021(continuation);
            }
        } else {
            c10021 = new C10021(continuation);
        }
        Object objDiscard = c10021.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10021.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objDiscard);
            c10021.L$0 = SpillingKt.nullOutSpilledVariable(byteReadChannel);
            c10021.J$0 = j;
            c10021.label = 1;
            objDiscard = discard(byteReadChannel, j, c10021);
            if (objDiscard == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j = c10021.J$0;
            ResultKt.throwOnFailure(objDiscard);
        }
        if (((Number) objDiscard).longValue() < j) {
            throw new EOFException("Unable to discard " + j + " bytes");
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0044  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x004e -> B:26:0x0067). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0061 -> B:25:0x0064). Please report as a decompilation issue!!! */
    public static final Object discard(ByteReadChannel byteReadChannel, long j, Continuation<? super Long> continuation) throws EOFException {
        C10011 c10011;
        long j2;
        ByteReadChannel byteReadChannel2;
        long j3;
        if (continuation instanceof C10011) {
            c10011 = (C10011) continuation;
            if ((c10011.label & Integer.MIN_VALUE) != 0) {
                c10011.label -= Integer.MIN_VALUE;
            } else {
                c10011 = new C10011(continuation);
            }
        } else {
            c10011 = new C10011(continuation);
        }
        Object obj = c10011.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10011.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            j2 = j;
            if (j <= 0 && !byteReadChannel.isClosedForRead()) {
                if (getAvailableForRead(byteReadChannel) == 0) {
                    c10011.L$0 = byteReadChannel;
                    c10011.J$0 = j2;
                    c10011.J$1 = j;
                    c10011.label = 1;
                    if (ByteReadChannel.CC.awaitContent$default(byteReadChannel, 0, c10011, 1, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    byteReadChannel2 = byteReadChannel;
                    j3 = j;
                }
                long jMin = Math.min(j, ByteReadPacketKt.getRemaining(byteReadChannel.getReadBuffer()));
                ByteReadPacketKt.discard(byteReadChannel.getReadBuffer(), jMin);
                j -= jMin;
                if (j <= 0) {
                }
                return Boxing.boxLong(j2 - j);
            }
            return Boxing.boxLong(j2 - j);
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        j3 = c10011.J$1;
        j2 = c10011.J$0;
        byteReadChannel2 = (ByteReadChannel) c10011.L$0;
        ResultKt.throwOnFailure(obj);
        long j4 = j3;
        byteReadChannel = byteReadChannel2;
        j = j4;
        long jMin2 = Math.min(j, ByteReadPacketKt.getRemaining(byteReadChannel.getReadBuffer()));
        ByteReadPacketKt.discard(byteReadChannel.getReadBuffer(), jMin2);
        j -= jMin2;
        if (j <= 0) {
        }
        return Boxing.boxLong(j2 - j);
    }

    public static /* synthetic */ Object discard$default(ByteReadChannel byteReadChannel, long j, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Long.MAX_VALUE;
        }
        return discard(byteReadChannel, j, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Deprecated(message = "Use readLineStrict or readLine instead. See deprecation notes for more details.")
    public static final Object readUTF8Line(ByteReadChannel byteReadChannel, int i, Continuation<? super String> continuation) {
        C10231 c10231;
        StringBuilder sb;
        if (continuation instanceof C10231) {
            c10231 = (C10231) continuation;
            if ((c10231.label & Integer.MIN_VALUE) != 0) {
                c10231.label -= Integer.MIN_VALUE;
            } else {
                c10231 = new C10231(continuation);
            }
        } else {
            c10231 = new C10231(continuation);
        }
        Object obj = c10231.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c10231.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            StringBuilder sb2 = new StringBuilder();
            c10231.L$0 = SpillingKt.nullOutSpilledVariable(byteReadChannel);
            c10231.L$1 = sb2;
            c10231.I$0 = i;
            c10231.label = 1;
            Object uTF8LineTo = readUTF8LineTo(byteReadChannel, sb2, i, c10231);
            if (uTF8LineTo == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = uTF8LineTo;
            sb = sb2;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c10231.I$0;
            sb = (StringBuilder) c10231.L$1;
            ResultKt.throwOnFailure(obj);
        }
        if (((Boolean) obj).booleanValue()) {
            return sb.toString();
        }
        return null;
    }

    public static /* synthetic */ Object readUTF8Line$default(ByteReadChannel byteReadChannel, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readUTF8Line(byteReadChannel, i, continuation);
    }

    public static /* synthetic */ Object readUTF8LineTo$default(ByteReadChannel byteReadChannel, Appendable appendable, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return readUTF8LineTo(byteReadChannel, appendable, i, continuation);
    }

    @Deprecated(message = "Use readLineStrictTo or readLineTo instead. See deprecation notes for more details.")
    public static final Object readUTF8LineTo(ByteReadChannel byteReadChannel, Appendable appendable, int i, Continuation<? super Boolean> continuation) {
        return m8865readUTF8LineToRRvyBJ8(byteReadChannel, appendable, i, LineEndingMode.INSTANCE.m8876getAnyf0jXZW8(), continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Deprecated(message = "Use readLineStrictTo instead.")
    /* JADX INFO: renamed from: readUTF8LineTo-RRvyBJ8, reason: not valid java name */
    public static final Object m8865readUTF8LineToRRvyBJ8(ByteReadChannel byteReadChannel, Appendable appendable, int i, int i2, Continuation<? super Boolean> continuation) throws EOFException {
        C10242 c10242;
        if (continuation instanceof C10242) {
            c10242 = (C10242) continuation;
            if ((c10242.label & Integer.MIN_VALUE) != 0) {
                c10242.label -= Integer.MIN_VALUE;
            } else {
                c10242 = new C10242(continuation);
            }
        } else {
            c10242 = new C10242(continuation);
        }
        C10242 c10243 = c10242;
        Object lineStrictTo = c10243.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c10243.label;
        try {
            if (i3 == 0) {
                ResultKt.throwOnFailure(lineStrictTo);
                long j = i;
                LineEnding lineEnding = LineEndingMode.m8869containslTjpP64(i2, LineEndingMode.INSTANCE.m8877getCRf0jXZW8()) ? LineEnding.Lenient : LineEnding.Default;
                c10243.L$0 = SpillingKt.nullOutSpilledVariable(byteReadChannel);
                c10243.L$1 = SpillingKt.nullOutSpilledVariable(appendable);
                c10243.I$0 = i;
                c10243.I$1 = i2;
                c10243.label = 1;
                lineStrictTo = readLineStrictTo(byteReadChannel, appendable, j, lineEnding, c10243);
                if (lineStrictTo == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i3 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i4 = c10243.I$1;
                int i5 = c10243.I$0;
                ResultKt.throwOnFailure(lineStrictTo);
            }
            return Boxing.boxBoolean(((Number) lineStrictTo).longValue() >= 0);
        } catch (EOFException e) {
            String message = e.getMessage();
            if (message == null || !StringsKt.startsWith$default(message, "Unexpected end of stream after reading", false, 2, (Object) null)) {
                throw e;
            }
            return Boxing.boxBoolean(true);
        }
    }

    /* JADX INFO: renamed from: readUTF8LineTo-RRvyBJ8$default, reason: not valid java name */
    public static /* synthetic */ Object m8866readUTF8LineToRRvyBJ8$default(ByteReadChannel byteReadChannel, Appendable appendable, int i, int i2, Continuation continuation, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        if ((i3 & 4) != 0) {
            i2 = LineEndingMode.INSTANCE.m8876getAnyf0jXZW8();
        }
        return m8865readUTF8LineToRRvyBJ8(byteReadChannel, appendable, i, i2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object readLine(ByteReadChannel byteReadChannel, LineEnding lineEnding, Continuation<? super String> continuation) {
        C10151 c10151;
        StringBuilder sb;
        if (continuation instanceof C10151) {
            c10151 = (C10151) continuation;
            if ((c10151.label & Integer.MIN_VALUE) != 0) {
                c10151.label -= Integer.MIN_VALUE;
            } else {
                c10151 = new C10151(continuation);
            }
        } else {
            c10151 = new C10151(continuation);
        }
        Object obj = c10151.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10151.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            StringBuilder sb2 = new StringBuilder();
            c10151.L$0 = SpillingKt.nullOutSpilledVariable(byteReadChannel);
            c10151.L$1 = SpillingKt.nullOutSpilledVariable(lineEnding);
            c10151.L$2 = sb2;
            c10151.label = 1;
            Object lineTo = readLineTo(byteReadChannel, sb2, lineEnding, c10151);
            if (lineTo == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = lineTo;
            sb = sb2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            sb = (StringBuilder) c10151.L$2;
            ResultKt.throwOnFailure(obj);
        }
        if (((Number) obj).longValue() >= 0) {
            return sb.toString();
        }
        return null;
    }

    public static /* synthetic */ Object readLine$default(ByteReadChannel byteReadChannel, LineEnding lineEnding, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            lineEnding = LineEnding.Default;
        }
        return readLine(byteReadChannel, lineEnding, continuation);
    }

    public static /* synthetic */ Object readLineTo$default(ByteReadChannel byteReadChannel, Appendable appendable, LineEnding lineEnding, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            lineEnding = LineEnding.Default;
        }
        return readLineTo(byteReadChannel, appendable, lineEnding, continuation);
    }

    public static final Object readLineTo(ByteReadChannel byteReadChannel, Appendable appendable, LineEnding lineEnding, Continuation<? super Long> continuation) {
        return internalReadLineTo(byteReadChannel, appendable, Long.MAX_VALUE, lineEnding == LineEnding.Lenient, false, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object readLineStrict(ByteReadChannel byteReadChannel, long j, LineEnding lineEnding, Continuation<? super String> continuation) {
        C10161 c10161;
        StringBuilder sb;
        if (continuation instanceof C10161) {
            c10161 = (C10161) continuation;
            if ((c10161.label & Integer.MIN_VALUE) != 0) {
                c10161.label -= Integer.MIN_VALUE;
            } else {
                c10161 = new C10161(continuation);
            }
        } else {
            c10161 = new C10161(continuation);
        }
        C10161 c10162 = c10161;
        Object obj = c10162.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c10162.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            StringBuilder sb2 = new StringBuilder();
            c10162.L$0 = SpillingKt.nullOutSpilledVariable(byteReadChannel);
            c10162.L$1 = SpillingKt.nullOutSpilledVariable(lineEnding);
            c10162.L$2 = sb2;
            c10162.J$0 = j;
            c10162.label = 1;
            Object lineStrictTo = readLineStrictTo(byteReadChannel, sb2, j, lineEnding, c10162);
            if (lineStrictTo == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = lineStrictTo;
            sb = sb2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            long j2 = c10162.J$0;
            sb = (StringBuilder) c10162.L$2;
            ResultKt.throwOnFailure(obj);
        }
        if (((Number) obj).longValue() >= 0) {
            return sb.toString();
        }
        return null;
    }

    public static /* synthetic */ Object readLineStrict$default(ByteReadChannel byteReadChannel, long j, LineEnding lineEnding, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Long.MAX_VALUE;
        }
        if ((i & 2) != 0) {
            lineEnding = LineEnding.Default;
        }
        return readLineStrict(byteReadChannel, j, lineEnding, continuation);
    }

    public static /* synthetic */ Object readLineStrictTo$default(ByteReadChannel byteReadChannel, Appendable appendable, long j, LineEnding lineEnding, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        long j2 = j;
        if ((i & 4) != 0) {
            lineEnding = LineEnding.Default;
        }
        return readLineStrictTo(byteReadChannel, appendable, j2, lineEnding, continuation);
    }

    public static final Object readLineStrictTo(ByteReadChannel byteReadChannel, Appendable appendable, long j, LineEnding lineEnding, Continuation<? super Long> continuation) {
        if (j < 0) {
            throw new IllegalArgumentException(("Limit (" + j + ") should be non-negative").toString());
        }
        return internalReadLineTo(byteReadChannel, appendable, j, lineEnding == LineEnding.Lenient, true, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to calculate best type for var: r19v4 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r19v4 ??, new type: long
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r19v5 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r19v5 ??, new type: long
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r19v6 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r19v6 ??, new type: long
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r19v8 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r19v8 ??, new type: long
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r19v9 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r19v9 ??, new type: long
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:71:0x0293 -> B:72:0x02a1). Please report as a decompilation issue!!! */
    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r19v2 ??, new type: long
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:186)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:245)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        	... 5 more
        */
    public static final java.lang.Object internalReadLineTo(io.ktor.utils.io.ByteReadChannel r36, java.lang.Appendable r37, long r38, boolean r40, boolean r41, kotlin.coroutines.Continuation<? super java.lang.Long> r42) {
        /*
            Method dump skipped, instruction units count: 980
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelOperationsKt.internalReadLineTo(io.ktor.utils.io.ByteReadChannel, java.lang.Appendable, long, boolean, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final void internalReadLineTo$transferString(Source source, Appendable appendable, Ref.LongRef longRef, long j) throws IOException {
        if (j > 0) {
            String string = Utf8Kt.readString(source, j);
            appendable.append(string);
            longRef.element += (long) string.length();
        }
    }

    private static final long internalReadLineTo$scanForSoleCr(Source source, boolean z, long j, long j2) {
        long jMin;
        if (!z) {
            return -1L;
        }
        if (j == -1) {
            jMin = Math.min(j2, ByteReadPacketKt.getRemaining(source) - 1);
        } else {
            jMin = j == 0 ? 0L : j - 1;
        }
        return SourcesKt.indexOf$default(source, (byte) 13, 0L, jMin, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:21:0x0067 A[PHI: r10 r12
      0x0067: PHI (r10v5 kotlinx.io.Source) = (r10v0 kotlinx.io.Source), (r10v7 kotlinx.io.Source) binds: [B:15:0x0049, B:20:0x0065] A[DONT_GENERATE, DONT_INLINE]
      0x0067: PHI (r12v2 boolean) = (r12v0 boolean), (r12v3 boolean) binds: [B:15:0x0049, B:20:0x0065] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:23:0x0073  */
    /* JADX WARN: Code duplicated, block: B:26:0x007d  */
    /* JADX WARN: Code duplicated, block: B:28:0x0085  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object internalReadLineTo$discardCrlfOrCr(Source source, ByteReadChannel byteReadChannel, boolean z, Continuation<? super Boolean> continuation) throws EOFException {
        ByteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1 byteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1;
        if (continuation instanceof ByteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1) {
            byteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1 = (ByteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1) continuation;
            if ((byteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1.label & Integer.MIN_VALUE) != 0) {
                byteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1.label -= Integer.MIN_VALUE;
            } else {
                byteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1 = new ByteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1(continuation);
            }
        } else {
            byteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1 = new ByteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1(continuation);
        }
        Object objAwaitContent = byteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = byteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objAwaitContent);
            if (ByteReadPacketKt.getRemaining(source) < 2) {
                byteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1.L$0 = source;
                byteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1.L$1 = SpillingKt.nullOutSpilledVariable(byteReadChannel);
                byteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1.Z$0 = z;
                byteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1.label = 1;
                objAwaitContent = byteReadChannel.awaitContent(2, byteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1);
                if (objAwaitContent == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (source.getBufferField().get(1L) == 10) {
                ByteReadPacketKt.discard(source, 2L);
                return Boxing.boxBoolean(true);
            }
            if (z) {
                ByteReadPacketKt.discard(source, 1L);
                return Boxing.boxBoolean(true);
            }
            return Boxing.boxBoolean(false);
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        z = byteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1.Z$0;
        source = (Source) byteReadChannelOperationsKt$internalReadLineTo$discardCrlfOrCr$1.L$0;
        ResultKt.throwOnFailure(objAwaitContent);
        if (((Boolean) objAwaitContent).booleanValue()) {
            if (source.getBufferField().get(1L) == 10) {
                ByteReadPacketKt.discard(source, 2L);
                return Boxing.boxBoolean(true);
            }
        }
        if (z) {
            ByteReadPacketKt.discard(source, 1L);
            return Boxing.boxBoolean(true);
        }
        return Boxing.boxBoolean(false);
    }

    private static final Void throwTooLongLineException(long j) throws TooLongLineException {
        throw new TooLongLineException("Line exceeds limit of " + j + " characters");
    }

    private static final Void throwEndOfStreamException(long j) throws EOFException {
        throw new EOFException("Unexpected end of stream after reading " + j + " characters");
    }

    /* JADX WARN: Code duplicated, block: B:39:0x0121 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:40:0x0123  */
    /* JADX WARN: Code duplicated, block: B:42:0x0129  */
    /* JADX WARN: Code duplicated, block: B:43:0x012e  */
    /* JADX WARN: Code duplicated, block: B:45:0x0136  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x009e, code lost:
    
        if (io.ktor.utils.io.ByteReadChannel.CC.awaitContent$default(r0, 0, r2, 1, null) == r3) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object read(io.ktor.utils.io.ByteReadChannel r16, kotlin.jvm.functions.Function4<? super byte[], ? super java.lang.Integer, ? super java.lang.Integer, ? super kotlin.coroutines.Continuation<? super java.lang.Integer>, ? extends java.lang.Object> r17, kotlin.coroutines.Continuation<? super java.lang.Integer> r18) throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 337
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelOperationsKt.read(io.ktor.utils.io.ByteReadChannel, kotlin.jvm.functions.Function4, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object read$$forInline(ByteReadChannel byteReadChannel, Function4<? super byte[], ? super Integer, ? super Integer, ? super Continuation<? super Integer>, ? extends Object> function4, Continuation<? super Integer> continuation) throws EOFException {
        if (byteReadChannel.isClosedForRead()) {
            return -1;
        }
        if (byteReadChannel.getReadBuffer().exhausted()) {
            ByteReadChannel.CC.awaitContent$default(byteReadChannel, 0, continuation, 1, null);
        }
        if (byteReadChannel.isClosedForRead()) {
            return -1;
        }
        Ref.IntRef intRef = new Ref.IntRef();
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        Buffer bufferField = byteReadChannel.getReadBuffer().getBufferField();
        if (bufferField.exhausted()) {
            throw new IllegalArgumentException("Buffer is empty".toString());
        }
        Segment head = bufferField.getHead();
        Intrinsics.checkNotNull(head);
        intRef.element = ((Number) function4.invoke(head.dataAsByteArray(true), Integer.valueOf(Integer.valueOf(head.getPos()).intValue()), Integer.valueOf(Integer.valueOf(head.getLimit()).intValue()), null)).intValue();
        int iIntValue = Integer.valueOf(intRef.element).intValue();
        if (iIntValue != 0) {
            if (iIntValue < 0) {
                throw new IllegalStateException("Returned negative read bytes count");
            }
            if (iIntValue > head.getSize()) {
                throw new IllegalStateException("Returned too many bytes");
            }
            bufferField.skip(iIntValue);
        }
        return Integer.valueOf(intRef.element);
    }

    public static final int getAvailableForRead(ByteReadChannel byteReadChannel) {
        Intrinsics.checkNotNullParameter(byteReadChannel, "<this>");
        return (int) byteReadChannel.getReadBuffer().getBufferField().getSizeMut();
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0058  */
    /* JADX WARN: Code duplicated, block: B:25:0x0062  */
    /* JADX WARN: Code duplicated, block: B:27:0x0076 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:28:0x0077  */
    /* JADX WARN: Code duplicated, block: B:32:0x0087  */
    /* JADX WARN: Code duplicated, block: B:35:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0060 -> B:30:0x0081). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0077 -> B:29:0x007c). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object readFully(io.ktor.utils.io.ByteReadChannel r7, byte[] r8, int r9, int r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            boolean r0 = r11 instanceof io.ktor.utils.io.ByteReadChannelOperationsKt.C10131
            if (r0 == 0) goto L14
            r0 = r11
            io.ktor.utils.io.ByteReadChannelOperationsKt$readFully$1 r0 = (io.ktor.utils.io.ByteReadChannelOperationsKt.C10131) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            io.ktor.utils.io.ByteReadChannelOperationsKt$readFully$1 r0 = new io.ktor.utils.io.ByteReadChannelOperationsKt$readFully$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "Channel is already closed"
            r4 = 1
            if (r2 == 0) goto L42
            if (r2 != r4) goto L3a
            int r7 = r0.I$2
            int r8 = r0.I$1
            int r9 = r0.I$0
            java.lang.Object r10 = r0.L$1
            byte[] r10 = (byte[]) r10
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r2 = (io.ktor.utils.io.ByteReadChannel) r2
            kotlin.ResultKt.throwOnFailure(r11)
            goto L7c
        L3a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L42:
            kotlin.ResultKt.throwOnFailure(r11)
            if (r10 <= r9) goto L54
            boolean r11 = r7.isClosedForRead()
            if (r11 != 0) goto L4e
            goto L54
        L4e:
            java.io.EOFException r7 = new java.io.EOFException
            r7.<init>(r3)
            throw r7
        L54:
            r11 = r10
            r10 = r9
        L56:
            if (r9 >= r11) goto La6
            kotlinx.io.Source r2 = r7.getReadBuffer()
            boolean r2 = r2.exhausted()
            if (r2 == 0) goto L81
            r0.L$0 = r7
            r0.L$1 = r8
            r0.I$0 = r10
            r0.I$1 = r11
            r0.I$2 = r9
            r0.label = r4
            r2 = 0
            r5 = 0
            java.lang.Object r2 = io.ktor.utils.io.ByteReadChannel.CC.awaitContent$default(r7, r2, r0, r4, r5)
            if (r2 != r1) goto L77
            return r1
        L77:
            r2 = r7
            r7 = r9
            r9 = r10
            r10 = r8
            r8 = r11
        L7c:
            r11 = r8
            r8 = r10
            r10 = r9
            r9 = r7
            r7 = r2
        L81:
            boolean r2 = r7.isClosedForRead()
            if (r2 != 0) goto La0
            int r2 = r11 - r9
            kotlinx.io.Source r5 = r7.getReadBuffer()
            long r5 = io.ktor.utils.io.core.ByteReadPacketKt.getRemaining(r5)
            int r5 = (int) r5
            int r2 = java.lang.Math.min(r2, r5)
            kotlinx.io.Source r5 = r7.getReadBuffer()
            int r2 = r2 + r9
            kotlinx.io.SourcesKt.readTo(r5, r8, r9, r2)
            r9 = r2
            goto L56
        La0:
            java.io.EOFException r7 = new java.io.EOFException
            r7.<init>(r3)
            throw r7
        La6:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelOperationsKt.readFully(io.ktor.utils.io.ByteReadChannel, byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object readFully$default(ByteReadChannel byteReadChannel, byte[] bArr, int i, int i2, Continuation continuation, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return readFully(byteReadChannel, bArr, i, i2, continuation);
    }

    public static final void rethrowCloseCauseIfNeeded(ByteReadChannel byteReadChannel) throws Throwable {
        Intrinsics.checkNotNullParameter(byteReadChannel, "<this>");
        Throwable closedCause = byteReadChannel.getClosedCause();
        if (closedCause != null) {
            throw closedCause;
        }
    }

    public static final void rethrowCloseCauseIfNeeded(ByteWriteChannel byteWriteChannel) throws Throwable {
        Intrinsics.checkNotNullParameter(byteWriteChannel, "<this>");
        Throwable closedCause = byteWriteChannel.getClosedCause();
        if (closedCause != null) {
            throw closedCause;
        }
    }

    public static final void rethrowCloseCauseIfNeeded(ByteChannel byteChannel) throws Throwable {
        Intrinsics.checkNotNullParameter(byteChannel, "<this>");
        Throwable closedCause = byteChannel.getClosedCause();
        if (closedCause != null) {
            throw closedCause;
        }
    }

    public static /* synthetic */ Object readUntil$default(ByteReadChannel byteReadChannel, ByteString byteString, ByteWriteChannel byteWriteChannel, long j, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            j = Long.MAX_VALUE;
        }
        long j2 = j;
        if ((i & 8) != 0) {
            z = false;
        }
        return readUntil(byteReadChannel, byteString, byteWriteChannel, j2, z, continuation);
    }

    public static final Object readUntil(ByteReadChannel byteReadChannel, ByteString byteString, ByteWriteChannel byteWriteChannel, long j, boolean z, Continuation<? super Long> continuation) {
        return new ByteChannelScanner(byteReadChannel, byteString, byteWriteChannel, j).findNext$ktor_io(z, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x007b, code lost:
    
        if (discard(r7, r5, r0) == r1) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object skipIfFound(io.ktor.utils.io.ByteReadChannel r7, kotlinx.io.bytestring.ByteString r8, kotlin.coroutines.Continuation<? super java.lang.Boolean> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteReadChannelOperationsKt.C10261
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.ByteReadChannelOperationsKt$skipIfFound$1 r0 = (io.ktor.utils.io.ByteReadChannelOperationsKt.C10261) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.ByteReadChannelOperationsKt$skipIfFound$1 r0 = new io.ktor.utils.io.ByteReadChannelOperationsKt$skipIfFound$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4a
            if (r2 == r4) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r7 = r0.L$1
            kotlinx.io.bytestring.ByteString r7 = (kotlinx.io.bytestring.ByteString) r7
            java.lang.Object r7 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r7 = (io.ktor.utils.io.ByteReadChannel) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L7e
        L35:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3d:
            java.lang.Object r7 = r0.L$1
            r8 = r7
            kotlinx.io.bytestring.ByteString r8 = (kotlinx.io.bytestring.ByteString) r8
            java.lang.Object r7 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r7 = (io.ktor.utils.io.ByteReadChannel) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L5e
        L4a:
            kotlin.ResultKt.throwOnFailure(r9)
            int r9 = r8.getSize()
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r4
            java.lang.Object r9 = peek(r7, r9, r0)
            if (r9 != r1) goto L5e
            goto L7d
        L5e:
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r8)
            if (r9 == 0) goto L83
            int r9 = r8.getSize()
            long r5 = (long) r9
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$0 = r9
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r7 = discard(r7, r5, r0)
            if (r7 != r1) goto L7e
        L7d:
            return r1
        L7e:
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r7
        L83:
            r7 = 0
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelOperationsKt.skipIfFound(io.ktor.utils.io.ByteReadChannel, kotlinx.io.bytestring.ByteString, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object peek(ByteReadChannel byteReadChannel, int i, Continuation<? super ByteString> continuation) {
        C10051 c10051;
        if (continuation instanceof C10051) {
            c10051 = (C10051) continuation;
            if ((c10051.label & Integer.MIN_VALUE) != 0) {
                c10051.label -= Integer.MIN_VALUE;
            } else {
                c10051 = new C10051(continuation);
            }
        } else {
            c10051 = new C10051(continuation);
        }
        Object objAwaitContent = c10051.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c10051.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objAwaitContent);
            if (byteReadChannel.isClosedForRead()) {
                return null;
            }
            c10051.L$0 = byteReadChannel;
            c10051.I$0 = i;
            c10051.label = 1;
            objAwaitContent = byteReadChannel.awaitContent(i, c10051);
            if (objAwaitContent == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = c10051.I$0;
            byteReadChannel = (ByteReadChannel) c10051.L$0;
            ResultKt.throwOnFailure(objAwaitContent);
        }
        if (((Boolean) objAwaitContent).booleanValue()) {
            return ByteStringsKt.readByteString(byteReadChannel.getReadBuffer().peek(), i);
        }
        return null;
    }
}
