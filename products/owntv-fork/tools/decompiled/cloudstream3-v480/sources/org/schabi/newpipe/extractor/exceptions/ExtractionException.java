package org.schabi.newpipe.extractor.exceptions;

/* JADX INFO: loaded from: classes8.dex */
public class ExtractionException extends Exception {
    public ExtractionException(String str) {
        super(str);
    }

    public ExtractionException(Throwable th) {
        super(th);
    }

    public ExtractionException(String str, Throwable th) {
        super(str, th);
    }
}
