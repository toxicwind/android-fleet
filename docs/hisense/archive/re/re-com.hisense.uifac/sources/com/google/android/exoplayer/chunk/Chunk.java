package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.upstream.Loader;
import com.google.android.exoplayer.util.Assertions;

/* JADX INFO: loaded from: classes.dex */
public abstract class Chunk implements Loader.Loadable {
    protected final DataSource dataSource;
    public final DataSpec dataSpec;
    public final Format format;
    public final int parentId;
    public final int trigger;
    public final int type;

    public abstract long bytesLoaded();

    public Chunk(DataSource dataSource, DataSpec dataSpec, int i, int i2, Format format, int i3) {
        Assertions.checkNotNull(dataSource);
        this.dataSource = dataSource;
        Assertions.checkNotNull(dataSpec);
        this.dataSpec = dataSpec;
        this.type = i;
        this.trigger = i2;
        this.format = format;
        this.parentId = i3;
    }
}
