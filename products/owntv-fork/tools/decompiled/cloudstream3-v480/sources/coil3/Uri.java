package coil3;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Uri.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001bBK\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0003H\u0016J\u0006\u0010\u0019\u001a\u00020\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\r¨\u0006\u001c"}, d2 = {"Lcoil3/Uri;", "", "data", "", "separator", "scheme", "authority", "path", SearchIntents.EXTRA_QUERY, "fragment", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getSeparator", "()Ljava/lang/String;", "getScheme", "getAuthority", "getPath", "getQuery", "getFragment", "equals", "", "other", "hashCode", "", "toString", "newBuilder", "Lcoil3/Uri$Builder;", "Builder", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Uri {
    private final String authority;
    private final String data;
    private final String fragment;
    private final String path;
    private final String query;
    private final String scheme;
    private final String separator;

    public Uri(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.data = str;
        this.separator = str2;
        this.scheme = str3;
        this.authority = str4;
        this.path = str5;
        this.query = str6;
        this.fragment = str7;
    }

    public final String getSeparator() {
        return this.separator;
    }

    public final String getScheme() {
        return this.scheme;
    }

    public final String getAuthority() {
        return this.authority;
    }

    public final String getPath() {
        return this.path;
    }

    public final String getQuery() {
        return this.query;
    }

    public final String getFragment() {
        return this.fragment;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof Uri) && Intrinsics.areEqual(((Uri) other).data, this.data);
    }

    public int hashCode() {
        return this.data.hashCode();
    }

    /* JADX INFO: renamed from: toString, reason: from getter */
    public String getData() {
        return this.data;
    }

    public final Builder newBuilder() {
        return new Builder(this.scheme, this.authority, this.path, this.query, this.fragment, this.separator);
    }

    /* JADX INFO: compiled from: Uri.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BC\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0002\u001a\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\u0004\u001a\u00020\u00002\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\u0005\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\u0006\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\u0007\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0003J\u0006\u0010\u000b\u001a\u00020\fR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcoil3/Uri$Builder;", "", "scheme", "", "authority", "path", SearchIntents.EXTRA_QUERY, "fragment", "separator", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcoil3/Uri;", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Builder {
        private String authority;
        private String fragment;
        private String path;
        private String query;
        private String scheme;
        private String separator;

        public Builder(String str, String str2, String str3, String str4, String str5, String str6) {
            this.scheme = str;
            this.authority = str2;
            this.path = str3;
            this.query = str4;
            this.fragment = str5;
            this.separator = str6;
        }

        public final Builder scheme(String scheme) {
            this.scheme = scheme;
            return this;
        }

        public final Builder authority(String authority) {
            this.authority = authority;
            return this;
        }

        public final Builder path(String path) {
            this.path = path;
            return this;
        }

        public final Builder query(String query) {
            this.query = query;
            return this;
        }

        public final Builder fragment(String fragment) {
            this.fragment = fragment;
            return this;
        }

        public final Builder separator(String separator) {
            this.separator = separator;
            return this;
        }

        public final Uri build() {
            return UriKt.Uri(this.scheme, this.authority, this.path, this.query, this.fragment, this.separator);
        }
    }
}
