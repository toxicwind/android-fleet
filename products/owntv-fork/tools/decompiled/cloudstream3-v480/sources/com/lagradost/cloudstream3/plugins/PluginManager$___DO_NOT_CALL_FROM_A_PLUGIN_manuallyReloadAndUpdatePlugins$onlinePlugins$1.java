package com.lagradost.cloudstream3.plugins;

import com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData;
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

/* JADX INFO: compiled from: PluginManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/plugins/PluginWrapper;", "it", "Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.plugins.PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$onlinePlugins$1", f = "PluginManager.kt", i = {0}, l = {841}, m = "invokeSuspend", n = {"it"}, nl = {-1}, s = {"L$0"}, v = 2)
final class PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$onlinePlugins$1 extends SuspendLambda implements Function2<RepositoryData, Continuation<? super List<? extends PluginWrapper>>, Object> {
    /* synthetic */ Object L$0;
    int label;

    PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$onlinePlugins$1(Continuation<? super PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$onlinePlugins$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$onlinePlugins$1 pluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$onlinePlugins$1 = new PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$onlinePlugins$1(continuation);
        pluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$onlinePlugins$1.L$0 = obj;
        return pluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$onlinePlugins$1;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(RepositoryData repositoryData, Continuation<? super List<PluginWrapper>> continuation) {
        return ((PluginManager$___DO_NOT_CALL_FROM_A_PLUGIN_manuallyReloadAndUpdatePlugins$onlinePlugins$1) create(repositoryData, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(RepositoryData repositoryData, Continuation<? super List<? extends PluginWrapper>> continuation) {
        return invoke2(repositoryData, (Continuation<? super List<PluginWrapper>>) continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        RepositoryData repositoryData = (RepositoryData) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = SpillingKt.nullOutSpilledVariable(repositoryData);
            this.label = 1;
            obj = RepositoryManager.INSTANCE.getRepoPlugins(repositoryData, this);
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
        return list == null ? CollectionsKt.emptyList() : list;
    }
}
