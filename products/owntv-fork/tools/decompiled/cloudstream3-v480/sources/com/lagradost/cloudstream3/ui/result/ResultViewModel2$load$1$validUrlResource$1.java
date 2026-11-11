package com.lagradost.cloudstream3.ui.result;

import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.metaproviders.SyncRedirector;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$load$1$validUrlResource$1", f = "ResultViewModel2.kt", i = {}, l = {2643}, m = "invokeSuspend", n = {}, nl = {2646}, s = {}, v = 2)
final class ResultViewModel2$load$1$validUrlResource$1 extends SuspendLambda implements Function1<Continuation<? super String>, Object> {
    final /* synthetic */ MainAPI $api;
    final /* synthetic */ String $url;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ResultViewModel2$load$1$validUrlResource$1(String str, MainAPI mainAPI, Continuation<? super ResultViewModel2$load$1$validUrlResource$1> continuation) {
        super(1, continuation);
        this.$url = str;
        this.$api = mainAPI;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new ResultViewModel2$load$1$validUrlResource$1(this.$url, this.$api, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super String> continuation) {
        return ((ResultViewModel2$load$1$validUrlResource$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        this.label = 1;
        Object objRedirect = SyncRedirector.INSTANCE.redirect(this.$url, this.$api, this);
        return objRedirect == coroutine_suspended ? coroutine_suspended : objRedirect;
    }
}
