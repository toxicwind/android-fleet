package com.google.android.exoplayer.extractor;

import com.google.android.exoplayer.drm.DrmInitData;

/* JADX INFO: loaded from: classes.dex */
public interface ExtractorOutput {
    void drmInitData(DrmInitData drmInitData);

    void endTracks();

    void seekMap(SeekMap seekMap);

    TrackOutput track(int i);
}
