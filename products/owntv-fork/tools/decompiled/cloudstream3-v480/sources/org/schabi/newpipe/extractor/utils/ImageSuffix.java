package org.schabi.newpipe.extractor.utils;

import j$.util.Objects;
import java.io.Serializable;
import javax.annotation.Nonnull;
import org.schabi.newpipe.extractor.Image;

/* JADX INFO: loaded from: classes8.dex */
public final class ImageSuffix implements Serializable {
    private final int height;

    @Nonnull
    private final Image.ResolutionLevel resolutionLevel;

    @Nonnull
    private final String suffix;
    private final int width;

    public ImageSuffix(@Nonnull String str, int i, int i2, @Nonnull Image.ResolutionLevel resolutionLevel) throws NullPointerException {
        this.suffix = str;
        this.height = i;
        this.width = i2;
        this.resolutionLevel = (Image.ResolutionLevel) Objects.requireNonNull(resolutionLevel, "estimatedResolutionLevel is null");
    }

    @Nonnull
    public String getSuffix() {
        return this.suffix;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    @Nonnull
    public Image.ResolutionLevel getResolutionLevel() {
        return this.resolutionLevel;
    }

    @Nonnull
    public String toString() {
        return "ImageSuffix {suffix=" + this.suffix + ", height=" + this.height + ", width=" + this.width + ", resolutionLevel=" + this.resolutionLevel + "}";
    }
}
