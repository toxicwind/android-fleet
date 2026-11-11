package com.lagradost.cloudstream3.metaproviders;

import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MovieLoadResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: CrossTmdbProvider.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/MovieLoadResponse;", "api", "Lcom/lagradost/cloudstream3/MainAPI;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.CrossTmdbProvider$load$base$1$data$1", f = "CrossTmdbProvider.kt", i = {0, 1, 1, 1}, l = {85, 99}, m = "invokeSuspend", n = {"api", "api", "search", "$i$a$-let-CrossTmdbProvider$load$base$1$data$1$2"}, nl = {127, 100}, s = {"L$0", "L$0", "L$1", "I$0"}, v = 2)
final class CrossTmdbProvider$load$base$1$data$1 extends SuspendLambda implements Function2<MainAPI, Continuation<? super MovieLoadResponse>, Object> {
    final /* synthetic */ String $matchName;
    final /* synthetic */ LoadResponse $this_apply;
    int I$0;
    /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ CrossTmdbProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CrossTmdbProvider$load$base$1$data$1(LoadResponse loadResponse, CrossTmdbProvider crossTmdbProvider, String str, Continuation<? super CrossTmdbProvider$load$base$1$data$1> continuation) {
        super(2, continuation);
        this.$this_apply = loadResponse;
        this.this$0 = crossTmdbProvider;
        this.$matchName = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CrossTmdbProvider$load$base$1$data$1 crossTmdbProvider$load$base$1$data$1 = new CrossTmdbProvider$load$base$1$data$1(this.$this_apply, this.this$0, this.$matchName, continuation);
        crossTmdbProvider$load$base$1$data$1.L$0 = obj;
        return crossTmdbProvider$load$base$1$data$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(MainAPI mainAPI, Continuation<? super MovieLoadResponse> continuation) {
        return ((CrossTmdbProvider$load$base$1$data$1) create(mainAPI, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c3, code lost:
    
        if (r12 == r1) goto L41;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.metaproviders.CrossTmdbProvider$load$base$1$data$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
