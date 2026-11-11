package com.lagradost.cloudstream3.utils.downloader;

import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.RemoteMediaPlayer;
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
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadThing$2$jobs$1$1", f = "DownloadManager.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4}, l = {RemoteMediaPlayer.STATUS_TIMED_OUT, 1183, CastStatusCodes.ERROR_CAST_PLATFORM_NOT_CONNECTED, 1195, 2124}, m = "invokeSuspend", n = {"$this$launch", "callback", "buffer", "isTooFarAhead", "$this$withLock_u24default$iv", "$i$f$withLock", "$this$launch", "callback", "buffer", "isTooFarAhead", "$this$launch", "callback", "buffer", "isTooFarAhead", "$this$withLock_u24default$iv", "$i$f$withLock", "$this$launch", "callback", "buffer", "isTooFarAhead", "index", "$this$launch", "callback", "buffer", "isTooFarAhead", "$this$withLock_u24default$iv", "index", "$i$f$withLock"}, nl = {2103, 1184, CastStatusCodes.ERROR_NO_CAST_CONFIGURATION, 1196, 2125}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1"}, v = 2)
final class VideoDownloadManager$downloadThing$2$jobs$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ IntIterator $current;
    final /* synthetic */ Mutex $currentMutex;
    final /* synthetic */ Mutex $fileMutex;
    final /* synthetic */ Ref.ObjectRef<OutputStream> $fileStream;
    final /* synthetic */ VideoDownloadManager.LazyStreamDownloadData $items;
    final /* synthetic */ VideoDownloadManager.DownloadMetaData $metadata;
    final /* synthetic */ HashMap<Long, DownloadObjects.LazyStreamDownloadResponse> $pendingData;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoDownloadManager$downloadThing$2$jobs$1$1(VideoDownloadManager.LazyStreamDownloadData lazyStreamDownloadData, Mutex mutex, Mutex mutex2, VideoDownloadManager.DownloadMetaData downloadMetaData, Ref.ObjectRef<OutputStream> objectRef, HashMap<Long, DownloadObjects.LazyStreamDownloadResponse> map, IntIterator intIterator, Continuation<? super VideoDownloadManager$downloadThing$2$jobs$1$1> continuation) {
        super(2, continuation);
        this.$items = lazyStreamDownloadData;
        this.$fileMutex = mutex;
        this.$currentMutex = mutex2;
        this.$metadata = downloadMetaData;
        this.$fileStream = objectRef;
        this.$pendingData = map;
        this.$current = intIterator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VideoDownloadManager$downloadThing$2$jobs$1$1 videoDownloadManager$downloadThing$2$jobs$1$1 = new VideoDownloadManager$downloadThing$2$jobs$1$1(this.$items, this.$fileMutex, this.$currentMutex, this.$metadata, this.$fileStream, this.$pendingData, this.$current, continuation);
        videoDownloadManager$downloadThing$2$jobs$1$1.L$0 = obj;
        return videoDownloadManager$downloadThing$2$jobs$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoDownloadManager$downloadThing$2$jobs$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:21:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:24:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:27:0x00f9 A[Catch: all -> 0x0211, TryCatch #0 {all -> 0x0211, blocks: (B:25:0x00f1, B:27:0x00f9, B:30:0x0103, B:32:0x0114, B:33:0x0116, B:73:0x020b), top: B:79:0x00f1 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x0114 A[Catch: all -> 0x0211, TryCatch #0 {all -> 0x0211, blocks: (B:25:0x00f1, B:27:0x00f9, B:30:0x0103, B:32:0x0114, B:33:0x0116, B:73:0x020b), top: B:79:0x00f1 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x011f  */
    /* JADX WARN: Code duplicated, block: B:39:0x013e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x013e -> B:40:0x0140). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x01a8 -> B:54:0x01ad). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r26) {
        /*
            Method dump skipped, instruction units count: 534
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadThing$2$jobs$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
