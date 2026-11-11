package com.lagradost.cloudstream3.ui.library;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.actions.SearchIntents;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.syncproviders.AccountManager;
import com.lagradost.cloudstream3.syncproviders.SyncAPI;
import com.lagradost.cloudstream3.syncproviders.SyncRepo;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: LibraryViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0015J\u001a\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020$2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0015J*\u0010,\u001a\u00020\u00052\u0006\u0010.\u001a\u00020$2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00152\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u000e\u00101\u001a\u00020\u00052\u0006\u00102\u001a\u000203J\b\u00104\u001a\u00020\u0005H\u0014R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR \u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\rR\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR(\u0010\u001d\u001a\u0004\u0018\u00010\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u0019@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00150\u00108F¢\u0006\u0006\u001a\u0004\b#\u0010\u001bR*\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\u00102\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020$0\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001bR\"\u0010'\u001a\u0004\u0018\u00010$2\b\u0010\u001c\u001a\u0004\u0018\u00010$@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)¨\u00065"}, d2 = {"Lcom/lagradost/cloudstream3/ui/library/LibraryViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "switchPage", "", "page", "", "_currentPage", "Landroidx/lifecycle/MutableLiveData;", "currentPage", "Landroidx/lifecycle/LiveData;", "getCurrentPage", "()Landroidx/lifecycle/LiveData;", "_pages", "Lcom/lagradost/cloudstream3/mvvm/Resource;", "", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$Page;", "pages", "getPages", "_currentApiName", "", "currentApiName", "getCurrentApiName", "availableSyncApis", "Lcom/lagradost/cloudstream3/syncproviders/SyncRepo;", "getAvailableSyncApis", "()Ljava/util/List;", ES6Iterator.VALUE_PROPERTY, "currentSyncApi", "getCurrentSyncApi", "()Lcom/lagradost/cloudstream3/syncproviders/SyncRepo;", "setCurrentSyncApi", "(Lcom/lagradost/cloudstream3/syncproviders/SyncRepo;)V", "availableApiNames", "getAvailableApiNames", "Lcom/lagradost/cloudstream3/ui/library/ListSorting;", "sortingMethods", "getSortingMethods", "currentSortingMethod", "getCurrentSortingMethod", "()Lcom/lagradost/cloudstream3/ui/library/ListSorting;", "switchList", "name", "sort", "Lkotlinx/coroutines/Job;", "method", SearchIntents.EXTRA_QUERY, "items", "reloadPages", "forceReload", "", "onCleared", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LibraryViewModel extends ViewModel {
    private final MutableLiveData<String> _currentApiName;
    private final MutableLiveData<Integer> _currentPage;
    private final MutableLiveData<Resource<List<SyncAPI.Page>>> _pages;
    private final LiveData<String> currentApiName;
    private final LiveData<Integer> currentPage;
    private ListSorting currentSortingMethod;
    private SyncRepo currentSyncApi;
    private final LiveData<Resource<List<SyncAPI.Page>>> pages;
    private List<? extends ListSorting> sortingMethods;

    /* JADX WARN: Code duplicated, block: B:22:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:29:0x00bb A[Catch: Exception -> 0x00cd, TRY_LEAVE, TryCatch #0 {Exception -> 0x00cd, blocks: (B:5:0x0051, B:8:0x005d, B:13:0x0073, B:20:0x009f, B:23:0x00a6, B:29:0x00bb, B:28:0x00b6, B:19:0x0095, B:12:0x0069, B:16:0x007a, B:9:0x005f, B:25:0x00aa), top: B:45:0x0051, inners: #1, #2, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:50:0x00aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public LibraryViewModel() {
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>(0);
        this._currentPage = mutableLiveData;
        this.currentPage = mutableLiveData;
        Object obj = null;
        MutableLiveData<Resource<List<SyncAPI.Page>>> mutableLiveData2 = new MutableLiveData<>(null);
        this._pages = mutableLiveData2;
        this.pages = mutableLiveData2;
        MutableLiveData<String> mutableLiveData3 = new MutableLiveData<>("");
        this._currentApiName = mutableLiveData3;
        this.currentApiName = mutableLiveData3;
        List<SyncRepo> availableSyncApis = getAvailableSyncApis();
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/last_sync_api";
        Context context = companion.getContext();
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(str, null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(StringSerializer.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                        kSerializer = (KSerializer) (Result.m8942isFailureimpl(objM8936constructorimpl) ? null : objM8936constructorimpl);
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryViewModel$currentSyncApi$lambda$0$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryViewModel$currentSyncApi$lambda$0$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryViewModel$currentSyncApi$lambda$0$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(String.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        kSerializer = (KSerializer) (Result.m8942isFailureimpl(objM8936constructorimpl) ? null : objM8936constructorimpl);
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.ui.library.LibraryViewModel$currentSyncApi$lambda$0$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
            }
        } else {
            objDecodeFromString = null;
        }
        String str2 = (String) objDecodeFromString;
        for (Object obj2 : getAvailableSyncApis()) {
            if (Intrinsics.areEqual(((SyncRepo) obj2).getName(), str2)) {
                obj = obj2;
                break;
            }
        }
        SyncRepo syncRepo = (SyncRepo) obj;
        this.currentSyncApi = syncRepo == null ? (SyncRepo) CollectionsKt.firstOrNull((List) availableSyncApis) : syncRepo;
        List<? extends ListSorting> listEmptyList = CollectionsKt.emptyList();
        this.sortingMethods = listEmptyList;
        this.currentSortingMethod = (ListSorting) CollectionsKt.firstOrNull((List) listEmptyList);
        MainActivity.INSTANCE.getReloadLibraryEvent().plusAssign(new AnonymousClass1(this));
    }

    public final void switchPage(int page) {
        this._currentPage.postValue(Integer.valueOf(page));
    }

    public final LiveData<Integer> getCurrentPage() {
        return this.currentPage;
    }

    public final LiveData<Resource<List<SyncAPI.Page>>> getPages() {
        return this.pages;
    }

    public final LiveData<String> getCurrentApiName() {
        return this.currentApiName;
    }

    private final List<SyncRepo> getAvailableSyncApis() {
        SyncRepo[] syncApis = AccountManager.INSTANCE.getSyncApis();
        ArrayList arrayList = new ArrayList();
        for (SyncRepo syncRepo : syncApis) {
            if (syncRepo.isAvailable()) {
                arrayList.add(syncRepo);
            }
        }
        return arrayList;
    }

    public final SyncRepo getCurrentSyncApi() {
        return this.currentSyncApi;
    }

    private final void setCurrentSyncApi(SyncRepo syncRepo) {
        this.currentSyncApi = syncRepo;
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/last_sync_api";
        SyncRepo syncRepo2 = this.currentSyncApi;
        companion.setKey(str, syncRepo2 != null ? syncRepo2.getName() : null);
    }

    public final List<String> getAvailableApiNames() {
        List<SyncRepo> availableSyncApis = getAvailableSyncApis();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(availableSyncApis, 10));
        Iterator<T> it = availableSyncApis.iterator();
        while (it.hasNext()) {
            arrayList.add(((SyncRepo) it.next()).getName());
        }
        return arrayList;
    }

    public final List<ListSorting> getSortingMethods() {
        return this.sortingMethods;
    }

    public final ListSorting getCurrentSortingMethod() {
        return this.currentSortingMethod;
    }

    public final void switchList(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        setCurrentSyncApi(getAvailableSyncApis().get(getAvailableApiNames().indexOf(name)));
        MutableLiveData<String> mutableLiveData = this._currentApiName;
        SyncRepo syncRepo = this.currentSyncApi;
        mutableLiveData.postValue(syncRepo != null ? syncRepo.getName() : null);
        reloadPages(true);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.library.LibraryViewModel$sort$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LibraryViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/library/LibraryViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.library.LibraryViewModel$sort$1", f = "LibraryViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C08561 extends SuspendLambda implements Function3<CoroutineScope, LibraryViewModel, Continuation<? super Unit>, Object> {
        final /* synthetic */ ListSorting $method;
        final /* synthetic */ String $query;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08561(ListSorting listSorting, String str, Continuation<? super C08561> continuation) {
            super(3, continuation);
            this.$method = listSorting;
            this.$query = str;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, LibraryViewModel libraryViewModel, Continuation<? super Unit> continuation) {
            return LibraryViewModel.this.new C08561(this.$method, this.$query, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Resource resource = (Resource) LibraryViewModel.this._pages.getValue();
                if (resource == null) {
                    return Unit.INSTANCE;
                }
                if (resource instanceof Resource.Success) {
                    LibraryViewModel.this.sort(this.$method, this.$query, (List) ((Resource.Success) resource).getValue());
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static /* synthetic */ Job sort$default(LibraryViewModel libraryViewModel, ListSorting listSorting, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return libraryViewModel.sort(listSorting, str);
    }

    public final Job sort(ListSorting method, String query) {
        Intrinsics.checkNotNullParameter(method, "method");
        return Coroutines.INSTANCE.ioSafe(this, new C08561(method, query, null));
    }

    static /* synthetic */ void sort$default(LibraryViewModel libraryViewModel, ListSorting listSorting, String str, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        libraryViewModel.sort(listSorting, str, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sort(ListSorting method, String query, List<SyncAPI.Page> items) {
        this.currentSortingMethod = method;
        DataStoreHelper.INSTANCE.setLibrarySortingMode(method.ordinal());
        Iterator<T> it = items.iterator();
        while (it.hasNext()) {
            ((SyncAPI.Page) it.next()).sort(method, query);
        }
        this._pages.postValue(new Resource.Success(items));
    }

    public final void reloadPages(boolean forceReload) {
        List list;
        SyncRepo syncRepo;
        if (!forceReload) {
            Resource<List<SyncAPI.Page>> value = this.pages.getValue();
            Resource.Success success = value instanceof Resource.Success ? (Resource.Success) value : null;
            if (success != null && (list = (List) success.getValue()) != null && (!list.isEmpty()) && ((syncRepo = this.currentSyncApi) == null || !syncRepo.getRequireLibraryRefresh())) {
                return;
            }
        }
        Coroutines.INSTANCE.ioSafe(this, new C08551(null));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.library.LibraryViewModel$reloadPages$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LibraryViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/library/LibraryViewModel;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.library.LibraryViewModel$reloadPages$1", f = "LibraryViewModel.kt", i = {0, 0}, l = {101}, m = "invokeSuspend", n = {"repo", "$i$a$-let-LibraryViewModel$reloadPages$1$1"}, nl = {102}, s = {"L$1", "I$0"}, v = 2)
    static final class C08551 extends SuspendLambda implements Function3<CoroutineScope, LibraryViewModel, Continuation<? super Unit>, Object> {
        int I$0;
        Object L$0;
        Object L$1;
        int label;

        C08551(Continuation<? super C08551> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, LibraryViewModel libraryViewModel, Continuation<? super Unit> continuation) {
            return LibraryViewModel.this.new C08551(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            SyncRepo syncRepo;
            Object value;
            LibraryViewModel libraryViewModel;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SyncRepo currentSyncApi = LibraryViewModel.this.getCurrentSyncApi();
                if (currentSyncApi != null) {
                    LibraryViewModel libraryViewModel2 = LibraryViewModel.this;
                    libraryViewModel2._currentApiName.postValue(currentSyncApi.getName());
                    libraryViewModel2._pages.postValue(new Resource.Loading(null, 1, null));
                    this.L$0 = libraryViewModel2;
                    this.L$1 = currentSyncApi;
                    this.I$0 = 0;
                    this.label = 1;
                    Object objM8425libraryIoAF18A = currentSyncApi.m8425libraryIoAF18A(this);
                    if (objM8425libraryIoAF18A == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    syncRepo = currentSyncApi;
                    value = objM8425libraryIoAF18A;
                    libraryViewModel = libraryViewModel2;
                }
                return Unit.INSTANCE;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            syncRepo = (SyncRepo) this.L$1;
            libraryViewModel = (LibraryViewModel) this.L$0;
            ResultKt.throwOnFailure(obj);
            value = ((Result) obj).getValue();
            Throwable thM8939exceptionOrNullimpl = Result.m8939exceptionOrNullimpl(value);
            if (thM8939exceptionOrNullimpl != null) {
                libraryViewModel._pages.postValue(ArchComponentExtKt.throwAbleToResource(thM8939exceptionOrNullimpl));
            } else {
                if (Result.m8942isFailureimpl(value)) {
                    value = null;
                }
                SyncAPI.LibraryMetadata libraryMetadata = (SyncAPI.LibraryMetadata) value;
                if (libraryMetadata == null) {
                    libraryViewModel._pages.postValue(new Resource.Failure(false, "Unable to fetch library"));
                } else {
                    libraryViewModel.sortingMethods = CollectionsKt.toList(libraryMetadata.getSupportedListSorting());
                    syncRepo.setRequireLibraryRefresh(false);
                    List<SyncAPI.LibraryList> allLibraryLists = libraryMetadata.getAllLibraryLists();
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(allLibraryLists, 10));
                    for (SyncAPI.LibraryList libraryList : allLibraryLists) {
                        arrayList.add(new SyncAPI.Page(libraryList.getName(), libraryList.getItems()));
                    }
                    ArrayList arrayList2 = arrayList;
                    ListSorting listSorting = (ListSorting) CollectionsKt.getOrNull(ListSorting.getEntries(), DataStoreHelper.INSTANCE.getLibrarySortingMode());
                    if (listSorting == null || !libraryMetadata.getSupportedListSorting().contains(listSorting)) {
                        libraryViewModel.sort(ListSorting.Query, null, arrayList2);
                    } else {
                        libraryViewModel.sort(listSorting, null, arrayList2);
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.library.LibraryViewModel$1, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        AnonymousClass1(Object obj) {
            super(1, obj, LibraryViewModel.class, "reloadPages", "reloadPages(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((LibraryViewModel) this.receiver).reloadPages(z);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.library.LibraryViewModel$onCleared$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LibraryViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C08541 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        C08541(Object obj) {
            super(1, obj, LibraryViewModel.class, "reloadPages", "reloadPages(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((LibraryViewModel) this.receiver).reloadPages(z);
        }
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        MainActivity.INSTANCE.getReloadLibraryEvent().minusAssign(new C08541(this));
        super.onCleared();
    }
}
