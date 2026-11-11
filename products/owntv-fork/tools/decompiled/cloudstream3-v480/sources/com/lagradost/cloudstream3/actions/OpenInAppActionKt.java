package com.lagradost.cloudstream3.actions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.FileProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.result.LinkLoadingResult;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.ui.result.ResultFragment;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.io.File;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: OpenInAppAction.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003\u001a\u001e\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"updateDurationAndPosition", "", "position", "", TypedValues.TransitionType.S_DURATION, "makeTempM3U8Intent", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "result", "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class OpenInAppActionKt {
    /* JADX WARN: Code duplicated, block: B:27:0x0071  */
    /* JADX WARN: Code duplicated, block: B:34:0x0087 A[Catch: Exception -> 0x0099, TRY_LEAVE, TryCatch #4 {Exception -> 0x0099, blocks: (B:10:0x0019, B:13:0x0025, B:18:0x003f, B:25:0x006b, B:28:0x0072, B:34:0x0087, B:33:0x0082, B:24:0x0061, B:17:0x0035, B:21:0x0046, B:14:0x0027, B:30:0x0076), top: B:48:0x0019, inners: #0, #2, #5 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static final void updateDurationAndPosition(long j, long j2) {
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        if (j <= 0 || j2 <= 0) {
            return;
        }
        Context context = CloudStreamApp.INSTANCE.getContext();
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString("last_opened", null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.ui.result.ResultEpisode"));
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
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<ResultEpisode>() { // from class: com.lagradost.cloudstream3.actions.OpenInAppActionKt$updateDurationAndPosition$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<ResultEpisode>() { // from class: com.lagradost.cloudstream3.actions.OpenInAppActionKt$updateDurationAndPosition$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<ResultEpisode>() { // from class: com.lagradost.cloudstream3.actions.OpenInAppActionKt$updateDurationAndPosition$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(ResultEpisode.class), null, 2, null));
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<ResultEpisode>() { // from class: com.lagradost.cloudstream3.actions.OpenInAppActionKt$updateDurationAndPosition$$inlined$getKey$1
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
        ResultEpisode resultEpisode = (ResultEpisode) objDecodeFromString;
        if (resultEpisode == null) {
            return;
        }
        DataStoreHelper.INSTANCE.setViewPosAndResume(Integer.valueOf(resultEpisode.getId()), j, j2, resultEpisode, null);
        ResultFragment.updateUI$default(ResultFragment.INSTANCE, null, 1, null);
    }

    public static final void makeTempM3U8Intent(Context context, Intent intent, LinkLoadingResult result) throws IOException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getLinks().size() == 1) {
            intent.setDataAndType(Uri.parse(((ExtractorLink) CollectionsKt.first((List) result.getLinks())).getUrl()), "video/*");
            return;
        }
        intent.addFlags(64);
        intent.addFlags(128);
        intent.addFlags(1);
        intent.addFlags(2);
        File fileCreateTempFile = File.createTempFile("mirrorlist", ".m3u8", context.getCacheDir());
        String str = "#EXTM3U\n#EXT-X-VERSION:3";
        for (ExtractorLink extractorLink : result.getLinks()) {
            str = str + "\n#EXTINF:0," + extractorLink.getName() + '\n' + extractorLink.getUrl();
        }
        Intrinsics.checkNotNull(fileCreateTempFile);
        FilesKt.writeText$default(fileCreateTempFile, str + "\n#EXT-X-ENDLIST", null, 2, null);
        intent.setDataAndType(FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", fileCreateTempFile), "application/x-mpegURL");
    }
}
