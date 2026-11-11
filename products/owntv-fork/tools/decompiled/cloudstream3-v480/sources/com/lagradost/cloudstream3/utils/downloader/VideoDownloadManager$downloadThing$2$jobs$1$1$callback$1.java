package com.lagradost.cloudstream3.utils.downloader;

import com.google.android.gms.cast.RemoteMediaPlayer;
import java.io.OutputStream;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;

/* JADX INFO: compiled from: DownloadManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "response", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$LazyStreamDownloadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadThing$2$jobs$1$1$callback$1", f = "DownloadManager.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {RemoteMediaPlayer.STATUS_TIMED_OUT, 1120}, m = "invokeSuspend", n = {"$this$callback", "response", "$this$withLock_u24default$iv", "$i$f$withLock", "$this$callback", "response", "$this$withLock_u24default$iv", "$i$f$withLock", "$i$a$-withLock$default-VideoDownloadManager$downloadThing$2$jobs$1$1$callback$1$1"}, nl = {2103, 1122}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "I$0", "I$1"}, v = 2)
final class VideoDownloadManager$downloadThing$2$jobs$1$1$callback$1 extends SuspendLambda implements Function3<CoroutineScope, DownloadObjects.LazyStreamDownloadResponse, Continuation<? super Unit>, Object> {
    final /* synthetic */ Mutex $fileMutex;
    final /* synthetic */ Ref.ObjectRef<OutputStream> $fileStream;
    final /* synthetic */ VideoDownloadManager.DownloadMetaData $metadata;
    final /* synthetic */ HashMap<Long, DownloadObjects.LazyStreamDownloadResponse> $pendingData;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoDownloadManager$downloadThing$2$jobs$1$1$callback$1(Mutex mutex, VideoDownloadManager.DownloadMetaData downloadMetaData, Ref.ObjectRef<OutputStream> objectRef, HashMap<Long, DownloadObjects.LazyStreamDownloadResponse> map, Continuation<? super VideoDownloadManager$downloadThing$2$jobs$1$1$callback$1> continuation) {
        super(3, continuation);
        this.$fileMutex = mutex;
        this.$metadata = downloadMetaData;
        this.$fileStream = objectRef;
        this.$pendingData = map;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, DownloadObjects.LazyStreamDownloadResponse lazyStreamDownloadResponse, Continuation<? super Unit> continuation) {
        VideoDownloadManager$downloadThing$2$jobs$1$1$callback$1 videoDownloadManager$downloadThing$2$jobs$1$1$callback$1 = new VideoDownloadManager$downloadThing$2$jobs$1$1$callback$1(this.$fileMutex, this.$metadata, this.$fileStream, this.$pendingData, continuation);
        videoDownloadManager$downloadThing$2$jobs$1$1$callback$1.L$0 = coroutineScope;
        videoDownloadManager$downloadThing$2$jobs$1$1$callback$1.L$1 = lazyStreamDownloadResponse;
        return videoDownloadManager$downloadThing$2$jobs$1$1$callback$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00ba A[Catch: all -> 0x0152, TRY_ENTER, TryCatch #1 {all -> 0x0152, blocks: (B:23:0x0090, B:31:0x00ba, B:33:0x00c2, B:36:0x00cc, B:41:0x00f4), top: B:62:0x0090 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x00e3 A[Catch: all -> 0x00b6, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x00b6, blocks: (B:25:0x0098, B:39:0x00e3), top: B:64:0x0098 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x00f4 A[Catch: all -> 0x0152, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0152, blocks: (B:23:0x0090, B:31:0x00ba, B:33:0x00c2, B:36:0x00cc, B:41:0x00f4), top: B:62:0x0090 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x0131 A[Catch: all -> 0x0150, LOOP:0: B:44:0x0119->B:49:0x0131, LOOP_END, TRY_ENTER, TryCatch #0 {all -> 0x0150, blocks: (B:44:0x0119, B:46:0x0129, B:49:0x0131, B:43:0x0112, B:50:0x0145), top: B:61:0x00c0 }] */
    /* JADX WARN: Code duplicated, block: B:64:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:68:0x0129 A[SYNTHETIC] */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00b0, code lost:
    
        if (kotlinx.coroutines.DelayKt.delay(100, r16) == r2) goto L27;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00b0 -> B:28:0x00b3). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadThing$2$jobs$1$1$callback$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
