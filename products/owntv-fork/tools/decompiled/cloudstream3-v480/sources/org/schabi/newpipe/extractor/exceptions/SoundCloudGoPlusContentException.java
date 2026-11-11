package org.schabi.newpipe.extractor.exceptions;

/* JADX INFO: loaded from: classes8.dex */
public class SoundCloudGoPlusContentException extends ContentNotAvailableException {
    public SoundCloudGoPlusContentException() {
        super("This track is a SoundCloud Go+ track");
    }

    public SoundCloudGoPlusContentException(Throwable th) {
        super("This track is a SoundCloud Go+ track", th);
    }
}
