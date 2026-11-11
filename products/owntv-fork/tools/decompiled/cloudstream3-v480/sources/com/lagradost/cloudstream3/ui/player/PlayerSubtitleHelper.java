package com.lagradost.cloudstream3.ui.player;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.media3.ui.SubtitleView;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.ui.subtitles.SaveCaptionStyle;
import com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PlayerSubtitleHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0014\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0014\u0010\f\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0010\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J$\u0010\u0019\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/PlayerSubtitleHelper;", "", "<init>", "()V", "activeSubtitles", "", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "allSubtitles", "getAllSubtitles", "setActiveSubtitles", "", "list", "setAllSubtitles", "subtitleView", "Landroidx/media3/ui/SubtitleView;", "getSubtitleView", "()Landroidx/media3/ui/SubtitleView;", "setSubtitleView", "(Landroidx/media3/ui/SubtitleView;)V", "subtitleStatus", "Lcom/lagradost/cloudstream3/ui/player/SubtitleStatus;", "sub", "setSubStyle", "style", "Lcom/lagradost/cloudstream3/ui/subtitles/SaveCaptionStyle;", "initSubtitles", "subView", "subHolder", "Landroid/widget/FrameLayout;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PlayerSubtitleHelper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Set<SubtitleData> activeSubtitles = SetsKt.emptySet();
    private Set<SubtitleData> allSubtitles = SetsKt.emptySet();
    private SubtitleView subtitleView;

    public final Set<SubtitleData> getAllSubtitles() {
        return this.allSubtitles;
    }

    public final void setActiveSubtitles(Set<SubtitleData> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.activeSubtitles = list;
    }

    public final void setAllSubtitles(Set<SubtitleData> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.allSubtitles = list;
    }

    public final SubtitleView getSubtitleView() {
        return this.subtitleView;
    }

    public final void setSubtitleView(SubtitleView subtitleView) {
        this.subtitleView = subtitleView;
    }

    /* JADX INFO: compiled from: PlayerSubtitleHelper.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/PlayerSubtitleHelper$Companion;", "", "<init>", "()V", "toSubtitleMimeType", "", "getSubtitleData", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "subtitleFile", "Lcom/lagradost/cloudstream3/SubtitleFile;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String toSubtitleMimeType(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            if (StringsKt.endsWith(str, "vtt", true)) {
                return "text/vtt";
            }
            if (StringsKt.endsWith(str, "srt", true)) {
                return "application/x-subrip";
            }
            return (StringsKt.endsWith(str, "xml", true) || StringsKt.endsWith(str, "ttml", true)) ? "application/ttml+xml" : "application/x-subrip";
        }

        public final SubtitleData getSubtitleData(SubtitleFile subtitleFile) {
            Intrinsics.checkNotNullParameter(subtitleFile, "subtitleFile");
            String lang = subtitleFile.getLang();
            String url = subtitleFile.getUrl();
            SubtitleOrigin subtitleOrigin = SubtitleOrigin.URL;
            String subtitleMimeType = toSubtitleMimeType(subtitleFile.getUrl());
            Map<String, String> headers = subtitleFile.getHeaders();
            if (headers == null) {
                headers = MapsKt.emptyMap();
            }
            Map<String, String> map = headers;
            String langTag = subtitleFile.getLangTag();
            if (langTag == null) {
                langTag = subtitleFile.getLang();
            }
            return new SubtitleData(lang, "", url, subtitleOrigin, subtitleMimeType, map, langTag);
        }
    }

    public final SubtitleStatus subtitleStatus(SubtitleData sub) {
        if (CollectionsKt.contains(this.activeSubtitles, sub)) {
            return SubtitleStatus.IS_ACTIVE;
        }
        if (CollectionsKt.contains(this.allSubtitles, sub)) {
            return SubtitleStatus.REQUIRES_RELOAD;
        }
        return SubtitleStatus.NOT_FOUND;
    }

    public final void setSubStyle(SaveCaptionStyle style) {
        Intrinsics.checkNotNullParameter(style, "style");
        Log.i(CS3IPlayerKt.TAG, "SET STYLE = " + style);
        SubtitleView subtitleView = this.subtitleView;
        if (subtitleView != null) {
            subtitleView.setTranslationY(-UIHelper.INSTANCE.getToPx(style.getElevation()));
        }
        SubtitlesFragment.INSTANCE.setSubtitleViewStyle(this.subtitleView, style, true);
    }

    public final void initSubtitles(SubtitleView subView, FrameLayout subHolder, SaveCaptionStyle style) {
        this.subtitleView = subView;
        if (subView != null) {
            ViewGroup viewGroup = (ViewGroup) subView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(subView);
            }
            if (subHolder != null) {
                subHolder.addView(subView);
            }
        }
        if (style != null) {
            setSubStyle(style);
        }
    }
}
