package com.lagradost.cloudstream3.ui.player;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.cloudstream3.utils.SubtitleUtils;
import com.lagradost.cloudstream3.utils.downloader.DownloadFileManagement;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DownloadFileGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u0010Jl\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\"\u0010\u0016\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0018\u0012\u0004\u0012\u00020\u001a0\u00172\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001a0\u00172\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u001fR\u0014\u0010\u0007\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/DownloadFileGenerator;", "Lcom/lagradost/cloudstream3/ui/player/VideoGenerator;", "Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;", "episodes", "", "<init>", "(Ljava/util/List;)V", "hasCache", "", "getHasCache", "()Z", "canSkipLoading", "getCanSkipLoading", "getId", "", "index", "(I)Ljava/lang/Integer;", "generateLinks", "clearCache", "sourceTypes", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "callback", "Lkotlin/Function1;", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "", "subtitleCallback", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", TypedValues.CycleType.S_WAVE_OFFSET, "isCasting", "(ZLjava/util/Set;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadFileGenerator extends VideoGenerator<ExtractorUri> {
    private final boolean canSkipLoading;
    private final boolean hasCache;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadFileGenerator(List<ExtractorUri> episodes) {
        super(episodes);
        Intrinsics.checkNotNullParameter(episodes, "episodes");
    }

    @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
    public boolean getHasCache() {
        return this.hasCache;
    }

    @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
    public boolean getCanSkipLoading() {
        return this.canSkipLoading;
    }

    @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
    public Integer getId(int index) {
        ExtractorUri extractorUri = (ExtractorUri) CollectionsKt.getOrNull(getVideos(), index);
        if (extractorUri != null) {
            return extractorUri.getId();
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0041  */
    @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
    public Object generateLinks(boolean z, Set<? extends ExtractorLinkType> set, Function1<? super Pair<? extends ExtractorLink, ExtractorUri>, Unit> function1, Function1<? super SubtitleData, Unit> function2, int i, boolean z2, Continuation<? super Boolean> continuation) {
        Object obj;
        String relativePath;
        String displayName;
        List<String> groupValues;
        String str;
        DownloadObjects.DownloadedFileInfoResult downloadFileInfo;
        ExtractorUri extractorUri = (ExtractorUri) CollectionsKt.getOrNull(getVideos(), i);
        if (extractorUri == null) {
            return Boxing.boxBoolean(false);
        }
        if (Intrinsics.areEqual(extractorUri.getUri(), Uri.EMPTY)) {
            Integer id = extractorUri.getId();
            if (id != null) {
                int iIntValue = id.intValue();
                Activity activity = CommonActivity.INSTANCE.getActivity();
                if (activity != null) {
                    downloadFileInfo = VideoDownloadManager.INSTANCE.getDownloadFileInfo(activity, iIntValue);
                } else {
                    downloadFileInfo = null;
                }
            } else {
                downloadFileInfo = null;
            }
            if (downloadFileInfo != null) {
                obj = null;
                function1.invoke(TuplesKt.to(null, ExtractorUri.copy$default(extractorUri, downloadFileInfo.getPath(), null, null, null, null, null, null, null, null, null, null, 2046, null)));
            } else {
                obj = null;
                function1.invoke(TuplesKt.to(null, extractorUri));
            }
        } else {
            obj = null;
            function1.invoke(TuplesKt.to(null, extractorUri));
        }
        Context context = CloudStreamApp.INSTANCE.getContext();
        if (context != null && (relativePath = extractorUri.getRelativePath()) != null && (displayName = extractorUri.getDisplayName()) != null) {
            String strCleanDisplayName = SubtitleUtils.INSTANCE.cleanDisplayName(displayName);
            List<Pair<String, Uri>> folder$app = DownloadFileManagement.INSTANCE.getFolder$app(context, relativePath, extractorUri.getBasePath());
            if (folder$app != null) {
                Iterator<T> it = folder$app.iterator();
                while (it.hasNext()) {
                    Pair pair = (Pair) it.next();
                    String str2 = (String) pair.component1();
                    Uri uri = (Uri) pair.component2();
                    if (SubtitleUtils.INSTANCE.isMatchingSubtitle(str2, displayName, strCleanDisplayName)) {
                        String strCleanDisplayName2 = SubtitleUtils.INSTANCE.cleanDisplayName(str2);
                        Regex regex = new Regex(" ([0-9]+)$");
                        MatchResult matchResultFind$default = Regex.find$default(regex, strCleanDisplayName2, 0, 2, obj);
                        String str3 = (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = groupValues.get(1)) == null) ? "" : str;
                        String string = StringsKt.trim((CharSequence) regex.replace(StringsKt.removePrefix(strCleanDisplayName2, (CharSequence) strCleanDisplayName), "")).toString();
                        String string2 = string;
                        if (StringsKt.isBlank(string2)) {
                            string2 = context.getString(R.string.default_subtitles);
                            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        }
                        String string3 = uri.toString();
                        Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                        function2.invoke(new SubtitleData(string2, str3, string3, SubtitleOrigin.DOWNLOADED_FILE, PlayerSubtitleHelper.INSTANCE.toSubtitleMimeType(str2), MapsKt.emptyMap(), SubtitleHelper.INSTANCE.fromLanguageToTagIETF(string, Boxing.boxBoolean(true))));
                    }
                }
            }
            return Boxing.boxBoolean(true);
        }
        return Boxing.boxBoolean(true);
    }
}
