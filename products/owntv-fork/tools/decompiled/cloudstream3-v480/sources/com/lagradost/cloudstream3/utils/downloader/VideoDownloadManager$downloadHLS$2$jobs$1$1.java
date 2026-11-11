package com.lagradost.cloudstream3.utils.downloader;

import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.lagradost.cloudstream3.utils.M3u8Helper2;
import java.io.OutputStream;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;

/* JADX INFO: compiled from: DownloadManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadHLS$2$jobs$1$1", f = "DownloadManager.kt", i = {0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6}, l = {RemoteMediaPlayer.STATUS_TIMED_OUT, 1368, CastStatusCodes.ERROR_CAST_PLATFORM_NOT_CONNECTED, 1380, 2124, 2135, 1392}, m = "invokeSuspend", n = {"$this$launch", "isTooFarAhead", "$this$withLock_u24default$iv", "$i$f$withLock", "$this$launch", "isTooFarAhead", "$this$launch", "isTooFarAhead", "$this$withLock_u24default$iv", "$i$f$withLock", "$this$launch", "isTooFarAhead", "index", "$this$launch", "isTooFarAhead", "$this$invokeSuspend_u24lambda_u242", "$this$withLock_u24default$iv", "$completion$iv", "index", "$i$a$-run-VideoDownloadManager$downloadHLS$2$jobs$1$1$bytes$1", "$i$f$withLock", "$this$launch", "isTooFarAhead", "bytes", "$this$withLock_u24default$iv", "index", "$i$f$withLock", "$this$launch", "isTooFarAhead", "bytes", "$this$withLock_u24default$iv", "index", "$i$f$withLock", "$i$a$-withLock$default-VideoDownloadManager$downloadHLS$2$jobs$1$1$2"}, nl = {2103, 1369, CastStatusCodes.ERROR_NO_CAST_CONFIGURATION, 1381, 2125, 2136, 1394}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "I$0", "L$0", "L$1", "L$3", "L$4", "L$5", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "I$2"}, v = 2)
final class VideoDownloadManager$downloadHLS$2$jobs$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ IntIterator $current;
    final /* synthetic */ Mutex $currentMutex;
    final /* synthetic */ Mutex $fileMutex;
    final /* synthetic */ Ref.ObjectRef<OutputStream> $fileStream;
    final /* synthetic */ M3u8Helper2.LazyHlsDownloadData $items;
    final /* synthetic */ VideoDownloadManager.DownloadMetaData $metadata;
    final /* synthetic */ HashMap<Integer, byte[]> $pendingData;
    int I$0;
    int I$1;
    int I$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoDownloadManager$downloadHLS$2$jobs$1$1(Mutex mutex, Mutex mutex2, M3u8Helper2.LazyHlsDownloadData lazyHlsDownloadData, VideoDownloadManager.DownloadMetaData downloadMetaData, IntIterator intIterator, Ref.ObjectRef<OutputStream> objectRef, HashMap<Integer, byte[]> map, Continuation<? super VideoDownloadManager$downloadHLS$2$jobs$1$1> continuation) {
        super(2, continuation);
        this.$fileMutex = mutex;
        this.$currentMutex = mutex2;
        this.$items = lazyHlsDownloadData;
        this.$metadata = downloadMetaData;
        this.$current = intIterator;
        this.$fileStream = objectRef;
        this.$pendingData = map;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VideoDownloadManager$downloadHLS$2$jobs$1$1 videoDownloadManager$downloadHLS$2$jobs$1$1 = new VideoDownloadManager$downloadHLS$2$jobs$1$1(this.$fileMutex, this.$currentMutex, this.$items, this.$metadata, this.$current, this.$fileStream, this.$pendingData, continuation);
        videoDownloadManager$downloadHLS$2$jobs$1$1.L$0 = obj;
        return videoDownloadManager$downloadHLS$2$jobs$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoDownloadManager$downloadHLS$2$jobs$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:18:0x00b6 A[PHI: r4 r5
      0x00b6: PHI (r4v5 boolean) = (r4v0 boolean), (r4v4 boolean), (r4v6 boolean) binds: [B:17:0x00b3, B:38:0x012f, B:102:0x02b7] A[DONT_GENERATE, DONT_INLINE]
      0x00b6: PHI (r5v4 int) = (r5v0 int), (r5v3 int), (r5v5 int) binds: [B:17:0x00b3, B:38:0x012f, B:102:0x02b7] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:20:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:22:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:25:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:28:0x00f2 A[Catch: all -> 0x02ce, TryCatch #3 {all -> 0x02ce, blocks: (B:26:0x00ea, B:28:0x00f2, B:31:0x00fc, B:33:0x010c, B:34:0x010e, B:109:0x02c8), top: B:121:0x00ea }] */
    /* JADX WARN: Code duplicated, block: B:33:0x010c A[Catch: all -> 0x02ce, TryCatch #3 {all -> 0x02ce, blocks: (B:26:0x00ea, B:28:0x00f2, B:31:0x00fc, B:33:0x010c, B:34:0x010e, B:109:0x02c8), top: B:121:0x00ea }] */
    /* JADX WARN: Code duplicated, block: B:37:0x0117  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x012f -> B:18:0x00b6). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:76:0x023a -> B:78:0x023d). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 744
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadHLS$2$jobs$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
