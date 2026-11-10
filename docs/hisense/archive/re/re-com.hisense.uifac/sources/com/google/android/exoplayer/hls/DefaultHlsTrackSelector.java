package com.google.android.exoplayer.hls;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.exoplayer.chunk.VideoFormatSelectorUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultHlsTrackSelector implements HlsTrackSelector {
    private final Context context;
    private final int type;

    public static DefaultHlsTrackSelector newDefaultInstance(Context context) {
        return new DefaultHlsTrackSelector(context, 0);
    }

    public static DefaultHlsTrackSelector newAudioInstance() {
        return new DefaultHlsTrackSelector(null, 1);
    }

    public static DefaultHlsTrackSelector newSubtitleInstance() {
        return new DefaultHlsTrackSelector(null, 2);
    }

    private DefaultHlsTrackSelector(Context context, int i) {
        this.context = context;
        this.type = i;
    }

    @Override // com.google.android.exoplayer.hls.HlsTrackSelector
    public void selectTracks(HlsMasterPlaylist hlsMasterPlaylist, HlsTrackSelector.Output output) throws IOException {
        int i = this.type;
        int i2 = 0;
        if (i == 1 || i == 2) {
            List<Variant> list = this.type == 1 ? hlsMasterPlaylist.audios : hlsMasterPlaylist.subtitles;
            if (list == null || list.isEmpty()) {
                return;
            }
            while (i2 < list.size()) {
                output.fixedTrack(hlsMasterPlaylist, list.get(i2));
                i2++;
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 : VideoFormatSelectorUtil.selectVideoFormatsForDefaultDisplay(this.context, hlsMasterPlaylist.variants, null, false)) {
            arrayList.add(hlsMasterPlaylist.variants.get(i3));
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            Variant variant = (Variant) arrayList.get(i4);
            if (variant.format.height > 0 || variantHasExplicitCodecWithPrefix(variant, "avc")) {
                arrayList2.add(variant);
            } else if (variantHasExplicitCodecWithPrefix(variant, "mp4a")) {
                arrayList3.add(variant);
            }
        }
        if (!arrayList2.isEmpty()) {
            arrayList = arrayList2;
        } else if (arrayList3.size() < arrayList.size()) {
            arrayList.removeAll(arrayList3);
        }
        if (arrayList.size() > 1) {
            Variant[] variantArr = new Variant[arrayList.size()];
            arrayList.toArray(variantArr);
            output.adaptiveTrack(hlsMasterPlaylist, variantArr);
        }
        while (i2 < arrayList.size()) {
            output.fixedTrack(hlsMasterPlaylist, (Variant) arrayList.get(i2));
            i2++;
        }
    }

    private static boolean variantHasExplicitCodecWithPrefix(Variant variant, String str) {
        String str2 = variant.format.codecs;
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        for (String str3 : str2.split("(\\s*,\\s*)|(\\s*$)")) {
            if (str3.startsWith(str)) {
                return true;
            }
        }
        return false;
    }
}
