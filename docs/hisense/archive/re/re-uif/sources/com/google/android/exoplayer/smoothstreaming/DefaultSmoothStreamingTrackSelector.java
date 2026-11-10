package com.google.android.exoplayer.smoothstreaming;

import android.content.Context;
import com.google.android.exoplayer.chunk.VideoFormatSelectorUtil;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultSmoothStreamingTrackSelector implements SmoothStreamingTrackSelector {
    private final Context context;
    private final boolean filterProtectedHdContent;
    private final boolean filterVideoRepresentations;
    private final int streamElementType;

    public static DefaultSmoothStreamingTrackSelector newVideoInstance(Context context, boolean z, boolean z2) {
        return new DefaultSmoothStreamingTrackSelector(1, context, z, z2);
    }

    public static DefaultSmoothStreamingTrackSelector newAudioInstance() {
        return new DefaultSmoothStreamingTrackSelector(0, null, false, false);
    }

    public static DefaultSmoothStreamingTrackSelector newTextInstance() {
        return new DefaultSmoothStreamingTrackSelector(2, null, false, false);
    }

    private DefaultSmoothStreamingTrackSelector(int i, Context context, boolean z, boolean z2) {
        this.context = context;
        this.streamElementType = i;
        this.filterVideoRepresentations = z;
        this.filterProtectedHdContent = z2;
    }

    @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingTrackSelector
    public void selectTracks(SmoothStreamingManifest smoothStreamingManifest, SmoothStreamingTrackSelector.Output output) throws IOException {
        int[] iArrFirstIntegersArray;
        int i = 0;
        while (true) {
            SmoothStreamingManifest.StreamElement[] streamElementArr = smoothStreamingManifest.streamElements;
            if (i >= streamElementArr.length) {
                return;
            }
            SmoothStreamingManifest.TrackElement[] trackElementArr = streamElementArr[i].tracks;
            int i2 = streamElementArr[i].type;
            int i3 = this.streamElementType;
            if (i2 == i3) {
                if (i3 == 1) {
                    if (this.filterVideoRepresentations) {
                        iArrFirstIntegersArray = VideoFormatSelectorUtil.selectVideoFormatsForDefaultDisplay(this.context, Arrays.asList(trackElementArr), null, this.filterProtectedHdContent && smoothStreamingManifest.protectionElement != null);
                    } else {
                        iArrFirstIntegersArray = Util.firstIntegersArray(trackElementArr.length);
                    }
                    if (iArrFirstIntegersArray.length > 1) {
                        output.adaptiveTrack(smoothStreamingManifest, i, iArrFirstIntegersArray);
                    }
                    for (int i4 : iArrFirstIntegersArray) {
                        output.fixedTrack(smoothStreamingManifest, i, i4);
                    }
                } else {
                    for (int i5 = 0; i5 < trackElementArr.length; i5++) {
                        output.fixedTrack(smoothStreamingManifest, i, i5);
                    }
                }
            }
            i++;
        }
    }
}
