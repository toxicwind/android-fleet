package com.lagradost.cloudstream3.ui.settings;

import androidx.fragment.app.FragmentActivity;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.utils.InAppUpdater;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import org.mozilla.universalchardet.prober.HebrewProber;

/* JADX INFO: compiled from: SettingsUpdates.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/settings/SettingsUpdates;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.SettingsUpdates$onCreatePreferences$8$1$1", f = "SettingsUpdates.kt", i = {}, l = {236}, m = "invokeSuspend", n = {}, nl = {HebrewProber.FINAL_MEM}, s = {}, v = 2)
final class SettingsUpdates$onCreatePreferences$8$1$1 extends SuspendLambda implements Function3<CoroutineScope, SettingsUpdates, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ SettingsUpdates this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SettingsUpdates$onCreatePreferences$8$1$1(SettingsUpdates settingsUpdates, Continuation<? super SettingsUpdates$onCreatePreferences$8$1$1> continuation) {
        super(3, continuation);
        this.this$0 = settingsUpdates;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, SettingsUpdates settingsUpdates, Continuation<? super Unit> continuation) {
        return new SettingsUpdates$onCreatePreferences$8$1$1(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        FragmentActivity activity;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FragmentActivity activity2 = this.this$0.getActivity();
            if (activity2 != null) {
                this.label = 1;
                obj = InAppUpdater.runAutoUpdate$default(InAppUpdater.INSTANCE, activity2, false, false, this, 2, null);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (!((Boolean) obj).booleanValue() && (activity = this.this$0.getActivity()) != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.ui.settings.SettingsUpdates$onCreatePreferences$8$1$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CommonActivity.INSTANCE.showToast(R.string.no_update_found, (Integer) 0);
                }
            });
        }
        return Unit.INSTANCE;
    }
}
