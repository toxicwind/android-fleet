package com.lagradost.cloudstream3;

import androidx.fragment.app.FragmentActivity;
import com.lagradost.cloudstream3.ui.home.HomeViewModel;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import java.util.Iterator;
import java.util.List;
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
@DebugMetadata(c = "com.lagradost.cloudstream3.MainActivity$Companion$handleAppIntentUrl$1$3", f = "MainActivity.kt", i = {}, l = {377}, m = "invokeSuspend", n = {}, nl = {2078}, s = {}, v = 2)
final class MainActivity$Companion$handleAppIntentUrl$1$3 extends SuspendLambda implements Function3<CoroutineScope, FragmentActivity, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ int $id;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainActivity$Companion$handleAppIntentUrl$1$3(FragmentActivity fragmentActivity, int i, Continuation<? super MainActivity$Companion$handleAppIntentUrl$1$3> continuation) {
        super(3, continuation);
        this.$activity = fragmentActivity;
        this.$id = i;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, FragmentActivity fragmentActivity, Continuation<? super Unit> continuation) {
        return new MainActivity$Companion$handleAppIntentUrl$1$3(this.$activity, this.$id, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object next;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = HomeViewModel.INSTANCE.getResumeWatching(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        List list = (List) obj;
        if (list != null) {
            int i2 = this.$id;
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                Integer id = ((DataStoreHelper.ResumeWatchingResult) next).getId();
                if (id != null && id.intValue() == i2) {
                    break;
                }
            }
            DataStoreHelper.ResumeWatchingResult resumeWatchingResult = (DataStoreHelper.ResumeWatchingResult) next;
            if (resumeWatchingResult != null) {
                AppContextUtils.loadSearchResult$default(AppContextUtils.INSTANCE, this.$activity, resumeWatchingResult, 1, null, 4, null);
                return Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }
}
