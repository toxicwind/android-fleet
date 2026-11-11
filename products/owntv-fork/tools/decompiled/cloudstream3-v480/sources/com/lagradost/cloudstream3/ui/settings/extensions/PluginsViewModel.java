package com.lagradost.cloudstream3.ui.settings.extensions;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import coil3.util.UtilsKt;
import com.google.android.gms.actions.SearchIntents;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.plugins.PluginData;
import com.lagradost.cloudstream3.plugins.PluginManager;
import com.lagradost.cloudstream3.plugins.PluginWrapper;
import com.lagradost.cloudstream3.plugins.RepositoryManager;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.Levenshtein;
import io.ktor.http.ContentType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: PluginsViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 <2\u00020\u0001:\u0001<B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u00052\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0014J$\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\f\u00101\u001a\b\u0012\u0004\u0012\u00020)0\u0005H\u0082@¢\u0006\u0002\u00102J\u0018\u00103\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u0018\u00104\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\"\u00105\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u00106\u001a\u0004\u0018\u00010\fH\u0002J\u0006\u00107\u001a\u00020.J\u0006\u00108\u001a\u00020.J\u001e\u00109\u001a\u00020%2\b\u0010/\u001a\u0004\u0018\u0001002\f\u00101\u001a\b\u0012\u0004\u0012\u00020)0\u0005J\u0010\u0010:\u001a\u00020.2\b\u00106\u001a\u0004\u0018\u00010\fJ\u0006\u0010;\u001a\u00020%R*\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R*\u0010\u0011\u001a\u001e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0013j\u0002`\u00150\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R6\u0010\u0016\u001a\u001e\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0013j\u0002`\u00150\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\f0\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR \u0010 \u001a\b\u0012\u0004\u0012\u00020\f0\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001f\"\u0004\b\"\u0010\tR\u0010\u0010#\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginsViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", ES6Iterator.VALUE_PROPERTY, "", "Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginViewData;", "plugins", "setPlugins", "(Ljava/util/List;)V", "pluginLanguages", "", "", "getPluginLanguages", "()Ljava/util/Set;", "setPluginLanguages", "(Ljava/util/Set;)V", "_filteredPlugins", "Landroidx/lifecycle/MutableLiveData;", "Lkotlin/Pair;", "", "Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginViewDataUpdate;", "filteredPlugins", "Landroidx/lifecycle/LiveData;", "getFilteredPlugins", "()Landroidx/lifecycle/LiveData;", "setFilteredPlugins", "(Landroidx/lifecycle/LiveData;)V", "tvTypes", "", "getTvTypes", "()Ljava/util/List;", "selectedLanguages", "getSelectedLanguages", "setSelectedLanguages", "currentQuery", "handlePluginAction", "Lkotlinx/coroutines/Job;", "activity", "Landroid/app/Activity;", "repositoryUrls", "Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;", "pluginWrapper", "Lcom/lagradost/cloudstream3/plugins/PluginWrapper;", PluginsFragmentKt.PLUGINS_BUNDLE_LOCAL, "updatePluginListPrivate", "", "context", "Landroid/content/Context;", "repositories", "(Landroid/content/Context;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "filterTvTypes", "filterLang", "sortByQuery", SearchIntents.EXTRA_QUERY, "updateFilteredPlugins", "clear", "updatePluginList", "search", "updatePluginListLocal", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PluginsViewModel extends ViewModel {
    public static final String TAG = "PLG";
    private MutableLiveData<Pair<Boolean, List<PluginViewData>>> _filteredPlugins;
    private String currentQuery;
    private LiveData<Pair<Boolean, List<PluginViewData>>> filteredPlugins;
    private List<String> selectedLanguages;
    private final List<String> tvTypes;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Map<String, List<PluginWrapper>> repositoryCache = new LinkedHashMap();
    private List<PluginViewData> plugins = CollectionsKt.emptyList();
    private Set<String> pluginLanguages = new LinkedHashSet();

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$updatePluginListPrivate$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginsViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel", f = "PluginsViewModel.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {209}, m = "updatePluginListPrivate", n = {"context", "repositories", "$this$flatMap$iv", "$this$flatMapTo$iv$iv", "destination$iv$iv", "element$iv$iv", "repositoryUrl", "isAdult", "$i$f$flatMap", "$i$f$flatMapTo", "$i$a$-flatMap-PluginsViewModel$updatePluginListPrivate$plugins$1"}, nl = {319}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "L$7", "I$0", "I$1", "I$2", "I$3"}, v = 2)
    static final class C09111 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        C09111(Continuation<? super C09111> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PluginsViewModel.this.updatePluginListPrivate(null, null, this);
        }
    }

    public PluginsViewModel() {
        MutableLiveData<Pair<Boolean, List<PluginViewData>>> mutableLiveData = new MutableLiveData<>();
        this._filteredPlugins = mutableLiveData;
        this.filteredPlugins = mutableLiveData;
        this.tvTypes = new ArrayList();
        this.selectedLanguages = CollectionsKt.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPlugins(List<PluginViewData> list) {
        String lowerCase;
        String lowerCase2;
        List<PluginViewData> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            String language = ((PluginViewData) it.next()).getPluginWrapper().getPlugin().getLanguage();
            if (language != null) {
                lowerCase = language.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            } else {
                lowerCase = null;
            }
            Set<String> set = this.pluginLanguages;
            String str = lowerCase;
            if (str == null || StringsKt.isBlank(str)) {
                lowerCase2 = "none";
            } else {
                lowerCase2 = lowerCase.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            }
            arrayList.add(Boolean.valueOf(set.add(lowerCase2)));
        }
        this.plugins = list;
    }

    public final Set<String> getPluginLanguages() {
        return this.pluginLanguages;
    }

    public final void setPluginLanguages(Set<String> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.pluginLanguages = set;
    }

    public final LiveData<Pair<Boolean, List<PluginViewData>>> getFilteredPlugins() {
        return this.filteredPlugins;
    }

    public final void setFilteredPlugins(LiveData<Pair<Boolean, List<PluginViewData>>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.filteredPlugins = liveData;
    }

    public final List<String> getTvTypes() {
        return this.tvTypes;
    }

    public final List<String> getSelectedLanguages() {
        return this.selectedLanguages;
    }

    public final void setSelectedLanguages(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.selectedLanguages = list;
    }

    /* JADX INFO: compiled from: PluginsViewModel.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J&\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u000bH\u0082@¢\u0006\u0002\u0010\u0014J\"\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aR \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginsViewModel$Companion;", "", "<init>", "()V", "repositoryCache", "", "", "", "Lcom/lagradost/cloudstream3/plugins/PluginWrapper;", "TAG", "isDownloaded", "", "context", "Landroid/content/Context;", "pluginName", "repositoryUrl", "getPlugins", "repository", "Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;", "canUseCache", "(Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadAll", "Lkotlinx/coroutines/Job;", "activity", "Landroid/app/Activity;", "viewModel", "Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginsViewModel;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isDownloaded(Context context, String pluginName, String repositoryUrl) {
            return PluginManager.INSTANCE.getPluginPath(context, pluginName, repositoryUrl).exists();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        public final Object getPlugins(RepositoryData repositoryData, boolean z, Continuation<? super List<PluginWrapper>> continuation) {
            PluginsViewModel$Companion$getPlugins$1 pluginsViewModel$Companion$getPlugins$1;
            List list;
            if (continuation instanceof PluginsViewModel$Companion$getPlugins$1) {
                pluginsViewModel$Companion$getPlugins$1 = (PluginsViewModel$Companion$getPlugins$1) continuation;
                if ((pluginsViewModel$Companion$getPlugins$1.label & Integer.MIN_VALUE) != 0) {
                    pluginsViewModel$Companion$getPlugins$1.label -= Integer.MIN_VALUE;
                } else {
                    pluginsViewModel$Companion$getPlugins$1 = new PluginsViewModel$Companion$getPlugins$1(this, continuation);
                }
            } else {
                pluginsViewModel$Companion$getPlugins$1 = new PluginsViewModel$Companion$getPlugins$1(this, continuation);
            }
            Object repoPlugins = pluginsViewModel$Companion$getPlugins$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = pluginsViewModel$Companion$getPlugins$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(repoPlugins);
                Log.i(PluginsViewModel.TAG, "getPlugins = " + repositoryData);
                if (z && PluginsViewModel.repositoryCache.containsKey(repositoryData.getUrl()) && (list = (List) PluginsViewModel.repositoryCache.get(repositoryData.getUrl())) != null) {
                    return list;
                }
                RepositoryManager repositoryManager = RepositoryManager.INSTANCE;
                pluginsViewModel$Companion$getPlugins$1.L$0 = repositoryData;
                pluginsViewModel$Companion$getPlugins$1.Z$0 = z;
                pluginsViewModel$Companion$getPlugins$1.label = 1;
                repoPlugins = repositoryManager.getRepoPlugins(repositoryData, pluginsViewModel$Companion$getPlugins$1);
                if (repoPlugins == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                boolean z2 = pluginsViewModel$Companion$getPlugins$1.Z$0;
                repositoryData = (RepositoryData) pluginsViewModel$Companion$getPlugins$1.L$0;
                ResultKt.throwOnFailure(repoPlugins);
            }
            List list2 = (List) repoPlugins;
            if (list2 == null) {
                return CollectionsKt.emptyList();
            }
            PluginsViewModel.repositoryCache.put(repositoryData.getUrl(), list2);
            return list2;
        }

        static /* synthetic */ Object getPlugins$default(Companion companion, RepositoryData repositoryData, boolean z, Continuation continuation, int i, Object obj) {
            if ((i & 2) != 0) {
                z = true;
            }
            return companion.getPlugins(repositoryData, z, continuation);
        }

        public final Job downloadAll(Activity activity, RepositoryData repository, PluginsViewModel viewModel) {
            Intrinsics.checkNotNullParameter(repository, "repository");
            return Coroutines.INSTANCE.ioSafe(this, new PluginsViewModel$Companion$downloadAll$1(activity, repository, viewModel, null));
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$handlePluginAction$1, reason: invalid class name */
    /* JADX INFO: compiled from: PluginsViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/settings/extensions/PluginsViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$handlePluginAction$1", f = "PluginsViewModel.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {175, 179, 200}, m = "invokeSuspend", n = {"repositoryData", TtmlNode.TAG_METADATA, UtilsKt.SCHEME_FILE, "repositoryData", TtmlNode.TAG_METADATA, UtilsKt.SCHEME_FILE, "isEnabled", ContentType.Message.TYPE, "repositoryData", TtmlNode.TAG_METADATA, UtilsKt.SCHEME_FILE, "success", ContentType.Message.TYPE}, nl = {177, 186, 201}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "I$0", "I$1", "L$0", "L$1", "L$2", "Z$0", "I$0"}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, PluginsViewModel, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $activity;
        final /* synthetic */ boolean $isLocal;
        final /* synthetic */ PluginWrapper $pluginWrapper;
        final /* synthetic */ List<RepositoryData> $repositoryUrls;
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;
        final /* synthetic */ PluginsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(List<RepositoryData> list, PluginWrapper pluginWrapper, boolean z, Activity activity, PluginsViewModel pluginsViewModel, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$repositoryUrls = list;
            this.$pluginWrapper = pluginWrapper;
            this.$isLocal = z;
            this.$activity = activity;
            this.this$0 = pluginsViewModel;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, PluginsViewModel pluginsViewModel, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(this.$repositoryUrls, this.$pluginWrapper, this.$isLocal, this.$activity, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:46:0x01a4, code lost:
        
            if (r8.updatePluginListPrivate(r17.$activity, r17.$repositoryUrls, r17) == r1) goto L47;
         */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r18) {
            /*
                Method dump skipped, instruction units count: 426
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        static final Unit invokeSuspend$lambda$0(boolean z, int i) {
            if (z) {
                CommonActivity.INSTANCE.showToast(i, (Integer) 0);
            } else {
                CommonActivity.INSTANCE.showToast(R.string.error, (Integer) 0);
            }
            return Unit.INSTANCE;
        }
    }

    public final Job handlePluginAction(Activity activity, List<RepositoryData> repositoryUrls, PluginWrapper pluginWrapper, boolean isLocal) {
        Intrinsics.checkNotNullParameter(repositoryUrls, "repositoryUrls");
        Intrinsics.checkNotNullParameter(pluginWrapper, "pluginWrapper");
        return Coroutines.INSTANCE.ioSafe(this, new AnonymousClass1(repositoryUrls, pluginWrapper, isLocal, activity, this, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:23:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:25:0x00f9 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:26:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00fa -> B:27:0x00fe). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object updatePluginListPrivate(android.content.Context r20, java.util.List<com.lagradost.cloudstream3.ui.settings.extensions.RepositoryData> r21, kotlin.coroutines.Continuation<? super kotlin.Unit> r22) {
        /*
            Method dump skipped, instruction units count: 416
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel.updatePluginListPrivate(android.content.Context, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:21:0x005a  */
    /* JADX WARN: Code duplicated, block: B:25:0x0072  */
    /* JADX WARN: Code duplicated, block: B:33:0x0064 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:34:0x007c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x0016 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:38:0x0016 A[SYNTHETIC] */
    public final List<PluginViewData> filterTvTypes(List<PluginViewData> list) {
        List<String> tvTypes;
        if (this.tvTypes.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            PluginViewData pluginViewData = (PluginViewData) obj;
            List<String> tvTypes2 = pluginViewData.getPluginWrapper().getPlugin().getTvTypes();
            if (tvTypes2 == null) {
                if (this.tvTypes.contains("Others")) {
                    tvTypes = pluginViewData.getPluginWrapper().getPlugin().getTvTypes();
                    if (tvTypes == null) {
                        tvTypes = CollectionsKt.emptyList();
                    }
                    if (tvTypes.isEmpty()) {
                        arrayList.add(obj);
                    }
                }
            } else {
                List<String> list2 = tvTypes2;
                if ((list2 instanceof Collection) && list2.isEmpty()) {
                    if (this.tvTypes.contains("Others")) {
                        tvTypes = pluginViewData.getPluginWrapper().getPlugin().getTvTypes();
                        if (tvTypes == null) {
                            tvTypes = CollectionsKt.emptyList();
                        }
                        if (tvTypes.isEmpty()) {
                            arrayList.add(obj);
                        }
                    }
                } else {
                    Iterator<T> it = list2.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (this.tvTypes.contains((String) it.next())) {
                            }
                        } else if (this.tvTypes.contains("Others")) {
                            tvTypes = pluginViewData.getPluginWrapper().getPlugin().getTvTypes();
                            if (tvTypes == null) {
                                tvTypes = CollectionsKt.emptyList();
                            }
                            if (tvTypes.isEmpty()) {
                            }
                        }
                        arrayList.add(obj);
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<PluginViewData> filterLang(List<PluginViewData> list) {
        boolean zContains;
        if (this.selectedLanguages.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            PluginViewData pluginViewData = (PluginViewData) obj;
            String language = pluginViewData.getPluginWrapper().getPlugin().getLanguage();
            List<String> list2 = this.selectedLanguages;
            if (language == null) {
                zContains = list2.contains("none");
            } else {
                String lowerCase = pluginViewData.getPluginWrapper().getPlugin().getLanguage().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                zContains = list2.contains(lowerCase);
            }
            if (zContains) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:15:0x0066 A[PHI: r4
      0x0066: PHI (r4v13 java.lang.Integer) = (r4v3 java.lang.Integer), (r4v8 java.lang.Integer) binds: [B:14:0x0064, B:26:0x00ae] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:25:0x00ad  */
    public final List<PluginViewData> sortByQuery(List<PluginViewData> list, String str) {
        String strTake;
        Pair pair;
        String str2 = str;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return CollectionsKt.sortedWith(list, new Comparator() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$sortByQuery$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((PluginViewData) t).getPluginWrapper().getPlugin().getName(), ((PluginViewData) t2).getPluginWrapper().getPlugin().getName());
                }
            });
        }
        ArrayList arrayList = new ArrayList();
        for (PluginViewData pluginViewData : list) {
            Levenshtein levenshtein = Levenshtein.INSTANCE;
            String lowerCase = pluginViewData.getPluginWrapper().getPlugin().getName().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String lowerCase2 = str.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            Integer numValueOf = Integer.valueOf(Levenshtein.partialRatio$default(levenshtein, lowerCase, lowerCase2, null, 4, null));
            if (numValueOf.intValue() <= 80) {
                numValueOf = null;
            }
            if (numValueOf == null) {
                String description = pluginViewData.getPluginWrapper().getPlugin().getDescription();
                if (description != null) {
                    String lowerCase3 = description.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                    if (lowerCase3 == null || (strTake = StringsKt.take(lowerCase3, 64)) == null) {
                        numValueOf = null;
                    } else {
                        Levenshtein levenshtein2 = Levenshtein.INSTANCE;
                        String lowerCase4 = str.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
                        numValueOf = Integer.valueOf(Levenshtein.partialRatio$default(levenshtein2, strTake, lowerCase4, null, 4, null));
                        if (numValueOf.intValue() <= 80) {
                            numValueOf = null;
                        }
                    }
                } else {
                    numValueOf = null;
                }
                pair = numValueOf != null ? TuplesKt.to(pluginViewData, Integer.valueOf(numValueOf.intValue())) : null;
            }
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        List listSortedWith = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$sortByQuery$$inlined$sortedBy$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(-((Number) ((Pair) t).getSecond()).intValue()), Integer.valueOf(-((Number) ((Pair) t2).getSecond()).intValue()));
            }
        });
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSortedWith, 10));
        Iterator it = listSortedWith.iterator();
        while (it.hasNext()) {
            arrayList2.add((PluginViewData) ((Pair) it.next()).getFirst());
        }
        return arrayList2;
    }

    public final void updateFilteredPlugins() {
        this._filteredPlugins.postValue(TuplesKt.to(false, sortByQuery(filterLang(filterTvTypes(this.plugins)), this.currentQuery)));
    }

    public final void clear() {
        this.currentQuery = null;
        this._filteredPlugins.postValue(TuplesKt.to(false, CollectionsKt.emptyList()));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$updatePluginList$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$updatePluginList$1", f = "PluginsViewModel.kt", i = {}, l = {288}, m = "invokeSuspend", n = {}, nl = {289}, s = {}, v = 2)
    static final class C09091 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ List<RepositoryData> $repositories;
        int label;
        final /* synthetic */ PluginsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09091(Context context, List<RepositoryData> list, PluginsViewModel pluginsViewModel, Continuation<? super C09091> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$repositories = list;
            this.this$0 = pluginsViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09091(this.$context, this.$repositories, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C09091) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$context == null) {
                    return Unit.INSTANCE;
                }
                Log.i(PluginsViewModel.TAG, "updatePluginList = " + this.$repositories);
                this.label = 1;
                if (this.this$0.updatePluginListPrivate(this.$context, this.$repositories, this) == coroutine_suspended) {
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

    public final Job updatePluginList(Context context, List<RepositoryData> repositories) {
        Intrinsics.checkNotNullParameter(repositories, "repositories");
        return ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new C09091(context, repositories, this, null), 3, null);
    }

    public final void search(String query) {
        this.currentQuery = query;
        this._filteredPlugins.postValue(TuplesKt.to(true, sortByQuery(filterLang(filterTvTypes(this.plugins)), query)));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$updatePluginListLocal$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PluginsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.extensions.PluginsViewModel$updatePluginListLocal$1", f = "PluginsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C09101 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C09101(Continuation<? super C09101> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PluginsViewModel.this.new C09101(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C09101) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Log.i(PluginsViewModel.TAG, "updatePluginList = local");
            Object[] objArrPlus = ArraysKt.plus((Object[]) PluginManager.INSTANCE.getPluginsOnline(), (Object[]) PluginManager.INSTANCE.getPluginsLocal());
            HashSet hashSet = new HashSet();
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : objArrPlus) {
                if (hashSet.add(((PluginData) obj2).getFilePath())) {
                    arrayList.add(obj2);
                }
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList3.add(new PluginViewData(PluginWrapper.INSTANCE.getLocalPluginWrapper(((PluginData) it.next()).toSitePlugin()), true));
            }
            ArrayList arrayList4 = arrayList3;
            PluginsViewModel.this.setPlugins(arrayList4);
            MutableLiveData mutableLiveData = PluginsViewModel.this._filteredPlugins;
            Boolean boolBoxBoolean = Boxing.boxBoolean(false);
            PluginsViewModel pluginsViewModel = PluginsViewModel.this;
            mutableLiveData.postValue(TuplesKt.to(boolBoxBoolean, pluginsViewModel.sortByQuery(pluginsViewModel.filterLang(pluginsViewModel.filterTvTypes(arrayList4)), PluginsViewModel.this.currentQuery)));
            return Unit.INSTANCE;
        }
    }

    public final Job updatePluginListLocal() {
        return ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new C09101(null), 3, null);
    }
}
