package com.lagradost.cloudstream3.ui.settings.extensions;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.ParCollectionsKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.DebugException;
import com.lagradost.cloudstream3.plugins.PluginData;
import com.lagradost.cloudstream3.plugins.PluginManager;
import com.lagradost.cloudstream3.plugins.PluginWrapper;
import com.lagradost.cloudstream3.plugins.RepositoryManager;
import com.lagradost.cloudstream3.utils.AppDebug;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: ExtensionsViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0010\u001a\u00020\u0011J\u0013\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002¢\u0006\u0002\u0010\u0013J\u0006\u0010\u0014\u001a\u00020\u0015R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/extensions/ExtensionsViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "_repositories", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;", "repositories", "Landroidx/lifecycle/LiveData;", "getRepositories", "()Landroidx/lifecycle/LiveData;", "_pluginStats", "Lcom/lagradost/cloudstream3/ui/settings/extensions/ExtensionsViewModel$PluginStats;", "pluginStats", "getPluginStats", "loadStats", "Lkotlinx/coroutines/Job;", "repos", "()[Lcom/lagradost/cloudstream3/ui/settings/extensions/RepositoryData;", "loadRepositories", "", "PluginStats", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ExtensionsViewModel extends ViewModel {
    private final MutableLiveData<PluginStats> _pluginStats;
    private final MutableLiveData<RepositoryData[]> _repositories;
    private final LiveData<PluginStats> pluginStats;
    private final LiveData<RepositoryData[]> repositories;

    /* JADX INFO: compiled from: ExtensionsViewModel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J\t\u0010\u001c\u001a\u00020\bHÆ\u0003JO\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\bHÆ\u0001J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/extensions/ExtensionsViewModel$PluginStats;", "", "total", "", "downloaded", "disabled", "notDownloaded", "downloadedText", "Lcom/lagradost/cloudstream3/utils/UiText;", "disabledText", "notDownloadedText", "<init>", "(IIIILcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;)V", "getTotal", "()I", "getDownloaded", "getDisabled", "getNotDownloaded", "getDownloadedText", "()Lcom/lagradost/cloudstream3/utils/UiText;", "getDisabledText", "getNotDownloadedText", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class PluginStats {
        private final int disabled;
        private final UiText disabledText;
        private final int downloaded;
        private final UiText downloadedText;
        private final int notDownloaded;
        private final UiText notDownloadedText;
        private final int total;

        public static /* synthetic */ PluginStats copy$default(PluginStats pluginStats, int i, int i2, int i3, int i4, UiText uiText, UiText uiText2, UiText uiText3, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                i = pluginStats.total;
            }
            if ((i5 & 2) != 0) {
                i2 = pluginStats.downloaded;
            }
            if ((i5 & 4) != 0) {
                i3 = pluginStats.disabled;
            }
            if ((i5 & 8) != 0) {
                i4 = pluginStats.notDownloaded;
            }
            if ((i5 & 16) != 0) {
                uiText = pluginStats.downloadedText;
            }
            if ((i5 & 32) != 0) {
                uiText2 = pluginStats.disabledText;
            }
            if ((i5 & 64) != 0) {
                uiText3 = pluginStats.notDownloadedText;
            }
            UiText uiText4 = uiText2;
            UiText uiText5 = uiText3;
            UiText uiText6 = uiText;
            int i6 = i3;
            return pluginStats.copy(i, i2, i6, i4, uiText6, uiText4, uiText5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getTotal() {
            return this.total;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getDownloaded() {
            return this.downloaded;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getDisabled() {
            return this.disabled;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getNotDownloaded() {
            return this.notDownloaded;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final UiText getDownloadedText() {
            return this.downloadedText;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final UiText getDisabledText() {
            return this.disabledText;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final UiText getNotDownloadedText() {
            return this.notDownloadedText;
        }

        public final PluginStats copy(int total, int downloaded, int disabled, int notDownloaded, UiText downloadedText, UiText disabledText, UiText notDownloadedText) {
            Intrinsics.checkNotNullParameter(downloadedText, "downloadedText");
            Intrinsics.checkNotNullParameter(disabledText, "disabledText");
            Intrinsics.checkNotNullParameter(notDownloadedText, "notDownloadedText");
            return new PluginStats(total, downloaded, disabled, notDownloaded, downloadedText, disabledText, notDownloadedText);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PluginStats)) {
                return false;
            }
            PluginStats pluginStats = (PluginStats) other;
            return this.total == pluginStats.total && this.downloaded == pluginStats.downloaded && this.disabled == pluginStats.disabled && this.notDownloaded == pluginStats.notDownloaded && Intrinsics.areEqual(this.downloadedText, pluginStats.downloadedText) && Intrinsics.areEqual(this.disabledText, pluginStats.disabledText) && Intrinsics.areEqual(this.notDownloadedText, pluginStats.notDownloadedText);
        }

        public int hashCode() {
            return (((((((((((this.total * 31) + this.downloaded) * 31) + this.disabled) * 31) + this.notDownloaded) * 31) + this.downloadedText.hashCode()) * 31) + this.disabledText.hashCode()) * 31) + this.notDownloadedText.hashCode();
        }

        public String toString() {
            return "PluginStats(total=" + this.total + ", downloaded=" + this.downloaded + ", disabled=" + this.disabled + ", notDownloaded=" + this.notDownloaded + ", downloadedText=" + this.downloadedText + ", disabledText=" + this.disabledText + ", notDownloadedText=" + this.notDownloadedText + ')';
        }

        public PluginStats(int i, int i2, int i3, int i4, UiText downloadedText, UiText disabledText, UiText notDownloadedText) {
            Intrinsics.checkNotNullParameter(downloadedText, "downloadedText");
            Intrinsics.checkNotNullParameter(disabledText, "disabledText");
            Intrinsics.checkNotNullParameter(notDownloadedText, "notDownloadedText");
            this.total = i;
            this.downloaded = i2;
            this.disabled = i3;
            this.notDownloaded = i4;
            this.downloadedText = downloadedText;
            this.disabledText = disabledText;
            this.notDownloadedText = notDownloadedText;
        }

        public final int getTotal() {
            return this.total;
        }

        public final int getDownloaded() {
            return this.downloaded;
        }

        public final int getDisabled() {
            return this.disabled;
        }

        public final int getNotDownloaded() {
            return this.notDownloaded;
        }

        public final UiText getDownloadedText() {
            return this.downloadedText;
        }

        public final UiText getDisabledText() {
            return this.disabledText;
        }

        public final UiText getNotDownloadedText() {
            return this.notDownloadedText;
        }
    }

    public ExtensionsViewModel() {
        MutableLiveData<RepositoryData[]> mutableLiveData = new MutableLiveData<>();
        this._repositories = mutableLiveData;
        this.repositories = mutableLiveData;
        MutableLiveData<PluginStats> mutableLiveData2 = new MutableLiveData<>(null);
        this._pluginStats = mutableLiveData2;
        this.pluginStats = mutableLiveData2;
    }

    public final LiveData<RepositoryData[]> getRepositories() {
        return this.repositories;
    }

    public final LiveData<PluginStats> getPluginStats() {
        return this.pluginStats;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsViewModel$loadStats$1, reason: invalid class name */
    /* JADX INFO: compiled from: ExtensionsViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/settings/extensions/ExtensionsViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsViewModel$loadStats$1", f = "ExtensionsViewModel.kt", i = {0}, l = {57}, m = "invokeSuspend", n = {"urls"}, nl = {59}, s = {"L$0"}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, ExtensionsViewModel, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, ExtensionsViewModel extensionsViewModel, Continuation<? super Unit> continuation) {
            return ExtensionsViewModel.this.new AnonymousClass1(continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:28:0x0090  */
        /* JADX WARN: Code duplicated, block: B:35:0x00a6 A[Catch: Exception -> 0x00b8, TRY_LEAVE, TryCatch #3 {Exception -> 0x00b8, blocks: (B:11:0x002d, B:14:0x0039, B:19:0x005e, B:26:0x008a, B:29:0x0091, B:35:0x00a6, B:34:0x00a1, B:25:0x0080, B:18:0x0054, B:15:0x003b, B:22:0x0065, B:31:0x0095), top: B:96:0x002d, inners: #1, #4, #5 }] */
        /* JADX WARN: Code duplicated, block: B:99:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws DebugException {
            Object objDecodeFromString;
            Object objM8936constructorimpl;
            KSerializer kSerializer;
            int i;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                Context context = CloudStreamApp.INSTANCE.getContext();
                if (context != null) {
                    try {
                        String string = DataStore.INSTANCE.getSharedPrefs(context).getString(ExtensionsViewModelKt.REPOSITORIES_KEY, null);
                        if (string == null) {
                            objDecodeFromString = null;
                        } else {
                            AppUtils appUtils = AppUtils.INSTANCE;
                            try {
                                Result.Companion companion = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(RepositoryData.class), RepositoryData.INSTANCE.serializer()));
                            } catch (Throwable th) {
                                Result.Companion companion2 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                            }
                            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                    objM8936constructorimpl = null;
                                }
                                kSerializer = (KSerializer) objM8936constructorimpl;
                                if (kSerializer != null) {
                                    try {
                                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                                    } catch (SerializationException e) {
                                        ArchComponentExtKt.logError(e);
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsViewModel$loadStats$1$invokeSuspend$$inlined$getKey$1
                                        });
                                    } catch (Throwable unused) {
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsViewModel$loadStats$1$invokeSuspend$$inlined$getKey$1
                                        });
                                    }
                                } else {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsViewModel$loadStats$1$invokeSuspend$$inlined$getKey$1
                                    });
                                }
                            } else {
                                try {
                                    Result.Companion companion3 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(RepositoryData[].class), null, 2, null));
                                } catch (Throwable th2) {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                                }
                                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                    objM8936constructorimpl = null;
                                }
                                kSerializer = (KSerializer) objM8936constructorimpl;
                                if (kSerializer != null) {
                                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                                } else {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsViewModel$loadStats$1$invokeSuspend$$inlined$getKey$1
                                    });
                                }
                            }
                        }
                    } catch (Exception unused2) {
                        objDecodeFromString = null;
                    }
                } else {
                    objDecodeFromString = null;
                }
                RepositoryData[] repositoryDataArr = (RepositoryData[]) objDecodeFromString;
                if (repositoryDataArr == null) {
                    repositoryDataArr = new RepositoryData[0];
                }
                RepositoryData[] repositoryDataArr2 = (RepositoryData[]) ArraysKt.plus((Object[]) repositoryDataArr, (Object[]) RepositoryManager.INSTANCE.getPREBUILT_REPOSITORIES());
                this.L$0 = SpillingKt.nullOutSpilledVariable(repositoryDataArr2);
                this.label = 1;
                obj = ParCollectionsKt.amap(ArraysKt.toList(repositoryDataArr2), new ExtensionsViewModel$loadStats$1$onlinePlugins$1(null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            List listFlatten = CollectionsKt.flatten((Iterable) obj);
            HashSet hashSet = new HashSet();
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : listFlatten) {
                if (hashSet.add(((PluginWrapper) obj2).getPlugin().getUrl())) {
                    arrayList.add(obj2);
                }
            }
            ArrayList arrayList2 = arrayList;
            PluginData[] pluginsOnline = PluginManager.INSTANCE.getPluginsOnline();
            ArrayList arrayList3 = new ArrayList();
            for (PluginData pluginData : pluginsOnline) {
                ArrayList arrayList4 = new ArrayList();
                for (Object obj3 : arrayList2) {
                    if (Intrinsics.areEqual(pluginData.getInternalName(), ((PluginWrapper) obj3).getPlugin().getInternalName())) {
                        arrayList4.add(obj3);
                    }
                }
                ArrayList arrayList5 = arrayList4;
                ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
                Iterator it = arrayList5.iterator();
                while (it.hasNext()) {
                    arrayList6.add(new PluginManager.OnlinePluginData(pluginData, (PluginWrapper) it.next()));
                }
                CollectionsKt.addAll(arrayList3, arrayList6);
            }
            HashSet hashSet2 = new HashSet();
            ArrayList arrayList7 = new ArrayList();
            for (Object obj4 : arrayList3) {
                if (hashSet2.add(((PluginManager.OnlinePluginData) obj4).getOnlineData().getPlugin().getUrl())) {
                    arrayList7.add(obj4);
                }
            }
            ArrayList arrayList8 = arrayList7;
            int size = arrayList2.size();
            ArrayList arrayList9 = arrayList8;
            if ((arrayList9 instanceof Collection) && arrayList9.isEmpty()) {
                i = 0;
            } else {
                Iterator it2 = arrayList9.iterator();
                int i3 = 0;
                while (it2.hasNext()) {
                    if (((PluginManager.OnlinePluginData) it2.next()).getIsDisabled() && (i3 = i3 + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
                i = i3;
            }
            int size2 = arrayList8.size();
            int i4 = size2 - i;
            int i5 = size - size2;
            PluginStats pluginStats = new PluginStats(size, i4, i, i5, TextUtilKt.txt(R.string.plugins_downloaded, Boxing.boxInt(i4)), TextUtilKt.txt(R.string.plugins_disabled, Boxing.boxInt(i)), TextUtilKt.txt(R.string.plugins_not_downloaded, Boxing.boxInt(i5)));
            if (!AppDebug.INSTANCE.isDebug() || pluginStats.getDownloaded() + pluginStats.getNotDownloaded() + pluginStats.getDisabled() == pluginStats.getTotal()) {
                ExtensionsViewModel.this._pluginStats.postValue(pluginStats);
                return Unit.INSTANCE;
            }
            throw new DebugException("downloaded(" + pluginStats.getDownloaded() + ") + notDownloaded(" + pluginStats.getNotDownloaded() + ") + disabled(" + pluginStats.getDisabled() + ") != total(" + pluginStats.getTotal() + ')');
        }
    }

    public final Job loadStats() {
        return Coroutines.INSTANCE.ioSafe(this, new AnonymousClass1(null));
    }

    private final RepositoryData[] repos() {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Context context = CloudStreamApp.INSTANCE.getContext();
        Object obj = null;
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(ExtensionsViewModelKt.REPOSITORIES_KEY, null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(RepositoryData.class), RepositoryData.INSTANCE.serializer()));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(RepositoryData[].class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                    }
                    if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                        objM8936constructorimpl = null;
                    }
                    KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
                    if (kSerializer != null) {
                        try {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                        } catch (SerializationException e) {
                            ArchComponentExtKt.logError(e);
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsViewModel$repos$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsViewModel$repos$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<RepositoryData[]>() { // from class: com.lagradost.cloudstream3.ui.settings.extensions.ExtensionsViewModel$repos$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        RepositoryData[] repositoryDataArr = (RepositoryData[]) obj;
        if (repositoryDataArr == null) {
            repositoryDataArr = new RepositoryData[0];
        }
        return (RepositoryData[]) ArraysKt.plus((Object[]) repositoryDataArr, (Object[]) RepositoryManager.INSTANCE.getPREBUILT_REPOSITORIES());
    }

    public final void loadRepositories() {
        this._repositories.postValue(repos());
    }
}
