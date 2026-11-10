package androidx.core.content;

import android.content.LocusId;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class LocusIdCompat {
    public final String mId;
    public final LocusId mWrapped;

    @RequiresApi(29)
    private static class Api29Impl {
        @NonNull
        public static LocusId create(@NonNull final String id) {
            return new LocusId(id);
        }

        @NonNull
        public static String getId(@NonNull final LocusId obj) {
            return obj.getId();
        }
    }

    public LocusIdCompat(@NonNull String id) {
        Preconditions.checkStringNotEmpty(id, "id cannot be empty");
        this.mId = id;
        if (Build.VERSION.SDK_INT >= 29) {
            this.mWrapped = new LocusId(id);
        } else {
            this.mWrapped = null;
        }
    }

    @NonNull
    private String getSanitizedId() {
        return this.mId.length() + "_chars";
    }

    @NonNull
    @RequiresApi(29)
    public static LocusIdCompat toLocusIdCompat(@NonNull final LocusId locusId) {
        Preconditions.checkNotNull(locusId, "locusId cannot be null");
        String id = locusId.getId();
        Preconditions.checkStringNotEmpty(id, "id cannot be empty");
        return new LocusIdCompat(id);
    }

    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LocusIdCompat.class != obj.getClass()) {
            return false;
        }
        LocusIdCompat locusIdCompat = (LocusIdCompat) obj;
        String str = this.mId;
        if (str == null) {
            return locusIdCompat.mId == null;
        }
        return str.equals(locusIdCompat.mId);
    }

    @NonNull
    public String getId() {
        return this.mId;
    }

    public int hashCode() {
        String str = this.mId;
        return 31 + (str == null ? 0 : str.hashCode());
    }

    @NonNull
    @RequiresApi(29)
    public LocusId toLocusId() {
        return this.mWrapped;
    }

    @NonNull
    public String toString() {
        return a.a(a.a("LocusIdCompat["), getSanitizedId(), "]");
    }
}
