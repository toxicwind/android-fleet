package com.lagradost.cloudstream3.ui.settings.extensions;

import android.app.Activity;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.ParCollectionsKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.plugins.PluginManager;
import com.lagradost.cloudstream3.plugins.PluginWrapper;
import com.lagradost.cloudstream3.plugins.SitePlugin;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PluginsViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginsViewModel$Companion;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$Companion$downloadAll$1", f = "PluginsViewModel.kt", i = {0, 1, 1}, l = {95, 126}, m = "invokeSuspend", n = {"$this$ioSafe", "$this$ioSafe", "plugins"}, nl = {97, 135}, s = {"L$0", "L$0", "L$1"}, v = 2)
final class PluginsViewModel$Companion$downloadAll$1 extends SuspendLambda implements Function3<CoroutineScope, PluginsViewModel.Companion, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ RepositoryData $repository;
    final /* synthetic */ PluginsViewModel $viewModel;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PluginsViewModel$Companion$downloadAll$1(Activity activity, RepositoryData repositoryData, PluginsViewModel pluginsViewModel, Continuation<? super PluginsViewModel$Companion$downloadAll$1> continuation) {
        super(3, continuation);
        this.$activity = activity;
        this.$repository = repositoryData;
        this.$viewModel = pluginsViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, PluginsViewModel.Companion companion, Continuation<? super Unit> continuation) {
        PluginsViewModel$Companion$downloadAll$1 pluginsViewModel$Companion$downloadAll$1 = new PluginsViewModel$Companion$downloadAll$1(this.$activity, this.$repository, this.$viewModel, continuation);
        pluginsViewModel$Companion$downloadAll$1.L$0 = coroutineScope;
        return pluginsViewModel$Companion$downloadAll$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Coroutines coroutines;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$activity == null) {
                return Unit.INSTANCE;
            }
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = PluginsViewModel.Companion.getPlugins$default(PluginsViewModel.INSTANCE, this.$repository, false, this, 2, null);
            if (obj != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutines = (Coroutines) this.L$2;
            ResultKt.throwOnFailure(obj);
        }
        coroutines.main(obj, new AnonymousClass4(this.$viewModel, this.$activity, this.$repository, null));
        return Unit.INSTANCE;
        List list = (List) obj;
        Coroutines coroutines2 = Coroutines.INSTANCE;
        Activity activity = this.$activity;
        RepositoryData repositoryData = this.$repository;
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : list) {
            if (!PluginsViewModel.INSTANCE.isDownloaded(activity, ((PluginWrapper) obj2).getPlugin().getInternalName(), repositoryData.getUrl())) {
                arrayList.add(obj2);
            }
        }
        ArrayList arrayList2 = arrayList;
        Coroutines.INSTANCE.main(coroutineScope, new PluginsViewModel$Companion$downloadAll$1$2$1(list, arrayList2, null));
        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
        this.L$1 = SpillingKt.nullOutSpilledVariable(list);
        this.L$2 = coroutines2;
        this.label = 2;
        obj = ParCollectionsKt.amap(arrayList2, new AnonymousClass3(this.$activity, null), this);
        if (obj != coroutine_suspended) {
            coroutines = coroutines2;
            coroutines.main(obj, new AnonymousClass4(this.$viewModel, this.$activity, this.$repository, null));
            return Unit.INSTANCE;
        }
        return coroutine_suspended;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$Companion$downloadAll$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: PluginsViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "<destruct>", "Lcom/lagradost/cloudstream3/plugins/PluginWrapper;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$Companion$downloadAll$1$3", f = "PluginsViewModel.kt", i = {0, 0, 0}, l = {127}, m = "invokeSuspend", n = {"<destruct>", "repo", TtmlNode.TAG_METADATA}, nl = {134}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<PluginWrapper, Continuation<? super Boolean>, Object> {
        final /* synthetic */ Activity $activity;
        /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Activity activity, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$activity = activity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$activity, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PluginWrapper pluginWrapper, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass3) create(pluginWrapper, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            PluginWrapper pluginWrapper = (PluginWrapper) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            RepositoryData repositoryData = pluginWrapper.getRepositoryData();
            SitePlugin plugin = pluginWrapper.getPlugin();
            PluginManager pluginManager = PluginManager.INSTANCE;
            Activity activity = this.$activity;
            String url = plugin.getUrl();
            String fileHash = plugin.getFileHash();
            String internalName = plugin.getInternalName();
            String url2 = repositoryData.getUrl();
            boolean z = plugin.getStatus() != 0;
            this.L$0 = SpillingKt.nullOutSpilledVariable(pluginWrapper);
            this.L$1 = SpillingKt.nullOutSpilledVariable(repositoryData);
            this.L$2 = SpillingKt.nullOutSpilledVariable(plugin);
            this.label = 1;
            Object objDownloadPlugin = pluginManager.downloadPlugin(activity, url, fileHash, internalName, url2, z, this);
            return objDownloadPlugin == coroutine_suspended ? coroutine_suspended : objDownloadPlugin;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$Companion$downloadAll$1$4, reason: invalid class name */
    /* JADX INFO: compiled from: PluginsViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n"}, d2 = {"<anonymous>", "", "list", "", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$Companion$downloadAll$1$4", f = "PluginsViewModel.kt", i = {0}, l = {145}, m = "invokeSuspend", n = {"list"}, nl = {146}, s = {"L$0"}, v = 2)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<List<? extends Boolean>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $activity;
        final /* synthetic */ RepositoryData $repository;
        final /* synthetic */ PluginsViewModel $viewModel;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(PluginsViewModel pluginsViewModel, Activity activity, RepositoryData repositoryData, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$viewModel = pluginsViewModel;
            this.$activity = activity;
            this.$repository = repositoryData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.$viewModel, this.$activity, this.$repository, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(List<? extends Boolean> list, Continuation<? super Unit> continuation) {
            return invoke2((List<Boolean>) list, continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(List<Boolean> list, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(list, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:42:0x00c7  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            int i;
            List list = (List) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                List list2 = list;
                boolean z = list2 instanceof Collection;
                if (z && list2.isEmpty()) {
                    if (!list.isEmpty()) {
                        CommonActivity.INSTANCE.showToast(R.string.download_failed, Boxing.boxInt(0));
                    }
                } else {
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        if (((Boolean) it.next()).booleanValue()) {
                            CommonActivity commonActivity = CommonActivity.INSTANCE;
                            int i3 = R.string.batch_download_finish_format;
                            if (z && list2.isEmpty()) {
                                i = 0;
                            } else {
                                Iterator it2 = list2.iterator();
                                i = 0;
                                while (it2.hasNext()) {
                                    if (((Boolean) it2.next()).booleanValue() && (i = i + 1) < 0) {
                                        CollectionsKt.throwCountOverflow();
                                    }
                                }
                            }
                            commonActivity.showToast(TextUtilKt.txt(i3, Boxing.boxInt(i), TextUtilKt.txt(list.size() == 1 ? R.string.plugin_singular : R.string.plugin, new Object[0])), Boxing.boxInt(0));
                            PluginsViewModel pluginsViewModel = this.$viewModel;
                            if (pluginsViewModel != null) {
                                this.L$0 = SpillingKt.nullOutSpilledVariable(list);
                                this.label = 1;
                                if (pluginsViewModel.updatePluginListPrivate(this.$activity, CollectionsKt.listOf(this.$repository), this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                        }
                    }
                    if (!list.isEmpty()) {
                        CommonActivity.INSTANCE.showToast(R.string.download_failed, Boxing.boxInt(0));
                    }
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }
}
