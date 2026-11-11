package com.lagradost.cloudstream3.ui.download;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.StatFs;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.api.Log;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.services.DownloadQueueService;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.ConsistentLiveData;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.DataStoreKt;
import com.lagradost.cloudstream3.utils.ResourceLiveData;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import io.ktor.http.ContentType;
import j$.util.function.BiFunction$CC;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: DownloadViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 _2\u00020\u0001:\u0003_`aB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020!J\u000e\u0010(\u001a\u00020%2\u0006\u0010'\u001a\u00020!J\u0006\u0010)\u001a\u00020%J\u0006\u0010*\u001a\u00020%J\u0006\u0010+\u001a\u00020%J\u0006\u0010,\u001a\u00020-J\u0006\u0010.\u001a\u00020-J,\u0010/\u001a\u00020%2\"\u00100\u001a\u001e\u0012\f\u0012\n\u0012\u0004\u0012\u00020!\u0018\u00010 \u0012\f\u0012\n\u0012\u0004\u0012\u00020!\u0018\u00010 01H\u0002J\b\u00102\u001a\u000203H\u0002J(\u00104\u001a\u00020%2\u0006\u00105\u001a\u0002062\u0018\u00107\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020!080\u0006JD\u00109\u001a\u00020%2\u0006\u00105\u001a\u0002062\f\u0010:\u001a\b\u0012\u0004\u0012\u00020;0\u00062\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00130=2\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020!0=J\u000e\u0010?\u001a\u0002032\u0006\u00105\u001a\u000206J\u0018\u0010@\u001a\u00020%2\u0010\b\u0002\u0010A\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006J\u0018\u0010B\u001a\u00020%2\u0010\b\u0002\u0010A\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0006J\u001e\u0010C\u001a\u00020D2\u0006\u00105\u001a\u0002062\f\u0010E\u001a\b\u0012\u0004\u0012\u00020F0\u0006H\u0002J`\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u00105\u001a\u0002062\f\u0010:\u001a\b\u0012\u0004\u0012\u00020;0\u00062\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00130=2\u0012\u0010H\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00130=2\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020!0=H\u0002J\u0016\u0010I\u001a\u0002032\u0006\u00105\u001a\u0002062\u0006\u0010J\u001a\u00020KJ\u0016\u0010L\u001a\u0002032\f\u0010M\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002J\u0016\u0010N\u001a\u00020%2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J\u000e\u0010P\u001a\u0002032\u0006\u00105\u001a\u000206J\u0016\u0010Q\u001a\u0002032\u0006\u00105\u001a\u0002062\u0006\u0010'\u001a\u00020!J\u001e\u0010R\u001a\u00020S2\u0006\u00105\u001a\u0002062\f\u0010T\u001a\b\u0012\u0004\u0012\u00020U0\u0006H\u0002J\u0018\u0010V\u001a\u00020K2\u0006\u00105\u001a\u0002062\u0006\u0010W\u001a\u00020SH\u0002J4\u0010X\u001a\u00020%2\u0006\u00105\u001a\u0002062\u0006\u0010Y\u001a\u00020K2\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020!0 2\f\u0010[\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002J\u0010\u0010\\\u001a\n\u0012\u0004\u0012\u00020U\u0018\u00010\u0006H\u0002J\u0016\u0010]\u001a\b\u0012\u0004\u0012\u00020U0\u00062\u0006\u0010'\u001a\u00020!H\u0002J\u0006\u0010^\u001a\u00020%R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00060\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\fR\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\fR\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00130\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\fR\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\t¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\fR\u001c\u0010\u001f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020!\u0018\u00010 0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\"\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020!\u0018\u00010 0\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\f¨\u0006b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/DownloadViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "_headerCards", "Lcom/lagradost/cloudstream3/utils/ResourceLiveData;", "", "Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached$Header;", "headerCards", "Landroidx/lifecycle/LiveData;", "Lcom/lagradost/cloudstream3/mvvm/Resource;", "getHeaderCards", "()Landroidx/lifecycle/LiveData;", "_childCards", "Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached$Child;", "childCards", "getChildCards", "_usedBytes", "Lcom/lagradost/cloudstream3/utils/ConsistentLiveData;", "", "usedBytes", "getUsedBytes", "_availableBytes", "availableBytes", "getAvailableBytes", "_downloadBytes", "downloadBytes", "getDownloadBytes", "_selectedBytes", "selectedBytes", "getSelectedBytes", "_selectedItemIds", "", "", "selectedItemIds", "getSelectedItemIds", "cancelSelection", "", "addSelected", "itemId", "removeSelected", "selectAllHeaders", "selectAllChildren", "clearSelectedItems", "isAllChildrenSelected", "", "isAllHeadersSelected", "updateSelectedItems", "action", "Lkotlin/Function1;", "updateSelectedBytes", "Lkotlinx/coroutines/Job;", "removeRedundantEpisodeKeys", "context", "Landroid/content/Context;", "keys", "Lkotlin/Pair;", "removeRedundantHeaderKeys", "cached", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadHeaderCached;", "totalBytesUsedByChild", "", "totalDownloads", "updateHeaderList", "postHeaders", "newValue", "postChildren", "calculateDownloadStats", "Lcom/lagradost/cloudstream3/ui/download/DownloadViewModel$DownloadStats;", "children", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeCached;", "createVisualDownloadList", "currentBytesUsedByChild", "updateChildList", "folder", "", "removeItems", "idsToRemove", "updateStorageStats", "visual", "handleMultiDelete", "handleSingleDelete", "processSelectedItems", "Lcom/lagradost/cloudstream3/ui/download/DownloadViewModel$DeleteData;", "selectedItemsList", "Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached;", "buildDeleteMessage", "data", "showDeleteConfirmationDialog", ContentType.Message.TYPE, "ids", "parentIds", "getSelectedItemsData", "getItemDataFromId", "clearChildren", "Companion", "DownloadStats", "DeleteData", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadViewModel extends ViewModel {
    public static final String TAG = "DownloadViewModel";
    private final ConsistentLiveData<Long> _availableBytes;
    private final ResourceLiveData<List<VisualDownloadCached.Child>> _childCards;
    private final ConsistentLiveData<Long> _downloadBytes;
    private final ResourceLiveData<List<VisualDownloadCached.Header>> _headerCards;
    private final ConsistentLiveData<Long> _selectedBytes;
    private final ConsistentLiveData<Set<Integer>> _selectedItemIds;
    private final ConsistentLiveData<Long> _usedBytes;
    private final LiveData<Long> availableBytes;
    private final LiveData<Resource<List<VisualDownloadCached.Child>>> childCards;
    private final LiveData<Long> downloadBytes;
    private final LiveData<Resource<List<VisualDownloadCached.Header>>> headerCards;
    private final LiveData<Long> selectedBytes;
    private final LiveData<Set<Integer>> selectedItemIds;
    private final LiveData<Long> usedBytes;

    static final Set cancelSelection$lambda$0(Set set) {
        return null;
    }

    public DownloadViewModel() {
        ResourceLiveData<List<VisualDownloadCached.Header>> resourceLiveData = new ResourceLiveData<>(new Resource.Loading(null, 1, null));
        this._headerCards = resourceLiveData;
        this.headerCards = resourceLiveData;
        ResourceLiveData<List<VisualDownloadCached.Child>> resourceLiveData2 = new ResourceLiveData<>(new Resource.Loading(null, 1, null));
        this._childCards = resourceLiveData2;
        this.childCards = resourceLiveData2;
        ConsistentLiveData<Long> consistentLiveData = new ConsistentLiveData<>(null, 1, null);
        this._usedBytes = consistentLiveData;
        this.usedBytes = consistentLiveData;
        ConsistentLiveData<Long> consistentLiveData2 = new ConsistentLiveData<>(null, 1, null);
        this._availableBytes = consistentLiveData2;
        this.availableBytes = consistentLiveData2;
        ConsistentLiveData<Long> consistentLiveData3 = new ConsistentLiveData<>(null, 1, null);
        this._downloadBytes = consistentLiveData3;
        this.downloadBytes = consistentLiveData3;
        ConsistentLiveData<Long> consistentLiveData4 = new ConsistentLiveData<>(0L);
        this._selectedBytes = consistentLiveData4;
        this.selectedBytes = consistentLiveData4;
        ConsistentLiveData<Set<Integer>> consistentLiveData5 = new ConsistentLiveData<>(null);
        this._selectedItemIds = consistentLiveData5;
        this.selectedItemIds = consistentLiveData5;
    }

    public final LiveData<Resource<List<VisualDownloadCached.Header>>> getHeaderCards() {
        return this.headerCards;
    }

    public final LiveData<Resource<List<VisualDownloadCached.Child>>> getChildCards() {
        return this.childCards;
    }

    public final LiveData<Long> getUsedBytes() {
        return this.usedBytes;
    }

    public final LiveData<Long> getAvailableBytes() {
        return this.availableBytes;
    }

    public final LiveData<Long> getDownloadBytes() {
        return this.downloadBytes;
    }

    public final LiveData<Long> getSelectedBytes() {
        return this.selectedBytes;
    }

    public final LiveData<Set<Integer>> getSelectedItemIds() {
        return this.selectedItemIds;
    }

    public final void cancelSelection() {
        updateSelectedItems(new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadViewModel.cancelSelection$lambda$0((Set) obj);
            }
        });
    }

    static final Set addSelected$lambda$0(int i, Set set) {
        Set setPlus;
        return (set == null || (setPlus = SetsKt.plus((Set<? extends Integer>) set, Integer.valueOf(i))) == null) ? SetsKt.setOf(Integer.valueOf(i)) : setPlus;
    }

    public final void addSelected(final int itemId) {
        updateSelectedItems(new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadViewModel.addSelected$lambda$0(itemId, (Set) obj);
            }
        });
    }

    static final Set removeSelected$lambda$0(int i, Set set) {
        Set setMinus;
        return (set == null || (setMinus = SetsKt.minus((Set<? extends Integer>) set, Integer.valueOf(i))) == null) ? SetsKt.emptySet() : setMinus;
    }

    public final void removeSelected(final int itemId) {
        updateSelectedItems(new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadViewModel.removeSelected$lambda$0(itemId, (Set) obj);
            }
        });
    }

    public final void selectAllHeaders() {
        updateSelectedItems(new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadViewModel.selectAllHeaders$lambda$0(this.f$0, (Set) obj);
            }
        });
    }

    static final Set selectAllHeaders$lambda$0(DownloadViewModel downloadViewModel, Set set) {
        List<VisualDownloadCached.Header> success = downloadViewModel._headerCards.getSuccess();
        if (success == null) {
            success = CollectionsKt.emptyList();
        }
        List<VisualDownloadCached.Header> list = success;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((VisualDownloadCached.Header) it.next()).getData().getId()));
        }
        return CollectionsKt.toSet(arrayList);
    }

    public final void selectAllChildren() {
        updateSelectedItems(new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadViewModel.selectAllChildren$lambda$0(this.f$0, (Set) obj);
            }
        });
    }

    static final Set selectAllChildren$lambda$0(DownloadViewModel downloadViewModel, Set set) {
        List<VisualDownloadCached.Child> success = downloadViewModel._childCards.getSuccess();
        if (success == null) {
            success = CollectionsKt.emptyList();
        }
        List<VisualDownloadCached.Child> list = success;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((VisualDownloadCached.Child) it.next()).getData().getId()));
        }
        return CollectionsKt.toSet(arrayList);
    }

    public final void clearSelectedItems() {
        updateSelectedItems(new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SetsKt.emptySet();
            }
        });
    }

    public final boolean isAllChildrenSelected() {
        Set<Integer> value = this.selectedItemIds.getValue();
        if (value == null) {
            return false;
        }
        List<VisualDownloadCached.Child> success = this._childCards.getSuccess();
        if (success == null) {
            success = CollectionsKt.emptyList();
        }
        if (value.size() == success.size()) {
            List<VisualDownloadCached.Child> list = success;
            if ((list instanceof Collection) && list.isEmpty()) {
                return true;
            }
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (!value.contains(Integer.valueOf(((VisualDownloadCached.Child) it.next()).getData().getId()))) {
                }
            }
            return true;
        }
        return false;
    }

    public final boolean isAllHeadersSelected() {
        Set<Integer> value = this.selectedItemIds.getValue();
        if (value == null) {
            return false;
        }
        List<VisualDownloadCached.Header> success = this._headerCards.getSuccess();
        if (success == null) {
            success = CollectionsKt.emptyList();
        }
        if (value.size() == success.size()) {
            List<VisualDownloadCached.Header> list = success;
            if ((list instanceof Collection) && list.isEmpty()) {
                return true;
            }
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (!value.contains(Integer.valueOf(((VisualDownloadCached.Header) it.next()).getData().getId()))) {
                }
            }
            return true;
        }
        return false;
    }

    private final void updateSelectedItems(Function1<? super Set<Integer>, ? extends Set<Integer>> action) {
        this._selectedItemIds.postValue(action.invoke(this.selectedItemIds.getValue()));
        postHeaders$default(this, null, 1, null);
        postChildren$default(this, null, 1, null);
        updateSelectedBytes();
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateSelectedBytes$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateSelectedBytes$1", f = "DownloadViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C08361 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C08361(Continuation<? super C08361> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadViewModel.this.new C08361(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08361) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List selectedItemsData = DownloadViewModel.this.getSelectedItemsData();
                if (selectedItemsData == null) {
                    return Unit.INSTANCE;
                }
                Iterator it = selectedItemsData.iterator();
                long totalBytes = 0;
                while (it.hasNext()) {
                    totalBytes += ((VisualDownloadCached) it.next()).getTotalBytes();
                }
                DownloadViewModel.this._selectedBytes.postValue(Boxing.boxLong(totalBytes));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private final Job updateSelectedBytes() {
        return ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new C08361(null), 3, null);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.DownloadViewModel$removeRedundantEpisodeKeys$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/download/DownloadViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.DownloadViewModel$removeRedundantEpisodeKeys$1", f = "DownloadViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C08321 extends SuspendLambda implements Function3<CoroutineScope, DownloadViewModel, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<Pair<Integer, Integer>> $keys;
        final /* synthetic */ SharedPreferences $settingsManager;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08321(SharedPreferences sharedPreferences, List<Pair<Integer, Integer>> list, Continuation<? super C08321> continuation) {
            super(3, continuation);
            this.$settingsManager = sharedPreferences;
            this.$keys = list;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, DownloadViewModel downloadViewModel, Continuation<? super Unit> continuation) {
            return new C08321(this.$settingsManager, this.$keys, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            SharedPreferences sharedPreferences = this.$settingsManager;
            List<Pair<Integer, Integer>> list = this.$keys;
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                int iIntValue = ((Number) pair.component1()).intValue();
                int iIntValue2 = ((Number) pair.component2()).intValue();
                Log.INSTANCE.i(DownloadViewModel.TAG, "Removing download episode key: " + iIntValue + '/' + iIntValue2);
                String folderName = DataStore.INSTANCE.getFolderName(DataStore.INSTANCE.getFolderName(DataStoreKt.DOWNLOAD_EPISODE_CACHE, String.valueOf(iIntValue)), String.valueOf(iIntValue2));
                editorEdit.putString(DataStore.INSTANCE.getFolderName(DataStore.INSTANCE.getFolderName(DataStoreKt.DOWNLOAD_EPISODE_CACHE_BACKUP, String.valueOf(iIntValue)), String.valueOf(iIntValue2)), sharedPreferences.getString(folderName, null));
                editorEdit.remove(folderName);
            }
            editorEdit.apply();
            return Unit.INSTANCE;
        }
    }

    public final void removeRedundantEpisodeKeys(Context context, List<Pair<Integer, Integer>> keys) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(keys, "keys");
        Coroutines.INSTANCE.ioSafe(this, new C08321(DataStore.INSTANCE.getSharedPrefs(context), keys, null));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.DownloadViewModel$removeRedundantHeaderKeys$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/download/DownloadViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.DownloadViewModel$removeRedundantHeaderKeys$1", f = "DownloadViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C08331 extends SuspendLambda implements Function3<CoroutineScope, DownloadViewModel, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<DownloadObjects.DownloadHeaderCached> $cached;
        final /* synthetic */ SharedPreferences $settingsManager;
        final /* synthetic */ Map<Integer, Long> $totalBytesUsedByChild;
        final /* synthetic */ Map<Integer, Integer> $totalDownloads;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08331(SharedPreferences sharedPreferences, List<DownloadObjects.DownloadHeaderCached> list, Map<Integer, Integer> map, Map<Integer, Long> map2, Continuation<? super C08331> continuation) {
            super(3, continuation);
            this.$settingsManager = sharedPreferences;
            this.$cached = list;
            this.$totalDownloads = map;
            this.$totalBytesUsedByChild = map2;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, DownloadViewModel downloadViewModel, Continuation<? super Unit> continuation) {
            return new C08331(this.$settingsManager, this.$cached, this.$totalDownloads, this.$totalBytesUsedByChild, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:17:0x0056  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Set setEmptySet;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List<Integer> allResumeStateIds = DataStoreHelper.INSTANCE.getAllResumeStateIds();
            if (allResumeStateIds != null) {
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = allResumeStateIds.iterator();
                while (it.hasNext()) {
                    DownloadObjects.ResumeWatching lastWatched = DataStoreHelper.INSTANCE.getLastWatched(Boxing.boxInt(((Number) it.next()).intValue()));
                    Integer numBoxInt = lastWatched != null ? Boxing.boxInt(lastWatched.getParentId()) : null;
                    if (numBoxInt != null) {
                        arrayList.add(numBoxInt);
                    }
                }
                setEmptySet = CollectionsKt.toSet(arrayList);
                if (setEmptySet == null) {
                    setEmptySet = SetsKt.emptySet();
                }
            } else {
                setEmptySet = SetsKt.emptySet();
            }
            SharedPreferences sharedPreferences = this.$settingsManager;
            List<DownloadObjects.DownloadHeaderCached> list = this.$cached;
            Map<Integer, Integer> map = this.$totalDownloads;
            Map<Integer, Long> map2 = this.$totalBytesUsedByChild;
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            for (DownloadObjects.DownloadHeaderCached downloadHeaderCached : list) {
                Integer num = map.get(Boxing.boxInt(downloadHeaderCached.getId()));
                int iIntValue = num != null ? num.intValue() : 0;
                Long l = map2.get(Boxing.boxInt(downloadHeaderCached.getId()));
                long jLongValue = l != null ? l.longValue() : 0L;
                if (iIntValue <= 0 || jLongValue <= 0) {
                    if (!setEmptySet.contains(Boxing.boxInt(downloadHeaderCached.getId()))) {
                        Log.INSTANCE.i(DownloadViewModel.TAG, "Removing download header key: " + downloadHeaderCached.getId());
                        String folderName = DataStore.INSTANCE.getFolderName(DataStoreKt.DOWNLOAD_HEADER_CACHE, String.valueOf(downloadHeaderCached.getId()));
                        editorEdit.putString(DataStore.INSTANCE.getFolderName(DataStoreKt.DOWNLOAD_HEADER_CACHE_BACKUP, String.valueOf(downloadHeaderCached.getId())), sharedPreferences.getString(folderName, null));
                        editorEdit.remove(folderName);
                    }
                }
            }
            editorEdit.apply();
            return Unit.INSTANCE;
        }
    }

    public final void removeRedundantHeaderKeys(Context context, List<DownloadObjects.DownloadHeaderCached> cached, Map<Integer, Long> totalBytesUsedByChild, Map<Integer, Integer> totalDownloads) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cached, "cached");
        Intrinsics.checkNotNullParameter(totalBytesUsedByChild, "totalBytesUsedByChild");
        Intrinsics.checkNotNullParameter(totalDownloads, "totalDownloads");
        Coroutines.INSTANCE.ioSafe(this, new C08331(DataStore.INSTANCE.getSharedPrefs(context), cached, totalDownloads, totalBytesUsedByChild, null));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateHeaderList$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateHeaderList$1", f = "DownloadViewModel.kt", i = {0}, l = {200}, m = "invokeSuspend", n = {"$this$launchSafe"}, nl = {236}, s = {"L$0"}, v = 2)
    static final class C08351 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08351(Context context, Continuation<? super C08351> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C08351 c08351 = DownloadViewModel.this.new C08351(this.$context, continuation);
            c08351.L$0 = obj;
            return c08351;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08351) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.label = 1;
                obj = Coroutines.INSTANCE.ioWork(coroutineScope, new DownloadViewModel$updateHeaderList$1$visual$1(this.$context, DownloadViewModel.this, null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            List<VisualDownloadCached.Header> list = (List) obj;
            DownloadViewModel.this.updateStorageStats(list);
            DownloadViewModel.this.postHeaders(list);
            return Unit.INSTANCE;
        }
    }

    public final Job updateHeaderList(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new C08351(context, null), 3, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void postHeaders$default(DownloadViewModel downloadViewModel, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = null;
        }
        downloadViewModel.postHeaders(list);
    }

    public final void postHeaders(List<VisualDownloadCached.Header> newValue) {
        List<VisualDownloadCached.Header> success;
        if (newValue == null) {
            success = this._headerCards.getSuccess();
            if (success == null) {
                return;
            }
        } else {
            success = newValue;
        }
        Set<Integer> value = this.selectedItemIds.getValue();
        if (value == null) {
            value = SetsKt.emptySet();
        }
        ResourceLiveData<List<VisualDownloadCached.Header>> resourceLiveData = this._headerCards;
        List<VisualDownloadCached.Header> list = success;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (VisualDownloadCached.Header header : list) {
            arrayList.add(VisualDownloadCached.Header.copy$default(header, 0L, 0L, null, value.contains(Integer.valueOf(header.getData().getId())), null, 0, 0, 119, null));
        }
        resourceLiveData.postValue((List<VisualDownloadCached.Header>) new Resource.Success(arrayList));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void postChildren$default(DownloadViewModel downloadViewModel, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = null;
        }
        downloadViewModel.postChildren(list);
    }

    public final void postChildren(List<VisualDownloadCached.Child> newValue) {
        if (newValue == null && (newValue = this._childCards.getSuccess()) == null) {
            return;
        }
        Set<Integer> value = this.selectedItemIds.getValue();
        if (value == null) {
            value = SetsKt.emptySet();
        }
        ResourceLiveData<List<VisualDownloadCached.Child>> resourceLiveData = this._childCards;
        List<VisualDownloadCached.Child> list = newValue;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (VisualDownloadCached.Child child : list) {
            arrayList.add(VisualDownloadCached.Child.copy$default(child, 0L, 0L, null, value.contains(Integer.valueOf(child.getData().getId())), 7, null));
        }
        resourceLiveData.postValue((List<VisualDownloadCached.Child>) new Resource.Success(arrayList));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: DownloadViewModel.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B]\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0018\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u0015\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u0015\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u001b\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\n0\tHÆ\u0003Jg\u0010\u0017\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00032\u001a\b\u0002\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\n0\tHÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0004HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR#\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/DownloadViewModel$DownloadStats;", "", "totalBytesUsedByChild", "", "", "", "currentBytesUsedByChild", "totalDownloads", "redundantDownloads", "", "Lkotlin/Pair;", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;)V", "getTotalBytesUsedByChild", "()Ljava/util/Map;", "getCurrentBytesUsedByChild", "getTotalDownloads", "getRedundantDownloads", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final /* data */ class DownloadStats {
        private final Map<Integer, Long> currentBytesUsedByChild;
        private final List<Pair<Integer, Integer>> redundantDownloads;
        private final Map<Integer, Long> totalBytesUsedByChild;
        private final Map<Integer, Integer> totalDownloads;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DownloadStats copy$default(DownloadStats downloadStats, Map map, Map map2, Map map3, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                map = downloadStats.totalBytesUsedByChild;
            }
            if ((i & 2) != 0) {
                map2 = downloadStats.currentBytesUsedByChild;
            }
            if ((i & 4) != 0) {
                map3 = downloadStats.totalDownloads;
            }
            if ((i & 8) != 0) {
                list = downloadStats.redundantDownloads;
            }
            return downloadStats.copy(map, map2, map3, list);
        }

        public final Map<Integer, Long> component1() {
            return this.totalBytesUsedByChild;
        }

        public final Map<Integer, Long> component2() {
            return this.currentBytesUsedByChild;
        }

        public final Map<Integer, Integer> component3() {
            return this.totalDownloads;
        }

        public final List<Pair<Integer, Integer>> component4() {
            return this.redundantDownloads;
        }

        public final DownloadStats copy(Map<Integer, Long> totalBytesUsedByChild, Map<Integer, Long> currentBytesUsedByChild, Map<Integer, Integer> totalDownloads, List<Pair<Integer, Integer>> redundantDownloads) {
            Intrinsics.checkNotNullParameter(totalBytesUsedByChild, "totalBytesUsedByChild");
            Intrinsics.checkNotNullParameter(currentBytesUsedByChild, "currentBytesUsedByChild");
            Intrinsics.checkNotNullParameter(totalDownloads, "totalDownloads");
            Intrinsics.checkNotNullParameter(redundantDownloads, "redundantDownloads");
            return new DownloadStats(totalBytesUsedByChild, currentBytesUsedByChild, totalDownloads, redundantDownloads);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadStats)) {
                return false;
            }
            DownloadStats downloadStats = (DownloadStats) other;
            return Intrinsics.areEqual(this.totalBytesUsedByChild, downloadStats.totalBytesUsedByChild) && Intrinsics.areEqual(this.currentBytesUsedByChild, downloadStats.currentBytesUsedByChild) && Intrinsics.areEqual(this.totalDownloads, downloadStats.totalDownloads) && Intrinsics.areEqual(this.redundantDownloads, downloadStats.redundantDownloads);
        }

        public int hashCode() {
            return (((((this.totalBytesUsedByChild.hashCode() * 31) + this.currentBytesUsedByChild.hashCode()) * 31) + this.totalDownloads.hashCode()) * 31) + this.redundantDownloads.hashCode();
        }

        public String toString() {
            return "DownloadStats(totalBytesUsedByChild=" + this.totalBytesUsedByChild + ", currentBytesUsedByChild=" + this.currentBytesUsedByChild + ", totalDownloads=" + this.totalDownloads + ", redundantDownloads=" + this.redundantDownloads + ')';
        }

        public DownloadStats(Map<Integer, Long> totalBytesUsedByChild, Map<Integer, Long> currentBytesUsedByChild, Map<Integer, Integer> totalDownloads, List<Pair<Integer, Integer>> redundantDownloads) {
            Intrinsics.checkNotNullParameter(totalBytesUsedByChild, "totalBytesUsedByChild");
            Intrinsics.checkNotNullParameter(currentBytesUsedByChild, "currentBytesUsedByChild");
            Intrinsics.checkNotNullParameter(totalDownloads, "totalDownloads");
            Intrinsics.checkNotNullParameter(redundantDownloads, "redundantDownloads");
            this.totalBytesUsedByChild = totalBytesUsedByChild;
            this.currentBytesUsedByChild = currentBytesUsedByChild;
            this.totalDownloads = totalDownloads;
            this.redundantDownloads = redundantDownloads;
        }

        public final Map<Integer, Long> getTotalBytesUsedByChild() {
            return this.totalBytesUsedByChild;
        }

        public final Map<Integer, Long> getCurrentBytesUsedByChild() {
            return this.currentBytesUsedByChild;
        }

        public final Map<Integer, Integer> getTotalDownloads() {
            return this.totalDownloads;
        }

        public final List<Pair<Integer, Integer>> getRedundantDownloads() {
            return this.redundantDownloads;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DownloadStats calculateDownloadStats(Context context, List<DownloadObjects.DownloadEpisodeCached> children) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        ArrayList arrayList = new ArrayList();
        for (DownloadObjects.DownloadEpisodeCached downloadEpisodeCached : children) {
            DownloadObjects.DownloadedFileInfoResult downloadFileInfo = VideoDownloadManager.INSTANCE.getDownloadFileInfo(context, downloadEpisodeCached.getId());
            if (downloadFileInfo == null) {
                if (!DownloadQueueService.INSTANCE.isRunning() && DownloadQueueService.INSTANCE.getDownloadInstances().getValue().isEmpty() && DownloadQueueManager.INSTANCE.getQueue().getValue().length == 0) {
                    arrayList.add(TuplesKt.to(Integer.valueOf(downloadEpisodeCached.getParentId()), Integer.valueOf(downloadEpisodeCached.getId())));
                }
            } else if (downloadFileInfo.getFileLength() > 1) {
                long totalBytes = downloadFileInfo.getTotalBytes();
                long fileLength = downloadFileInfo.getFileLength();
                Integer numValueOf = Integer.valueOf(downloadEpisodeCached.getParentId());
                Long lValueOf = Long.valueOf(totalBytes);
                final DownloadViewModel$calculateDownloadStats$1$1 downloadViewModel$calculateDownloadStats$1$1 = DownloadViewModel$calculateDownloadStats$1$1.INSTANCE;
                j$.util.Map.EL.merge(linkedHashMap, numValueOf, lValueOf, new BiFunction() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$$ExternalSyntheticLambda5
                    public /* synthetic */ BiFunction andThen(Function function) {
                        return BiFunction$CC.$default$andThen(this, function);
                    }

                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        return DownloadViewModel.calculateDownloadStats$lambda$0$0(downloadViewModel$calculateDownloadStats$1$1, obj, obj2);
                    }
                });
                Integer numValueOf2 = Integer.valueOf(downloadEpisodeCached.getParentId());
                Long lValueOf2 = Long.valueOf(fileLength);
                final DownloadViewModel$calculateDownloadStats$1$2 downloadViewModel$calculateDownloadStats$1$2 = DownloadViewModel$calculateDownloadStats$1$2.INSTANCE;
                j$.util.Map.EL.merge(linkedHashMap2, numValueOf2, lValueOf2, new BiFunction() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$$ExternalSyntheticLambda6
                    public /* synthetic */ BiFunction andThen(Function function) {
                        return BiFunction$CC.$default$andThen(this, function);
                    }

                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        return DownloadViewModel.calculateDownloadStats$lambda$0$1(downloadViewModel$calculateDownloadStats$1$2, obj, obj2);
                    }
                });
                Integer numValueOf3 = Integer.valueOf(downloadEpisodeCached.getParentId());
                final DownloadViewModel$calculateDownloadStats$1$3 downloadViewModel$calculateDownloadStats$1$3 = DownloadViewModel$calculateDownloadStats$1$3.INSTANCE;
                j$.util.Map.EL.merge(linkedHashMap3, numValueOf3, 1, new BiFunction() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$$ExternalSyntheticLambda7
                    public /* synthetic */ BiFunction andThen(Function function) {
                        return BiFunction$CC.$default$andThen(this, function);
                    }

                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        return DownloadViewModel.calculateDownloadStats$lambda$0$2(downloadViewModel$calculateDownloadStats$1$3, obj, obj2);
                    }
                });
            }
        }
        return new DownloadStats(linkedHashMap, linkedHashMap2, linkedHashMap3, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long calculateDownloadStats$lambda$0$0(Function2 function2, Object obj, Object obj2) {
        return (Long) function2.invoke(obj, obj2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long calculateDownloadStats$lambda$0$1(Function2 function2, Object obj, Object obj2) {
        return (Long) function2.invoke(obj, obj2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer calculateDownloadStats$lambda$0$2(Function2 function2, Object obj, Object obj2) {
        return (Integer) function2.invoke(obj, obj2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<VisualDownloadCached.Header> createVisualDownloadList(Context context, List<DownloadObjects.DownloadHeaderCached> cached, Map<Integer, Long> totalBytesUsedByChild, Map<Integer, Long> currentBytesUsedByChild, Map<Integer, Integer> totalDownloads) {
        VisualDownloadCached.Header header;
        boolean z;
        Object obj;
        DownloadObjects.DownloadEpisodeCached downloadEpisodeCached;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        ArrayList arrayList = new ArrayList();
        Iterator it = cached.iterator();
        while (it.hasNext()) {
            DownloadObjects.DownloadHeaderCached downloadHeaderCached = (DownloadObjects.DownloadHeaderCached) it.next();
            Integer num = totalDownloads.get(Integer.valueOf(downloadHeaderCached.getId()));
            int iIntValue = num != null ? num.intValue() : 0;
            Long l = totalBytesUsedByChild.get(Integer.valueOf(downloadHeaderCached.getId()));
            long jLongValue = l != null ? l.longValue() : 0L;
            Long l2 = currentBytesUsedByChild.get(Integer.valueOf(downloadHeaderCached.getId()));
            long jLongValue2 = l2 != null ? l2.longValue() : 0L;
            if (jLongValue <= 0 || iIntValue <= 0) {
                it = it;
                header = null;
            } else {
                Set<Integer> value = this.selectedItemIds.getValue();
                boolean zContains = value != null ? value.contains(Integer.valueOf(downloadHeaderCached.getId())) : false;
                if (MainAPIKt.isEpisodeBased(downloadHeaderCached.getType())) {
                    z = zContains;
                    downloadEpisodeCached = null;
                } else {
                    DataStore dataStore = DataStore.INSTANCE;
                    try {
                        try {
                            String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(DataStoreKt.DOWNLOAD_EPISODE_CACHE, DataStore.INSTANCE.getFolderName(String.valueOf(downloadHeaderCached.getId()), String.valueOf(downloadHeaderCached.getId()))), null);
                            if (string == null) {
                                z = zContains;
                                obj = null;
                            } else {
                                AppUtils appUtils = AppUtils.INSTANCE;
                                try {
                                    Result.Companion companion = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadEpisodeCached"));
                                } catch (Throwable th) {
                                    Result.Companion companion2 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                                }
                                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                                    z = zContains;
                                    obj = null;
                                } else {
                                    try {
                                        Result.Companion companion3 = Result.INSTANCE;
                                        z = zContains;
                                        obj = null;
                                        try {
                                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadEpisodeCached.class), null, 2, null));
                                        } catch (Throwable th2) {
                                            th = th2;
                                            Result.Companion companion4 = Result.INSTANCE;
                                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        z = zContains;
                                        obj = null;
                                    }
                                }
                                Object obj2 = objM8936constructorimpl;
                                try {
                                    if (Result.m8942isFailureimpl(obj2)) {
                                        obj2 = obj;
                                    }
                                    KSerializer kSerializer = (KSerializer) obj2;
                                    if (kSerializer != null) {
                                        try {
                                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                                        } catch (SerializationException e) {
                                            ArchComponentExtKt.logError(e);
                                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$createVisualDownloadList$lambda$0$$inlined$getKey$1
                                            });
                                        } catch (Throwable unused) {
                                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$createVisualDownloadList$lambda$0$$inlined$getKey$1
                                            });
                                        }
                                    } else {
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$createVisualDownloadList$lambda$0$$inlined$getKey$1
                                        });
                                    }
                                    obj = objDecodeFromString;
                                } catch (Exception unused2) {
                                }
                            }
                        } catch (Exception unused3) {
                            z = zContains;
                            obj = null;
                        }
                    } catch (Exception unused4) {
                        z = zContains;
                        obj = null;
                    }
                    downloadEpisodeCached = (DownloadObjects.DownloadEpisodeCached) obj;
                }
                header = new VisualDownloadCached.Header(jLongValue2, jLongValue, downloadHeaderCached, z, downloadEpisodeCached, 0, iIntValue);
            }
            if (header != null) {
                arrayList.add(header);
            }
            it = it;
        }
        final Comparator comparator = new Comparator() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$createVisualDownloadList$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Boolean.valueOf(MainAPIKt.isEpisodeBased(((VisualDownloadCached.Header) t).getData().getType())), Boolean.valueOf(MainAPIKt.isEpisodeBased(((VisualDownloadCached.Header) t2).getData().getType())));
            }
        };
        return CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$createVisualDownloadList$$inlined$thenBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparator.compare(t, t2);
                if (iCompare != 0) {
                    return iCompare;
                }
                String lowerCase = ((VisualDownloadCached.Header) t).getData().getName().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                String lowerCase2 = ((VisualDownloadCached.Header) t2).getData().getName().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                return ComparisonsKt.compareValues(lowerCase, lowerCase2);
            }
        });
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateChildList$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateChildList$1", f = "DownloadViewModel.kt", i = {}, l = {362}, m = "invokeSuspend", n = {}, nl = {379}, s = {}, v = 2)
    static final class C08341 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ String $folder;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08341(Context context, String str, Continuation<? super C08341> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$folder = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadViewModel.this.new C08341(this.$context, this.$folder, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08341) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                DownloadViewModel.this._childCards.postValue(new Resource.Loading(null, 1, null));
                this.label = 1;
                obj = BuildersKt.withContext(Dispatchers.getIO(), new DownloadViewModel$updateChildList$1$visual$1(this.$context, this.$folder, DownloadViewModel.this, null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            DownloadViewModel.this.postChildren(CollectionsKt.sortedWith((Iterable) obj, ComparisonsKt.compareBy(new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateChildList$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return DownloadViewModel.C08341.invokeSuspend$lambda$0((VisualDownloadCached.Child) obj2);
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateChildList$1$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Integer.valueOf(((VisualDownloadCached.Child) obj2).getData().getEpisode());
                }
            })));
            return Unit.INSTANCE;
        }

        static final Comparable invokeSuspend$lambda$0(VisualDownloadCached.Child child) {
            Integer season = child.getData().getSeason();
            return season != null ? season : (Comparable) 0;
        }
    }

    public final Job updateChildList(Context context, String folder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(folder, "folder");
        return ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new C08341(context, folder, null), 3, null);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.DownloadViewModel$removeItems$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.DownloadViewModel$removeItems$1", f = "DownloadViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C08311 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Set<Integer> $idsToRemove;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08311(Set<Integer> set, Continuation<? super C08311> continuation) {
            super(2, continuation);
            this.$idsToRemove = set;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadViewModel.this.new C08311(this.$idsToRemove, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08311) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ArrayList arrayList;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ArrayList arrayList2 = null;
                DownloadViewModel.this._selectedItemIds.postValue(null);
                DownloadViewModel downloadViewModel = DownloadViewModel.this;
                List list = (List) downloadViewModel._headerCards.getSuccess();
                if (list != null) {
                    Set<Integer> set = this.$idsToRemove;
                    ArrayList arrayList3 = new ArrayList();
                    for (Object obj2 : list) {
                        if (!set.contains(Boxing.boxInt(((VisualDownloadCached.Header) obj2).getData().getId()))) {
                            arrayList3.add(obj2);
                        }
                    }
                    arrayList = arrayList3;
                } else {
                    arrayList = null;
                }
                downloadViewModel.postHeaders(arrayList);
                DownloadViewModel downloadViewModel2 = DownloadViewModel.this;
                List list2 = (List) downloadViewModel2._childCards.getSuccess();
                if (list2 != null) {
                    Set<Integer> set2 = this.$idsToRemove;
                    ArrayList arrayList4 = new ArrayList();
                    for (Object obj3 : list2) {
                        if (!set2.contains(Boxing.boxInt(((VisualDownloadCached.Child) obj3).getData().getId()))) {
                            arrayList4.add(obj3);
                        }
                    }
                    arrayList2 = arrayList4;
                }
                downloadViewModel2.postChildren(arrayList2);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job removeItems(Set<Integer> idsToRemove) {
        return ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new C08311(idsToRemove, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateStorageStats(List<VisualDownloadCached.Header> visual) {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            long availableBytes = statFs.getAvailableBytes();
            long blockSizeLong = statFs.getBlockSizeLong() * statFs.getBlockCountLong();
            Iterator<T> it = visual.iterator();
            long totalBytes = 0;
            while (it.hasNext()) {
                totalBytes += ((VisualDownloadCached.Header) it.next()).getTotalBytes();
            }
            this._usedBytes.postValue(Long.valueOf(blockSizeLong - availableBytes));
            this._availableBytes.postValue(Long.valueOf(availableBytes));
            this._downloadBytes.postValue(Long.valueOf(totalBytes));
        } catch (Throwable th) {
            this._downloadBytes.postValue(0L);
            ArchComponentExtKt.logError(th);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.DownloadViewModel$handleMultiDelete$1, reason: invalid class name */
    /* JADX INFO: compiled from: DownloadViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.DownloadViewModel$handleMultiDelete$1", f = "DownloadViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadViewModel.this.new AnonymousClass1(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List selectedItemsData = DownloadViewModel.this.getSelectedItemsData();
                if (selectedItemsData == null) {
                    selectedItemsData = CollectionsKt.emptyList();
                }
                DeleteData deleteDataProcessSelectedItems = DownloadViewModel.this.processSelectedItems(this.$context, selectedItemsData);
                DownloadViewModel.this.showDeleteConfirmationDialog(this.$context, DownloadViewModel.this.buildDeleteMessage(this.$context, deleteDataProcessSelectedItems), deleteDataProcessSelectedItems.getIds(), deleteDataProcessSelectedItems.getParentIds());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Job handleMultiDelete(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(context, null), 3, null);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.download.DownloadViewModel$handleSingleDelete$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.DownloadViewModel$handleSingleDelete$1", f = "DownloadViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C08301 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ int $itemId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08301(int i, Context context, Continuation<? super C08301> continuation) {
            super(2, continuation);
            this.$itemId = i;
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DownloadViewModel.this.new C08301(this.$itemId, this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08301) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                DeleteData deleteDataProcessSelectedItems = DownloadViewModel.this.processSelectedItems(this.$context, DownloadViewModel.this.getItemDataFromId(this.$itemId));
                DownloadViewModel.this.showDeleteConfirmationDialog(this.$context, DownloadViewModel.this.buildDeleteMessage(this.$context, deleteDataProcessSelectedItems), deleteDataProcessSelectedItems.getIds(), deleteDataProcessSelectedItems.getParentIds());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Job handleSingleDelete(Context context, int itemId) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new C08301(itemId, context, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:110:0x0259 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:112:0x00c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:37:0x00da A[Catch: Exception -> 0x00ec, TRY_LEAVE, TryCatch #3 {Exception -> 0x00ec, blocks: (B:13:0x006d, B:16:0x0079, B:21:0x0093, B:28:0x00be, B:31:0x00c5, B:37:0x00da, B:36:0x00d5, B:27:0x00b4, B:20:0x0089, B:24:0x009a, B:17:0x007b, B:33:0x00c9), top: B:104:0x006d, inners: #1, #5, #9, #9 }] */
    /* JADX WARN: Code duplicated, block: B:75:0x0254  */
    /* JADX WARN: Code duplicated, block: B:82:0x026a A[Catch: Exception -> 0x027c, TRY_LEAVE, TryCatch #2 {Exception -> 0x027c, blocks: (B:58:0x01fd, B:61:0x0209, B:66:0x0223, B:73:0x024e, B:76:0x0255, B:82:0x026a, B:81:0x0265, B:72:0x0244, B:65:0x0219, B:69:0x022a, B:62:0x020b, B:78:0x0259), top: B:102:0x01fd, inners: #0, #4, #8, #10 }] */
    public final DeleteData processSelectedItems(Context context, List<? extends VisualDownloadCached> selectedItemsList) {
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Object objDecodeFromString2;
        Object objM8936constructorimpl2;
        KSerializer kSerializer2;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        String str = null;
        for (VisualDownloadCached visualDownloadCached : selectedItemsList) {
            if (visualDownloadCached instanceof VisualDownloadCached.Header) {
                VisualDownloadCached.Header header = (VisualDownloadCached.Header) visualDownloadCached;
                if (MainAPIKt.isEpisodeBased(header.getData().getType())) {
                    List<String> keys = DataStore.INSTANCE.getKeys(context, DataStoreKt.DOWNLOAD_EPISODE_CACHE);
                    ArrayList arrayList3 = new ArrayList();
                    Iterator<T> it = keys.iterator();
                    while (it.hasNext()) {
                        try {
                            String string = DataStore.INSTANCE.getSharedPrefs(context).getString((String) it.next(), null);
                            if (string == null) {
                                objDecodeFromString = null;
                            } else {
                                AppUtils appUtils = AppUtils.INSTANCE;
                                try {
                                    Result.Companion companion = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadEpisodeCached"));
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
                                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$processSelectedItems$lambda$0$0$$inlined$getKey$1
                                            });
                                        } catch (Throwable unused) {
                                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$processSelectedItems$lambda$0$0$$inlined$getKey$1
                                            });
                                        }
                                    } else {
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$processSelectedItems$lambda$0$0$$inlined$getKey$1
                                        });
                                    }
                                } else {
                                    try {
                                        Result.Companion companion3 = Result.INSTANCE;
                                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadEpisodeCached.class), null, 2, null));
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
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$processSelectedItems$lambda$0$0$$inlined$getKey$1
                                        });
                                    }
                                }
                            }
                        } catch (Exception unused2) {
                            objDecodeFromString = null;
                        }
                        DownloadObjects.DownloadEpisodeCached downloadEpisodeCached = (DownloadObjects.DownloadEpisodeCached) objDecodeFromString;
                        if (downloadEpisodeCached != null) {
                            arrayList3.add(downloadEpisodeCached);
                        }
                    }
                    ArrayList arrayList4 = new ArrayList();
                    for (Object obj : arrayList3) {
                        if (((DownloadObjects.DownloadEpisodeCached) obj).getParentId() == header.getData().getId()) {
                            arrayList4.add(obj);
                        }
                    }
                    ArrayList arrayList5 = arrayList4;
                    ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
                    Iterator it2 = arrayList5.iterator();
                    while (it2.hasNext()) {
                        arrayList6.add(Integer.valueOf(((DownloadObjects.DownloadEpisodeCached) it2.next()).getId()));
                    }
                    linkedHashSet.addAll(arrayList6);
                    linkedHashSet2.add(Integer.valueOf(header.getData().getId()));
                    StringBuilder sb = new StringBuilder();
                    sb.append(header.getData().getName());
                    sb.append(" (");
                    sb.append(header.getTotalDownloads());
                    sb.append(' ');
                    String quantityString = context.getResources().getQuantityString(R.plurals.episodes, header.getTotalDownloads());
                    Intrinsics.checkNotNullExpressionValue(quantityString, "getQuantityString(...)");
                    String lowerCase = quantityString.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    sb.append(lowerCase);
                    sb.append(')');
                    arrayList2.add(sb.toString());
                } else {
                    linkedHashSet.add(Integer.valueOf(header.getData().getId()));
                    arrayList.add(header.getData().getName());
                }
            } else {
                if (!(visualDownloadCached instanceof VisualDownloadCached.Child)) {
                    throw new NoWhenBranchMatchedException();
                }
                VisualDownloadCached.Child child = (VisualDownloadCached.Child) visualDownloadCached;
                linkedHashSet.add(Integer.valueOf(child.getData().getId()));
                DataStore dataStore = DataStore.INSTANCE;
                try {
                    String string2 = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(DataStoreKt.DOWNLOAD_HEADER_CACHE, String.valueOf(child.getData().getParentId())), null);
                    if (string2 == null) {
                        objDecodeFromString2 = null;
                    } else {
                        AppUtils appUtils2 = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion5 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadHeaderCached"));
                        } catch (Throwable th3) {
                            Result.Companion companion6 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) == null) {
                            if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                                objM8936constructorimpl2 = null;
                            }
                            kSerializer2 = (KSerializer) objM8936constructorimpl2;
                            if (kSerializer2 != null) {
                                try {
                                    objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer2, string2);
                                } catch (SerializationException e2) {
                                    ArchComponentExtKt.logError(e2);
                                    objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$processSelectedItems$lambda$0$$inlined$getKey$1
                                    });
                                } catch (Throwable unused3) {
                                    objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$processSelectedItems$lambda$0$$inlined$getKey$1
                                    });
                                }
                            } else {
                                objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$processSelectedItems$lambda$0$$inlined$getKey$1
                                });
                            }
                        } else {
                            try {
                                Result.Companion companion7 = Result.INSTANCE;
                                objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadHeaderCached.class), null, 2, null));
                            } catch (Throwable th4) {
                                Result.Companion companion8 = Result.INSTANCE;
                                objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th4));
                            }
                            if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                                objM8936constructorimpl2 = null;
                            }
                            kSerializer2 = (KSerializer) objM8936constructorimpl2;
                            if (kSerializer2 != null) {
                                objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer2, string2);
                            } else {
                                objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$processSelectedItems$lambda$0$$inlined$getKey$1
                                });
                            }
                        }
                    }
                } catch (Exception unused4) {
                    objDecodeFromString2 = null;
                }
                DownloadObjects.DownloadHeaderCached downloadHeaderCached = (DownloadObjects.DownloadHeaderCached) objDecodeFromString2;
                String name = downloadHeaderCached != null ? downloadHeaderCached.getName() : null;
                arrayList.add(AppContextUtils.INSTANCE.getNameFull(context, child.getData().getName(), Integer.valueOf(child.getData().getEpisode()), child.getData().getSeason()));
                str = name;
            }
        }
        return new DeleteData(linkedHashSet, linkedHashSet2, arrayList2, arrayList, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String buildDeleteMessage(Context context, DeleteData data) {
        String strJoinToString$default = CollectionsKt.joinToString$default(CollectionsKt.sortedWith(data.getNames(), new Comparator() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$buildDeleteMessage$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                String lowerCase = ((String) t).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                String lowerCase2 = ((String) t2).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                return ComparisonsKt.compareValues(lowerCase, lowerCase2);
            }
        }), "\n", null, null, 0, null, new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadViewModel.buildDeleteMessage$lambda$1((String) obj);
            }
        }, 30, null);
        String strJoinToString$default2 = CollectionsKt.joinToString$default(CollectionsKt.sortedWith(data.getSeriesNames(), new Comparator() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$buildDeleteMessage$$inlined$sortedBy$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                String lowerCase = ((String) t).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                String lowerCase2 = ((String) t2).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                return ComparisonsKt.compareValues(lowerCase, lowerCase2);
            }
        }), "\n", null, null, 0, null, new Function1() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadViewModel.buildDeleteMessage$lambda$3((String) obj);
            }
        }, 30, null);
        if (!data.getSeriesNames().isEmpty() && data.getNames().isEmpty()) {
            String string = context.getString(R.string.delete_message_series_only);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String str = String.format(string, Arrays.copyOf(new Object[]{strJoinToString$default2}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            return str;
        }
        if (data.getIds().size() == 1) {
            String string2 = context.getString(R.string.delete_message);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            String str2 = String.format(string2, Arrays.copyOf(new Object[]{CollectionsKt.firstOrNull((List) data.getNames())}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
            return str2;
        }
        if (data.getParentName() != null && !data.getNames().isEmpty()) {
            String string3 = context.getString(R.string.delete_message_series_episodes);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            String str3 = String.format(string3, Arrays.copyOf(new Object[]{data.getParentName(), strJoinToString$default}, 2));
            Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
            return str3;
        }
        if (!data.getSeriesNames().isEmpty()) {
            String string4 = context.getString(R.string.delete_message_series_section);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            String str4 = String.format(string4, Arrays.copyOf(new Object[]{strJoinToString$default2}, 1));
            Intrinsics.checkNotNullExpressionValue(str4, "format(...)");
            StringBuilder sb = new StringBuilder();
            String string5 = context.getString(R.string.delete_message_multiple);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
            String str5 = String.format(string5, Arrays.copyOf(new Object[]{strJoinToString$default}, 1));
            Intrinsics.checkNotNullExpressionValue(str5, "format(...)");
            sb.append(str5);
            sb.append("\n\n");
            sb.append(str4);
            return sb.toString();
        }
        String string6 = context.getString(R.string.delete_message_multiple);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
        String str6 = String.format(string6, Arrays.copyOf(new Object[]{strJoinToString$default}, 1));
        Intrinsics.checkNotNullExpressionValue(str6, "format(...)");
        return str6;
    }

    static final CharSequence buildDeleteMessage$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "• " + it;
    }

    static final CharSequence buildDeleteMessage$lambda$3(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "• " + it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDeleteConfirmationDialog(final Context context, String message, final Set<Integer> ids, final Set<Integer> parentIds) {
        int i;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$$ExternalSyntheticLambda8
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                DownloadViewModel.showDeleteConfirmationDialog$lambda$0(this.f$0, context, ids, parentIds, dialogInterface, i2);
            }
        };
        try {
            if (ids.size() == 1) {
                i = R.string.delete_file;
            } else {
                i = R.string.delete_files;
            }
            AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
            AlertDialog alertDialogShow = builder.setTitle(i).setMessage(message).setPositiveButton(R.string.delete, onClickListener).setNegativeButton(R.string.cancel, onClickListener).show();
            Intrinsics.checkNotNullExpressionValue(alertDialogShow, "show(...)");
            AppContextUtils.setDefaultFocus$default(appContextUtils, alertDialogShow, 0, 1, null);
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    static final void showDeleteConfirmationDialog$lambda$0(DownloadViewModel downloadViewModel, Context context, Set set, Set set2, DialogInterface dialogInterface, int i) {
        if (i != -1) {
            return;
        }
        ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(downloadViewModel), null, null, new DownloadViewModel$showDeleteConfirmationDialog$dialogClickListener$1$1(context, set, downloadViewModel, set2, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<VisualDownloadCached> getSelectedItemsData() {
        Object next;
        List<VisualDownloadCached.Header> success = this._headerCards.getSuccess();
        if (success == null) {
            success = CollectionsKt.emptyList();
        }
        List<VisualDownloadCached.Child> success2 = this._childCards.getSuccess();
        if (success2 == null) {
            success2 = CollectionsKt.emptyList();
        }
        Set<Integer> value = this.selectedItemIds.getValue();
        if (value == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = value.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            Iterator<T> it2 = success.iterator();
            do {
                if (!it2.hasNext()) {
                    next = null;
                    break;
                }
                next = it2.next();
            } while (((VisualDownloadCached.Header) next).getData().getId() != iIntValue);
            Object next2 = (VisualDownloadCached.Header) next;
            if (next2 == null) {
                Iterator<T> it3 = success2.iterator();
                do {
                    if (!it3.hasNext()) {
                        next2 = null;
                        break;
                    }
                    next2 = it3.next();
                } while (((VisualDownloadCached.Child) next2).getData().getId() != iIntValue);
            }
            VisualDownloadCached visualDownloadCached = (VisualDownloadCached) next2;
            if (visualDownloadCached != null) {
                arrayList.add(visualDownloadCached);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<VisualDownloadCached> getItemDataFromId(int itemId) {
        List<VisualDownloadCached.Header> success = this._headerCards.getSuccess();
        if (success == null) {
            success = CollectionsKt.emptyList();
        }
        List<VisualDownloadCached.Header> list = success;
        List<VisualDownloadCached.Child> success2 = this._childCards.getSuccess();
        if (success2 == null) {
            success2 = CollectionsKt.emptyList();
        }
        List listPlus = CollectionsKt.plus((Collection) list, (Iterable) success2);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listPlus) {
            if (((VisualDownloadCached) obj).getData().getId() == itemId) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final void clearChildren() {
        this._childCards.postValue((List<VisualDownloadCached.Child>) new Resource.Loading(null, 1, null));
    }

    /* JADX INFO: compiled from: DownloadViewModel.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001BI\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\bHÆ\u0003JU\u0010\u001a\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u0004HÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\bHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\n\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/DownloadViewModel$DeleteData;", "", "ids", "", "", "parentIds", "seriesNames", "", "", "names", "parentName", "<init>", "(Ljava/util/Set;Ljava/util/Set;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "getIds", "()Ljava/util/Set;", "getParentIds", "getSeriesNames", "()Ljava/util/List;", "getNames", "getParentName", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final /* data */ class DeleteData {
        private final Set<Integer> ids;
        private final List<String> names;
        private final Set<Integer> parentIds;
        private final String parentName;
        private final List<String> seriesNames;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DeleteData copy$default(DeleteData deleteData, Set set, Set set2, List list, List list2, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                set = deleteData.ids;
            }
            if ((i & 2) != 0) {
                set2 = deleteData.parentIds;
            }
            if ((i & 4) != 0) {
                list = deleteData.seriesNames;
            }
            if ((i & 8) != 0) {
                list2 = deleteData.names;
            }
            if ((i & 16) != 0) {
                str = deleteData.parentName;
            }
            String str2 = str;
            List list3 = list;
            return deleteData.copy(set, set2, list3, list2, str2);
        }

        public final Set<Integer> component1() {
            return this.ids;
        }

        public final Set<Integer> component2() {
            return this.parentIds;
        }

        public final List<String> component3() {
            return this.seriesNames;
        }

        public final List<String> component4() {
            return this.names;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getParentName() {
            return this.parentName;
        }

        public final DeleteData copy(Set<Integer> ids, Set<Integer> parentIds, List<String> seriesNames, List<String> names, String parentName) {
            Intrinsics.checkNotNullParameter(ids, "ids");
            Intrinsics.checkNotNullParameter(parentIds, "parentIds");
            Intrinsics.checkNotNullParameter(seriesNames, "seriesNames");
            Intrinsics.checkNotNullParameter(names, "names");
            return new DeleteData(ids, parentIds, seriesNames, names, parentName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DeleteData)) {
                return false;
            }
            DeleteData deleteData = (DeleteData) other;
            return Intrinsics.areEqual(this.ids, deleteData.ids) && Intrinsics.areEqual(this.parentIds, deleteData.parentIds) && Intrinsics.areEqual(this.seriesNames, deleteData.seriesNames) && Intrinsics.areEqual(this.names, deleteData.names) && Intrinsics.areEqual(this.parentName, deleteData.parentName);
        }

        public int hashCode() {
            int iHashCode = ((((((this.ids.hashCode() * 31) + this.parentIds.hashCode()) * 31) + this.seriesNames.hashCode()) * 31) + this.names.hashCode()) * 31;
            String str = this.parentName;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "DeleteData(ids=" + this.ids + ", parentIds=" + this.parentIds + ", seriesNames=" + this.seriesNames + ", names=" + this.names + ", parentName=" + this.parentName + ')';
        }

        public DeleteData(Set<Integer> ids, Set<Integer> parentIds, List<String> seriesNames, List<String> names, String str) {
            Intrinsics.checkNotNullParameter(ids, "ids");
            Intrinsics.checkNotNullParameter(parentIds, "parentIds");
            Intrinsics.checkNotNullParameter(seriesNames, "seriesNames");
            Intrinsics.checkNotNullParameter(names, "names");
            this.ids = ids;
            this.parentIds = parentIds;
            this.seriesNames = seriesNames;
            this.names = names;
            this.parentName = str;
        }

        public final Set<Integer> getIds() {
            return this.ids;
        }

        public final Set<Integer> getParentIds() {
            return this.parentIds;
        }

        public final List<String> getSeriesNames() {
            return this.seriesNames;
        }

        public final List<String> getNames() {
            return this.names;
        }

        public final String getParentName() {
            return this.parentName;
        }
    }
}
