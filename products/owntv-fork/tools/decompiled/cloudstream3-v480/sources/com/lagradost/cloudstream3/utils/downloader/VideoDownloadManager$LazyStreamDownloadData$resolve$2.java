package com.lagradost.cloudstream3.utils.downloader;

import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.safefile.SafeFileKt;
import java.io.Closeable;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DownloadManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$LazyStreamDownloadData$resolve$2", f = "DownloadManager.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2}, l = {814, 835, 838}, m = "invokeSuspend", n = {"$this$withContext", "currentByte", "stopAt", "$this$withContext", "request", "requestStream", "read", "currentByte", "stopAt", "start", "$this$withContext", "request", "requestStream", "read", "currentByte", "stopAt", "start"}, nl = {824, 836, 846}, s = {"L$0", "J$0", "J$1", "L$0", "L$1", "L$2", "L$3", "J$0", "J$1", "J$2", "L$0", "L$1", "L$2", "L$3", "J$0", "J$1", "J$2"}, v = 2)
final class VideoDownloadManager$LazyStreamDownloadData$resolve$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Long>, Object> {
    final /* synthetic */ byte[] $buffer;
    final /* synthetic */ Function3<CoroutineScope, DownloadObjects.LazyStreamDownloadResponse, Continuation<? super Unit>, Object> $callback;
    final /* synthetic */ Long $endByte;
    final /* synthetic */ long $startByte;
    long J$0;
    long J$1;
    long J$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ VideoDownloadManager.LazyStreamDownloadData this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    VideoDownloadManager$LazyStreamDownloadData$resolve$2(long j, Long l, VideoDownloadManager.LazyStreamDownloadData lazyStreamDownloadData, byte[] bArr, Function3<? super CoroutineScope, ? super DownloadObjects.LazyStreamDownloadResponse, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super VideoDownloadManager$LazyStreamDownloadData$resolve$2> continuation) {
        super(2, continuation);
        this.$startByte = j;
        this.$endByte = l;
        this.this$0 = lazyStreamDownloadData;
        this.$buffer = bArr;
        this.$callback = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VideoDownloadManager$LazyStreamDownloadData$resolve$2 videoDownloadManager$LazyStreamDownloadData$resolve$2 = new VideoDownloadManager$LazyStreamDownloadData$resolve$2(this.$startByte, this.$endByte, this.this$0, this.$buffer, this.$callback, continuation);
        videoDownloadManager$LazyStreamDownloadData$resolve$2.L$0 = obj;
        return videoDownloadManager$LazyStreamDownloadData$resolve$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Long> continuation) {
        return ((VideoDownloadManager$LazyStreamDownloadData$resolve$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0103 A[Catch: all -> 0x018d, CancellationException -> 0x019f, TRY_LEAVE, TryCatch #11 {CancellationException -> 0x019f, all -> 0x018d, blocks: (B:34:0x00f2, B:36:0x0103), top: B:93:0x00f2 }] */
    /* JADX WARN: Code duplicated, block: B:44:0x013b  */
    /* JADX WARN: Code duplicated, block: B:53:0x014b  */
    /* JADX WARN: Code duplicated, block: B:61:0x0178 A[LOOP:0: B:93:0x00f2->B:61:0x0178, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:86:0x010e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:96:0x0185 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:97:0x0177 A[SYNTHETIC] */
    /* JADX WARN: Not initialized variable reg: 10, insn: 0x01a4: CHECK_CAST (r10 I:java.io.Closeable) = (java.io.Closeable) (r10 I:??[OBJECT, ARRAY]), block:B:82:0x01a4 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Closeable closeableCloseQuietly;
        long j;
        Object obj2;
        long j2;
        long j3;
        InputStream inputStreamByteStream;
        InputStream inputStream;
        long j4;
        InputStream inputStream2;
        long j5;
        Throwable th;
        NiceResponse niceResponse;
        Ref.IntRef intRef;
        long j6;
        InputStream inputStream3;
        int i;
        long j7;
        Function3<CoroutineScope, DownloadObjects.LazyStreamDownloadResponse, Continuation<? super Unit>, Object> function3;
        DownloadObjects.LazyStreamDownloadResponse lazyStreamDownloadResponse;
        DownloadObjects.LazyStreamDownloadResponse lazyStreamDownloadResponse2;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                j = this.$startByte;
                Long l = this.$endByte;
                long jLongValue = l != null ? l.longValue() : Long.MAX_VALUE;
                if (j >= jLongValue) {
                    return Boxing.boxLong(j);
                }
                Requests app2 = MainActivityKt.getApp();
                String str = this.this$0.url;
                Map map = this.this$0.headers;
                this.L$0 = coroutineScope;
                this.J$0 = j;
                this.J$1 = jLongValue;
                this.label = 1;
                obj2 = Requests.get$default(app2, str, MapsKt.plus(map, MapsKt.mapOf(TuplesKt.to("Range", "bytes=" + this.$startByte + '-'))), this.this$0.referer, null, null, false, 0, null, 0L, null, false, null, this, 3064, null);
                if (obj2 != coroutine_suspended) {
                    j2 = jLongValue;
                }
                return coroutine_suspended;
            }
            if (i2 != 1) {
                try {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        j2 = this.J$1;
                        long j8 = this.J$0;
                        intRef = (Ref.IntRef) this.L$3;
                        InputStream inputStream4 = (InputStream) this.L$2;
                        NiceResponse niceResponse2 = (NiceResponse) this.L$1;
                        ResultKt.throwOnFailure(obj);
                        j3 = j8;
                        inputStreamByteStream = inputStream4;
                        niceResponse = niceResponse2;
                        j6 = j2;
                        while (true) {
                            try {
                                i = inputStreamByteStream.read(this.$buffer, 0, this.this$0.getBufferSize());
                                intRef.element = i;
                                if (i >= 0) {
                                    break;
                                }
                                j7 = j3 + ((long) intRef.element);
                                function3 = this.$callback;
                                if (j7 >= j6) {
                                    try {
                                        try {
                                            long j9 = j3;
                                            j3 = j7;
                                            try {
                                                lazyStreamDownloadResponse = new DownloadObjects.LazyStreamDownloadResponse(this.$buffer, j9, j6);
                                                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                                this.L$1 = SpillingKt.nullOutSpilledVariable(niceResponse);
                                                this.L$2 = inputStreamByteStream;
                                                this.L$3 = SpillingKt.nullOutSpilledVariable(intRef);
                                                this.J$0 = j3;
                                                this.J$1 = j6;
                                                this.J$2 = j9;
                                                this.label = 2;
                                                if (function3.invoke(coroutineScope, lazyStreamDownloadResponse, this) != coroutine_suspended) {
                                                    inputStream = inputStreamByteStream;
                                                    j4 = j3;
                                                }
                                            } catch (Throwable th2) {
                                                th = th2;
                                                inputStream2 = inputStreamByteStream;
                                                j5 = j3;
                                                ArchComponentExtKt.logError(th);
                                                SafeFileKt.closeQuietly(inputStream2);
                                                j3 = j5;
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                            j3 = j7;
                                        }
                                    } catch (CancellationException e) {
                                        throw e;
                                    }
                                } else {
                                    inputStream3 = inputStreamByteStream;
                                    long j10 = j3;
                                    j3 = j7;
                                    try {
                                        try {
                                            try {
                                                lazyStreamDownloadResponse2 = new DownloadObjects.LazyStreamDownloadResponse(this.$buffer, j10, j3);
                                                j3 = j3;
                                                this.L$0 = coroutineScope;
                                                this.L$1 = SpillingKt.nullOutSpilledVariable(niceResponse);
                                                this.L$2 = inputStream3;
                                                this.L$3 = intRef;
                                                this.J$0 = j3;
                                                this.J$1 = j6;
                                                this.J$2 = j10;
                                                this.label = 3;
                                                if (function3.invoke(coroutineScope, lazyStreamDownloadResponse2, this) == coroutine_suspended) {
                                                    inputStreamByteStream = inputStream3;
                                                }
                                            } catch (Throwable th4) {
                                                th = th4;
                                                j3 = j3;
                                                inputStream2 = inputStream3;
                                                j5 = j3;
                                                ArchComponentExtKt.logError(th);
                                                SafeFileKt.closeQuietly(inputStream2);
                                                j3 = j5;
                                                return Boxing.boxLong(j3);
                                            }
                                        } catch (CancellationException e2) {
                                            e = e2;
                                            throw e;
                                        }
                                    } catch (Throwable th5) {
                                        th = th5;
                                    }
                                }
                                return coroutine_suspended;
                            } catch (CancellationException e3) {
                                e = e3;
                                inputStream3 = inputStreamByteStream;
                            } catch (Throwable th6) {
                                th = th6;
                                j5 = j3;
                                inputStream2 = inputStreamByteStream;
                            }
                        }
                        SafeFileKt.closeQuietly(inputStreamByteStream);
                        return Boxing.boxLong(j3);
                    }
                    j4 = this.J$0;
                    inputStream = (InputStream) this.L$2;
                    ResultKt.throwOnFailure(obj);
                    inputStreamByteStream = inputStream;
                    j3 = j4;
                    SafeFileKt.closeQuietly(inputStreamByteStream);
                } catch (CancellationException e4) {
                    throw e4;
                } catch (Throwable th7) {
                    th = th7;
                    ArchComponentExtKt.logError(th);
                    SafeFileKt.closeQuietly(inputStream2);
                    j3 = j5;
                    return Boxing.boxLong(j3);
                }
                return Boxing.boxLong(j3);
            }
            j2 = this.J$1;
            j = this.J$0;
            ResultKt.throwOnFailure(obj);
            obj2 = obj;
            NiceResponse niceResponse3 = (NiceResponse) obj2;
            niceResponse = niceResponse3;
            j3 = j;
            inputStreamByteStream = niceResponse3.getBody().byteStream();
            intRef = new Ref.IntRef();
            j6 = j2;
            while (true) {
                i = inputStreamByteStream.read(this.$buffer, 0, this.this$0.getBufferSize());
                intRef.element = i;
                if (i >= 0) {
                    break;
                    break;
                }
                j7 = j3 + ((long) intRef.element);
                function3 = this.$callback;
                if (j7 >= j6) {
                    long j11 = j3;
                    j3 = j7;
                    lazyStreamDownloadResponse = new DownloadObjects.LazyStreamDownloadResponse(this.$buffer, j11, j6);
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(niceResponse);
                    this.L$2 = inputStreamByteStream;
                    this.L$3 = SpillingKt.nullOutSpilledVariable(intRef);
                    this.J$0 = j3;
                    this.J$1 = j6;
                    this.J$2 = j11;
                    this.label = 2;
                    if (function3.invoke(coroutineScope, lazyStreamDownloadResponse, this) != coroutine_suspended) {
                        inputStream = inputStreamByteStream;
                        j4 = j3;
                        inputStreamByteStream = inputStream;
                        j3 = j4;
                        break;
                    }
                } else {
                    inputStream3 = inputStreamByteStream;
                    long j12 = j3;
                    j3 = j7;
                    lazyStreamDownloadResponse2 = new DownloadObjects.LazyStreamDownloadResponse(this.$buffer, j12, j3);
                    j3 = j3;
                    this.L$0 = coroutineScope;
                    this.L$1 = SpillingKt.nullOutSpilledVariable(niceResponse);
                    this.L$2 = inputStream3;
                    this.L$3 = intRef;
                    this.J$0 = j3;
                    this.J$1 = j6;
                    this.J$2 = j12;
                    this.label = 3;
                    if (function3.invoke(coroutineScope, lazyStreamDownloadResponse2, this) == coroutine_suspended) {
                        inputStreamByteStream = inputStream3;
                    }
                }
                return coroutine_suspended;
            }
            SafeFileKt.closeQuietly(inputStreamByteStream);
            return Boxing.boxLong(j3);
        } catch (Throwable th8) {
            SafeFileKt.closeQuietly(closeableCloseQuietly);
            throw th8;
        }
    }
}
