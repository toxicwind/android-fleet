package org.schabi.newpipe.extractor;

import j$.util.Objects;
import java.io.Serializable;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes8.dex */
public final class Image implements Serializable {
    public static final int HEIGHT_UNKNOWN = -1;
    public static final int WIDTH_UNKNOWN = -1;

    @Nonnull
    private final ResolutionLevel estimatedResolutionLevel;
    private final int height;

    @Nonnull
    private final String url;
    private final int width;

    public Image(@Nonnull String str, int i, int i2, @Nonnull ResolutionLevel resolutionLevel) throws NullPointerException {
        this.url = str;
        this.height = i;
        this.width = i2;
        this.estimatedResolutionLevel = (ResolutionLevel) Objects.requireNonNull(resolutionLevel, "estimatedResolutionLevel is null");
    }

    @Nonnull
    public String getUrl() {
        return this.url;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    @Nonnull
    public ResolutionLevel getEstimatedResolutionLevel() {
        return this.estimatedResolutionLevel;
    }

    @Nonnull
    public String toString() {
        return "Image {url=" + this.url + ", height=" + this.height + ", width=" + this.width + ", estimatedResolutionLevel=" + this.estimatedResolutionLevel + "}";
    }

    public enum ResolutionLevel {
        HIGH,
        MEDIUM,
        LOW,
        UNKNOWN;

        public static ResolutionLevel fromHeight(int i) {
            if (i <= 0) {
                return UNKNOWN;
            }
            if (i < 175) {
                return LOW;
            }
            if (i < 720) {
                return MEDIUM;
            }
            return HIGH;
        }
    }
}
