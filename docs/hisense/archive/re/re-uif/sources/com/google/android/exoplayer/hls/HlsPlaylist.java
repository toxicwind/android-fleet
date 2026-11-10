package com.google.android.exoplayer.hls;

/* JADX INFO: loaded from: classes.dex */
public abstract class HlsPlaylist {
    public final String baseUri;
    public final int type;

    protected HlsPlaylist(String str, int i) {
        this.baseUri = str;
        this.type = i;
    }
}
