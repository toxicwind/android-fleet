package com.google.android.gms.common.images;

import android.net.Uri;
import com.google.android.gms.common.internal.Objects;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class zaa {
    public final Uri uri;

    public zaa(Uri uri) {
        this.uri = uri;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zaa)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return Objects.equal(((zaa) obj).uri, this.uri);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.uri});
    }
}
