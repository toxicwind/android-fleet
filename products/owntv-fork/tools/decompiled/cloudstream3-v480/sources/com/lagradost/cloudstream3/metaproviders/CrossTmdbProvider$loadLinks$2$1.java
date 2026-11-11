package com.lagradost.cloudstream3.metaproviders;

import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: CrossTmdbProvider.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\n"}, d2 = {"<anonymous>", "", "<destruct>", "Lkotlin/Pair;", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.CrossTmdbProvider$loadLinks$2$1", f = "CrossTmdbProvider.kt", i = {0, 0, 0, 0, 0}, l = {55}, m = "invokeSuspend", n = {"<destruct>", "apiName", "data", "it", "$i$a$-let-CrossTmdbProvider$loadLinks$2$1$1"}, nl = {56}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 2)
final class CrossTmdbProvider$loadLinks$2$1 extends SuspendLambda implements Function2<Pair<? extends String, ? extends String>, Continuation<? super Object>, Object> {
    final /* synthetic */ Function1<ExtractorLink, Unit> $callback;
    final /* synthetic */ boolean $isCasting;
    final /* synthetic */ Function1<SubtitleFile, Unit> $subtitleCallback;
    int I$0;
    /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    CrossTmdbProvider$loadLinks$2$1(boolean z, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super CrossTmdbProvider$loadLinks$2$1> continuation) {
        super(2, continuation);
        this.$isCasting = z;
        this.$subtitleCallback = function1;
        this.$callback = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CrossTmdbProvider$loadLinks$2$1 crossTmdbProvider$loadLinks$2$1 = new CrossTmdbProvider$loadLinks$2$1(this.$isCasting, this.$subtitleCallback, this.$callback, continuation);
        crossTmdbProvider$loadLinks$2$1.L$0 = obj;
        return crossTmdbProvider$loadLinks$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Pair<? extends String, ? extends String> pair, Continuation<? super Object> continuation) {
        return invoke2((Pair<String, String>) pair, (Continuation<Object>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Pair<String, String> pair, Continuation<Object> continuation) {
        return ((CrossTmdbProvider$loadLinks$2$1) create(pair, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Pair pair = (Pair) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                String str = (String) pair.component1();
                String str2 = (String) pair.component2();
                MainAPI apiFromNameNull = APIHolder.INSTANCE.getApiFromNameNull(str);
                if (apiFromNameNull == null) {
                    return null;
                }
                boolean z = this.$isCasting;
                Function1<SubtitleFile, Unit> function1 = this.$subtitleCallback;
                Function1<ExtractorLink, Unit> function2 = this.$callback;
                this.L$0 = SpillingKt.nullOutSpilledVariable(pair);
                this.L$1 = SpillingKt.nullOutSpilledVariable(str);
                this.L$2 = SpillingKt.nullOutSpilledVariable(str2);
                this.L$3 = SpillingKt.nullOutSpilledVariable(apiFromNameNull);
                this.I$0 = 0;
                this.label = 1;
                obj = apiFromNameNull.loadLinks(str2, z, function1, function2, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
            return Unit.INSTANCE;
        }
    }
}
