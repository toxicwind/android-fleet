package com.lagradost.cloudstream3.ui.settings.extensions;

import android.content.Context;
import com.lagradost.cloudstream3.plugins.RepositoryManager;
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

/* JADX INFO: compiled from: ExtensionsFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/settings/extensions/RepoAdapter;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsFragment$onBindingCreated$1$4$1$dialogClickListener$1$1", f = "ExtensionsFragment.kt", i = {}, l = {131}, m = "invokeSuspend", n = {}, nl = {135}, s = {}, v = 2)
final class ExtensionsFragment$onBindingCreated$1$4$1$dialogClickListener$1$1 extends SuspendLambda implements Function3<CoroutineScope, RepoAdapter, Continuation<? super Unit>, Object> {
    final /* synthetic */ RepositoryData $repo;
    final /* synthetic */ Context $uiContext;
    int label;
    final /* synthetic */ ExtensionsFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ExtensionsFragment$onBindingCreated$1$4$1$dialogClickListener$1$1(Context context, RepositoryData repositoryData, ExtensionsFragment extensionsFragment, Continuation<? super ExtensionsFragment$onBindingCreated$1$4$1$dialogClickListener$1$1> continuation) {
        super(3, continuation);
        this.$uiContext = context;
        this.$repo = repositoryData;
        this.this$0 = extensionsFragment;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, RepoAdapter repoAdapter, Continuation<? super Unit> continuation) {
        return new ExtensionsFragment$onBindingCreated$1$4$1$dialogClickListener$1$1(this.$uiContext, this.$repo, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            RepositoryManager repositoryManager = RepositoryManager.INSTANCE;
            Context applicationContext = this.$uiContext.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            this.label = 1;
            if (repositoryManager.removeRepository(applicationContext, this.$repo, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.this$0.getExtensionViewModel().loadStats();
        this.this$0.getExtensionViewModel().loadRepositories();
        return Unit.INSTANCE;
    }
}
