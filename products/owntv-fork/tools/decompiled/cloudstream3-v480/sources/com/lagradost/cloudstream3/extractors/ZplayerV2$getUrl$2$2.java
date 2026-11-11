package com.lagradost.cloudstream3.extractors;

import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Zplayer.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "urlm3u8", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.extractors.ZplayerV2$getUrl$2$2", f = "Zplayer.kt", i = {0, 1, 1}, l = {41, 43}, m = "invokeSuspend", n = {"urlm3u8", "urlm3u8", "testurl"}, nl = {42, 48}, s = {"L$0", "L$0", "L$1"}, v = 2)
final class ZplayerV2$getUrl$2$2 extends SuspendLambda implements Function2<String, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<ExtractorLink> $sources;
    final /* synthetic */ String $url;
    /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ ZplayerV2 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ZplayerV2$getUrl$2$2(String str, ZplayerV2 zplayerV2, List<ExtractorLink> list, Continuation<? super ZplayerV2$getUrl$2$2> continuation) {
        super(2, continuation);
        this.$url = str;
        this.this$0 = zplayerV2;
        this.$sources = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ZplayerV2$getUrl$2$2 zplayerV2$getUrl$2$2 = new ZplayerV2$getUrl$2$2(this.$url, this.this$0, this.$sources, continuation);
        zplayerV2$getUrl$2$2.L$0 = obj;
        return zplayerV2$getUrl$2$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(String str, Continuation<? super Unit> continuation) {
        return ((ZplayerV2$getUrl$2$2) create(str, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x00d5, code lost:
    
        if (r0 == r1) goto L19;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r26) {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.ZplayerV2$getUrl$2$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
