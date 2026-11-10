package com.google.android.exoplayer.upstream;

import android.content.Context;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultUriDataSource implements UriDataSource {
    private final UriDataSource assetDataSource;
    private final UriDataSource contentDataSource;
    private UriDataSource dataSource;
    private final UriDataSource fileDataSource;
    private final UriDataSource httpDataSource;

    public DefaultUriDataSource(Context context, String str) {
        this(context, null, str, false);
    }

    public DefaultUriDataSource(Context context, TransferListener transferListener, String str) {
        this(context, transferListener, str, false);
    }

    public DefaultUriDataSource(Context context, TransferListener transferListener, String str, boolean z) {
        this(context, transferListener, new DefaultHttpDataSource(str, null, transferListener, 8000, 8000, z));
    }

    public DefaultUriDataSource(Context context, TransferListener transferListener, UriDataSource uriDataSource) {
        Assertions.checkNotNull(uriDataSource);
        this.httpDataSource = uriDataSource;
        this.fileDataSource = new FileDataSource(transferListener);
        this.assetDataSource = new AssetDataSource(context, transferListener);
        this.contentDataSource = new ContentDataSource(context, transferListener);
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        Assertions.checkState(this.dataSource == null);
        String scheme = dataSpec.uri.getScheme();
        if (Util.isLocalFileUri(dataSpec.uri)) {
            if (dataSpec.uri.getPath().startsWith("/android_asset/")) {
                this.dataSource = this.assetDataSource;
            } else {
                this.dataSource = this.fileDataSource;
            }
        } else if ("asset".equals(scheme)) {
            this.dataSource = this.assetDataSource;
        } else if ("content".equals(scheme)) {
            this.dataSource = this.contentDataSource;
        } else {
            this.dataSource = this.httpDataSource;
        }
        return this.dataSource.open(dataSpec);
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.dataSource.read(bArr, i, i2);
    }

    @Override // com.google.android.exoplayer.upstream.UriDataSource
    public String getUri() {
        UriDataSource uriDataSource = this.dataSource;
        if (uriDataSource == null) {
            return null;
        }
        return uriDataSource.getUri();
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public void close() throws IOException {
        UriDataSource uriDataSource = this.dataSource;
        if (uriDataSource != null) {
            try {
                uriDataSource.close();
            } finally {
                this.dataSource = null;
            }
        }
    }
}
