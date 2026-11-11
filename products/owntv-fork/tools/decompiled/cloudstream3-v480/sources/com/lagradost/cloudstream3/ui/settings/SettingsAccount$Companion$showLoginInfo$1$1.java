package com.lagradost.cloudstream3.ui.settings;

import com.lagradost.cloudstream3.syncproviders.AuthRepo;
import com.lagradost.cloudstream3.syncproviders.AuthUser;
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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/settings/SettingsAccount$Companion;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.SettingsAccount$Companion$showLoginInfo$1$1", f = "SettingsAccount.kt", i = {}, l = {99}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
final class SettingsAccount$Companion$showLoginInfo$1$1 extends SuspendLambda implements Function3<CoroutineScope, SettingsAccount.Companion, Continuation<? super Unit>, Object> {
    final /* synthetic */ AuthRepo $api;
    final /* synthetic */ AuthUser $info;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SettingsAccount$Companion$showLoginInfo$1$1(AuthRepo authRepo, AuthUser authUser, Continuation<? super SettingsAccount$Companion$showLoginInfo$1$1> continuation) {
        super(3, continuation);
        this.$api = authRepo;
        this.$info = authUser;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, SettingsAccount.Companion companion, Continuation<? super Unit> continuation) {
        return new SettingsAccount$Companion$showLoginInfo$1$1(this.$api, this.$info, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.$api.logout(this.$info, this) == coroutine_suspended) {
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
