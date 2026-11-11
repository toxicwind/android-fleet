package com.lagradost.cloudstream3.ui.settings.testing;

import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.plugins.PluginManager;
import com.lagradost.cloudstream3.utils.Coroutines;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TestResultAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/settings/testing/TestResultAdapter;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.testing.TestResultAdapter$onBindContent$2$2$1$1", f = "TestResultAdapter.kt", i = {}, l = {114}, m = "invokeSuspend", n = {}, nl = {116}, s = {}, v = 2)
final class TestResultAdapter$onBindContent$2$2$1$1 extends SuspendLambda implements Function3<CoroutineScope, TestResultAdapter, Continuation<? super Unit>, Object> {
    final /* synthetic */ File $pluginFile;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TestResultAdapter$onBindContent$2$2$1$1(File file, Continuation<? super TestResultAdapter$onBindContent$2$2$1$1> continuation) {
        super(3, continuation);
        this.$pluginFile = file;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, TestResultAdapter testResultAdapter, Continuation<? super Unit> continuation) {
        return new TestResultAdapter$onBindContent$2$2$1$1(this.$pluginFile, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = PluginManager.INSTANCE.deletePlugin(this.$pluginFile, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        final boolean zBooleanValue = ((Boolean) obj).booleanValue();
        Coroutines.INSTANCE.runOnMainThread(new Function0() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestResultAdapter$onBindContent$2$2$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TestResultAdapter$onBindContent$2$2$1$1.invokeSuspend$lambda$0(zBooleanValue);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit invokeSuspend$lambda$0(boolean z) {
        if (z) {
            CommonActivity.INSTANCE.showToast(R.string.plugin_deleted, (Integer) 0);
        } else {
            CommonActivity.INSTANCE.showToast(R.string.error, (Integer) 0);
        }
        return Unit.INSTANCE;
    }
}
