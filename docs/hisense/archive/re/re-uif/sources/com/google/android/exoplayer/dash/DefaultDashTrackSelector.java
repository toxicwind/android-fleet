package com.google.android.exoplayer.dash;

import android.content.Context;
import com.google.android.exoplayer.chunk.VideoFormatSelectorUtil;
import com.google.android.exoplayer.dash.mpd.AdaptationSet;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;
import com.google.android.exoplayer.dash.mpd.Period;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultDashTrackSelector implements DashTrackSelector {
    private final int adaptationSetType;
    private final Context context;
    private final boolean filterProtectedHdContent;
    private final boolean filterVideoRepresentations;

    public static DefaultDashTrackSelector newVideoInstance(Context context, boolean z, boolean z2) {
        return new DefaultDashTrackSelector(0, context, z, z2);
    }

    public static DefaultDashTrackSelector newAudioInstance() {
        return new DefaultDashTrackSelector(1, null, false, false);
    }

    public static DefaultDashTrackSelector newTextInstance() {
        return new DefaultDashTrackSelector(2, null, false, false);
    }

    private DefaultDashTrackSelector(int i, Context context, boolean z, boolean z2) {
        this.adaptationSetType = i;
        this.context = context;
        this.filterVideoRepresentations = z;
        this.filterProtectedHdContent = z2;
    }

    @Override // com.google.android.exoplayer.dash.DashTrackSelector
    public void selectTracks(MediaPresentationDescription mediaPresentationDescription, int i, DashTrackSelector.Output output) throws IOException {
        int[] iArrFirstIntegersArray;
        Period period = mediaPresentationDescription.getPeriod(i);
        for (int i2 = 0; i2 < period.adaptationSets.size(); i2++) {
            AdaptationSet adaptationSet = period.adaptationSets.get(i2);
            int i3 = adaptationSet.type;
            int i4 = this.adaptationSetType;
            if (i3 == i4) {
                if (i4 == 0) {
                    if (this.filterVideoRepresentations) {
                        iArrFirstIntegersArray = VideoFormatSelectorUtil.selectVideoFormatsForDefaultDisplay(this.context, adaptationSet.representations, null, this.filterProtectedHdContent && adaptationSet.hasContentProtection());
                    } else {
                        iArrFirstIntegersArray = Util.firstIntegersArray(adaptationSet.representations.size());
                    }
                    if (iArrFirstIntegersArray.length > 1) {
                        output.adaptiveTrack(mediaPresentationDescription, i, i2, iArrFirstIntegersArray);
                    }
                    for (int i5 : iArrFirstIntegersArray) {
                        output.fixedTrack(mediaPresentationDescription, i, i2, i5);
                    }
                } else if (i4 == 1) {
                    int[] iArrFirstIntegersArray2 = Util.firstIntegersArray(adaptationSet.representations.size());
                    if (iArrFirstIntegersArray2.length > 1) {
                        output.adaptiveTrack(mediaPresentationDescription, i, i2, iArrFirstIntegersArray2);
                    }
                    for (int i6 = 0; i6 < adaptationSet.representations.size(); i6++) {
                        output.fixedTrack(mediaPresentationDescription, i, i2, i6);
                    }
                } else {
                    for (int i7 = 0; i7 < adaptationSet.representations.size(); i7++) {
                        output.fixedTrack(mediaPresentationDescription, i, i2, i7);
                    }
                }
            }
        }
    }
}
