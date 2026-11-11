package com.lagradost.cloudstream3.utils;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.mozilla.classfile.ByteCode;

/* JADX INFO: compiled from: M3u8Helper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.M3u8Helper2$LazyHlsDownloadData", f = "M3u8Helper.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {ByteCode.MONITORENTER, 201}, m = "resolveLinkWhileSafe", n = {"condition", "index", "tries", "failDelay", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "condition", "t", "index", "tries", "failDelay", CmcdData.OBJECT_TYPE_INIT_SEGMENT}, nl = {ByteCode.MONITOREXIT, ByteCode.ARRAYLENGTH}, s = {"L$0", "I$0", "I$1", "J$0", "I$2", "L$0", "L$1", "I$0", "I$1", "J$0", "I$2"}, v = 2)
final class M3u8Helper2$LazyHlsDownloadData$resolveLinkWhileSafe$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ M3u8Helper2.LazyHlsDownloadData this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    M3u8Helper2$LazyHlsDownloadData$resolveLinkWhileSafe$1(M3u8Helper2.LazyHlsDownloadData lazyHlsDownloadData, Continuation<? super M3u8Helper2$LazyHlsDownloadData$resolveLinkWhileSafe$1> continuation) {
        super(continuation);
        this.this$0 = lazyHlsDownloadData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.resolveLinkWhileSafe(0, 0, 0L, null, this);
    }
}
