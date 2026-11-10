package com.google.android.exoplayer;

/* JADX INFO: loaded from: classes.dex */
public final class ExoPlaybackException extends Exception {
    public final boolean caughtAtTopLevel;
    public final int codecErrorType;

    public ExoPlaybackException(String str) {
        super(str);
        this.caughtAtTopLevel = false;
        this.codecErrorType = -1;
    }

    public ExoPlaybackException(Throwable th) {
        super(th);
        this.caughtAtTopLevel = false;
        this.codecErrorType = -1;
    }

    ExoPlaybackException(Throwable th, boolean z) {
        super(th);
        this.caughtAtTopLevel = z;
        this.codecErrorType = -1;
    }

    public ExoPlaybackException(Throwable th, int i) {
        super(th);
        this.caughtAtTopLevel = false;
        this.codecErrorType = i;
    }
}
