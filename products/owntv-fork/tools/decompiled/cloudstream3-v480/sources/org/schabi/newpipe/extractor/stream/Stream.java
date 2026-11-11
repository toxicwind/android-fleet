package org.schabi.newpipe.extractor.stream;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.MediaFormat;
import org.schabi.newpipe.extractor.services.youtube.ItagItem;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public abstract class Stream implements Serializable {
    public static final int FORMAT_ID_UNKNOWN = -1;
    public static final String ID_UNKNOWN = " ";
    public static final int ITAG_NOT_AVAILABLE_OR_NOT_APPLICABLE = -1;
    private final String content;
    private final DeliveryMethod deliveryMethod;
    private final String id;
    private final boolean isUrl;

    @Nullable
    private final String manifestUrl;

    @Nullable
    private final MediaFormat mediaFormat;

    @Nullable
    public abstract ItagItem getItagItem();

    public Stream(String str, String str2, boolean z, @Nullable MediaFormat mediaFormat, DeliveryMethod deliveryMethod, @Nullable String str3) {
        this.id = str;
        this.content = str2;
        this.isUrl = z;
        this.mediaFormat = mediaFormat;
        this.deliveryMethod = deliveryMethod;
        this.manifestUrl = str3;
    }

    public static boolean containSimilarStream(@Nonnull Stream stream, List<? extends Stream> list) {
        if (Utils.isNullOrEmpty(list)) {
            return false;
        }
        Iterator<? extends Stream> it = list.iterator();
        while (it.hasNext()) {
            if (stream.equalStats(it.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean equalStats(@Nullable Stream stream) {
        MediaFormat mediaFormat;
        return stream != null && (mediaFormat = this.mediaFormat) != null && stream.mediaFormat != null && mediaFormat.id == stream.mediaFormat.id && this.deliveryMethod == stream.deliveryMethod && this.isUrl == stream.isUrl;
    }

    public String getId() {
        return this.id;
    }

    @Nullable
    @Deprecated
    public String getUrl() {
        if (this.isUrl) {
            return this.content;
        }
        return null;
    }

    @Nonnull
    public String getContent() {
        return this.content;
    }

    public boolean isUrl() {
        return this.isUrl;
    }

    @Nullable
    public MediaFormat getFormat() {
        return this.mediaFormat;
    }

    public int getFormatId() {
        MediaFormat mediaFormat = this.mediaFormat;
        if (mediaFormat != null) {
            return mediaFormat.id;
        }
        return -1;
    }

    @Nonnull
    public DeliveryMethod getDeliveryMethod() {
        return this.deliveryMethod;
    }

    @Nullable
    public String getManifestUrl() {
        return this.manifestUrl;
    }
}
