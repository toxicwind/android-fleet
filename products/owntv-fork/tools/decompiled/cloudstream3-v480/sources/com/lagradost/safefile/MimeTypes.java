package com.lagradost.safefile;

import android.webkit.MimeTypeMap;
import kotlin.Metadata;

/* JADX INFO: compiled from: MimeTypes.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004¨\u0006\b"}, d2 = {"Lcom/lagradost/safefile/MimeTypes;", "", "()V", "fromExtToMime", "", "ext", "fromMimeToExt", "mime", "library_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MimeTypes {
    public static final MimeTypes INSTANCE = new MimeTypes();

    private MimeTypes() {
    }

    public final String fromMimeToExt(String mime) {
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        if (mime == null) {
            return null;
        }
        return singleton.getExtensionFromMimeType(mime);
    }

    public final String fromExtToMime(String ext) {
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        if (ext == null) {
            return null;
        }
        return singleton.getMimeTypeFromExtension(ext);
    }
}
