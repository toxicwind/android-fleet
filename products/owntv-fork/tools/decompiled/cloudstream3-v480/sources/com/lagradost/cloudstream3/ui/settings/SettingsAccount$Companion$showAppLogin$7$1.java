package com.lagradost.cloudstream3.ui.settings;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.AddAccountInputBinding;
import com.lagradost.cloudstream3.syncproviders.AuthLoginResponse;
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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SettingsAccount.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/settings/SettingsAccount$Companion;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$showAppLogin$7$1", f = "SettingsAccount.kt", i = {}, l = {356}, m = "invokeSuspend", n = {}, nl = {357}, s = {}, v = 2)
final class SettingsAccount$Companion$showAppLogin$7$1 extends SuspendLambda implements Function3<CoroutineScope, SettingsAccount.Companion, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ AuthRepo $api;
    final /* synthetic */ AddAccountInputBinding $binding;
    final /* synthetic */ AlertDialog $dialog;
    final /* synthetic */ AuthLoginResponse $loginData;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SettingsAccount$Companion$showAppLogin$7$1(AuthRepo authRepo, AuthLoginResponse authLoginResponse, AlertDialog alertDialog, FragmentActivity fragmentActivity, AddAccountInputBinding addAccountInputBinding, Continuation<? super SettingsAccount$Companion$showAppLogin$7$1> continuation) {
        super(3, continuation);
        this.$api = authRepo;
        this.$loginData = authLoginResponse;
        this.$dialog = alertDialog;
        this.$activity = fragmentActivity;
        this.$binding = addAccountInputBinding;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, SettingsAccount.Companion companion, Continuation<? super Unit> continuation) {
        return new SettingsAccount$Companion$showAppLogin$7$1(this.$api, this.$loginData, this.$dialog, this.$activity, this.$binding, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        UIHelper uIHelper;
        MaterialButton applyBtt;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = this.$api.login(this.$loginData, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            if (((Boolean) obj).booleanValue()) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, TextUtilKt.txt(R.string.authenticated_user, this.$api.getName()), (Integer) null, 2, (Object) null);
                UIHelper.INSTANCE.dismissSafe(this.$dialog, this.$activity);
            } else {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, TextUtilKt.txt(R.string.authenticated_user_fail, this.$api.getName()), (Integer) null, 2, (Object) null);
            }
        } catch (Throwable th) {
            try {
                if ((th instanceof ErrorLoadingException) && th.getMessage() != null) {
                    CommonActivity.showToast$default(CommonActivity.INSTANCE, th.getMessage(), (Integer) null, 2, (Object) null);
                    return Unit.INSTANCE;
                }
                CommonActivity.showToast$default(CommonActivity.INSTANCE, TextUtilKt.txt(R.string.authenticated_user_fail, this.$api.getName()), (Integer) null, 2, (Object) null);
            } finally {
                uIHelper = UIHelper.INSTANCE;
                applyBtt = this.$binding.applyBtt;
                Intrinsics.checkNotNullExpressionValue(applyBtt, "applyBtt");
                uIHelper.hideProgress(applyBtt);
            }
        }
        return Unit.INSTANCE;
    }
}
