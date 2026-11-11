package com.lagradost.cloudstream3.ui.download;

import android.content.Context;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.services.DownloadQueueService;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreKt;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: DownloadViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached$Header;", "Lkotlinx/coroutines/CoroutineScope;", "it"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateHeaderList$1$visual$1", f = "DownloadViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class DownloadViewModel$updateHeaderList$1$visual$1 extends SuspendLambda implements Function3<CoroutineScope, CoroutineScope, Continuation<? super List<? extends VisualDownloadCached.Header>>, Object> {
    final /* synthetic */ Context $context;
    int label;
    final /* synthetic */ DownloadViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DownloadViewModel$updateHeaderList$1$visual$1(Context context, DownloadViewModel downloadViewModel, Continuation<? super DownloadViewModel$updateHeaderList$1$visual$1> continuation) {
        super(3, continuation);
        this.$context = context;
        this.this$0 = downloadViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, CoroutineScope coroutineScope2, Continuation<? super List<? extends VisualDownloadCached.Header>> continuation) {
        return invoke2(coroutineScope, coroutineScope2, (Continuation<? super List<VisualDownloadCached.Header>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, CoroutineScope coroutineScope2, Continuation<? super List<VisualDownloadCached.Header>> continuation) {
        return new DownloadViewModel$updateHeaderList$1$visual$1(this.$context, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:113:0x01ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:75:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:82:0x01be A[Catch: Exception -> 0x01d0, TRY_LEAVE, TryCatch #0 {Exception -> 0x01d0, blocks: (B:58:0x0151, B:61:0x015d, B:66:0x0177, B:73:0x01a2, B:76:0x01a9, B:82:0x01be, B:81:0x01b9, B:72:0x0198, B:65:0x016d, B:69:0x017e, B:78:0x01ad, B:62:0x015f), top: B:99:0x0151, inners: #3, #4, #6, #9 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Object objM8936constructorimpl2;
        Object objDecodeFromString2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List<String> keys = DataStore.INSTANCE.getKeys(this.$context, DataStoreKt.DOWNLOAD_EPISODE_CACHE);
        Context context = this.$context;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = keys.iterator();
        while (true) {
            Object obj2 = null;
            if (!it.hasNext()) {
                break;
            }
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString((String) it.next(), null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadEpisodeCached"));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) != null) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadEpisodeCached.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                    }
                    if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                        objM8936constructorimpl2 = null;
                    }
                    KSerializer kSerializer2 = (KSerializer) objM8936constructorimpl2;
                    if (kSerializer2 != null) {
                        try {
                            objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer2, string);
                        } catch (SerializationException e) {
                            ArchComponentExtKt.logError(e);
                            objDecodeFromString2 = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateHeaderList$1$visual$1$invokeSuspend$lambda$0$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString2 = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateHeaderList$1$visual$1$invokeSuspend$lambda$0$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString2 = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateHeaderList$1$visual$1$invokeSuspend$lambda$0$$inlined$getKey$1
                        });
                    }
                    obj2 = objDecodeFromString2;
                }
            } catch (Exception unused2) {
            }
            DownloadObjects.DownloadEpisodeCached downloadEpisodeCached = (DownloadObjects.DownloadEpisodeCached) obj2;
            if (downloadEpisodeCached != null) {
                arrayList.add(downloadEpisodeCached);
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj3 : arrayList) {
            if (hashSet.add(Boxing.boxInt(((DownloadObjects.DownloadEpisodeCached) obj3).getId()))) {
                arrayList2.add(obj3);
            }
        }
        ArrayList arrayList3 = arrayList2;
        boolean z = true;
        if (!DownloadQueueService.INSTANCE.isRunning() && DownloadQueueService.INSTANCE.getDownloadInstances().getValue().isEmpty()) {
            if (DownloadQueueManager.INSTANCE.getQueue().getValue().length == 0) {
                z = false;
            }
        }
        DownloadViewModel.DownloadStats downloadStatsCalculateDownloadStats = this.this$0.calculateDownloadStats(this.$context, arrayList3);
        List<String> keys2 = DataStore.INSTANCE.getKeys(this.$context, DataStoreKt.DOWNLOAD_HEADER_CACHE);
        Context context2 = this.$context;
        ArrayList arrayList4 = new ArrayList();
        Iterator<T> it2 = keys2.iterator();
        while (it2.hasNext()) {
            try {
                String string2 = DataStore.INSTANCE.getSharedPrefs(context2).getString((String) it2.next(), null);
                if (string2 == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils2 = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion5 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadHeaderCached"));
                    } catch (Throwable th3) {
                        Result.Companion companion6 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string2);
                            } catch (SerializationException e2) {
                                ArchComponentExtKt.logError(e2);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateHeaderList$1$visual$1$invokeSuspend$lambda$2$$inlined$getKey$1
                                });
                            } catch (Throwable unused3) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateHeaderList$1$visual$1$invokeSuspend$lambda$2$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateHeaderList$1$visual$1$invokeSuspend$lambda$2$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion7 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadHeaderCached.class), null, 2, null));
                        } catch (Throwable th4) {
                            Result.Companion companion8 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th4));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string2);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateHeaderList$1$visual$1$invokeSuspend$lambda$2$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused4) {
            }
            DownloadObjects.DownloadHeaderCached downloadHeaderCached = (DownloadObjects.DownloadHeaderCached) objDecodeFromString;
            if (downloadHeaderCached != null) {
                arrayList4.add(downloadHeaderCached);
            }
        }
        ArrayList arrayList5 = arrayList4;
        if (!z) {
            this.this$0.removeRedundantHeaderKeys(this.$context, arrayList5, downloadStatsCalculateDownloadStats.getTotalBytesUsedByChild(), downloadStatsCalculateDownloadStats.getTotalDownloads());
        }
        this.this$0.removeRedundantEpisodeKeys(this.$context, downloadStatsCalculateDownloadStats.getRedundantDownloads());
        return this.this$0.createVisualDownloadList(this.$context, arrayList5, downloadStatsCalculateDownloadStats.getTotalBytesUsedByChild(), downloadStatsCalculateDownloadStats.getCurrentBytesUsedByChild(), downloadStatsCalculateDownloadStats.getTotalDownloads());
    }
}
