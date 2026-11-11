package com.lagradost.cloudstream3.ui.download.queue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.DownloadQueueItemBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.ui.download.DownloadButtonSetup;
import com.lagradost.cloudstream3.ui.download.DownloadClickEvent;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: DownloadQueueAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 !2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001!B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J&\u0010\u0014\u001a\u00020\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u00112\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u000bH\u0016J\u000e\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001dJ\u0016\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020 R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/queue/DownloadQueueAdapter;", "Lcom/lagradost/cloudstream3/ui/BaseAdapter;", "Lcom/lagradost/cloudstream3/ui/download/queue/DownloadAdapterItem;", "", "fragment", "Landroidx/fragment/app/Fragment;", "<init>", "(Landroidx/fragment/app/Fragment;)V", "getFragment", "()Landroidx/fragment/app/Fragment;", "currentDownloads", "", "getCurrentDownloads", "()I", "setCurrentDownloads", "(I)V", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "parent", "Landroid/view/ViewGroup;", "onBindContent", "holder", "item", "position", "submitQueue", "newQueue", "Lcom/lagradost/cloudstream3/ui/download/queue/DownloadAdapterQueue;", "bindSeparator", "binding", "Lcom/lagradost/cloudstream3/databinding/DownloadQueueItemBinding;", "bind", "queueWrapper", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadQueueAdapter extends BaseAdapter<DownloadAdapterItem, Unit> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DOWNLOAD_SEPARATOR_TAG = "DOWNLOAD_SEPARATOR_TAG";
    private int currentDownloads;
    private final Fragment fragment;

    /* JADX INFO: compiled from: DownloadQueueAdapter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VideoDownloadManager.DownloadType.values().length];
            try {
                iArr[VideoDownloadManager.DownloadType.IsDownloading.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[VideoDownloadManager.DownloadType.IsPaused.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final Fragment getFragment() {
        return this.fragment;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadQueueAdapter(Fragment fragment) {
        super(0, new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueAdapter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(DownloadQueueAdapter._init_$lambda$0((DownloadAdapterItem) obj, (DownloadAdapterItem) obj2));
            }
        }, new Function2() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueAdapter$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(DownloadQueueAdapter._init_$lambda$1((DownloadAdapterItem) obj, (DownloadAdapterItem) obj2));
            }
        }), 1, null);
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.fragment = fragment;
    }

    static final boolean _init_$lambda$0(DownloadAdapterItem a, DownloadAdapterItem b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        DownloadObjects.DownloadQueueWrapper item = a.getItem();
        Integer numValueOf = item != null ? Integer.valueOf(item.getId()) : null;
        DownloadObjects.DownloadQueueWrapper item2 = b.getItem();
        return Intrinsics.areEqual(numValueOf, item2 != null ? Integer.valueOf(item2.getId()) : null);
    }

    static final boolean _init_$lambda$1(DownloadAdapterItem a, DownloadAdapterItem b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a.getItem(), b.getItem());
    }

    public final int getCurrentDownloads() {
        return this.currentDownloads;
    }

    public final void setCurrentDownloads(int i) {
        this.currentDownloads = i;
    }

    /* JADX INFO: compiled from: DownloadQueueAdapter.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/queue/DownloadQueueAdapter$Companion;", "", "<init>", "()V", "DOWNLOAD_SEPARATOR_TAG", "", "getDOWNLOAD_SEPARATOR_TAG", "()Ljava/lang/String;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getDOWNLOAD_SEPARATOR_TAG() {
            return DownloadQueueAdapter.DOWNLOAD_SEPARATOR_TAG;
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Unit> onCreateContent(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        DownloadQueueItemBinding downloadQueueItemBindingInflate = DownloadQueueItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(downloadQueueItemBindingInflate, "inflate(...)");
        return new ViewHolderState<>(downloadQueueItemBindingInflate);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Unit> holder, DownloadAdapterItem item, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        if (view instanceof DownloadQueueItemBinding) {
            if (item.getItem() == null) {
                holder.itemView.setTag(DOWNLOAD_SEPARATOR_TAG);
                bindSeparator((DownloadQueueItemBinding) view);
            } else {
                holder.itemView.setTag(null);
                bind((DownloadQueueItemBinding) view, item.getItem());
            }
        }
    }

    public final void submitQueue(DownloadAdapterQueue newQueue) {
        Intrinsics.checkNotNullParameter(newQueue, "newQueue");
        int size = newQueue.getCurrentDownloads().size();
        List<DownloadObjects.DownloadQueueWrapper> currentDownloads = newQueue.getCurrentDownloads();
        List<DownloadObjects.DownloadQueueWrapper> queue = newQueue.getQueue();
        this.currentDownloads = currentDownloads.size();
        List listPlus = CollectionsKt.plus((Collection) currentDownloads, (Iterable) queue);
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listPlus) {
            if (hashSet.add(Integer.valueOf(((DownloadObjects.DownloadQueueWrapper) obj).getId()))) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(new DownloadAdapterItem((DownloadObjects.DownloadQueueWrapper) it.next()));
        }
        List mutableList = CollectionsKt.toMutableList((Collection) arrayList3);
        if (size < mutableList.size()) {
            mutableList.add(size, new DownloadAdapterItem(null));
        }
        BaseAdapter.submitList$default(this, mutableList, null, 2, null);
    }

    public final void bindSeparator(DownloadQueueItemBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        LinearLayout separatorHolder = binding.separatorHolder;
        Intrinsics.checkNotNullExpressionValue(separatorHolder, "separatorHolder");
        separatorHolder.setVisibility(0);
        CardView downloadChildEpisodeHolder = binding.downloadChildEpisodeHolder;
        Intrinsics.checkNotNullExpressionValue(downloadChildEpisodeHolder, "downloadChildEpisodeHolder");
        downloadChildEpisodeHolder.setVisibility(8);
    }

    public final void bind(DownloadQueueItemBinding binding, final DownloadObjects.DownloadQueueWrapper queueWrapper) {
        String name;
        DownloadObjects.DownloadItem item;
        DownloadObjects.DownloadEpisodeMetadata ep;
        Integer episode;
        DownloadObjects.DownloadItem item2;
        DownloadObjects.DownloadEpisodeMetadata ep2;
        DownloadObjects.DownloadItem item3;
        DownloadObjects.DownloadEpisodeMetadata ep3;
        ResultEpisode episode2;
        Integer season;
        ResultEpisode episode3;
        ResultEpisode episode4;
        String mainName;
        DownloadObjects.DownloadItem item4;
        DownloadObjects.DownloadEpisodeMetadata ep4;
        Intrinsics.checkNotNullParameter(binding, "binding");
        Intrinsics.checkNotNullParameter(queueWrapper, "queueWrapper");
        final Context context = binding.getRoot().getContext();
        LinearLayout separatorHolder = binding.separatorHolder;
        Intrinsics.checkNotNullExpressionValue(separatorHolder, "separatorHolder");
        separatorHolder.setVisibility(8);
        CardView downloadChildEpisodeHolder = binding.downloadChildEpisodeHolder;
        Intrinsics.checkNotNullExpressionValue(downloadChildEpisodeHolder, "downloadChildEpisodeHolder");
        downloadChildEpisodeHolder.setVisibility(0);
        Integer season2 = null;
        if (queueWrapper.getId() != queueWrapper.getParentId()) {
            DownloadObjects.DownloadQueueItem downloadItem = queueWrapper.getDownloadItem();
            if (downloadItem == null || (mainName = downloadItem.getResultName()) == null) {
                DownloadObjects.DownloadResumePackage resumePackage = queueWrapper.getResumePackage();
                mainName = (resumePackage == null || (item4 = resumePackage.getItem()) == null || (ep4 = item4.getEp()) == null) ? null : ep4.getMainName();
            }
            binding.downloadChildEpisodeTextExtra.setText(mainName);
        } else {
            binding.downloadChildEpisodeTextExtra.setText((CharSequence) null);
        }
        TextView downloadChildEpisodeTextExtra = binding.downloadChildEpisodeTextExtra;
        Intrinsics.checkNotNullExpressionValue(downloadChildEpisodeTextExtra, "downloadChildEpisodeTextExtra");
        TextView textView = downloadChildEpisodeTextExtra;
        CharSequence text = binding.downloadChildEpisodeTextExtra.getText();
        textView.setVisibility(text == null || StringsKt.isBlank(text) ? 8 : 0);
        VideoDownloadManager.DownloadType downloadType = VideoDownloadManager.INSTANCE.getDownloadStatus().get(Integer.valueOf(queueWrapper.getId()));
        binding.downloadButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueAdapter$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadQueueAdapter.bind$lambda$0$0(queueWrapper, context, view);
            }
        });
        binding.downloadButton.resetView();
        binding.downloadButton.setStatus(downloadType);
        binding.downloadButton.setPersistentId(queueWrapper.getId());
        TextView textView2 = binding.downloadChildEpisodeText;
        DownloadObjects.DownloadQueueItem downloadItem2 = queueWrapper.getDownloadItem();
        if (downloadItem2 == null || (episode4 = downloadItem2.getEpisode()) == null || (name = episode4.getName()) == null) {
            DownloadObjects.DownloadResumePackage resumePackage2 = queueWrapper.getResumePackage();
            name = (resumePackage2 == null || (item = resumePackage2.getItem()) == null || (ep = item.getEp()) == null) ? null : ep.getName();
        }
        DownloadObjects.DownloadQueueItem downloadItem3 = queueWrapper.getDownloadItem();
        if (downloadItem3 == null || (episode3 = downloadItem3.getEpisode()) == null) {
            DownloadObjects.DownloadResumePackage resumePackage3 = queueWrapper.getResumePackage();
            episode = (resumePackage3 == null || (item2 = resumePackage3.getItem()) == null || (ep2 = item2.getEp()) == null) ? null : ep2.getEpisode();
        } else {
            episode = Integer.valueOf(episode3.getEpisode());
        }
        DownloadObjects.DownloadQueueItem downloadItem4 = queueWrapper.getDownloadItem();
        if (downloadItem4 == null || (episode2 = downloadItem4.getEpisode()) == null || (season = episode2.getSeason()) == null) {
            DownloadObjects.DownloadResumePackage resumePackage4 = queueWrapper.getResumePackage();
            if (resumePackage4 != null && (item3 = resumePackage4.getItem()) != null && (ep3 = item3.getEp()) != null) {
                season2 = ep3.getSeason();
            }
        } else {
            season2 = season;
        }
        AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
        Intrinsics.checkNotNull(context);
        textView2.setText(appContextUtils.getNameFull(context, name, episode, season2));
        textView2.setSelected(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:22:0x007f  */
    /* JADX WARN: Code duplicated, block: B:29:0x0095 A[Catch: Exception -> 0x00a7, TRY_LEAVE, TryCatch #6 {Exception -> 0x00a7, blocks: (B:5:0x0028, B:8:0x0034, B:13:0x004e, B:20:0x0079, B:23:0x0080, B:29:0x0095, B:28:0x0090, B:19:0x006f, B:12:0x0044, B:9:0x0036, B:16:0x0055, B:25:0x0084), top: B:89:0x0028, inners: #3, #9, #10 }] */
    /* JADX WARN: Code duplicated, block: B:92:0x0084 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static final void bind$lambda$0$0(final DownloadObjects.DownloadQueueWrapper downloadQueueWrapper, Context context, View view) {
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        Object objDecodeFromString2;
        Object objM8936constructorimpl2;
        KSerializer kSerializer;
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String folderName = DataStore.INSTANCE.getFolderName(String.valueOf(downloadQueueWrapper.getParentId()), String.valueOf(downloadQueueWrapper.getId()));
        Context context2 = companion.getContext();
        Object obj = null;
        if (context2 != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context2).getString(dataStore.getFolderName(DataStoreKt.DOWNLOAD_EPISODE_CACHE, folderName), null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadEpisodeCached"));
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                            objM8936constructorimpl2 = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl2;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueAdapter$bind$lambda$0$0$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueAdapter$bind$lambda$0$0$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueAdapter$bind$lambda$0$0$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadEpisodeCached.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                            objM8936constructorimpl2 = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl2;
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueAdapter$bind$lambda$0$0$$inlined$getKey$1
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
        final DownloadObjects.DownloadEpisodeCached downloadEpisodeCached = (DownloadObjects.DownloadEpisodeCached) objDecodeFromString;
        DataStore dataStore2 = DataStore.INSTANCE;
        Intrinsics.checkNotNull(context);
        try {
            String string2 = dataStore2.getSharedPrefs(context).getString(dataStore2.getFolderName(VideoDownloadManager.KEY_DOWNLOAD_INFO, String.valueOf(downloadQueueWrapper.getId())), null);
            if (string2 != null) {
                AppUtils appUtils2 = AppUtils.INSTANCE;
                try {
                    Result.Companion companion6 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadedFileInfo"));
                } catch (Throwable th3) {
                    Result.Companion companion7 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                    try {
                        Result.Companion companion8 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadedFileInfo.class), null, 2, null));
                    } catch (Throwable th4) {
                        Result.Companion companion9 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th4));
                    }
                }
                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                    objM8936constructorimpl = null;
                }
                KSerializer kSerializer2 = (KSerializer) objM8936constructorimpl;
                if (kSerializer2 != null) {
                    try {
                        objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer2, string2);
                    } catch (SerializationException e2) {
                        ArchComponentExtKt.logError(e2);
                        objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueAdapter$bind$lambda$0$0$$inlined$getKey$2
                        });
                    } catch (Throwable unused3) {
                        objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueAdapter$bind$lambda$0$0$$inlined$getKey$2
                        });
                    }
                } else {
                    objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueAdapter$bind$lambda$0$0$$inlined$getKey$2
                    });
                }
                obj = objDecodeFromString2;
            }
        } catch (Exception unused4) {
        }
        DownloadObjects.DownloadedFileInfo downloadedFileInfo = (DownloadObjects.DownloadedFileInfo) obj;
        boolean zIsCurrentlyDownloading = downloadQueueWrapper.isCurrentlyDownloading();
        ArrayList arrayList = new ArrayList();
        if (zIsCurrentlyDownloading && downloadEpisodeCached != null) {
            if (downloadedFileInfo != null) {
                arrayList.add(new Pair(1, Integer.valueOf(R.string.popup_delete_file)));
            } else {
                arrayList.add(new Pair(6, Integer.valueOf(R.string.cancel)));
            }
            VideoDownloadManager.DownloadType downloadType = VideoDownloadManager.INSTANCE.getDownloadStatus().get(Integer.valueOf(downloadQueueWrapper.getId()));
            int i = downloadType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[downloadType.ordinal()];
            if (i == 1) {
                arrayList.add(new Pair(3, Integer.valueOf(R.string.popup_pause_download)));
            } else if (i == 2) {
                arrayList.add(new Pair(2, Integer.valueOf(R.string.popup_resume_download)));
            }
            UIHelper uIHelper = UIHelper.INSTANCE;
            Intrinsics.checkNotNull(view);
            uIHelper.popupMenuNoIcons(view, arrayList, new Function1() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueAdapter$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return DownloadQueueAdapter.bind$lambda$0$0$0(downloadEpisodeCached, (MenuItem) obj2);
                }
            });
            return;
        }
        arrayList.add(new Pair(6, Integer.valueOf(R.string.cancel)));
        UIHelper uIHelper2 = UIHelper.INSTANCE;
        Intrinsics.checkNotNull(view);
        uIHelper2.popupMenuNoIcons(view, arrayList, new Function1() { // from class: com.lagradost.cloudstream3.ui.download.queue.DownloadQueueAdapter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return DownloadQueueAdapter.bind$lambda$0$0$1(downloadQueueWrapper, (MenuItem) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bind$lambda$0$0$0(DownloadObjects.DownloadEpisodeCached downloadEpisodeCached, MenuItem popupMenuNoIcons) {
        Intrinsics.checkNotNullParameter(popupMenuNoIcons, "$this$popupMenuNoIcons");
        DownloadButtonSetup.INSTANCE.handleDownloadClick(new DownloadClickEvent(popupMenuNoIcons.getItemId(), downloadEpisodeCached));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bind$lambda$0$0$1(DownloadObjects.DownloadQueueWrapper downloadQueueWrapper, MenuItem popupMenuNoIcons) {
        Intrinsics.checkNotNullParameter(popupMenuNoIcons, "$this$popupMenuNoIcons");
        if (popupMenuNoIcons.getItemId() == 6) {
            DownloadQueueManager.INSTANCE.cancelDownload(downloadQueueWrapper.getId());
        }
        return Unit.INSTANCE;
    }
}
