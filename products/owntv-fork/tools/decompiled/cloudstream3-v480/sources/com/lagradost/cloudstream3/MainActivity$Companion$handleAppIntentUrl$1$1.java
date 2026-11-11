package com.lagradost.cloudstream3;

import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.syncproviders.AuthRepo;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UiText;
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
@DebugMetadata(c = "com.lagradost.cloudstream3.MainActivity$Companion$handleAppIntentUrl$1$1", f = "MainActivity.kt", i = {}, l = {305}, m = "invokeSuspend", n = {}, nl = {306}, s = {}, v = 2)
final class MainActivity$Companion$handleAppIntentUrl$1$1 extends SuspendLambda implements Function3<CoroutineScope, FragmentActivity, Continuation<? super Unit>, Object> {
    final /* synthetic */ AuthRepo $api;
    final /* synthetic */ String $str;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainActivity$Companion$handleAppIntentUrl$1$1(String str, AuthRepo authRepo, Continuation<? super MainActivity$Companion$handleAppIntentUrl$1$1> continuation) {
        super(3, continuation);
        this.$str = str;
        this.$api = authRepo;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, FragmentActivity fragmentActivity, Continuation<? super Unit> continuation) {
        return new MainActivity$Companion$handleAppIntentUrl$1$1(this.$str, this.$api, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        UiText uiTextTxt;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Log.i(MainActivity.TAG, "handleAppIntent " + this.$str);
                this.label = 1;
                obj = this.$api.login(this.$str, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            if (zBooleanValue) {
                Log.i(MainActivity.TAG, "authenticated " + this.$api.getName());
            } else {
                Log.i(MainActivity.TAG, "failed to authenticate " + this.$api.getName());
            }
            CommonActivity commonActivity = CommonActivity.INSTANCE;
            if (zBooleanValue) {
                uiTextTxt = TextUtilKt.txt(R.string.authenticated_user, this.$api.getName());
            } else {
                uiTextTxt = TextUtilKt.txt(R.string.authenticated_user_fail, this.$api.getName());
            }
            CommonActivity.showToast$default(commonActivity, uiTextTxt, (Integer) null, 2, (Object) null);
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            CommonActivity.showToast$default(CommonActivity.INSTANCE, TextUtilKt.txt(R.string.authenticated_user_fail, this.$api.getName()), (Integer) null, 2, (Object) null);
        }
        return Unit.INSTANCE;
    }
}
