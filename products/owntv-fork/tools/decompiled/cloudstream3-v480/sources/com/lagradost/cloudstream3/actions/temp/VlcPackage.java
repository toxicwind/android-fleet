package com.lagradost.cloudstream3.actions.temp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lagradost.api.Log;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.actions.OpenInAppAction;
import com.lagradost.cloudstream3.actions.OpenInAppActionKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.ui.result.LinkLoadingResult;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragmentKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UiText;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: VlcPackage.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J8\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096@¢\u0006\u0002\u0010\u0014J\u001a\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/VlcPackage;", "Lcom/lagradost/cloudstream3/actions/OpenInAppAction;", "<init>", "()V", "oneSource", "", "getOneSource", "()Z", "putExtra", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "video", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "result", "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;", "index", "", "(Landroid/content/Context;Landroid/content/Intent;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onResult", "activity", "Landroid/app/Activity;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class VlcPackage extends OpenInAppAction {
    private final boolean oneSource;

    @Override // com.lagradost.cloudstream3.actions.OpenInAppAction
    public Object putExtra(Context context, Intent intent, ResultEpisode resultEpisode, LinkLoadingResult linkLoadingResult, Integer num, Continuation<? super Unit> continuation) {
        return putExtra$suspendImpl(this, context, intent, resultEpisode, linkLoadingResult, num, continuation);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public VlcPackage() {
        String str;
        UiText uiTextTxt = TextUtilKt.txt("VLC");
        String str2 = Build.VERSION.SDK_INT < 33 ? "org.videolan.vlc.gui.video.VideoPlayerActivity" : null;
        if (Build.VERSION.SDK_INT < 33) {
            str = "org.videolan.vlc.player.result";
        } else {
            str = "android.intent.action.VIEW";
        }
        super(uiTextTxt, "org.videolan.vlc", str2, str);
        this.oneSource = true;
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public boolean getOneSource() {
        return this.oneSource;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:36:0x00cd A[Catch: Exception -> 0x00df, TRY_LEAVE, TryCatch #0 {Exception -> 0x00df, blocks: (B:12:0x0063, B:15:0x006f, B:20:0x0085, B:27:0x00b1, B:30:0x00b8, B:36:0x00cd, B:35:0x00c8, B:26:0x00a7, B:19:0x007b, B:23:0x008c, B:16:0x0071, B:32:0x00bc), top: B:55:0x0063, inners: #1, #2, #3, #5 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x00bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static /* synthetic */ Object putExtra$suspendImpl(VlcPackage vlcPackage, Context context, Intent intent, ResultEpisode resultEpisode, LinkLoadingResult linkLoadingResult, Integer num, Continuation<? super Unit> continuation) throws IOException {
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        if (num != null) {
            Intrinsics.checkNotNull(intent.setDataAndType(Uri.parse(linkLoadingResult.getLinks().get(num.intValue()).getUrl()), "video/*"));
        } else {
            OpenInAppActionKt.makeTempM3U8Intent(context, intent, linkLoadingResult);
        }
        DataStoreHelper.PosDur viewPos = DataStoreHelper.INSTANCE.getViewPos(Boxing.boxInt(resultEpisode.getId()));
        long position = viewPos != null ? viewPos.getPosition() : 0L;
        intent.putExtra("from_start", false);
        intent.putExtra("position", position);
        intent.putExtra("secure_uri", true);
        intent.putExtra("title", resultEpisode.getName());
        Context context2 = CloudStreamApp.INSTANCE.getContext();
        Object obj = null;
        if (context2 != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context2).getString(SubtitlesFragmentKt.SUBTITLE_AUTO_SELECT_KEY, null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(StringSerializer.INSTANCE);
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
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.actions.temp.VlcPackage$putExtra$suspendImpl$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.actions.temp.VlcPackage$putExtra$suspendImpl$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.actions.temp.VlcPackage$putExtra$suspendImpl$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(String.class), null, 2, null));
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.actions.temp.VlcPackage$putExtra$suspendImpl$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
            }
        } else {
            objDecodeFromString = null;
        }
        String str = (String) objDecodeFromString;
        if (str == null) {
            str = "en";
        }
        for (Object obj2 : linkLoadingResult.getSubs()) {
            if (Intrinsics.areEqual(str, ((SubtitleData) obj2).getLanguageCode())) {
                obj = obj2;
                break;
            }
        }
        SubtitleData subtitleData = (SubtitleData) obj;
        if (subtitleData != null) {
            intent.putExtra("subtitles_location", subtitleData.getUrl());
        }
        return Unit.INSTANCE;
    }

    @Override // com.lagradost.cloudstream3.actions.OpenInAppAction
    public void onResult(Activity activity, Intent intent) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        long longExtra = intent != null ? intent.getLongExtra("extra_position", -1L) : -1L;
        long longExtra2 = intent != null ? intent.getLongExtra("extra_duration", -1L) : -1L;
        Log.INSTANCE.d("VLC", "Position: " + longExtra + ", Duration: " + longExtra2);
        OpenInAppActionKt.updateDurationAndPosition(longExtra, longExtra2);
    }
}
