package com.lagradost.cloudstream3.utils.downloader;

import android.content.Context;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.plugins.PluginManager;
import com.lagradost.cloudstream3.services.DownloadQueueService;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: DownloadQueueManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\nH\u0002J\u0010\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u001aH\u0002J\u0013\u0010 \u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002¢\u0006\u0002\u0010!J\u0018\u0010\"\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010#\u001a\u00020\u001aH\u0002J\u0010\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010\u0016\u001a\u00020\u0017J\u0018\u0010&\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020(H\u0002J\u0012\u0010)\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002J\u000e\u0010*\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u001aJ\u0006\u0010+\u001a\u00020\u0015J\u000e\u0010,\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u001aJ\u0016\u0010-\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010#\u001a\u00020\u001aJ\u0015\u0010.\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001d\u001a\u00020\n¢\u0006\u0002\u0010/J\u0006\u00100\u001a\u00020\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R'\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR'\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012¨\u00061"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/DownloadQueueManager;", "", "<init>", "()V", "TAG", "", "QUEUE_KEY", "_queue", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "get_queue", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "_queue$delegate", "Lkotlin/Lazy;", "queue", "Lkotlinx/coroutines/flow/StateFlow;", "getQueue", "()Lkotlinx/coroutines/flow/StateFlow;", "queue$delegate", "init", "", "context", "Landroid/content/Context;", "getPreResumeIds", "", "", "add", "", "downloadQueueWrapper", "remove", "id", "removeAll", "()[Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "reorder", "newPosition", "popQueue", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$EpisodeDownloadInstance;", "setQueueStatus", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;", "startQueueService", "cancelDownload", "removeAllFromQueue", "removeFromQueue", "reorderItem", "addToQueue", "(Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;)Lkotlin/Unit;", "forceRefreshQueue", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadQueueManager {
    public static final String QUEUE_KEY = "download_queue_key";
    private static final String TAG = "DownloadQueueManager";
    public static final DownloadQueueManager INSTANCE = new DownloadQueueManager();

    /* JADX INFO: renamed from: _queue$delegate, reason: from kotlin metadata */
    private static final Lazy _queue = LazyKt.lazy(new Function0() { // from class: com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return DownloadQueueManager._queue_delegate$lambda$0();
        }
    });

    /* JADX INFO: renamed from: queue$delegate, reason: from kotlin metadata */
    private static final Lazy queue = LazyKt.lazy(new Function0() { // from class: com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return DownloadQueueManager.INSTANCE.get_queue();
        }
    });

    private DownloadQueueManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MutableStateFlow<DownloadObjects.DownloadQueueWrapper[]> get_queue() {
        return (MutableStateFlow) _queue.getValue();
    }

    static final MutableStateFlow _queue_delegate$lambda$0() {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Context context = CloudStreamApp.INSTANCE.getContext();
        Object obj = null;
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(QUEUE_KEY, null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadQueueWrapper.class), SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadQueueWrapper")));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadQueueWrapper[].class), null, 2, null));
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadQueueWrapper[]>() { // from class: com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$_queue_delegate$lambda$0$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadQueueWrapper[]>() { // from class: com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$_queue_delegate$lambda$0$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadQueueWrapper[]>() { // from class: com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$_queue_delegate$lambda$0$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        DownloadObjects.DownloadQueueWrapper[] downloadQueueWrapperArr = (DownloadObjects.DownloadQueueWrapper[]) obj;
        if (downloadQueueWrapperArr == null) {
            downloadQueueWrapperArr = new DownloadObjects.DownloadQueueWrapper[0];
        }
        return StateFlowKt.MutableStateFlow(downloadQueueWrapperArr);
    }

    public final StateFlow<DownloadObjects.DownloadQueueWrapper[]> getQueue() {
        return (StateFlow) queue.getValue();
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: DownloadQueueManager.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadQueueManager;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$init$1", f = "DownloadQueueManager.kt", i = {}, l = {55}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, DownloadQueueManager, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, DownloadQueueManager downloadQueueManager, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DownloadQueueManager.INSTANCE.get_queue().collect(new FlowCollector() { // from class: com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager.init.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((DownloadObjects.DownloadQueueWrapper[]) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(DownloadObjects.DownloadQueueWrapper[] downloadQueueWrapperArr, Continuation<? super Unit> continuation) {
                        CloudStreamApp.INSTANCE.setKey(DownloadQueueManager.QUEUE_KEY, downloadQueueWrapperArr);
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Coroutines.INSTANCE.ioSafe(this, new AnonymousClass1(null));
        Coroutines.INSTANCE.ioSafe(this, new AnonymousClass2(context, null));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$init$2, reason: invalid class name */
    /* JADX INFO: compiled from: DownloadQueueManager.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadQueueManager;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$init$2", f = "DownloadQueueManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function3<CoroutineScope, DownloadQueueManager, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Context context, Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
            this.$context = context;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, DownloadQueueManager downloadQueueManager, Continuation<? super Unit> continuation) {
            return new AnonymousClass2(this.$context, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object value;
            int i;
            DownloadObjects.DownloadQueueWrapper[] downloadQueueWrapperArr;
            DownloadObjects.DownloadQueueWrapper downloadQueuePackage;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (!PluginManager.INSTANCE.isSafeMode()) {
                Set preResumeIds = DownloadQueueManager.INSTANCE.getPreResumeIds();
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : preResumeIds) {
                    if (!VideoDownloadManager.INSTANCE.getCurrentDownloads().getValue().contains(Boxing.boxInt(((Number) obj2).intValue()))) {
                        arrayList.add(obj2);
                    }
                }
                Context context = this.$context;
                ArrayList arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    int iIntValue = ((Number) it.next()).intValue();
                    DownloadObjects.DownloadResumePackage downloadResumePackage = VideoDownloadManager.INSTANCE.getDownloadResumePackage(context, iIntValue);
                    if (downloadResumePackage == null || (downloadQueuePackage = downloadResumePackage.toWrapper()) == null) {
                        downloadQueuePackage = VideoDownloadManager.INSTANCE.getDownloadQueuePackage(context, iIntValue);
                    }
                    if (downloadQueuePackage != null) {
                        arrayList2.add(downloadQueuePackage);
                    }
                }
                ArrayList arrayList3 = arrayList2;
                MutableStateFlow mutableStateFlow = DownloadQueueManager.INSTANCE.get_queue();
                do {
                    value = mutableStateFlow.getValue();
                    List listPlus = CollectionsKt.plus((Collection) arrayList3, (Object[]) value);
                    HashSet hashSet = new HashSet();
                    ArrayList arrayList4 = new ArrayList();
                    for (Object obj3 : listPlus) {
                        if (hashSet.add(Boxing.boxInt(((DownloadObjects.DownloadQueueWrapper) obj3).getId()))) {
                            arrayList4.add(obj3);
                        }
                    }
                    downloadQueueWrapperArr = (DownloadObjects.DownloadQueueWrapper[]) arrayList4.toArray(new DownloadObjects.DownloadQueueWrapper[0]);
                } while (!mutableStateFlow.compareAndSet(value, downloadQueueWrapperArr));
                CloudStreamApp.INSTANCE.removeKeys(VideoDownloadManager.KEY_RESUME_IN_QUEUE);
                for (DownloadObjects.DownloadQueueWrapper downloadQueueWrapper : downloadQueueWrapperArr) {
                    DownloadQueueManager.INSTANCE.setQueueStatus(downloadQueueWrapper.getId(), VideoDownloadManager.DownloadType.IsPending);
                }
                if (ArraysKt.any(downloadQueueWrapperArr)) {
                    DownloadQueueManager.INSTANCE.startQueueService(this.$context);
                }
                return Unit.INSTANCE;
            }
            VideoDownloadManager.INSTANCE.cancelAllDownloadNotifications(this.$context);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<Integer> getPreResumeIds() {
        List<String> keys = CloudStreamApp.INSTANCE.getKeys(VideoDownloadManager.KEY_RESUME_IN_QUEUE);
        if (keys != null) {
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = keys.iterator();
            while (it.hasNext()) {
                Integer intOrNull = StringsKt.toIntOrNull(StringsKt.substringAfter$default((String) it.next(), "download_resume_queue_key/", (String) null, 2, (Object) null));
                if (intOrNull != null) {
                    arrayList.add(intOrNull);
                }
            }
            Set<Integer> set = CollectionsKt.toSet(arrayList);
            if (set != null) {
                return set;
            }
        }
        return SetsKt.emptySet();
    }

    private final boolean add(DownloadObjects.DownloadQueueWrapper downloadQueueWrapper) {
        DownloadObjects.DownloadQueueWrapper[] value;
        DownloadObjects.DownloadQueueWrapper[] downloadQueueWrapperArr;
        Log.d(TAG, "Download added to queue: " + downloadQueueWrapper);
        MutableStateFlow<DownloadObjects.DownloadQueueWrapper[]> mutableStateFlow = get_queue();
        do {
            value = mutableStateFlow.getValue();
            downloadQueueWrapperArr = value;
            if (!downloadQueueWrapper.isCurrentlyDownloading()) {
                int length = downloadQueueWrapperArr.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        if (downloadQueueWrapperArr[i].getId() == downloadQueueWrapper.getId()) {
                            break;
                        }
                        i++;
                    } else {
                        downloadQueueWrapperArr = (DownloadObjects.DownloadQueueWrapper[]) ArraysKt.plus(downloadQueueWrapperArr, downloadQueueWrapper);
                        break;
                    }
                }
            }
        } while (!mutableStateFlow.compareAndSet(value, downloadQueueWrapperArr));
        for (DownloadObjects.DownloadQueueWrapper downloadQueueWrapper2 : downloadQueueWrapperArr) {
            if (downloadQueueWrapper2.getId() == downloadQueueWrapper.getId()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void remove(int id) {
        DownloadObjects.DownloadQueueWrapper[] value;
        DownloadObjects.DownloadQueueWrapper[] downloadQueueWrapperArr;
        Log.d(TAG, "Download removed from the queue: " + id);
        MutableStateFlow<DownloadObjects.DownloadQueueWrapper[]> mutableStateFlow = get_queue();
        do {
            value = mutableStateFlow.getValue();
            downloadQueueWrapperArr = value;
            for (DownloadObjects.DownloadQueueWrapper downloadQueueWrapper : downloadQueueWrapperArr) {
                if (downloadQueueWrapper.getId() == id) {
                    ArrayList arrayList = new ArrayList();
                    for (DownloadObjects.DownloadQueueWrapper downloadQueueWrapper2 : downloadQueueWrapperArr) {
                        if (downloadQueueWrapper2.getId() != id) {
                            arrayList.add(downloadQueueWrapper2);
                        }
                    }
                    downloadQueueWrapperArr = (DownloadObjects.DownloadQueueWrapper[]) arrayList.toArray(new DownloadObjects.DownloadQueueWrapper[0]);
                    break;
                }
            }
        } while (!mutableStateFlow.compareAndSet(value, downloadQueueWrapperArr));
    }

    private final DownloadObjects.DownloadQueueWrapper[] removeAll() {
        DownloadObjects.DownloadQueueWrapper[] value;
        DownloadObjects.DownloadQueueWrapper[] downloadQueueWrapperArr;
        Log.d(TAG, "Removed everything from queue");
        MutableStateFlow<DownloadObjects.DownloadQueueWrapper[]> mutableStateFlow = get_queue();
        do {
            value = mutableStateFlow.getValue();
            downloadQueueWrapperArr = value;
        } while (!mutableStateFlow.compareAndSet(value, new DownloadObjects.DownloadQueueWrapper[0]));
        return downloadQueueWrapperArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reorder(DownloadObjects.DownloadQueueWrapper downloadQueueWrapper, int newPosition) {
        DownloadObjects.DownloadQueueWrapper[] value;
        List mutableList;
        MutableStateFlow<DownloadObjects.DownloadQueueWrapper[]> mutableStateFlow = get_queue();
        do {
            value = mutableStateFlow.getValue();
            DownloadObjects.DownloadQueueWrapper[] downloadQueueWrapperArr = value;
            int iCoerceIn = RangesKt.coerceIn(newPosition, 0, downloadQueueWrapperArr.length);
            int id = downloadQueueWrapper.getId();
            ArrayList arrayList = new ArrayList();
            for (DownloadObjects.DownloadQueueWrapper downloadQueueWrapper2 : downloadQueueWrapperArr) {
                if (downloadQueueWrapper2.getId() != id) {
                    arrayList.add(downloadQueueWrapper2);
                }
            }
            mutableList = CollectionsKt.toMutableList((Collection) arrayList);
            mutableList.add(iCoerceIn, downloadQueueWrapper);
        } while (!mutableStateFlow.compareAndSet(value, (DownloadObjects.DownloadQueueWrapper[]) mutableList.toArray(new DownloadObjects.DownloadQueueWrapper[0])));
    }

    public final VideoDownloadManager.EpisodeDownloadInstance popQueue(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DownloadObjects.DownloadQueueWrapper downloadQueueWrapper = (DownloadObjects.DownloadQueueWrapper) ArraysKt.firstOrNull(getQueue().getValue());
        if (downloadQueueWrapper == null) {
            return null;
        }
        remove(downloadQueueWrapper.getId());
        return new VideoDownloadManager.EpisodeDownloadInstance(context, downloadQueueWrapper);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setQueueStatus(int id, VideoDownloadManager.DownloadType status) {
        VideoDownloadManager.INSTANCE.getDownloadStatusEvent().invoke(new Pair<>(Integer.valueOf(id), status));
        VideoDownloadManager.INSTANCE.getDownloadStatus().put(Integer.valueOf(id), status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startQueueService(Context context) {
        if (context == null) {
            Log.d(TAG, "Cannot start download queue service, null context.");
        } else {
            if (DownloadQueueService.INSTANCE.isRunning()) {
                return;
            }
            Coroutines.INSTANCE.ioSafe(this, new C09581(context, null));
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$startQueueService$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadQueueManager.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadQueueManager;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$startQueueService$1", f = "DownloadQueueManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C09581 extends SuspendLambda implements Function3<CoroutineScope, DownloadQueueManager, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09581(Context context, Continuation<? super C09581> continuation) {
            super(3, continuation);
            this.$context = context;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, DownloadQueueManager downloadQueueManager, Continuation<? super Unit> continuation) {
            return new C09581(this.$context, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ContextCompat.startForegroundService(this.$context, DownloadQueueService.INSTANCE.getIntent(this.$context));
            return Unit.INSTANCE;
        }
    }

    public final void cancelDownload(int id) {
        Object next;
        Log.d(TAG, "Cancelling download: " + id);
        Iterator<T> it = DownloadQueueService.INSTANCE.getDownloadInstances().getValue().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (((VideoDownloadManager.EpisodeDownloadInstance) next).getDownloadQueueWrapper().getId() != id);
        VideoDownloadManager.EpisodeDownloadInstance episodeDownloadInstance = (VideoDownloadManager.EpisodeDownloadInstance) next;
        if (episodeDownloadInstance != null) {
            episodeDownloadInstance.cancelDownload();
        } else {
            removeFromQueue(id);
        }
    }

    public final void removeAllFromQueue() {
        for (DownloadObjects.DownloadQueueWrapper downloadQueueWrapper : removeAll()) {
            INSTANCE.setQueueStatus(downloadQueueWrapper.getId(), VideoDownloadManager.DownloadType.IsStopped);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$removeFromQueue$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadQueueManager.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadQueueManager;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$removeFromQueue$1", f = "DownloadQueueManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C09561 extends SuspendLambda implements Function3<CoroutineScope, DownloadQueueManager, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $id;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09561(int i, Continuation<? super C09561> continuation) {
            super(3, continuation);
            this.$id = i;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, DownloadQueueManager downloadQueueManager, Continuation<? super Unit> continuation) {
            return new C09561(this.$id, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                DownloadQueueManager.INSTANCE.remove(this.$id);
                DownloadQueueManager.INSTANCE.setQueueStatus(this.$id, VideoDownloadManager.DownloadType.IsStopped);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void removeFromQueue(int id) {
        Coroutines.INSTANCE.ioSafe(this, new C09561(id, null));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$reorderItem$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadQueueManager.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadQueueManager;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$reorderItem$1", f = "DownloadQueueManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C09571 extends SuspendLambda implements Function3<CoroutineScope, DownloadQueueManager, Continuation<? super Unit>, Object> {
        final /* synthetic */ DownloadObjects.DownloadQueueWrapper $downloadQueueWrapper;
        final /* synthetic */ int $newPosition;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09571(DownloadObjects.DownloadQueueWrapper downloadQueueWrapper, int i, Continuation<? super C09571> continuation) {
            super(3, continuation);
            this.$downloadQueueWrapper = downloadQueueWrapper;
            this.$newPosition = i;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, DownloadQueueManager downloadQueueManager, Continuation<? super Unit> continuation) {
            return new C09571(this.$downloadQueueWrapper, this.$newPosition, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                DownloadQueueManager.INSTANCE.reorder(this.$downloadQueueWrapper, this.$newPosition);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void reorderItem(DownloadObjects.DownloadQueueWrapper downloadQueueWrapper, int newPosition) {
        Intrinsics.checkNotNullParameter(downloadQueueWrapper, "downloadQueueWrapper");
        Coroutines.INSTANCE.ioSafe(this, new C09571(downloadQueueWrapper, newPosition, null));
    }

    public final Unit addToQueue(final DownloadObjects.DownloadQueueWrapper downloadQueueWrapper) {
        Intrinsics.checkNotNullParameter(downloadQueueWrapper, "downloadQueueWrapper");
        return (Unit) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DownloadQueueManager.addToQueue$lambda$0(downloadQueueWrapper);
            }
        });
    }

    static final Unit addToQueue$lambda$0(DownloadObjects.DownloadQueueWrapper downloadQueueWrapper) {
        Context context = CloudStreamApp.INSTANCE.getContext();
        if (context == null) {
            return Unit.INSTANCE;
        }
        DownloadObjects.DownloadedFileInfoResult downloadFileInfo = VideoDownloadManager.INSTANCE.getDownloadFileInfo(context, downloadQueueWrapper.getId());
        if (downloadFileInfo != null && downloadFileInfo.getTotalBytes() > 0 && downloadFileInfo.getFileLength() / downloadFileInfo.getTotalBytes() > 0.98f) {
            return Unit.INSTANCE;
        }
        DownloadQueueManager downloadQueueManager = INSTANCE;
        if (downloadQueueManager.add(downloadQueueWrapper)) {
            downloadQueueManager.setQueueStatus(downloadQueueWrapper.getId(), VideoDownloadManager.DownloadType.IsPending);
            downloadQueueManager.startQueueService(context);
        }
        return Unit.INSTANCE;
    }

    public final void forceRefreshQueue() {
        DownloadObjects.DownloadQueueWrapper[] value;
        Object[] objArrCopyOf;
        MutableStateFlow<DownloadObjects.DownloadQueueWrapper[]> mutableStateFlow = get_queue();
        do {
            value = mutableStateFlow.getValue();
            DownloadObjects.DownloadQueueWrapper[] downloadQueueWrapperArr = value;
            objArrCopyOf = Arrays.copyOf(downloadQueueWrapperArr, downloadQueueWrapperArr.length);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        } while (!mutableStateFlow.compareAndSet(value, (DownloadObjects.DownloadQueueWrapper[]) objArrCopyOf));
    }
}
