package androidx.core.view;

import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import androidx.core.util.Predicate;
import b.a.a.a.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ContentInfoCompat {
    public static final int FLAG_CONVERT_TO_PLAIN_TEXT = 1;
    public static final int SOURCE_APP = 0;
    public static final int SOURCE_CLIPBOARD = 1;
    public static final int SOURCE_DRAG_AND_DROP = 3;
    public static final int SOURCE_INPUT_METHOD = 2;

    @NonNull
    public final ClipData mClip;

    @Nullable
    public final Bundle mExtras;
    public final int mFlags;

    @Nullable
    public final Uri mLinkUri;
    public final int mSource;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface Flags {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface Source {
    }

    public ContentInfoCompat(Builder b2) {
        ClipData clipData = b2.mClip;
        Preconditions.checkNotNull(clipData);
        this.mClip = clipData;
        int i = b2.mSource;
        Preconditions.checkArgumentInRange(i, 0, 3, FirebaseAnalytics.Param.SOURCE);
        this.mSource = i;
        int i2 = b2.mFlags;
        Preconditions.checkFlagsArgument(i2, 1);
        this.mFlags = i2;
        this.mLinkUri = b2.mLinkUri;
        this.mExtras = b2.mExtras;
    }

    public static ClipData buildClipData(ClipDescription description, List<ClipData.Item> items) {
        ClipData clipData = new ClipData(new ClipDescription(description), items.get(0));
        for (int i = 1; i < items.size(); i++) {
            clipData.addItem(items.get(i));
        }
        return clipData;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static String flagsToString(int flags) {
        return (flags & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(flags);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static String sourceToString(int source) {
        if (source == 0) {
            return "SOURCE_APP";
        }
        if (source == 1) {
            return "SOURCE_CLIPBOARD";
        }
        if (source != 2) {
            return source != 3 ? String.valueOf(source) : "SOURCE_DRAG_AND_DROP";
        }
        return "SOURCE_INPUT_METHOD";
    }

    @NonNull
    public ClipData getClip() {
        return this.mClip;
    }

    @Nullable
    public Bundle getExtras() {
        return this.mExtras;
    }

    public int getFlags() {
        return this.mFlags;
    }

    @Nullable
    public Uri getLinkUri() {
        return this.mLinkUri;
    }

    public int getSource() {
        return this.mSource;
    }

    @NonNull
    public Pair<ContentInfoCompat, ContentInfoCompat> partition(@NonNull Predicate<ClipData.Item> itemPredicate) {
        if (this.mClip.getItemCount() == 1) {
            boolean zTest = itemPredicate.test(this.mClip.getItemAt(0));
            return Pair.create(zTest ? this : null, zTest ? null : this);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < this.mClip.getItemCount(); i++) {
            ClipData.Item itemAt = this.mClip.getItemAt(i);
            if (itemPredicate.test(itemAt)) {
                arrayList.add(itemAt);
            } else {
                arrayList2.add(itemAt);
            }
        }
        if (arrayList.isEmpty()) {
            return Pair.create(null, this);
        }
        return arrayList2.isEmpty() ? Pair.create(this, null) : Pair.create(new Builder(this).setClip(buildClipData(this.mClip.getDescription(), arrayList)).build(), new Builder(this).setClip(buildClipData(this.mClip.getDescription(), arrayList2)).build());
    }

    @NonNull
    public String toString() {
        String string;
        StringBuilder sbA = a.a("ContentInfoCompat{clip=");
        sbA.append(this.mClip.getDescription());
        sbA.append(", source=");
        sbA.append(sourceToString(this.mSource));
        sbA.append(", flags=");
        sbA.append(flagsToString(this.mFlags));
        if (this.mLinkUri == null) {
            string = "";
        } else {
            StringBuilder sbA2 = a.a(", hasLinkUri(");
            sbA2.append(this.mLinkUri.toString().length());
            sbA2.append(")");
            string = sbA2.toString();
        }
        sbA.append(string);
        return a.a(sbA, this.mExtras != null ? ", hasExtras" : "", "}");
    }

    public static final class Builder {

        @NonNull
        public ClipData mClip;

        @Nullable
        public Bundle mExtras;
        public int mFlags;

        @Nullable
        public Uri mLinkUri;
        public int mSource;

        public Builder(@NonNull ContentInfoCompat other) {
            this.mClip = other.mClip;
            this.mSource = other.mSource;
            this.mFlags = other.mFlags;
            this.mLinkUri = other.mLinkUri;
            this.mExtras = other.mExtras;
        }

        @NonNull
        public ContentInfoCompat build() {
            return new ContentInfoCompat(this);
        }

        @NonNull
        public Builder setClip(@NonNull ClipData clip) {
            this.mClip = clip;
            return this;
        }

        @NonNull
        public Builder setExtras(@Nullable Bundle extras) {
            this.mExtras = extras;
            return this;
        }

        @NonNull
        public Builder setFlags(int flags) {
            this.mFlags = flags;
            return this;
        }

        @NonNull
        public Builder setLinkUri(@Nullable Uri linkUri) {
            this.mLinkUri = linkUri;
            return this;
        }

        @NonNull
        public Builder setSource(int source) {
            this.mSource = source;
            return this;
        }

        public Builder(@NonNull ClipData clip, int source) {
            this.mClip = clip;
            this.mSource = source;
        }
    }
}
