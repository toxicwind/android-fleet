package com.lagradost.cloudstream3.ui.download;

import android.content.Context;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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

/* JADX INFO: compiled from: DownloadViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/ui/download/VisualDownloadCached$Child;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateChildList$1$visual$1", f = "DownloadViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class DownloadViewModel$updateChildList$1$visual$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends VisualDownloadCached.Child>>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ String $folder;
    int label;
    final /* synthetic */ DownloadViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DownloadViewModel$updateChildList$1$visual$1(Context context, String str, DownloadViewModel downloadViewModel, Continuation<? super DownloadViewModel$updateChildList$1$visual$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$folder = str;
        this.this$0 = downloadViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DownloadViewModel$updateChildList$1$visual$1(this.$context, this.$folder, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends VisualDownloadCached.Child>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<VisualDownloadCached.Child>>) continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<VisualDownloadCached.Child>> continuation) {
        return ((DownloadViewModel$updateChildList$1$visual$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List<String> keys = DataStore.INSTANCE.getKeys(this.$context, this.$folder);
        Context context = this.$context;
        ArrayList<DownloadObjects.DownloadEpisodeCached> arrayList = new ArrayList();
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
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadEpisodeCached"));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadEpisodeCached.class), null, 2, null));
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateChildList$1$visual$1$invokeSuspend$lambda$0$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateChildList$1$visual$1$invokeSuspend$lambda$0$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadEpisodeCached>() { // from class: com.lagradost.cloudstream3.ui.download.DownloadViewModel$updateChildList$1$visual$1$invokeSuspend$lambda$0$$inlined$getKey$1
                        });
                    }
                    obj2 = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
            DownloadObjects.DownloadEpisodeCached downloadEpisodeCached = (DownloadObjects.DownloadEpisodeCached) obj2;
            if (downloadEpisodeCached != null) {
                arrayList.add(downloadEpisodeCached);
            }
        }
        DownloadViewModel downloadViewModel = this.this$0;
        Context context2 = this.$context;
        ArrayList arrayList2 = new ArrayList();
        for (DownloadObjects.DownloadEpisodeCached downloadEpisodeCached2 : arrayList) {
            Set<Integer> value = downloadViewModel.getSelectedItemIds().getValue();
            boolean zContains = value != null ? value.contains(Boxing.boxInt(downloadEpisodeCached2.getId())) : false;
            DownloadObjects.DownloadedFileInfoResult downloadFileInfo = VideoDownloadManager.INSTANCE.getDownloadFileInfo(context2, downloadEpisodeCached2.getId());
            VisualDownloadCached.Child child = downloadFileInfo == null ? null : new VisualDownloadCached.Child(downloadFileInfo.getFileLength(), downloadFileInfo.getTotalBytes(), downloadEpisodeCached2, zContains);
            if (child != null) {
                arrayList2.add(child);
            }
        }
        return arrayList2;
    }
}
