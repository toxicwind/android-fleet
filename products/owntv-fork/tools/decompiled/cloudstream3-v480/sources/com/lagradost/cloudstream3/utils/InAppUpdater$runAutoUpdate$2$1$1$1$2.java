package com.lagradost.cloudstream3.utils;

import android.app.Activity;
import androidx.appcompat.app.AlertDialog;
import com.google.android.gms.cast.MediaError;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: InAppUpdater.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Landroidx/appcompat/app/AlertDialog$Builder;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.InAppUpdater$runAutoUpdate$2$1$1$1$2", f = "InAppUpdater.kt", i = {}, l = {MediaError.DetailedErrorCode.SMOOTH_NO_MEDIA_DATA}, m = "invokeSuspend", n = {}, nl = {333}, s = {}, v = 2)
final class InAppUpdater$runAutoUpdate$2$1$1$1$2 extends SuspendLambda implements Function3<CoroutineScope, AlertDialog.Builder, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $this_runAutoUpdate;
    final /* synthetic */ InAppUpdater.Update $update;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    InAppUpdater$runAutoUpdate$2$1$1$1$2(Activity activity, InAppUpdater.Update update, Continuation<? super InAppUpdater$runAutoUpdate$2$1$1$1$2> continuation) {
        super(3, continuation);
        this.$this_runAutoUpdate = activity;
        this.$update = update;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, AlertDialog.Builder builder, Continuation<? super Unit> continuation) {
        return new InAppUpdater$runAutoUpdate$2$1$1$1$2(this.$this_runAutoUpdate, this.$update, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = InAppUpdater.INSTANCE.downloadUpdate(this.$this_runAutoUpdate, this.$update.getUpdateURL(), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (!((Boolean) obj).booleanValue()) {
            this.$this_runAutoUpdate.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.utils.InAppUpdater$runAutoUpdate$2$1$1$1$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CommonActivity.INSTANCE.showToast(R.string.download_failed, (Integer) 1);
                }
            });
        }
        return Unit.INSTANCE;
    }
}
