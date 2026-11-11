package io.ktor.util;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.ByteWriteChannelOperationsKt;
import io.ktor.utils.io.ByteWriteChannelOperations_jvmKt;
import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.util.zip.Checksum;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: EncodersJvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0003\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0082\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u001a)\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\n\u0010\u000b\u001a,\u0010\u0013\u001a\u00020\u0000*\u00020\f2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0082@¢\u0006\u0004\b\u0013\u0010\u0014\"\u0014\u0010\u0015\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016\"\u0017\u0010\u0018\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0017\u0010\u001c\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0019\u001a\u0004\b\u001d\u0010\u001b\"\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"", "flag", "", "has", "(II)Z", "Lio/ktor/utils/io/ByteReadChannel;", "source", "gzip", "Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "inflate", "(Lio/ktor/utils/io/ByteReadChannel;ZLkotlin/coroutines/CoroutineContext;)Lio/ktor/utils/io/ByteReadChannel;", "Ljava/util/zip/Inflater;", "Lio/ktor/utils/io/ByteWriteChannel;", TvContractCompat.PARAM_CHANNEL, "Ljava/nio/ByteBuffer;", "buffer", "Ljava/util/zip/Checksum;", "checksum", "inflateTo", "(Ljava/util/zip/Inflater;Lio/ktor/utils/io/ByteWriteChannel;Ljava/nio/ByteBuffer;Ljava/util/zip/Checksum;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "GZIP_HEADER_SIZE", "I", "Lio/ktor/util/Encoder;", "Deflate", "Lio/ktor/util/Encoder;", "getDeflate", "()Lio/ktor/util/Encoder;", "GZip", "getGZip", "Lkotlinx/coroutines/CoroutineName;", "InflateWriterCoroutineName", "Lkotlinx/coroutines/CoroutineName;", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class EncodersJvmKt {
    private static final int GZIP_HEADER_SIZE = 10;
    private static final Encoder Deflate = new Encoder() { // from class: io.ktor.util.EncodersJvmKt$Deflate$1
        @Override // io.ktor.util.Encoder
        public ByteReadChannel encode(ByteReadChannel source, CoroutineContext coroutineContext) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
            return DeflaterKt.deflated$default(source, false, (ObjectPool) null, coroutineContext, 2, (Object) null);
        }

        @Override // io.ktor.util.Encoder
        public ByteWriteChannel encode(ByteWriteChannel source, CoroutineContext coroutineContext) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
            return DeflaterKt.deflated$default(source, false, (ObjectPool) null, coroutineContext, 2, (Object) null);
        }

        @Override // io.ktor.util.Encoder
        public ByteReadChannel decode(ByteReadChannel source, CoroutineContext coroutineContext) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
            return EncodersJvmKt.inflate(source, false, coroutineContext);
        }
    };
    private static final Encoder GZip = new Encoder() { // from class: io.ktor.util.EncodersJvmKt$GZip$1
        @Override // io.ktor.util.Encoder
        public ByteReadChannel encode(ByteReadChannel source, CoroutineContext coroutineContext) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
            return DeflaterKt.deflated$default(source, true, (ObjectPool) null, coroutineContext, 2, (Object) null);
        }

        @Override // io.ktor.util.Encoder
        public ByteWriteChannel encode(ByteWriteChannel source, CoroutineContext coroutineContext) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
            return DeflaterKt.deflated$default(source, true, (ObjectPool) null, coroutineContext, 2, (Object) null);
        }

        @Override // io.ktor.util.Encoder
        public ByteReadChannel decode(ByteReadChannel source, CoroutineContext coroutineContext) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
            return EncodersJvmKt.inflate$default(source, false, coroutineContext, 2, null);
        }
    };
    private static final CoroutineName InflateWriterCoroutineName = new CoroutineName("encoder-inflate-writer");

    /* JADX INFO: renamed from: io.ktor.util.EncodersJvmKt$inflateTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: EncodersJvm.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.util.EncodersJvmKt", f = "EncodersJvm.kt", i = {0, 0, 0, 0, 0}, l = {178}, m = "inflateTo", n = {"$this$inflateTo", TvContractCompat.PARAM_CHANNEL, "buffer", "checksum", "inflated"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
    static final class C09921 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C09921(Continuation<? super C09921> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EncodersJvmKt.inflateTo(null, null, null, null, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean has(int i, int i2) {
        return (i & i2) != 0;
    }

    public static final Encoder getDeflate() {
        return Deflate;
    }

    public static final Encoder getGZip() {
        return GZip;
    }

    static /* synthetic */ ByteReadChannel inflate$default(ByteReadChannel byteReadChannel, boolean z, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return inflate(byteReadChannel, z, coroutineContext);
    }

    /* JADX INFO: renamed from: io.ktor.util.EncodersJvmKt$inflate$1, reason: invalid class name */
    /* JADX INFO: compiled from: EncodersJvm.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.util.EncodersJvmKt$inflate$1", f = "EncodersJvm.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6}, l = {90, 107, 108, 118, 125, 130, 142}, m = "invokeSuspend", n = {"$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "header", "magic", "format", "flags", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "header", "magic", "format", "flags", "extraLen", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "header", "magic", "format", "flags", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "totalSize", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "totalSize", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "totalSize"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "S$0", "B$0", "B$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "S$0", "B$0", "B$1", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "S$0", "B$0", "B$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $gzip;
        final /* synthetic */ ByteReadChannel $source;
        byte B$0;
        byte B$1;
        int I$0;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        short S$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(boolean z, ByteReadChannel byteReadChannel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$gzip = z;
            this.$source = byteReadChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$gzip, this.$source, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:63:0x025f A[Catch: all -> 0x03e8, TRY_ENTER, TryCatch #0 {all -> 0x03e8, blocks: (B:7:0x0034, B:89:0x0328, B:83:0x02fb, B:85:0x0301, B:90:0x033f, B:92:0x0343, B:94:0x034b, B:96:0x036b, B:99:0x0370, B:100:0x0394, B:101:0x0395, B:102:0x03a0, B:103:0x03a1, B:104:0x03c4, B:105:0x03c5, B:109:0x03df, B:110:0x03e6, B:69:0x0296, B:71:0x029c, B:73:0x02a2, B:79:0x02eb, B:60:0x0255, B:63:0x025f, B:66:0x027c, B:68:0x0284, B:80:0x02f0, B:82:0x02f6, B:111:0x03e7, B:15:0x007b, B:59:0x024b), top: B:117:0x000f }] */
        /* JADX WARN: Code duplicated, block: B:65:0x027a  */
        /* JADX WARN: Code duplicated, block: B:66:0x027c A[Catch: all -> 0x03e8, PHI: r3 r4 r5 r9 r10 r11
          0x027c: PHI (r3v25 kotlin.jvm.internal.Ref$IntRef) = (r3v24 kotlin.jvm.internal.Ref$IntRef), (r3v26 kotlin.jvm.internal.Ref$IntRef) binds: [B:16:0x007e, B:64:0x0278] A[DONT_GENERATE, DONT_INLINE]
          0x027c: PHI (r4v15 java.util.zip.CRC32) = (r4v14 java.util.zip.CRC32), (r4v16 java.util.zip.CRC32) binds: [B:16:0x007e, B:64:0x0278] A[DONT_GENERATE, DONT_INLINE]
          0x027c: PHI (r5v12 java.lang.Object) = (r5v11 java.lang.Object), (r5v21 java.lang.Object) binds: [B:16:0x007e, B:64:0x0278] A[DONT_GENERATE, DONT_INLINE]
          0x027c: PHI (r9v21 java.util.zip.Inflater) = (r9v19 java.util.zip.Inflater), (r9v22 java.util.zip.Inflater) binds: [B:16:0x007e, B:64:0x0278] A[DONT_GENERATE, DONT_INLINE]
          0x027c: PHI (r10v20 java.nio.ByteBuffer) = (r10v17 java.nio.ByteBuffer), (r10v21 java.nio.ByteBuffer) binds: [B:16:0x007e, B:64:0x0278] A[DONT_GENERATE, DONT_INLINE]
          0x027c: PHI (r11v19 java.nio.ByteBuffer) = (r11v16 java.nio.ByteBuffer), (r11v20 java.nio.ByteBuffer) binds: [B:16:0x007e, B:64:0x0278] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {all -> 0x03e8, blocks: (B:7:0x0034, B:89:0x0328, B:83:0x02fb, B:85:0x0301, B:90:0x033f, B:92:0x0343, B:94:0x034b, B:96:0x036b, B:99:0x0370, B:100:0x0394, B:101:0x0395, B:102:0x03a0, B:103:0x03a1, B:104:0x03c4, B:105:0x03c5, B:109:0x03df, B:110:0x03e6, B:69:0x0296, B:71:0x029c, B:73:0x02a2, B:79:0x02eb, B:60:0x0255, B:63:0x025f, B:66:0x027c, B:68:0x0284, B:80:0x02f0, B:82:0x02f6, B:111:0x03e7, B:15:0x007b, B:59:0x024b), top: B:117:0x000f }] */
        /* JADX WARN: Code duplicated, block: B:68:0x0284 A[Catch: all -> 0x03e8, TryCatch #0 {all -> 0x03e8, blocks: (B:7:0x0034, B:89:0x0328, B:83:0x02fb, B:85:0x0301, B:90:0x033f, B:92:0x0343, B:94:0x034b, B:96:0x036b, B:99:0x0370, B:100:0x0394, B:101:0x0395, B:102:0x03a0, B:103:0x03a1, B:104:0x03c4, B:105:0x03c5, B:109:0x03df, B:110:0x03e6, B:69:0x0296, B:71:0x029c, B:73:0x02a2, B:79:0x02eb, B:60:0x0255, B:63:0x025f, B:66:0x027c, B:68:0x0284, B:80:0x02f0, B:82:0x02f6, B:111:0x03e7, B:15:0x007b, B:59:0x024b), top: B:117:0x000f }] */
        /* JADX WARN: Code duplicated, block: B:71:0x029c A[Catch: all -> 0x03e8, TryCatch #0 {all -> 0x03e8, blocks: (B:7:0x0034, B:89:0x0328, B:83:0x02fb, B:85:0x0301, B:90:0x033f, B:92:0x0343, B:94:0x034b, B:96:0x036b, B:99:0x0370, B:100:0x0394, B:101:0x0395, B:102:0x03a0, B:103:0x03a1, B:104:0x03c4, B:105:0x03c5, B:109:0x03df, B:110:0x03e6, B:69:0x0296, B:71:0x029c, B:73:0x02a2, B:79:0x02eb, B:60:0x0255, B:63:0x025f, B:66:0x027c, B:68:0x0284, B:80:0x02f0, B:82:0x02f6, B:111:0x03e7, B:15:0x007b, B:59:0x024b), top: B:117:0x000f }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x0282 -> B:60:0x0255). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:68:0x0284 -> B:69:0x0296). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:76:0x02c9 -> B:77:0x02d0). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:88:0x0327 -> B:89:0x0328). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r19) {
            /*
                Method dump skipped, instruction units count: 1042
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.EncodersJvmKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ByteReadChannel inflate(ByteReadChannel byteReadChannel, boolean z, CoroutineContext coroutineContext) {
        return ByteWriteChannelOperationsKt.writer$default((CoroutineScope) GlobalScope.INSTANCE, coroutineContext.plus(InflateWriterCoroutineName), false, (Function2) new AnonymousClass1(z, byteReadChannel, null), 2, (Object) null).getChannel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object inflateTo(Inflater inflater, ByteWriteChannel byteWriteChannel, ByteBuffer byteBuffer, Checksum checksum, Continuation<? super Integer> continuation) throws DataFormatException {
        C09921 c09921;
        int i;
        if (continuation instanceof C09921) {
            c09921 = (C09921) continuation;
            if ((c09921.label & Integer.MIN_VALUE) != 0) {
                c09921.label -= Integer.MIN_VALUE;
            } else {
                c09921 = new C09921(continuation);
            }
        } else {
            c09921 = new C09921(continuation);
        }
        Object obj = c09921.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c09921.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            byteBuffer.clear();
            int iInflate = inflater.inflate(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining());
            byteBuffer.position(byteBuffer.position() + iInflate);
            byteBuffer.flip();
            DeflaterKt.updateKeepPosition(checksum, byteBuffer);
            c09921.L$0 = SpillingKt.nullOutSpilledVariable(inflater);
            c09921.L$1 = SpillingKt.nullOutSpilledVariable(byteWriteChannel);
            c09921.L$2 = SpillingKt.nullOutSpilledVariable(byteBuffer);
            c09921.L$3 = SpillingKt.nullOutSpilledVariable(checksum);
            c09921.I$0 = iInflate;
            c09921.label = 1;
            if (ByteWriteChannelOperations_jvmKt.writeFully(byteWriteChannel, byteBuffer, c09921) == coroutine_suspended) {
                return coroutine_suspended;
            }
            i = iInflate;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = c09921.I$0;
            ResultKt.throwOnFailure(obj);
        }
        return Boxing.boxInt(i);
    }
}
