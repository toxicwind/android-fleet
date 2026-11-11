package org.schabi.newpipe.extractor.services.youtube.dashmanifestcreators;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes8.dex */
public final class CreationException extends RuntimeException {
    public CreationException(String str) {
        super(str);
    }

    public CreationException(String str, Exception exc) {
        super(str, exc);
    }

    @Nonnull
    public static CreationException couldNotAddElement(String str, Exception exc) {
        return new CreationException("Could not add " + str + " element", exc);
    }

    @Nonnull
    public static CreationException couldNotAddElement(String str, String str2) {
        return new CreationException("Could not add " + str + " element: " + str2);
    }
}
