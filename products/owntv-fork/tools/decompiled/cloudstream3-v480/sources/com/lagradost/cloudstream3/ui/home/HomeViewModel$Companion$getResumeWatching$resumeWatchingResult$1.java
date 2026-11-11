package com.lagradost.cloudstream3.ui.home;

import android.content.Context;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.DataStoreKt;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import java.util.ArrayList;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: HomeViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$ResumeWatchingResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1", f = "HomeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends DataStoreHelper.ResumeWatchingResult>>, Object> {
    final /* synthetic */ List<DownloadObjects.ResumeWatching> $resumeWatching;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1(List<DownloadObjects.ResumeWatching> list, Continuation<? super HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1> continuation) {
        super(2, continuation);
        this.$resumeWatching = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1(this.$resumeWatching, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends DataStoreHelper.ResumeWatchingResult>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<DataStoreHelper.ResumeWatchingResult>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<DataStoreHelper.ResumeWatchingResult>> continuation) {
        return ((HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x013a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:0x009b  */
    /* JADX WARN: Code duplicated, block: B:36:0x00b1 A[Catch: Exception -> 0x00c3, TRY_LEAVE, TryCatch #8 {Exception -> 0x00c3, blocks: (B:12:0x0046, B:15:0x0052, B:20:0x006a, B:27:0x0095, B:30:0x009c, B:36:0x00b1, B:35:0x00ac, B:26:0x008b, B:19:0x0060, B:23:0x0071, B:16:0x0054, B:32:0x00a0), top: B:95:0x0046, inners: #5, #9, #10 }] */
    /* JADX WARN: Code duplicated, block: B:61:0x0135  */
    /* JADX WARN: Code duplicated, block: B:68:0x014b A[Catch: Exception -> 0x015d, TRY_LEAVE, TryCatch #1 {Exception -> 0x015d, blocks: (B:44:0x00e0, B:47:0x00ec, B:52:0x0104, B:59:0x012f, B:62:0x0136, B:68:0x014b, B:67:0x0146, B:58:0x0125, B:51:0x00fa, B:55:0x010b, B:48:0x00ee, B:64:0x013a), top: B:87:0x00e0, inners: #2, #4, #7, #9 }] */
    /* JADX WARN: Code duplicated, block: B:99:0x00a0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objDecodeFromString;
        DataStoreHelper.ResumeWatchingResult resumeWatchingResult;
        Object objDecodeFromString2;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Object objM8936constructorimpl2;
        KSerializer kSerializer2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List<DownloadObjects.ResumeWatching> list = this.$resumeWatching;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (DownloadObjects.ResumeWatching resumeWatching : list) {
            CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
            String strValueOf = String.valueOf(resumeWatching.getParentId());
            Context context = companion.getContext();
            if (context != null) {
                DataStore dataStore = DataStore.INSTANCE;
                try {
                    String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(DataStoreKt.DOWNLOAD_HEADER_CACHE, strValueOf), null);
                    if (string == null) {
                        objDecodeFromString = null;
                    } else {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadHeaderCached"));
                        } catch (Throwable th) {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) == null) {
                            if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                                objM8936constructorimpl2 = null;
                            }
                            kSerializer2 = (KSerializer) objM8936constructorimpl2;
                            if (kSerializer2 != null) {
                                try {
                                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer2, string);
                                } catch (SerializationException e) {
                                    ArchComponentExtKt.logError(e);
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1$invokeSuspend$lambda$0$$inlined$getKey$1
                                    });
                                } catch (Throwable unused) {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1$invokeSuspend$lambda$0$$inlined$getKey$1
                                    });
                                }
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1$invokeSuspend$lambda$0$$inlined$getKey$1
                                });
                            }
                        } else {
                            try {
                                Result.Companion companion4 = Result.INSTANCE;
                                objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadHeaderCached.class), null, 2, null));
                            } catch (Throwable th2) {
                                Result.Companion companion5 = Result.INSTANCE;
                                objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                            }
                            if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                                objM8936constructorimpl2 = null;
                            }
                            kSerializer2 = (KSerializer) objM8936constructorimpl2;
                            if (kSerializer2 != null) {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer2, string);
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1$invokeSuspend$lambda$0$$inlined$getKey$1
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
            DownloadObjects.DownloadHeaderCached downloadHeaderCached = (DownloadObjects.DownloadHeaderCached) objDecodeFromString;
            if (downloadHeaderCached == null) {
                CloudStreamApp.Companion companion6 = CloudStreamApp.INSTANCE;
                String strValueOf2 = String.valueOf(resumeWatching.getParentId());
                Context context2 = companion6.getContext();
                if (context2 != null) {
                    DataStore dataStore2 = DataStore.INSTANCE;
                    try {
                        String string2 = dataStore2.getSharedPrefs(context2).getString(dataStore2.getFolderName(DataStoreKt.DOWNLOAD_HEADER_CACHE_BACKUP, strValueOf2), null);
                        if (string2 == null) {
                            objDecodeFromString2 = null;
                        } else {
                            AppUtils appUtils2 = AppUtils.INSTANCE;
                            try {
                                Result.Companion companion7 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadHeaderCached"));
                            } catch (Throwable th3) {
                                Result.Companion companion8 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                            }
                            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                    objM8936constructorimpl = null;
                                }
                                kSerializer = (KSerializer) objM8936constructorimpl;
                                if (kSerializer != null) {
                                    try {
                                        objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, string2);
                                    } catch (SerializationException e2) {
                                        ArchComponentExtKt.logError(e2);
                                        objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1$invokeSuspend$lambda$0$$inlined$getKey$2
                                        });
                                    } catch (Throwable unused3) {
                                        objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1$invokeSuspend$lambda$0$$inlined$getKey$2
                                        });
                                    }
                                } else {
                                    objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1$invokeSuspend$lambda$0$$inlined$getKey$2
                                    });
                                }
                            } else {
                                try {
                                    Result.Companion companion9 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadHeaderCached.class), null, 2, null));
                                } catch (Throwable th4) {
                                    Result.Companion companion10 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th4));
                                }
                                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                    objM8936constructorimpl = null;
                                }
                                kSerializer = (KSerializer) objM8936constructorimpl;
                                if (kSerializer != null) {
                                    objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, string2);
                                } else {
                                    objDecodeFromString2 = MainAPIKt.getMapper().readValue(string2, new TypeReference<DownloadObjects.DownloadHeaderCached>() { // from class: com.lagradost.cloudstream3.ui.home.HomeViewModel$Companion$getResumeWatching$resumeWatchingResult$1$invokeSuspend$lambda$0$$inlined$getKey$2
                                    });
                                }
                            }
                        }
                    } catch (Exception unused4) {
                    }
                } else {
                    objDecodeFromString2 = null;
                }
                downloadHeaderCached = (DownloadObjects.DownloadHeaderCached) objDecodeFromString2;
                if (downloadHeaderCached == null) {
                    resumeWatchingResult = null;
                } else {
                    CloudStreamApp.INSTANCE.setKey(DataStoreKt.DOWNLOAD_HEADER_CACHE, String.valueOf(resumeWatching.getParentId()), downloadHeaderCached);
                    resumeWatchingResult = new DataStoreHelper.ResumeWatchingResult(downloadHeaderCached.getName(), downloadHeaderCached.getUrl(), downloadHeaderCached.getApiName(), downloadHeaderCached.getType(), downloadHeaderCached.getPoster(), DataStoreHelper.INSTANCE.getViewPos(resumeWatching.getEpisodeId()), resumeWatching.getEpisodeId(), Boxing.boxInt(resumeWatching.getParentId()), resumeWatching.getEpisode(), resumeWatching.getSeason(), resumeWatching.isFromDownload(), null, null, null, 14336, null);
                }
            } else {
                resumeWatchingResult = new DataStoreHelper.ResumeWatchingResult(downloadHeaderCached.getName(), downloadHeaderCached.getUrl(), downloadHeaderCached.getApiName(), downloadHeaderCached.getType(), downloadHeaderCached.getPoster(), DataStoreHelper.INSTANCE.getViewPos(resumeWatching.getEpisodeId()), resumeWatching.getEpisodeId(), Boxing.boxInt(resumeWatching.getParentId()), resumeWatching.getEpisode(), resumeWatching.getSeason(), resumeWatching.isFromDownload(), null, null, null, 14336, null);
            }
            if (resumeWatchingResult != null) {
                arrayList.add(resumeWatchingResult);
            }
        }
        return arrayList;
    }
}
