package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.util.Util;

/* JADX INFO: loaded from: classes.dex */
public final class Track {
    public final long durationUs;
    public final long[] editListDurations;
    public final long[] editListMediaTimes;
    public final int id;
    public final MediaFormat mediaFormat;
    public final long movieTimescale;
    public final int nalUnitLengthFieldLength;
    public final TrackEncryptionBox[] sampleDescriptionEncryptionBoxes;
    public final long timescale;
    public final int type;
    public static final int TYPE_vide = Util.getIntegerCodeForString("vide");
    public static final int TYPE_soun = Util.getIntegerCodeForString("soun");
    public static final int TYPE_text = Util.getIntegerCodeForString("text");
    public static final int TYPE_sbtl = Util.getIntegerCodeForString("sbtl");
    public static final int TYPE_subt = Util.getIntegerCodeForString("subt");
    public static final int TYPE_meta = Util.getIntegerCodeForString("meta");

    public Track(int i, int i2, long j, long j2, long j3, MediaFormat mediaFormat, TrackEncryptionBox[] trackEncryptionBoxArr, int i3, long[] jArr, long[] jArr2) {
        this.id = i;
        this.type = i2;
        this.timescale = j;
        this.movieTimescale = j2;
        this.durationUs = j3;
        this.mediaFormat = mediaFormat;
        this.sampleDescriptionEncryptionBoxes = trackEncryptionBoxArr;
        this.nalUnitLengthFieldLength = i3;
        this.editListDurations = jArr;
        this.editListMediaTimes = jArr2;
    }
}
