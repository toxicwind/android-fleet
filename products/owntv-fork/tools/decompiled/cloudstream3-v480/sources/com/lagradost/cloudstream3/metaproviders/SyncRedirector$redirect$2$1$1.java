package com.lagradost.cloudstream3.metaproviders;

import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.syncproviders.SyncIdName;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: SyncRedirector.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.SyncRedirector$redirect$2$1$1", f = "SyncRedirector.kt", i = {}, l = {48}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
final class SyncRedirector$redirect$2$1$1 extends SuspendLambda implements Function1<Continuation<? super String>, Object> {
    final /* synthetic */ String $it;
    final /* synthetic */ MainAPI $providerApi;
    final /* synthetic */ SyncIdName $syncName;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SyncRedirector$redirect$2$1$1(MainAPI mainAPI, SyncIdName syncIdName, String str, Continuation<? super SyncRedirector$redirect$2$1$1> continuation) {
        super(1, continuation);
        this.$providerApi = mainAPI;
        this.$syncName = syncIdName;
        this.$it = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new SyncRedirector$redirect$2$1$1(this.$providerApi, this.$syncName, this.$it, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super String> continuation) {
        return ((SyncRedirector$redirect$2$1$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
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
        Object loadUrl = this.$providerApi.getLoadUrl(this.$syncName, this.$it, this);
        return loadUrl == coroutine_suspended ? coroutine_suspended : loadUrl;
    }
}
