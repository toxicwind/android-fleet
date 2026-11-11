package io.ktor.utils.io.jvm.javaio;

import io.ktor.utils.io.ByteReadChannel;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: Writing.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u001a&\u0010\u0005\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0086@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "Ljava/io/OutputStream;", "out", "", "limit", "copyTo", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/io/OutputStream;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class WritingKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.jvm.javaio.WritingKt$copyTo$1, reason: invalid class name */
    /* JADX INFO: compiled from: Writing.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.jvm.javaio.WritingKt", f = "Writing.kt", i = {0, 0, 0, 0}, l = {25}, m = "copyTo", n = {"$this$copyTo", "out", "limit", "result"}, s = {"L$0", "L$1", "J$0", "J$1"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WritingKt.copyTo(null, null, 0L, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0057  */
    /* JADX WARN: Code duplicated, block: B:21:0x0061  */
    /* JADX WARN: Code duplicated, block: B:23:0x0073 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:24:0x0074  */
    /* JADX WARN: Code duplicated, block: B:26:0x0082  */
    /* JADX WARN: Code duplicated, block: B:28:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0074 -> B:25:0x007c). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0082 -> B:27:0x0083). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:21:0x0061
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object copyTo(io.ktor.utils.io.ByteReadChannel r17, java.io.OutputStream r18, long r19, kotlin.coroutines.Continuation<? super java.lang.Long> r21) {
        /*
            r0 = r19
            r2 = r21
            boolean r3 = r2 instanceof io.ktor.utils.io.jvm.javaio.WritingKt.AnonymousClass1
            if (r3 == 0) goto L18
            r3 = r2
            io.ktor.utils.io.jvm.javaio.WritingKt$copyTo$1 r3 = (io.ktor.utils.io.jvm.javaio.WritingKt.AnonymousClass1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L18
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L1d
        L18:
            io.ktor.utils.io.jvm.javaio.WritingKt$copyTo$1 r3 = new io.ktor.utils.io.jvm.javaio.WritingKt$copyTo$1
            r3.<init>(r2)
        L1d:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 1
            if (r5 == 0) goto L42
            if (r5 != r6) goto L3a
            long r0 = r3.J$1
            long r7 = r3.J$0
            java.lang.Object r5 = r3.L$1
            java.io.OutputStream r5 = (java.io.OutputStream) r5
            java.lang.Object r9 = r3.L$0
            io.ktor.utils.io.ByteReadChannel r9 = (io.ktor.utils.io.ByteReadChannel) r9
            kotlin.ResultKt.throwOnFailure(r2)
            goto L7c
        L3a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L42:
            kotlin.ResultKt.throwOnFailure(r2)
            r7 = 0
            int r2 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r2 < 0) goto La6
            r5 = r3
            r2 = r0
            r0 = r17
            r1 = r18
        L51:
            boolean r9 = r0.isClosedForRead()
            if (r9 != 0) goto La1
            kotlinx.io.Source r9 = r0.getReadBuffer()
            boolean r9 = r9.exhausted()
            if (r9 == 0) goto L82
            r5.L$0 = r0
            r5.L$1 = r1
            r5.J$0 = r2
            r5.J$1 = r7
            r5.label = r6
            r9 = 0
            r10 = 0
            java.lang.Object r9 = io.ktor.utils.io.ByteReadChannel.CC.awaitContent$default(r0, r9, r5, r6, r10)
            if (r9 != r4) goto L74
            return r4
        L74:
            r9 = r0
            r16 = r5
            r5 = r1
            r0 = r7
            r7 = r2
            r3 = r16
        L7c:
            r11 = r5
            r5 = r3
            r2 = r7
            r7 = r0
            r0 = r9
            goto L83
        L82:
            r11 = r1
        L83:
            kotlinx.io.Source r1 = r0.getReadBuffer()
            kotlinx.io.Buffer r1 = r1.getBufferField()
            long r9 = r1.getSizeMut()
            long r7 = r7 + r9
            kotlinx.io.Source r1 = r0.getReadBuffer()
            kotlinx.io.Buffer r10 = r1.getBufferField()
            r14 = 2
            r15 = 0
            r12 = 0
            kotlinx.io.BuffersJvmKt.readTo$default(r10, r11, r12, r14, r15)
            r1 = r11
            goto L51
        La1:
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r7)
            return r0
        La6:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Limit shouldn't be negative: "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.jvm.javaio.WritingKt.copyTo(io.ktor.utils.io.ByteReadChannel, java.io.OutputStream, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object copyTo$default(ByteReadChannel byteReadChannel, OutputStream outputStream, long j, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyTo(byteReadChannel, outputStream, j, continuation);
    }
}
