package com.lagradost.cloudstream3.plugins;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: RepositoryManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/plugins/PluginWrapper;", ImagesContract.URL, ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.plugins.RepositoryManager$getRepoPlugins$list$1", f = "RepositoryManager.kt", i = {0}, l = {178}, m = "invokeSuspend", n = {ImagesContract.URL}, nl = {279}, s = {"L$0"}, v = 2)
final class RepositoryManager$getRepoPlugins$list$1 extends SuspendLambda implements Function2<String, Continuation<? super List<? extends PluginWrapper>>, Object> {
    final /* synthetic */ Repository $repo;
    final /* synthetic */ RepositoryData $repositoryData;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RepositoryManager$getRepoPlugins$list$1(Repository repository, RepositoryData repositoryData, Continuation<? super RepositoryManager$getRepoPlugins$list$1> continuation) {
        super(2, continuation);
        this.$repo = repository;
        this.$repositoryData = repositoryData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RepositoryManager$getRepoPlugins$list$1 repositoryManager$getRepoPlugins$list$1 = new RepositoryManager$getRepoPlugins$list$1(this.$repo, this.$repositoryData, continuation);
        repositoryManager$getRepoPlugins$list$1.L$0 = obj;
        return repositoryManager$getRepoPlugins$list$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(String str, Continuation<? super List<? extends PluginWrapper>> continuation) {
        return invoke2(str, (Continuation<? super List<PluginWrapper>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(String str, Continuation<? super List<PluginWrapper>> continuation) {
        return ((RepositoryManager$getRepoPlugins$list$1) create(str, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str = (String) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = SpillingKt.nullOutSpilledVariable(str);
            this.label = 1;
            obj = RepositoryManager.INSTANCE.parsePlugins(str, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Iterable iterable = (Iterable) obj;
        Repository repository = this.$repo;
        RepositoryData repositoryData = this.$repositoryData;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(new PluginWrapper(repository, repositoryData, (SitePlugin) it.next()));
        }
        return arrayList;
    }
}
