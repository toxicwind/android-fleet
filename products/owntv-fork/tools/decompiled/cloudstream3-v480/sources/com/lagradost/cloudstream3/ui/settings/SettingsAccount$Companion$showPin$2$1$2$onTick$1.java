package com.lagradost.cloudstream3.ui.settings;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.syncproviders.AuthPinData;
import com.lagradost.cloudstream3.syncproviders.AuthRepo;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SettingsAccount.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0000*\u0001\u0004\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "com/lagradost/cloudstream3/ui/settings/SettingsAccount$Companion$showPin$2$1$2"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$showPin$2$1$2$onTick$1", f = "SettingsAccount.kt", i = {}, l = {250}, m = "invokeSuspend", n = {}, nl = {254}, s = {}, v = 2)
final class SettingsAccount$Companion$showPin$2$1$2$onTick$1 extends SuspendLambda implements Function3<CoroutineScope, SettingsAccount$Companion$showPin$2$1$2, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ AuthRepo $api;
    final /* synthetic */ AlertDialog $dialog;
    final /* synthetic */ AuthPinData $pinCodeData;
    final /* synthetic */ int $secondsUntilFinished;
    int label;
    final /* synthetic */ SettingsAccount$Companion$showPin$2$1$2 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SettingsAccount$Companion$showPin$2$1$2$onTick$1(int i, AuthPinData authPinData, AuthRepo authRepo, AlertDialog alertDialog, FragmentActivity fragmentActivity, SettingsAccount$Companion$showPin$2$1$2 settingsAccount$Companion$showPin$2$1$2, Continuation<? super SettingsAccount$Companion$showPin$2$1$2$onTick$1> continuation) {
        super(3, continuation);
        this.$secondsUntilFinished = i;
        this.$pinCodeData = authPinData;
        this.$api = authRepo;
        this.$dialog = alertDialog;
        this.$activity = fragmentActivity;
        this.this$0 = settingsAccount$Companion$showPin$2$1$2;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, SettingsAccount$Companion$showPin$2$1$2 settingsAccount$Companion$showPin$2$1$2, Continuation<? super Unit> continuation) {
        return new SettingsAccount$Companion$showPin$2$1$2$onTick$1(this.$secondsUntilFinished, this.$pinCodeData, this.$api, this.$dialog, this.$activity, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws ErrorLoadingException {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$secondsUntilFinished % this.$pinCodeData.getInterval() == 0) {
                this.label = 1;
                obj = this.$api.login(this.$pinCodeData, this);
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
        if (((Boolean) obj).booleanValue()) {
            CommonActivity.showToast$default(CommonActivity.INSTANCE, TextUtilKt.txt(R.string.authenticated_user, this.$api.getName()), (Integer) null, 2, (Object) null);
            UIHelper.INSTANCE.dismissSafe(this.$dialog, this.$activity);
            cancel();
        }
        return Unit.INSTANCE;
    }
}
