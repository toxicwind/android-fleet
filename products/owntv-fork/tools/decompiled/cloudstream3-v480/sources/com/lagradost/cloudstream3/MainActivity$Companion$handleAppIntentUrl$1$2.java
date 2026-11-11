package com.lagradost.cloudstream3;

import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.cast.MediaError;
import com.lagradost.cloudstream3.plugins.PluginManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Landroidx/fragment/app/FragmentActivity;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.MainActivity$Companion$handleAppIntentUrl$1$2", f = "MainActivity.kt", i = {}, l = {MediaError.DetailedErrorCode.SMOOTH_NO_MEDIA_DATA}, m = "invokeSuspend", n = {}, nl = {335}, s = {}, v = 2)
final class MainActivity$Companion$handleAppIntentUrl$1$2 extends SuspendLambda implements Function3<CoroutineScope, FragmentActivity, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentActivity $activity;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainActivity$Companion$handleAppIntentUrl$1$2(FragmentActivity fragmentActivity, Continuation<? super MainActivity$Companion$handleAppIntentUrl$1$2> continuation) {
        super(3, continuation);
        this.$activity = fragmentActivity;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, FragmentActivity fragmentActivity, Continuation<? super Unit> continuation) {
        return new MainActivity$Companion$handleAppIntentUrl$1$2(this.$activity, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (PluginManager.INSTANCE.___DO_NOT_CALL_FROM_A_PLUGIN_hotReloadAllLocalPlugins(this.$activity, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
